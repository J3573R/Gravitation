package fi.tamk.gravitation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by root on 19.2.2016.
 */
public class GameCollision implements ContactListener {
    Player player;

    public GameCollision(Player player){
        this.player = player;
    }

    public void beginContact(Contact contact) {

        Body a=contact.getFixtureA().getBody();
        Body b=contact.getFixtureB().getBody();

        String aData = (String) a.getUserData();
        String bData = (String) b.getUserData();

        if(aData != null && bData != null) {
            if( (aData.equalsIgnoreCase("player") && bData.equalsIgnoreCase("ground")) ||
                    aData.equalsIgnoreCase("ground") && bData.equalsIgnoreCase("player")  ) {
                this.player.jumping = false;
            }

        }




        //if(a.getUserData() instanceof Obstacle&&b.getUserData() instanceof Car)
        //{

        //}
    }


    public void endContact(Contact contact) {
        // TODO Auto-generated method stub

    }

    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub

    }

    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO Auto-generated method stub

    }

}
