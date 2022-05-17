package com.hit.aircraftwar.factory;

import static com.hit.aircraftwar.activity.GameActivity.screenHeight;
import static com.hit.aircraftwar.activity.GameActivity.screenWidth;

import com.hit.aircraftwar.aircraft.AbstractAircraft;
import com.hit.aircraftwar.aircraft.MobEnemy;
import com.hit.aircraftwar.application.ImageManager;
import com.hit.aircraftwar.strategy.EnemyStraightShootStrategy;
import com.hit.aircraftwar.strategy.ShootStrategy;

/**
 * 创建普通机的工厂
 *
 * @author lxl,qh
 */

public class MobEnemyFactory implements AbstractEnemyFactory{

    ShootStrategy enemyShootStrategy = new EnemyStraightShootStrategy();

    /**
     * @return 创建的敌机
     */

    @Override
    public AbstractAircraft createEnemy(double enemyHpRate){
        return new MobEnemy(
                (int) ( Math.random() * (screenWidth - ImageManager.ELITE_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * screenHeight * 0.2)*1,
                0,
                5,
                (int) (30 * enemyHpRate),
                enemyShootStrategy
        );
    }
}
