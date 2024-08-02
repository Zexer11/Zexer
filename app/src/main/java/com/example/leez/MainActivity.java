package com.example.leez;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // Delay in milliseconds (3 seconds)
    private static final int ANIMATION_DURATION = 1000; // Animation duration in milliseconds
    private static final float TRANSLATION_Y_START = -100f;
    private static final float TRANSLATION_Y_END = 0f;
    private static final float ALPHA_START = 0f;
    private static final float ALPHA_END = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        ImageView logoImageView = findViewById(R.id.logoImageView);

        // Apply translation animation
        ObjectAnimator translationY = ObjectAnimator.ofFloat(logoImageView, View.TRANSLATION_Y, TRANSLATION_Y_START, TRANSLATION_Y_END);
        translationY.setInterpolator(new AccelerateDecelerateInterpolator());

        // Apply alpha animation
        ObjectAnimator alpha = ObjectAnimator.ofFloat(logoImageView, View.ALPHA, ALPHA_START, ALPHA_END);

        // Create an AnimatorSet to combine animations
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationY, alpha);

        // Set animation duration
        animatorSet.setDuration(ANIMATION_DURATION);

        // Set up a handler to delay the transition to AccountActivity
        new Handler().postDelayed(() -> {
            animatorSet.start(); // Start the animation
            startLoginActivity();
        }, SPLASH_DELAY);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
        finish(); // Finish the current activity so that the user cannot navigate back to it
    }
}