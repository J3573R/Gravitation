package fi.tamk.gravitation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Clown on 18.2.2016.
 */
public class GameLoop extends ScreenAdapter {

    MainGame game;
    Texture img;
    OrthographicCamera camera;

    public GameLoop(MainGame game){
        this.game = game;
        img = new Texture("badlogic.jpg");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 9);


    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(camera.combined);
        camera.update();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(img, 1, 1, 1, 1);
        game.batch.end();
    }
}
