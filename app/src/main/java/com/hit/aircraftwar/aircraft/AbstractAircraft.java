package com.hit.aircraftwar.aircraft;

import com.hit.aircraftwar.basic.AbstractFlyingObject;
import com.hit.aircraftwar.bullet.BaseBullet;
import com.hit.aircraftwar.prop.AbstractProp;
import com.hit.aircraftwar.strategy.ShootStrategy;



import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author lxl,qh
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    private ShootStrategy shootStrategy;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy shootStrategy){
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.shootStrategy = shootStrategy;
    }

    public void setShootStrategy(ShootStrategy shootStrategy) {
        this.shootStrategy = shootStrategy;
    }

    public ShootStrategy getShootStrategy(){
        return shootStrategy;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public void increaseHp(int increase){
        hp += increase;
        if(hp >= maxHp) {hp=maxHp;}
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    /**
     * 飞机遗留道具方法，精英战机对象必须实现
     * @return
     *  精英战机对象需实现，返回道具
     *  其他对象空实现，返回null
     */
    public abstract List<AbstractProp> leaveProp();

    /**
     * 飞机射击方法，可射击对象必须实现
     * @return
     *  可射击对象需实现，返回子弹
     *  非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();
    /**
     * 判断敌机是否为boss机
     * @return
     *  boss返回ture
     *  其他返回false
     */
    public abstract boolean isBoss();
}


