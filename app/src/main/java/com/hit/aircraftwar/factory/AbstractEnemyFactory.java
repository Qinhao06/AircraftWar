package com.hit.aircraftwar.factory;


import com.hit.aircraftwar.aircraft.AbstractAircraft;

/**
 * 创建敌机的抽象父类工厂接口
 * 包括创建boss，elite，mod的工厂
 *
 * @author lxl,qh
 */

public interface AbstractEnemyFactory {

    /**
     * 创建具体的敌机
     * @return 创建的敌机
     */
    AbstractAircraft createEnemy(double hpRate);
}
