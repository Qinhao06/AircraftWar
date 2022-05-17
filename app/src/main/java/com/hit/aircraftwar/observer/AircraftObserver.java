package com.hit.aircraftwar.observer;

import com.hit.aircraftwar.aircraft.AbstractAircraft;
import com.hit.aircraftwar.bullet.BaseBullet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxl,qh
 */
public class AircraftObserver {
    private final List<AbstractAircraft> enemyList;
    private final List<BaseBullet> enemyBulletList;

    public AircraftObserver(){
        enemyBulletList = new ArrayList<>();
        enemyList = new ArrayList<>();
    }

    public void addAircraft(AbstractAircraft enemyAircraft){
        enemyList.add(enemyAircraft);
    }

    public void addBullets(List<BaseBullet> enemyBullet){
        enemyBulletList.addAll(enemyBullet);
    }

    public void removeAircraft(AbstractAircraft enemyAircraft){
        enemyList.remove(enemyAircraft);
    }

    public void removeBullet(BaseBullet bullet){enemyBulletList.remove(bullet);}

    public int notifyAllAircraft(){
        int increase = 0;
        for (AbstractAircraft aircraft : enemyList) {
            aircraft.vanish();
            increase += 10;
        }
        for (BaseBullet bullet : enemyBulletList) {
            bullet.vanish();
        }
        return increase;
    }
}
