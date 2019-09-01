package ru.nsu.vtimofeev.Inkball.activities;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 7/18/12
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import ru.nsu.vtimofeev.Inkball.resources.Rating;

public class MainMenu extends Activity
{

    public static final String PREFS_COMPL_LEVELS = "CompletedLevels";
    public static final String PREFS_SETTINGS = "UserSettings";
    public static final String PREFS_RATING = "UserRating";

    private Intent play;
    private Intent help;
    private Intent settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //FullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.main);

        play = new Intent(this, ChooseLevelActivity.class);
        help = new Intent(this, HelpActivity.class);
        settings = new Intent(this, SettingsActivity.class);


        final ImageButton playButton = (ImageButton) findViewById(R.id.play);
        final ImageButton helpButton = (ImageButton) findViewById(R.id.help);
        final ImageButton settingsButton = (ImageButton) findViewById(R.id.settings);


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch(v.getId())
                {
                    case R.id.play:
                    {
                        setLoadingImageVisibility(true);
                        startActivity(play);
                        break;
                    }
                    case R.id.help:
                    {
                        startActivity(help);
                        break;
                    }
                    case R.id.settings:
                    {
                        startActivity(settings);
                        break;
                    }
                }
            }
        };

        playButton.setOnClickListener(listener);
        helpButton.setOnClickListener(listener);
        settingsButton.setOnClickListener(listener);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    protected void onResume(){

        super.onResume();
        setLoadingImageVisibility(false);
        updateRating();
    }

//    private void cancelAnimation() {
//        if (loadingAnimation != null) {
//            loadingAnimation.cancel(true);
//        }
//        loadingAnimation = null;
//    }

    private void updateRating() {
        SharedPreferences settings = getSharedPreferences(MainMenu.PREFS_RATING,
                Context.MODE_PRIVATE);
        
        int rating = settings.getInt(Rating.rating, Rating.BEGINNER);

        ImageView ratingImage = (ImageView) findViewById(R.id.mark);

        if (rating == Rating.BEGINNER) {
            ratingImage.setImageResource(R.drawable.beginner);
        }
        else if (rating == Rating.NOVICE) {
            ratingImage.setImageResource(R.drawable.novice);
        }
        else if (rating == Rating.INTERMEDIATE) {
            ratingImage.setImageResource(R.drawable.intermediate);
        }
        else if (rating == Rating.ADVANCED) {
            ratingImage.setImageResource(R.drawable.advanced);
        }
        else if (rating == Rating.EXPERT) {
            ratingImage.setImageResource(R.drawable.expert);
        }
        else if (rating == Rating.MASTER) {
            ratingImage.setImageResource(R.drawable.master);
        }

        ratingImage.invalidate();
    }

    private void setLoadingImageVisibility(boolean visible) {

        ImageView loadingAnimation = (ImageView) findViewById(R.id.loading);

        int visibility = (visible) ? View.VISIBLE : View.INVISIBLE;
        loadingAnimation.setVisibility(visibility);
        loadingAnimation.invalidate();
    }

//    private class LoadingAnimation extends AsyncTask<Void, Void, Void> {
//
//        private final long blinkingIntervalMilliseconds = 100;
//        private float currentIntervalTime = 0f;
//
//        @Override
//        @SuppressWarnings("unchecked")
//        protected Void doInBackground(Void... voids) {
//
//            while (true) {
//
//                try {
//                    Thread.sleep((blinkingIntervalMilliseconds));
//                    publishProgress();
//                } catch (InterruptedException e) {
//                    return null;
//                }
//            }
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//
//            ImageView loadingAnimation = (ImageView) findViewById(R.id.loading);
//
//            int visibility = loadingAnimation.getVisibility();
//            visibility = switchVisibility(visibility);
//            loadingAnimation.setVisibility(visibility);
//
//            loadingAnimation.invalidate();
//        }
//
//        private int switchVisibility(int visibility) {
//
//            if (visibility == View.INVISIBLE) {
//                visibility = View.VISIBLE;
//            }
//            else {
//                visibility = View.INVISIBLE;
//            }
//            return visibility;
//        }
//
//        @Override
//        protected void onCancelled() {
//
//            ImageView loadingAnimation = (ImageView) findViewById(R.id.loading);
//            loadingAnimation.setVisibility(View.INVISIBLE);
//            loadingAnimation.invalidate();
//        }
//    }
}