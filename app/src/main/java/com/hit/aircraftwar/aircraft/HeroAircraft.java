package com.hit.aircraftwar.aircraft;

import static com.hit.aircraftwar.activity.GameActivity.screenHeight;
import static com.hit.aircraftwar.activity.GameActivity.screenWidth;

import com.hit.aircraftwar.application.ImageManager;
import com.hit.aircraftwar.bullet.BaseBullet;
import com.hit.aircraftwar.prop.AbstractProp;
import com.hit.aircraftwar.strategy.HeroStraightShootStrategy;
import com.hit.aircraftwar.strategy.ShootStrategy;


import java.util.LinkedList;
import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author lxl,qh
 */
public class HeroAircraft extends AbstractAircraft {

    static ShootStrategy heroShootStrategy = new HeroStraightShootStrategy();

    private volatile static HeroAircraft heroAircraft = new HeroAircraft(
            screenWidth / 2,
            screenHeight - ImageManager.HERO_IMAGE.getHeight() ,
            0, 0, 1000, heroShootStrategy
    );

    /** 攻击方式 */

    /**
     * @param maxShootNum 子弹一次发射数量
     * @param shootNum 子弹一次发射数量
     * @param power 子弹伤害
     * @param direction 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int maxShootNum = 5;
    private int minShootNum = 1;
    private int shootNum = 1;
    private int power = 30;
    private int direction = -1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp, shootStrategy);
    }

    /**
     * 通过单例模式对英雄机进行创建与访问，以下为访问方法
     */

    public static HeroAircraft getHeroAircraft() {
        if(heroAircraft == null) {
            synchronized (HeroAircraft.class) {
                if(heroAircraft == null) {
                    heroAircraft = new HeroAircraft(
                            screenWidth / 2,
                            screenHeight - ImageManager.HERO_IMAGE.getHeight() ,
                            0, 0, 100, heroShootStrategy
                    );
                }
            }
        }
        return heroAircraft;
    }


    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        heroShootStrategy = this.getShootStrategy();
        return heroShootStrategy.shootStrategy(shootNum, direction, power, heroAircraft);
    }

    @Override
    public List<AbstractProp> leaveProp(){return new LinkedList<>();}


    /**
     * 进行子弹数的增减
     * @param num 增减的数量
     */
    public void changeShootNum(int num){
        this.shootNum += num;
        if(shootNum > maxShootNum){shootNum = maxShootNum;}
        if(shootNum < minShootNum){shootNum = minShootNum;}
    }

    /**
     * 获得当前的子弹数
     * @return 子弹数
     */
    public int getShootNum(){
        return shootNum;
    }

    @Override
    public boolean isBoss(){return false;}
}
