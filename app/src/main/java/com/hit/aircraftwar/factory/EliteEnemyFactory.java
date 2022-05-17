package com.hit.aircraftwar.factory;

import static com.hit.aircraftwar.activity.GameActivity.screenHeight;
import static com.hit.aircraftwar.activity.GameActivity.screenWidth;

import com.hit.aircraftwar.aircraft.AbstractAircraft;
import com.hit.aircraftwar.aircraft.EliteEnemy;
import com.hit.aircraftwar.application.ImageManager;
import com.hit.aircraftwar.strategy.EnemyStraightShootStrategy;
import com.hit.aircraftwar.strategy.ShootStrategy;



/**
 * 创建精英机的工厂
 *
 * @author lxl,qh
 */

public class EliteEnemyFactory implements AbstractEnemyFactory{

    ShootStrategy enemyShootStrategy = new EnemyStraightShootStrategy();

    /**
     * @return 创建的敌机
     */

    @Override
    public AbstractAircraft createEnemy(double enemyHpRate){
        return new EliteEnemy(
                (int) ( Math.random() * (screenWidth - ImageManager.ELITE_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * screenHeight * 0.2)*1,
                3,
                5,
                (int) (60 * enemyHpRate),
                enemyShootStrategy
        );
    }
}
