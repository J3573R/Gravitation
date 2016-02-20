package fi.tamk.gravitation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by root on 19.2.2016.
 */
public class InputHandler {
    Player player;
    Arrow arrow;

    public InputHandler(Player player, Arrow arrow){
        this.player = player;
        this.arrow = arrow;
    }

    public boolean move(){
        boolean is_moving = false;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            Vector2 vel = player.body.getLinearVelocity();
            player.body.applyForceToCenter(-20, vel.y, true);
            //player.body.applyLinearImpulse(new Vector2(-1, 0), new Vector2(0, 0), true);
            is_moving = true;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            Vector2 vel = player.body.getLinearVelocity();
            player.body.applyForceToCenter(20, vel.y, true);
            is_moving = true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.jumping == false) {
            Vector2 vel = player.body.getLinearVelocity();
            vel.y = 10;
            player.body.setLinearVelocity(vel);
            player.jumping = true;
            //player.body.applyLinearImpulse(new Vector2(0, 1), new Vector2(0, 0), true);
        }

        if(Gdx.input.isTouched()) {
            //System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            float fixedX = 960 / 2;
            float fixedY = 640;

            float point1 = (touchX - fixedX) * -1;
            float point2 = (touchY - fixedY) * -1;

            //System.out.println(MathUtils.atan2(point1 , point2));

            arrow.rotation = MathUtils.atan2(point1, point2);

            Vector2 force = new Vector2(point1 * -1, point2);

            player.body.applyForce(force, new Vector2(0, 0), true);

            //player.body.applyLinearImpulse(point1 * -1, point2, 0, 0, true);

            //player.body.applyLinearImpulse(point1, point2, 0, 0, true);
            //player.body.applyForceToCenter(point1 * -1, point2, true);
            /*float dot = touchX * fixedX + touchY * fixedY;

            double mg_v1 = Math.sqrt(touchX * touchX + fixedY * fixedY);
            double mg_v2 = Math.sqrt(fixedX * fixedX + fixedY * fixedY);

            double cosa = (double) (dot / (mg_v1 * mg_v2));

            double angle = Math.acos(cosa);*/

            //arrow.rotation = (float) angle;

            //System.out.println(angle * MathUtils.radiansToDegrees);
        }



        return is_moving;
    }

}


