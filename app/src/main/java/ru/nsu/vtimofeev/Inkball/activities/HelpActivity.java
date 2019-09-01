package ru.nsu.vtimofeev.Inkball.activities;

/**
 * Created by IntelliJ IDEA.
 * User: vtimofeev
 * Date: 7/18/12
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */

import android.app.Activity;

import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import com.google.ads.AdRequest;
import com.google.ads.AdView;


public class HelpActivity extends Activity
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
        setContentView(R.layout.help);

//        initAd();

        ImageButton back = (ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }



    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

}
