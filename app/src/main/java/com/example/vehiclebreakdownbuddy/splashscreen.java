package com.example.vehiclebreakdownbuddy;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashscreen extends AppCompatActivity {

    ImageView splashImage;
    TextView splashText;
    Animation top, bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splashscreen);

        splashImage = findViewById(R.id.splashImage);
        splashText = findViewById(R.id.splashText);

        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);

        splashImage.setAnimation(top);
        splashText.setAnimation(bottom);




        // Set up everything needed for our app's screen

        new Handler().postDelayed(new Runnable() {
            // Handler --> is used for managing time as a helper
            //The postDelayed --> part is like telling your Handler, "Wait for a bit, and then do the specific given  task."
            //The new Runnable() --> part is like writing down the specific task you want to do.
            @Override
            public void run() {
                // Define what to do after a delay
                // intent--> it is used to switch from one activity to another activity
                // Create an 'intent' to switch from the splash activity to the main activity
                Intent intent = new Intent(splashscreen.this,sginupActivity.class);

                // Tell the app to start the main activity
                startActivity(intent);

                // Close the splash activity, like turning off a light when we don't need it anymore
                finish();
            }
        },2500);  // screen visible for 3 sec
    }
}