package com.hit.aircraftwar.strategy;

import com.hit.aircraftwar.aircraft.AbstractAircraft;
import com.hit.aircraftwar.bullet.BaseBullet;
import com.hit.aircraftwar.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;


/**
 * 具体散射射击策略的类
 *
 * @author lxl,qh
 */
public class HeroScatterShootStrategy implements ShootStrategy{
    @Override
    public List<BaseBullet> shootStrategy(int shootNum, int direction, int power, AbstractAircraft airCraft){
        List<BaseBullet> bullets = new LinkedList<BaseBullet>();
        int x = airCraft.getLocationX();
        int y = airCraft.getLocationY() + direction*2;
        int speedX = 1;
        int speedY = airCraft.getSpeedY() + direction*5;
        BaseBullet baseBullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            baseBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX * (i*2 - shootNum + 1) / 2, speedY, power);
            bullets.add(baseBullet);
        }
        return bullets;
    }
}
