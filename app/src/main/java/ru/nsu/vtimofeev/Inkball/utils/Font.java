package ru.nsu.vtimofeev.Inkball.utils;

import android.util.Log;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.SpriteBatcher;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.TextureRegion;
import ru.nsu.vtimofeev.Inkball.graphics.GameScreenInfo;
import ru.nsu.vtimofeev.Inkball.resources.Assets;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 8/10/12
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Font {

    private final float glyphWidth = 20;
    private final float glyphHeight = 32;

    private GameScreenInfo gameScreenInfo;

    public Font(GameScreenInfo gameScreenInfo){
        this.gameScreenInfo = gameScreenInfo;
    }

    public void drawString(SpriteBatcher batcher, String string) {

        if (string == null || string.length() == 0) {
            return;
        }

        int lineLength = string.length();
        float lineWidth = lineLength * glyphWidth;

        float textY = gameScreenInfo.getYForHint();

        //Centered horizontally
        float textX = gameScreenInfo.frustumWidth/2f - lineWidth/2f;

        for(int i = 0; i < lineLength; i++) {

            int index = string.charAt(i) - 'A';
            
            if(index < 0 || index > Assets.letters.length - 1) {

                //space is default
                index = Assets.letters.length - 1;
            }

            batcher.drawSprite(textX, textY, glyphWidth, glyphHeight, Assets.letters[index]);
            textX += glyphWidth;
        }
    }
}