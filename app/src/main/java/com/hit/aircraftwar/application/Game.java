package com.hit.aircraftwar.application;


import com.hit.aircraftwar.aircraft.AbstractAircraft;
import com.hit.aircraftwar.aircraft.HeroAircraft;
import com.hit.aircraftwar.basic.AbstractFlyingObject;
import com.hit.aircraftwar.bullet.BaseBullet;
import com.hit.aircraftwar.factory.AbstractEnemyFactory;
import com.hit.aircraftwar.factory.BossEnemyFactory;
import com.hit.aircraftwar.factory.EliteEnemyFactory;
import com.hit.aircraftwar.factory.MobEnemyFactory;
import com.hit.aircraftwar.observer.AircraftObserver;
import com.hit.aircraftwar.prop.AbstractProp;

import java.util.*;
import java.util.concurrent.*;



/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public abstract class Game {

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private final int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractAircraft> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProp> allProp;
    public final AircraftObserver aircraftObserver;

    public static boolean bossExist = false;
    public static boolean gameOverFlag = false;
    private int score = 0;
    private boolean bossHasAppear = false;
    private int stage = 0;
    protected int time = 0;

    /**
     * 周期（ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int shootCycleTime = 0;
    private int enemyCreateCycleTime = 0;

    /**
     *  游戏难度相关设置
     */
    protected boolean isBossAppear = true;
    protected int enemyMaxNumber = 5;
    protected int eliteEnemyAppear = 20;
    protected double bossHpRate = 1;
    protected double enemyHpRate = 1;
    protected int bossAppear = 500;
    protected int shootCycleDuration = 600;
    protected int enemyCreateCycleDuration = 600;
    protected int bossDefeatNumber = 0;


    public Game() {
        heroAircraft = HeroAircraft.getHeroAircraft();
        aircraftObserver = new AircraftObserver();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        allProp = new LinkedList<>();
        //Scheduled 线程池，用于定时任务调度
        executorService = new ScheduledThreadPoolExecutor(3);
        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }


    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {


        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {


            time += timeInterval;

            // 周期性执行（控制频率）
            if (shootTimeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 飞机射出子弹
                shootAction();
            }

            if(enemyCreateTimeCountAndNewCycleJudge()){
                // 新敌机产生
                if(score - stage * bossAppear >= bossAppear){
                    stage++;
                    if(!bossExist && isBossAppear){
                        bossExist = true;
                        bossHasAppear = true;
                        AbstractEnemyFactory factory = new BossEnemyFactory();
                        enemyAircrafts.add(factory.createEnemy(bossHpRate));
                    }
                }
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    Random r = new Random();
                    int randomNumber = r.nextInt(100);
                    AbstractEnemyFactory factory;
                    if(randomNumber < eliteEnemyAppear) {factory = new EliteEnemyFactory();}
                    else {factory = new MobEnemyFactory();}
                    AbstractAircraft enemyAircraft = factory.createEnemy(enemyHpRate);
                    enemyAircrafts.add(enemyAircraft);
                    aircraftObserver.addAircraft(enemyAircraft);
                }
            }

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
            }

        };

        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */

    }

    //***********************
    //      Action 各部分
    //***********************

    private boolean shootTimeCountAndNewCycleJudge() {
        shootCycleTime += timeInterval;
        if (shootCycleTime >= shootCycleDuration && shootCycleTime - timeInterval < shootCycleTime) {
            // 跨越到新的周期
            shootCycleTime %= shootCycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private boolean enemyCreateTimeCountAndNewCycleJudge() {
        enemyCreateCycleTime += timeInterval;
        if (enemyCreateCycleTime >= enemyCreateCycleDuration && enemyCreateCycleTime - timeInterval < enemyCreateCycleTime) {
            // 跨越到新的周期
            enemyCreateCycleTime %= enemyCreateCycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void shootAction() {
        // TODO 敌机射击
        for(AbstractAircraft aircraft : enemyAircrafts){
            List<BaseBullet> bullets = aircraft.shoot();
            enemyBullets.addAll(bullets);
            aircraftObserver.addBullets(bullets);
        }
        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot());
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
        for (AbstractProp prop : allProp) {
            prop.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // TODO 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                // 英雄机撞击到敌机子弹
                // 英雄机损失一定生命值
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
                aircraftObserver.removeBullet(bullet);
            }
        }
        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    aircraftObserver.removeBullet(bullet);
                    if (enemyAircraft.notValid()) {
                        // TODO 获得分数，产生道具补给
                        aircraftObserver.removeAircraft(enemyAircraft);
                        if(enemyAircraft.isBoss()){
                            bossExist = false;
                            bossDefeatNumber++;
                        }
                        if(enemyAircraft.leaveProp() != null) {allProp.addAll(enemyAircraft.leaveProp());}
                        score += 10;
                    }
                }
                // 英雄机 与 敌机 相撞，均损毁
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    enemyAircraft.vanish();
                    aircraftObserver.addAircraft(enemyAircraft);
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        for (AbstractProp prop : allProp) {
            if (prop.notValid()) {
                continue;
            }
            if (heroAircraft.crash(prop)) {
                // 英雄机捡到道具
                // 道具生效
                score += prop.effect(heroAircraft, aircraftObserver);
                prop.vanish();
            }
        }
    }

    public int getScore(){
        return score;
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 删除无效道具
     * 4. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */


    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        allProp.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
    }

}
