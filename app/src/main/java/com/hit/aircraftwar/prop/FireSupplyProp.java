package com.hit.aircraftwar.prop;

import com.hit.aircraftwar.aircraft.HeroAircraft;
import com.hit.aircraftwar.observer.AircraftObserver;
import com.hit.aircraftwar.strategy.HeroScatterShootStrategy;
import com.hit.aircraftwar.strategy.HeroStraightShootStrategy;
import com.hit.aircraftwar.strategy.ShootStrategy;


import java.util.LinkedList;
import java.util.List;

/**
 * 火力道具的具体实现
 *
 * @author lxl,qh
 */

public class FireSupplyProp extends AbstractProp{

    public FireSupplyProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    /**
     * 火力支援，增加英雄机子弹数
     * @param aircraft 道具生效的具体对象
     * @return
     */
    public int effect(HeroAircraft aircraft, AircraftObserver aircraftObserver){
        Runnable r = () -> {
            ShootStrategy nowHeroStrategy = new HeroScatterShootStrategy();
            ShootStrategy thenHeroStrategy = new HeroStraightShootStrategy();
            Object lock = new Object();
            synchronized (lock){
                aircraft.changeShootNum(2);
                aircraft.setShootStrategy(nowHeroStrategy);
                System.out.println("FireSupply active!");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                aircraft.changeShootNum(-2);
                if(aircraft.getShootNum() == 1){
                    aircraft.setShootStrategy(thenHeroStrategy);
                }
            }
        };
        new Thread(r).start();
        return 0;
    }

}
