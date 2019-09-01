package ru.nsu.vtimofeev.Inkball.resources;

import android.content.Context;
import android.content.SharedPreferences;
import ru.nsu.vtimofeev.Inkball.activities.MainMenu;
import ru.nsu.vtimofeev.Inkball.chooseLevelModel.Level;
import ru.nsu.vtimofeev.Inkball.levels.LevelList;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 7/26/12
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Settings {
    
    public static String filtering = "filtering";
    public static String background = "background";
    public static String hints = "hints";

    private static boolean filteringEnabled;
    private static boolean backgroundEnabled;
    private static boolean hintsEnabled;

    public static void setBackgroundEnabled(boolean backgroundEnabled) {
        Settings.backgroundEnabled = backgroundEnabled;
    }

    public static void setFilteringEnabled(boolean filteringEnabled) {
        Settings.filteringEnabled = filteringEnabled;
    }

    public static void setHintsEnabled(boolean hintsEnabled) {
        Settings.hintsEnabled = hintsEnabled;
    }

    public static boolean isFilteringEnabled() {
        return filteringEnabled;
    }

    public static boolean isBackgroundEnabled() {
        return backgroundEnabled;
    }

    public static boolean isHintsEnabled() {
        return hintsEnabled;
    }
    
    public static void loadSettings(Context context) {

        SharedPreferences settings = context.getSharedPreferences(MainMenu.PREFS_SETTINGS,
                Context.MODE_PRIVATE);

        filteringEnabled = settings.getBoolean(filtering, true);
        backgroundEnabled = settings.getBoolean(background, true);
        hintsEnabled = settings.getBoolean(hints, true);
    }
    
    public static void saveSettings(Context context) {

        SharedPreferences settings = context.getSharedPreferences(MainMenu.PREFS_SETTINGS,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        
        editor.putBoolean(filtering, filteringEnabled);
        editor.putBoolean(background, backgroundEnabled);
        editor.putBoolean(hints, hintsEnabled);

        editor.commit();
        
    }
}
