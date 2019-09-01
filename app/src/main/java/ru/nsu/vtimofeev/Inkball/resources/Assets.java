package ru.nsu.vtimofeev.Inkball.resources;

import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.Texture;
import ru.nsu.vtimofeev.GameFramework.HelperClasses.GLHelpers.TextureRegion;
import ru.nsu.vtimofeev.GameFramework.MainClasses.FileIO;
import ru.nsu.vtimofeev.GameFramework.MainClasses.GLGraphics;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 3/27/12
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assets {

    public static String firstHint = null;
    public static String secondHint = null;

    public static Texture objects;

    public static TextureRegion uncoloredWall;
    public static TextureRegion redWall;
    public static TextureRegion blueWall;
    public static TextureRegion greenWall;
    public static TextureRegion yellowWall;
    public static TextureRegion crackedUncoloredWall;
    public static TextureRegion crackedRedWall;
    public static TextureRegion crackedBlueWall;
    public static TextureRegion crackedGreenWall;
    public static TextureRegion crackedYellowWall;
    public static TextureRegion accelerator;
    public static TextureRegion unstableWallTransparent;
    public static TextureRegion unstableWallOpaque;
    public static TextureRegion generator;


    public static Texture score;
    public static TextureRegion scores[];

    public static Texture background;
    public static TextureRegion backgroundReg;

    public static Texture balls;
    public static TextureRegion blue_ball;
    public static TextureRegion green_ball;
    public static TextureRegion red_ball;
    public static TextureRegion yellow_ball;
    public static TextureRegion shader;
    public static TextureRegion multiline;

    public static Texture holes;
    public static TextureRegion orange_hole;
    public static TextureRegion green_hole;
    public static TextureRegion blue_hole;
    public static TextureRegion yellow_hole;
    public static TextureRegion uncolored_hole;
    
    public static Texture bars;
    public static TextureRegion[] barRegions;

    public static Texture puzzles;
    public static TextureRegion[] puzzlesRegions;

//    public static Texture win;
//    public static TextureRegion winReg;
//
//    public static Texture lost;
//    public static TextureRegion lostReg;

    public static Texture winlost;
    public static TextureRegion levelFailed;
    public static TextureRegion levelComplete;
    public static TextureRegion timeIsUp;
    public static TextureRegion wrongColor;
    public static TextureRegion winLostMenu;
    public static TextureRegion winLostRestart;
    public static TextureRegion winLostNextLevel;
    public static TextureRegion wellDoneReg;

    public static Texture font;
    public static TextureRegion[] letters;



//    public static Texture wallpaper;
//    public static TextureRegion wallpaperRegion;

    public static Texture pointers;
    public static TextureRegion pointerLeft;
    public static TextureRegion pointerRight;

    public static Texture gameMenu;
    public static TextureRegion gameMenuReg;
    public static TextureRegion gameIconReg;
    public static TextureRegion resumeReg;
    public static TextureRegion restartReg;
    public static TextureRegion accelerateReg;
    public static TextureRegion clearScreenReg;
    public static TextureRegion exitReg;

    public static Texture levelups;
    public static TextureRegion noviceReg;
    public static TextureRegion advancedReg;
    public static TextureRegion intermediateReg;
    public static TextureRegion expertReg;
    public static TextureRegion masterReg;

    public static Texture easyLevels;
    public static Texture mediumLevels;
    public static Texture hardLevels;
    public static Texture veryHardLevels;
    public static Texture intenseLevels;

    public static TextureRegion[] levels;
    public static TextureRegion noLevel;

    public static Texture diff;

    public static TextureRegion diffEasy;
    public static TextureRegion diffNormal;
    public static TextureRegion diffHard;
    public static TextureRegion diffVeryHard;
    public static TextureRegion diffIntense;

    public static Texture checkmarkLock;
    public static TextureRegion checkmark;
    public static TextureRegion lock;

    public static Texture backButton;
    public static TextureRegion backButtonReg;

    public static void loadGameTextures(GLGraphics graphics, FileIO fileIO) {

        objects = new Texture(graphics, fileIO, "objects.png");
        uncoloredWall = new TextureRegion(objects,1,1,30,30);
        redWall = new TextureRegion(objects,0,32,32,32);
        blueWall = new TextureRegion(objects,0,64,32,32);
        greenWall = new TextureRegion(objects,0,96,32,32);
        yellowWall = new TextureRegion(objects,32,0,32,32);
        crackedUncoloredWall = new TextureRegion(objects,32,32,32,32);
        crackedRedWall = new TextureRegion(objects,32,64,32,32);
        crackedBlueWall = new TextureRegion(objects,32,96,32,32);
        crackedGreenWall = new TextureRegion(objects,64,0,32,32);
        crackedYellowWall = new TextureRegion(objects,64,32,32,32);
        accelerator = new TextureRegion(objects,64,64,32,32);
        unstableWallTransparent = new TextureRegion(objects, 64, 96, 32, 32);
        unstableWallOpaque = new TextureRegion(objects, 96, 0, 32, 32);
        generator = new TextureRegion(objects, 96, 32, 32, 32);

        balls = new Texture(graphics, fileIO, "balls.png");
        blue_ball = new TextureRegion(balls, 0, 0, 64, 64);
        green_ball = new TextureRegion(balls, 64, 0, 64, 64);
        red_ball = new TextureRegion(balls, 0, 64, 64, 64);
        yellow_ball = new TextureRegion(balls, 64, 64, 64, 64);
        shader = new TextureRegion(balls, 0, 128, 64, 64);
        multiline = new TextureRegion(balls, 64, 128, 64, 64);

        score = new Texture(graphics, fileIO, "score.png");
        scores = new TextureRegion[10];
        scores[0] = new TextureRegion(score, 0, 30, 20, 29);
        scores[1] = new TextureRegion(score, 20, 29, 20, 29);
        scores[2] = new TextureRegion(score, 42, 30, 20, 29);
        scores[3] = new TextureRegion(score, 65, 30, 20, 29);
        scores[4] = new TextureRegion(score, 85, 29, 20, 29);
        scores[5] = new TextureRegion(score, 0, 61, 20, 29);
        scores[6] = new TextureRegion(score, 20, 61, 20, 29);
        scores[7] = new TextureRegion(score, 43, 62, 20, 29);
        scores[8] = new TextureRegion(score, 65, 62, 20, 29);
        scores[9] = new TextureRegion(score, 87, 61, 20, 29);

        background = new Texture(graphics, fileIO, "pic.png");
        backgroundReg = new TextureRegion(background, 0, 0, 1024, 600);


        holes = new Texture(graphics, fileIO, "holes.png");
        orange_hole = new TextureRegion(holes, 0, 0, 64, 64);
        green_hole = new TextureRegion(holes, 64, 0, 64, 64);
        blue_hole = new TextureRegion(holes, 0, 64, 64, 64);
        yellow_hole = new TextureRegion(holes, 64, 64, 64, 64);
        uncolored_hole = new TextureRegion(holes, 128, 0, 64, 64);

        bars = new Texture(graphics, fileIO, "bars.png");
        barRegions = new TextureRegion[28];
        initBars();

        puzzles = new Texture(graphics, fileIO, "puzzles.png");
        puzzlesRegions = new TextureRegion[8];
        initPuzzles();

        winlost = new Texture(graphics, fileIO, "winlost.png");
        levelFailed = new TextureRegion(winlost, 0, 70, 417, 70);
        levelComplete = new TextureRegion(winlost, 0, 0, 512, 69);
        timeIsUp = new TextureRegion(winlost, 0, 195, 195, 35);
        wrongColor= new TextureRegion(winlost, 0, 144, 487, 47);
        winLostMenu= new TextureRegion(winlost, 0, 236, 160, 53);
        winLostRestart= new TextureRegion(winlost, 0, 298, 213, 55);
        winLostNextLevel = new TextureRegion(winlost, 0, 362, 273, 57);
        wellDoneReg = new TextureRegion(winlost, 0, 425, 440, 73);

        gameMenu = new Texture(graphics, fileIO, "gamemenu.png");
        gameMenuReg = new TextureRegion(gameMenu, 0, 0, 512, 512);
        gameIconReg = new TextureRegion(gameMenu, 79, 26, 345, 97);
        resumeReg = new TextureRegion(gameMenu, 0, 146, 512, 61);
        restartReg = new TextureRegion(gameMenu, 0, 206, 512, 62);
        accelerateReg = new TextureRegion(gameMenu, 0, 277, 512, 65);
        clearScreenReg = new TextureRegion(gameMenu, 0, 344, 512, 57);
        exitReg = new TextureRegion(gameMenu, 0, 402, 512, 72);

        levelups = new Texture(graphics, fileIO, "levelups.png");
        noviceReg = new TextureRegion(levelups, 0, 0, 285, 80);
        intermediateReg = new TextureRegion(levelups, 0, 80, 285, 80);
        advancedReg = new TextureRegion(levelups, 0, 160, 285, 80);
        expertReg = new TextureRegion(levelups, 0, 240, 285, 80);
        masterReg = new TextureRegion(levelups, 0, 320, 285, 80);

        if (Settings.isBackgroundEnabled()) {
            font = new Texture(graphics, fileIO, "font_green.png");
        }
        else {
            font = new Texture(graphics, fileIO, "font_black.png");
        }
        initLetters();
    }

    public static void reloadGameTextures() {
        objects.reload();
        score.reload();
        background.reload();
        balls.reload();
        holes.reload();
        bars.reload();
//        win.reload();
//        lost.reload();
        puzzles.reload();
        gameMenu.reload();
        winlost.reload();
        levelups.reload();
        font.reload();
    }

    public static void loadLevelTextures(GLGraphics graphics, FileIO fileIO) {

//        wallpaper = new Texture(graphics, fileIO, "wall.png");
//        wallpaperRegion = new TextureRegion(wallpaper, 0, 0, 1024, 600);

        easyLevels = new Texture(graphics, fileIO, "levels.png");
        mediumLevels = new Texture(graphics, fileIO, "medLevels.png");
        hardLevels = new Texture(graphics, fileIO, "hardLevels.png");
        veryHardLevels = new Texture(graphics, fileIO, "veryHardLevels.png");
        intenseLevels = new Texture(graphics, fileIO, "intenseLevels.png");

        levels = new TextureRegion[45];

        initLevels();

        noLevel = new TextureRegion(easyLevels, 0, 300, 170, 100);

        diff = new Texture(graphics, fileIO, "diff.png");

        diffEasy = new TextureRegion(diff, 0, 0, 125, 512);
        diffNormal = new TextureRegion(diff, 126, 0, 90, 512);
        diffHard = new TextureRegion(diff, 214, 0, 76, 512);
        diffIntense = new TextureRegion(diff, 292, 0, 118, 512);
        diffVeryHard = new TextureRegion(diff, 408, 0, 103, 512);

        checkmarkLock = new Texture(graphics, fileIO, "checkmarkLock.png");
        checkmark = new TextureRegion(checkmarkLock, 0, 0, 256, 256);
        lock = new TextureRegion(checkmarkLock, 256, 0, 256, 256);

        pointers = new Texture(graphics, fileIO, "pointers.png");
        pointerLeft = new TextureRegion(pointers, 0, 30, 256, 180);
        pointerRight = new TextureRegion(pointers, 256, 30, 256, 180);

        backButton = new Texture(graphics, fileIO, "back_button.png");
        backButtonReg = new TextureRegion(backButton, 0, 0, 50, 200);
    }

    public static void reloadLevelTextures() {

//        wallpaper.reload();
        pointers.reload();
        easyLevels.reload();
        mediumLevels.reload();
        hardLevels.reload();
        veryHardLevels.reload();
        intenseLevels.reload();
        diff.reload();
        checkmarkLock.reload();
        backButton.reload();
    }

    private static void initBars() {
        barRegions[0] = new TextureRegion(bars, 1, 1, 32, 32);
        barRegions[1] = new TextureRegion(bars, 34, 1, 32, 32);
        barRegions[2] = new TextureRegion(bars, 67, 1, 32, 32);
        barRegions[3] = new TextureRegion(bars, 100, 1, 32, 32);

        barRegions[4] = new TextureRegion(bars, 1, 34, 32, 32);
        barRegions[5] = new TextureRegion(bars, 34, 34, 32, 32);
        barRegions[6] = new TextureRegion(bars, 67, 34, 32, 32);
        barRegions[7] = new TextureRegion(bars, 100, 34, 32, 32);

        barRegions[8] = new TextureRegion(bars, 1, 67, 32, 32);
        barRegions[9] = new TextureRegion(bars, 34, 67, 32, 32);
        barRegions[10] = new TextureRegion(bars, 67, 67, 32, 32);
        barRegions[11] = new TextureRegion(bars, 100, 67, 32, 32);

        barRegions[12] = new TextureRegion(bars, 1, 100, 32, 32);
        barRegions[13] = new TextureRegion(bars, 34, 100, 32, 32);
        barRegions[14] = new TextureRegion(bars, 67, 100, 32, 32);
        barRegions[15] = new TextureRegion(bars, 100, 100, 32, 32);

        barRegions[16] = new TextureRegion(bars, 1, 133, 32, 32);
        barRegions[17] = new TextureRegion(bars, 34, 133, 32, 32);
        barRegions[18] = new TextureRegion(bars, 67, 133, 32, 32);
        barRegions[19] = new TextureRegion(bars, 100, 133, 32, 32);

        barRegions[20] = new TextureRegion(bars, 133, 1, 32, 32);
        barRegions[21] = new TextureRegion(bars, 166, 1, 32, 32);

        barRegions[22] = new TextureRegion(bars, 133, 34, 32, 32);
        barRegions[23] = new TextureRegion(bars, 166, 34, 32, 32);

        barRegions[24] = new TextureRegion(bars, 133, 67, 32, 32);
        barRegions[25] = new TextureRegion(bars, 166, 67, 32, 32);

        barRegions[26] = new TextureRegion(bars, 133, 100, 32, 32);
        barRegions[27] = new TextureRegion(bars, 166, 100, 32, 32);
    }

    private static void initLevels() {
        levels[0] = new TextureRegion(easyLevels, 0, 0, 170, 100);
        levels[1] = new TextureRegion(easyLevels, 0, 100, 170, 100);
        levels[2] = new TextureRegion(easyLevels, 0, 200, 170, 100);
        levels[3] = new TextureRegion(easyLevels, 170, 0, 170, 100);
        levels[4] = new TextureRegion(easyLevels, 170, 100, 170, 100);
        levels[5] = new TextureRegion(easyLevels, 170, 200, 170, 100);
        levels[6] = new TextureRegion(easyLevels, 340, 0, 170, 100);
        levels[7] = new TextureRegion(easyLevels, 340, 100, 170, 100);
        levels[8] = new TextureRegion(easyLevels, 340, 200, 170, 100);

        levels[9] = new TextureRegion(mediumLevels, 0, 0, 170, 100);
        levels[10] = new TextureRegion(mediumLevels, 0, 100, 170, 100);
        levels[11] = new TextureRegion(mediumLevels, 0, 200, 170, 100);
        levels[12] = new TextureRegion(mediumLevels, 170, 0, 170, 100);
        levels[13] = new TextureRegion(mediumLevels, 170, 100, 170, 100);
        levels[14] = new TextureRegion(mediumLevels, 170, 200, 170, 100);
        levels[15] = new TextureRegion(mediumLevels, 340, 0, 170, 100);
        levels[16] = new TextureRegion(mediumLevels, 340, 100, 170, 100);
        levels[17] = new TextureRegion(mediumLevels, 340, 200, 170, 100);

        levels[18] = new TextureRegion(hardLevels, 0, 0, 170, 100);
        levels[19] = new TextureRegion(hardLevels, 0, 100, 170, 100);
        levels[20] = new TextureRegion(hardLevels, 0, 200, 170, 100);
        levels[21] = new TextureRegion(hardLevels, 170, 0, 170, 100);
        levels[22] = new TextureRegion(hardLevels, 170, 100, 170, 100);
        levels[23] = new TextureRegion(hardLevels, 170, 200, 170, 100);
        levels[24] = new TextureRegion(hardLevels, 340, 0, 170, 100);
        levels[25] = new TextureRegion(hardLevels, 340, 100, 170, 100);
        levels[26] = new TextureRegion(hardLevels, 340, 200, 170, 100);

        levels[27] = new TextureRegion(veryHardLevels, 0, 0, 170, 100);
        levels[28] = new TextureRegion(veryHardLevels, 0, 100, 170, 100);
        levels[29] = new TextureRegion(veryHardLevels, 0, 200, 170, 100);
        levels[30] = new TextureRegion(veryHardLevels, 170, 0, 170, 100);
        levels[31] = new TextureRegion(veryHardLevels, 170, 100, 170, 100);
        levels[32] = new TextureRegion(veryHardLevels, 170, 200, 170, 100);
        levels[33] = new TextureRegion(veryHardLevels, 340, 0, 170, 100);
        levels[34] = new TextureRegion(veryHardLevels, 340, 100, 170, 100);
        levels[35] = new TextureRegion(veryHardLevels, 340, 200, 170, 100);

        levels[36] = new TextureRegion(intenseLevels, 0, 0, 170, 100);
        levels[37] = new TextureRegion(intenseLevels, 0, 100, 170, 100);
        levels[38] = new TextureRegion(intenseLevels, 0, 200, 170, 100);
        levels[39] = new TextureRegion(intenseLevels, 170, 0, 170, 100);
        levels[40] = new TextureRegion(intenseLevels, 170, 100, 170, 100);
        levels[41] = new TextureRegion(intenseLevels, 170, 200, 170, 100);
        levels[42] = new TextureRegion(intenseLevels, 340, 0, 170, 100);
        levels[43] = new TextureRegion(intenseLevels, 340, 100, 170, 100);
        levels[44] = new TextureRegion(intenseLevels, 340, 200, 170, 100);
    }

    private static void initPuzzles() {
        puzzlesRegions[0] = new TextureRegion(puzzles, 1, 1, 32, 32);
        puzzlesRegions[1] = new TextureRegion(puzzles, 34, 1, 32, 32);
        puzzlesRegions[2] = new TextureRegion(puzzles, 1, 34, 32, 32);
        puzzlesRegions[3] = new TextureRegion(puzzles, 34, 34, 32, 32);
        puzzlesRegions[4] = new TextureRegion(puzzles, 1, 67, 32, 32);
        puzzlesRegions[5] = new TextureRegion(puzzles, 34, 67, 32, 32);
        puzzlesRegions[6] = new TextureRegion(puzzles, 1, 100, 32, 32);
        puzzlesRegions[7] = new TextureRegion(puzzles, 34, 100, 32, 32);
    }

    private static void initLetters() {
        
        //We have 26 english letter and 1 space
        final int letterCount = 27;

        //According to file font.png which is currently used...
        //In pixels
        final int letterWidth = 20;
        final int letterHeight = 32;
        final int letterStartX = 0;
        final int letterStartY = 0;
        final int lettersInRow = 8;
        final int distanceBetweenLettersX = 32;
        final int distanceBetweenLettersY = 32;

        letters = new TextureRegion[letterCount];
        
        for (int i = 0; i < letterCount; ++i) {
            
            int x = letterStartX + (i%lettersInRow) * distanceBetweenLettersX;
            int y = letterStartY + (i/lettersInRow) * distanceBetweenLettersY;
            
            letters[i] = new TextureRegion(font, x, y,letterWidth, letterHeight);
        }
    }
}
