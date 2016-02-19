package fi.tamk.gravitation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Clown on 18.2.2016.
 */
public class GameLoop extends ScreenAdapter {

    MainGame game;
    OrthographicCamera camera;
    World world;
    Player player;
    Body ground;
    Box2DDebugRenderer debug = new Box2DDebugRenderer();
    InputHandler input;
    GameCollision collision;

    public GameLoop(MainGame game){
        this.game = game;

        world = new World(new Vector2(0, -10f), true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 9);
        player = new Player(world);

        input = new InputHandler(player);
        collision = new GameCollision(player);
        world.setContactListener(collision);

        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(camera.viewportWidth / 2, 0);

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(camera.viewportWidth / 2, 0.1f);

        ground = world.createBody(groundDef);

        ground.createFixture(groundShape, 0);

        ground.setUserData("ground");

        

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(camera.combined);
        camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        input.move();


        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();
        debug.render(world, camera.combined);
        world.step(1/60f, 8, 3);
    }
}
