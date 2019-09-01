package ru.nsu.vtimofeev.Inkball.graphics;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.SpriteBatcher;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;
import ru.nsu.vtimofeev.Inkball.resources.Assets;
import ru.nsu.vtimofeev.Inkball.utils.Font;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 8/10/12
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class HintRenderer {

    public static final int MAX_LETTERS_WITH_SPACES = 120;
    
    private SpriteBatcher batcher;
    private GameScreenInfo gameScreenInfo;
    
    private Font font;
    
    public HintRenderer(GLGraphics graphics, GameScreenInfo gameScreenInfo) {

        this.batcher = new SpriteBatcher(graphics, MAX_LETTERS_WITH_SPACES);
        this.gameScreenInfo = gameScreenInfo;
        this.font = new Font(gameScreenInfo);
    }

    public void render() {
        
        HintStatus hintStatus = gameScreenInfo.getHintStatus();
        
        if (hintStatus == HintStatus.NONE_SHOWN) {
            return;
        }
        
        batcher.beginBatch(Assets.font);
        if (hintStatus == HintStatus.FIRST_HINT_SHOWN) {

            font.drawString(batcher, Assets.firstHint);
        }
        else if (hintStatus == HintStatus.SECOND_HINT_SHOWN) {

            font.drawString(batcher, Assets.secondHint);
        }
        batcher.endBatch();
    }
}
