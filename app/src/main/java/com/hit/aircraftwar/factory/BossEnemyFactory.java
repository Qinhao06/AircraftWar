package com.hit.aircraftwar.factory;

import static com.hit.aircraftwar.activity.GameActivity.screenHeight;
import static com.hit.aircraftwar.activity.GameActivity.screenWidth;

import com.hit.aircraftwar.aircraft.AbstractAircraft;
import com.hit.aircraftwar.aircraft.BossEnemy;
import com.hit.aircraftwar.application.ImageManager;
import com.hit.aircraftwar.strategy.EnemyScatterShootStrategy;
import com.hit.aircraftwar.strategy.ShootStrategy;


/**
 * 创建boss机的工厂
 *
 * @author lxl,qh
 */

public class BossEnemyFactory implements AbstractEnemyFactory{

    ShootStrategy enemyShootStrategy = new EnemyScatterShootStrategy();
    /**
     * @return 创建的敌机
     */

    @Override
    public AbstractAircraft createEnemy(double bossHpRate){
        return new BossEnemy(
                (int) ( Math.random() * (screenWidth - ImageManager.ELITE_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * screenHeight * 0.2)*1,
                3,
                0,
                (int) (500 * bossHpRate),
                enemyShootStrategy
        );
    }
}
