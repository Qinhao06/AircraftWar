package com.hit.aircraftwar.factory;

import com.hit.aircraftwar.prop.AbstractProp;
import com.hit.aircraftwar.prop.HpSupplyProp;


/**
 * 创建生命道具的工厂
 *
 * @author lxl,qh
 */

public class HpSupplyPropFactory implements AbstractPropFactory{

    /**
     * @return 创建的道具
     */

    @Override
    public AbstractProp createProp(int locationX, int locationY, int speedX, int speedY){
        AbstractProp prop = new HpSupplyProp(locationX, locationY, speedX, speedY);
        return prop;
    }
}
