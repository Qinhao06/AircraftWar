package com.hit.aircraftwar.aircraft;

import static com.hit.aircraftwar.activity.GameActivity.screenHeight;
import static com.hit.aircraftwar.activity.GameActivity.screenWidth;

import com.hit.aircraftwar.bullet.BaseBullet;
import com.hit.aircraftwar.factory.AbstractPropFactory;
import com.hit.aircraftwar.factory.BombSupplyPropFactory;
import com.hit.aircraftwar.factory.FireSupplyPropFactory;
import com.hit.aircraftwar.factory.HpSupplyPropFactory;
import com.hit.aircraftwar.prop.AbstractProp;
import com.hit.aircraftwar.strategy.ShootStrategy;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * boss敌机的具体实现
 *
 * @author lxl,qh
 */

public class BossEnemy extends AbstractAircraft{
    /** 攻击方式 */

    /**
     * @param shootNum 子弹一次发射数量
     * @param power 子弹伤害
     * @param direction 子弹射击方向 (向上发射：1，向下发射：-1)
     */

    private int shootNum = 6;
    private int power = 30;
    private int direction = 1;

    /**
     * @param locationX 敌机位置x坐标
     * @param locationY 敌机位置y坐标
     * @param speedX 敌机射出的子弹的基准速度
     * @param speedY 敌机射出的子弹的基准速度
     * @param hp    初始生命值
     */
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp, shootStrategy);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= screenHeight ) {
            vanish();
        }
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        return this.getShootStrategy().shootStrategy(shootNum, direction, power, this);
    }

    @Override
    public List<AbstractProp> leaveProp(){
        List<AbstractProp> prop = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction*7;
        int none = 0;
        int hpProp = 3;
        int fireProp = 6;
        int speedX = 0;
        int speedY = direction * 7;
        Random r = new Random();
        int randomNumber = r.nextInt(10);
        AbstractPropFactory factory = null;
        if(randomNumber <= none){}
        else if(randomNumber <= hpProp) {factory = new HpSupplyPropFactory();}
        else if(randomNumber <= fireProp) {factory = new FireSupplyPropFactory();}
        else {factory = new BombSupplyPropFactory();}
        if(factory != null) {prop.add(factory.createProp(x, y, speedX, speedY));}
        return prop;
    }

    @Override
    public boolean isBoss(){return true;}
}
