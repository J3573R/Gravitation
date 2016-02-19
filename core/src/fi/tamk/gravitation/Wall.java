package fi.tamk.gravitation;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by root on 19.2.2016.
 */
public class Wall {
    World world;
    Body wall;
    public BodyDef bodyDef;

    float x;
    float y;
    float width;
    float height;
    String userdata;

    public Wall(World world) {
        this.world = world;
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
    }

    public void init(float x, float y, float width, float height, String userdata) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.userdata = userdata;
        create();
    }

    public void move(float x, float y){
        wall.destroyFixture(wall.getFixtureList().first());
        world.destroyBody(wall);
        this.x = x;
        this.y = y;
        create();
    }

    private void create(){
        bodyDef.position.set(x, y);
        wall = world.createBody(bodyDef);
        PolygonShape wallShape = new PolygonShape();
        wallShape.setAsBox(this.width, this.height);
        wall.createFixture(wallShape, 0);
        wallShape.dispose();

        wall.setUserData(this.userdata);
    }
}
