package com.hit.aircraftwar.strategy;



import com.hit.aircraftwar.aircraft.AbstractAircraft;
import com.hit.aircraftwar.bullet.BaseBullet;

import java.util.List;

/**
 * 抽象射击策略接口
 * 包括散射、直射的策略
 *
 * @author lxl,qh
 */

public interface ShootStrategy {
    /**
     * 不同的射击策略
     * @param airCraft 射击子弹的飞机
     * @param shootNum 子弹一次发射数量
     * @param power 子弹伤害
     * @param direction 子弹射击方向 (向上发射：1，向下发射：-1)
     * @return 射击产生的子弹
     */
    List<BaseBullet> shootStrategy(int shootNum, int direction, int power, AbstractAircraft airCraft);
}
