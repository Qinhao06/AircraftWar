package com.hit.aircraftwar.factory;


import com.hit.aircraftwar.prop.AbstractProp;
import com.hit.aircraftwar.prop.BombSupplyProp;

/**
 * 创建炸弹道具的工厂
 *
 * @author lxl,qh
 */

public class BombSupplyPropFactory implements AbstractPropFactory{

    /**
     * @return 创建的道具
     */

    @Override
    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY){
        BombSupplyProp prop = new BombSupplyProp(locationX,locationY,speedX,speedY);
        return prop;
    }
}
