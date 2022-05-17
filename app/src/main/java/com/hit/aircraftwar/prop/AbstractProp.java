package com.hit.aircraftwar.prop;

import com.hit.aircraftwar.aircraft.HeroAircraft;
import com.hit.aircraftwar.basic.AbstractFlyingObject;
import com.hit.aircraftwar.observer.AircraftObserver;


import java.util.LinkedList;
import java.util.List;

/**
 * 所有种类道具的抽象父类
 * 包括生命，火力，炸弹三种道具
 *
 * @author lxl,qh
 */

public abstract class AbstractProp extends AbstractFlyingObject {

    public AbstractProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }
    /**
     * 道具生效方法，三个道具对象均须实现
     * @return increase 增加的分数
     * @param aircraft 道具生效的具体对象
     *  加血道具实现英雄机血量增加
     *  火力道具实现英雄机子弹数增加
     *  炸弹道具实现对敌机的清除
     */
    public abstract int effect(HeroAircraft aircraft, AircraftObserver aircraftObserver);

}
