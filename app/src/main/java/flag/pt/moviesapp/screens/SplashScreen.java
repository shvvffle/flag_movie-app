package flag.pt.moviesapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import flag.pt.moviesapp.R;

/**
 * Created by Marina on 12/02/2017.
 */

public class SplashScreen extends Screen {

    private ImageView logo;
    private TextView appName;
    private TextView appAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        findViews();
        addActions();
    }


    private void findViews() {
        logo = (ImageView) findViewById(R.id.splash_screen_logo);
        appName = (TextView) findViewById(R.id.splash_screen_app_name);
        appAuthor = (TextView) findViewById(R.id.splash_screen_app_author);

    }


    private void addActions() {
        final Animation splashAnimation = AnimationUtils.loadAnimation(this, R.anim.splash);
        logo.startAnimation(splashAnimation);
        appName.startAnimation(splashAnimation);
        appAuthor.startAnimation(splashAnimation);
        final Intent goToMainScreen = new Intent(this, StartScreen.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(goToMainScreen);
                    finish();
                }
            }
        };

        timer.start();
    }

}
