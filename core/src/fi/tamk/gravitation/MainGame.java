package fi.tamk.gravitation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {
	SpriteBatch batch;
    private MainGame mainGame;

    public SpriteBatch getBatch(){
        return batch;
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        GameLoop gameLoop = new GameLoop(this);
        setScreen(gameLoop);
	}

	@Override
	public void render () {
        super.render();
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
}
