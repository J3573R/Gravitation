package fi.tamk.gravitation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 *
 * Created by root on 19.2.2016.
 */
public class Player {
    private World world;
    private Texture img;
    Body body;
    public boolean jumping;

    public Player(World world){
        this.world = world;
        img = new Texture("badlogic.jpg");
        jumping = false;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(16 / 2 - 0.5f, 9 / 2 - 0.5f);
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.friction = 10f;

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f, 0.5f);
        fixtureDef.shape = box;

        body.createFixture(fixtureDef);
        body.setFixedRotation(true);

        body.setUserData("player");

    }

    public void draw(SpriteBatch batch){
        batch.draw(img, body.getPosition().x - 0.5f, body.getPosition().y - 0.5f, 1, 1);
    }
}
