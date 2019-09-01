package ru.nsu.vtimofeev.Inkball.levels;

import ru.nsu.vtimofeev.Inkball.levels.easy.*;
import ru.nsu.vtimofeev.Inkball.levels.hard.*;
import ru.nsu.vtimofeev.Inkball.levels.master.*;
import ru.nsu.vtimofeev.Inkball.levels.medium.*;
import ru.nsu.vtimofeev.Inkball.levels.veryhard.*;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 6/22/12
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class LevelList {

    //The idea is that we can quickly access level by array index

    public static final LevelInitializer[] LEVELS = {

            //1st category
            new Level1Initializer(),
            new Level2Initializer(),
            new Level3Initializer(),
            new Level4Initializer(),
            new Level5Initializer(),
            new Level6Initializer(),
            new Level7Initializer(),
            new Level8Initializer(),
            new Level9Initializer(),

            //2nd category
            new Level10Initializer(),
            new Level11Initializer(),
            new Level12Initializer(),
            new Level13Initializer(),
            new Level14Initializer(),
            new Level15Initializer(),
            new Level16Initializer(),
            new Level17Initializer(),
            new Level18Initializer(),

            //3rd category
            new Level19Initializer(),
            new Level20Initializer(),
            new Level21Initializer(),
            new Level22Initializer(),
            new Level23Initializer() ,
            new Level24Initializer(),
            new Level25Initializer(),
            new Level26Initializer(),
            new Level27Initializer(),

            //4th category
            new Level28Initializer(),
            new Level29Initializer(),
            new Level30Initializer(),
            new Level31Initializer(),
            new Level32Initializer(),
            new Level33Initializer(),
            new Level34Initializer(),
            new Level35Initializer(),
            new Level36Initializer(),

            //5th category
            new Level37Initializer(),
            new Level38Initializer(),
            new Level39Initializer(),
            new Level40Initializer(),
            new Level41Initializer(),
            new Level42Initializer(),
            new Level43Initializer(),
            new Level44Initializer(),
            new Level45Initializer(),
    };

    public static LevelInitializer getNext(LevelInitializer initializer) {

        for (int i = 0; i < LEVELS.length; ++i) {

            if (LEVELS[i].equals(initializer)) {
                return LEVELS[(i+1) % LEVELS.length];
            }
        }

        return initializer;
    }

    public static String getID(LevelInitializer levelInitializer) {

        for (int i = 0; i < LEVELS.length; ++i) {

            if (LEVELS[i].equals(levelInitializer)) {
                return String.valueOf(i);
            }
        }

        return null;
    }

    public static int getIDInt(LevelInitializer levelInitializer) {

        for (int i = 0; i < LEVELS.length; ++i) {

            if (LEVELS[i].equals(levelInitializer)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean isLevelLast(LevelInitializer initializer) {
        return (LEVELS[LEVELS.length - 1].equals(initializer));
    }
}
