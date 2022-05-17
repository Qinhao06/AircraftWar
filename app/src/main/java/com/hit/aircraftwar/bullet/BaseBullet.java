package com.hit.aircraftwar.bullet;

import static com.hit.aircraftwar.activity.GameActivity.screenHeight;
import static com.hit.aircraftwar.activity.GameActivity.screenWidth;

import com.hit.aircraftwar.basic.AbstractFlyingObject;


/**
 * 子弹类。
 * 也可以考虑不同类型的子弹
 *
 * @author lxl,qh
 */
public class BaseBullet extends AbstractFlyingObject {

    private int power = 10;

    public BaseBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY);
        this.power = power;
    }

    @Override
    public void forward() {
        super.forward();

        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= screenWidth) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= screenHeight ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }

    public int getPower() {
        return power;
    }
}
