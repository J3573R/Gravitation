package fi.tamk.gravitation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
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
    Box2DDebugRenderer debug = new Box2DDebugRenderer();
    InputHandler input;
    GameCollision collision;
    Wall ground;

    Wall leftWall;
    Wall rightWall;

    Arrow arrow;


    public GameLoop(MainGame game){
        this.game = game;

        world = new World(new Vector2(0, -10f), true);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 9);
        player = new Player(world);
        arrow = new Arrow(world);

        input = new InputHandler(player, arrow);
        collision = new GameCollision(player);
        world.setContactListener(collision);

        ground = new Wall(world);
        ground.init(camera.viewportWidth / 2, 0f, camera.viewportWidth / 2, 0.1f, "ground");

        leftWall = new Wall(world);
        //leftWall.bodyDef.type = BodyDef.BodyType.KinematicBody;
        leftWall.init(0f, camera.viewportHeight / 2, 0.1f, camera.viewportHeight / 2, "wall");

        rightWall = new Wall(world);
        //rightWall.bodyDef.type = BodyDef.BodyType.KinematicBody;
        rightWall.init(camera.viewportWidth, camera.viewportHeight / 2, 0.1f, camera.viewportHeight / 2, "wall");



    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(camera.combined);
        Vector2 camPos = new Vector2(player.body.getPosition());
        if(camPos.y < 9 / 2) {
            camPos.y = 9/2;
        }
        System.out.println(camPos);
        camera.position.set(camPos, 0);
        camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        leftWall.move(0f, player.body.getPosition().y);
        rightWall.move(camera.viewportWidth, player.body.getPosition().y);
        input.move();


        game.batch.begin();
        arrow.draw(game.batch);
        player.draw(game.batch);
        game.batch.end();
        debug.render(world, camera.combined);
        world.step(1/60f, 8, 3);
    }
}
