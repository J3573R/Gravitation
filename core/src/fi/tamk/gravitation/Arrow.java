package fi.tamk.gravitation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Clown on 20.2.2016.
 */
public class Arrow {
    Texture img;
    public float rotation;
    public Body body;
    World world;

    public Arrow(World world){
        this.world = world;
        img = new Texture("arrow.png");

        rotation = 0;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(16 / 2, 0);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f, new Vector2(0, 0.5f), rotation);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0;
        fixtureDef.isSensor = true;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

    }

    public void draw(SpriteBatch batch) {
        //System.out.println(rotation);

        //rotation += 1 * Gdx.graphics.getDeltaTime();
        body.setTransform(body.getPosition(), rotation);

        batch.draw(img, body.getPosition().x - 0.5f, body.getPosition().y, // Texture, x, y
                        0.5f, 0, // Origin x, Origin y
                        1, 1, // Width, Height
                        1, 1, // Scale X, Scale Y
                        body.getTransform().getRotation() * MathUtils.radiansToDegrees,    // Rotation
                        1, 1, // srcX, srcY
                        img.getWidth(), img.getHeight(), // srcWidth, srcHeight
                        false, false); // flip x, flip y

        //batch.draw(img, 1, 1, 1, 1);
    }
}
