package fi.tamk.gravitation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by root on 19.2.2016.
 */
public class InputHandler {
    Player player;

    public InputHandler(Player player){
        this.player = player;
    }

    public boolean move(){
        boolean is_moving = false;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.body.applyForceToCenter(-20, 0, true);
            //player.body.applyLinearImpulse(new Vector2(-1, 0), new Vector2(0, 0), true);
            is_moving = true;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.body.applyForceToCenter(20, 0, true);
            is_moving = true;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.jumping == false) {
            Vector2 vel = player.body.getLinearVelocity();
            vel.y = 10;
            player.body.setLinearVelocity(vel);
            player.jumping = true;
            //player.body.applyLinearImpulse(new Vector2(0, 1), new Vector2(0, 0), true);
        }

        return is_moving;
    }

}
