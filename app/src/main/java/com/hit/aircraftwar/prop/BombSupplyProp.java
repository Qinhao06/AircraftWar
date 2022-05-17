package com.hit.aircraftwar.prop;


import com.hit.aircraftwar.aircraft.HeroAircraft;
import com.hit.aircraftwar.observer.AircraftObserver;

/**
 * 炸弹道具的具体实现
 *
 * @author lxl,qh
 */

public class BombSupplyProp extends AbstractProp{

    private int increase = 0;

    public BombSupplyProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }


    @Override
    /**
     * 炸弹生效，清除敌机
     * @param aircraft 道具生效的具体对象
     * @return increase 增加的分数
     */
    public int effect(HeroAircraft aircraft, AircraftObserver aircraftObserver){
        System.out.println("BombSupply active!");
        Object lock = new Object();
        Runnable r = () -> {
            synchronized (lock){
                increase = aircraftObserver.notifyAllAircraft();
            }
        };
        new Thread(r).start();
        try{
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock){
            return increase;
        }
    }
}
