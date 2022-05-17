package com.hit.aircraftwar.prop;

import com.hit.aircraftwar.aircraft.HeroAircraft;
import com.hit.aircraftwar.observer.AircraftObserver;


import java.util.LinkedList;
import java.util.List;

/**
 * 生命道具的具体实现
 *
 * @author lxl,qh
 */

public class HpSupplyProp extends AbstractProp{

    public HpSupplyProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    /**
     * Hp支援生效，增加英雄机血量
     * @param aircraft 道具生效的具体对象
     * @return
     */
    public int effect(HeroAircraft aircraft, AircraftObserver aircraftObserver){
        aircraft.increaseHp(60);
        return 0;
    }

}
