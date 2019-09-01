package ru.nsu.vtimofeev.Inkball.activities;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 7/18/12
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import ru.nsu.vtimofeev.Inkball.resources.Settings;


public class SettingsActivity extends Activity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //FullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.settings);

        Settings.loadSettings(this);
        initListeners();
        updateGui();

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    private void initListeners() {

        ImageButton cancel = (ImageButton)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageButton save = (ImageButton)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
                onBackPressed();
            }
        });
    }

    private void updateGui() {

        CheckBox filteringCheckBox = (CheckBox)findViewById(R.id.filtering);
        CheckBox backgroundCheckBox = (CheckBox)findViewById(R.id.background);
        CheckBox hintsCheckBox = (CheckBox)findViewById(R.id.hints);
        
        filteringCheckBox.setChecked(Settings.isFilteringEnabled());
        backgroundCheckBox.setChecked(Settings.isBackgroundEnabled());
        hintsCheckBox.setChecked(Settings.isHintsEnabled());
    }

    private void saveSettings() {

        CheckBox filteringCheckBox = (CheckBox)findViewById(R.id.filtering);
        CheckBox backgroundCheckBox = (CheckBox)findViewById(R.id.background);
        CheckBox hintsCheckBox = (CheckBox)findViewById(R.id.hints);

        Settings.setFilteringEnabled(filteringCheckBox.isChecked());
        Settings.setBackgroundEnabled(backgroundCheckBox.isChecked());
        Settings.setHintsEnabled(hintsCheckBox.isChecked());

        Settings.saveSettings(this);
    }
}
