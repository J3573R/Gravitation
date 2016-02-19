package fi.tamk.gravitation;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by root on 19.2.2016.
 */
public class GestureHandler implements InputProcessor{

    Player player;

    float startPoint;
    float endPoint;

    public GestureHandler(Player player){
        this.player = player;
    }

    @Override
    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        //System.out.println(x + ", " + y);
        startPoint = x;
        return false;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        //System.out.println(x + ", " + y);
        endPoint = y;
        Vector2 direction = new Vector2(startPoint, endPoint);
        player.body.applyForceToCenter(direction, true);
        System.out.println(direction.x + ", " + direction.y);
        return false; /* This should be what you're looking for. */
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean touchMoved (int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }

    public boolean mouseMoved(int x, int y) {
        return false;
    }
}
