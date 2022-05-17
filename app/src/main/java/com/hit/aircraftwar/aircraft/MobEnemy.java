package com.hit.aircraftwar.aircraft;


import static com.hit.aircraftwar.activity.GameActivity.screenWidth;

import com.hit.aircraftwar.bullet.BaseBullet;
import com.hit.aircraftwar.prop.AbstractProp;
import com.hit.aircraftwar.strategy.ShootStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author lxl,qh
 */
public class MobEnemy extends AbstractAircraft {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy shootStrategy) {
        super(locationX, locationY, speedX, speedY, hp, shootStrategy);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= screenWidth) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }

    @Override
    public List<AbstractProp> leaveProp(){return new LinkedList<>();}

    @Override
    public boolean isBoss(){return false;}
}
