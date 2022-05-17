package com.hit.aircraftwar.factory;


import com.hit.aircraftwar.prop.AbstractProp;

/**
 * 创建道具的抽象父类工厂接口
 * 包括创建Hp，Bomb，Fire道具的工厂
 *
 * @author lxl,qh
 */

public interface AbstractPropFactory {

    /**
     * 创建具体道具
     * @param locationX 道具生成的x坐标
     * @param locationY 道具生成的y坐标
     * @param speedX 道具移动的x速度
     * @param speedY 道具移动的y速度
     * @return 创建的敌机
     */

    AbstractProp createProp(int locationX, int locationY, int speedX, int speedY);
}
