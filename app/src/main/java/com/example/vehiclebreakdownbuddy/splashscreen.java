package com.example.vehiclebreakdownbuddy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
            @Override
            public void run() {
                // Define what to do after a delay
                // intent --> it is used to switch from one activity to another activity
                // Create an 'intent' to switch from the splash activity to the main activity
                Intent intent = new Intent(splashscreen.this, sginupActivity.class);

                // Tell the app to start the main activity
                startActivity(intent);

                // Close the splash activity, like turning off a light when we don't need it anymore
                finish();
            }
        }, 2500);  // screen visible for 2.5 sec

        checkRememberMe(); // Call the checkRememberMe() method here
    }
    private void checkRememberMe() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", null);
        String savedPassword = sharedPreferences.getString("password", null);

        Log.d("RememberMe", "Saved Username: " + savedUsername);
        Log.d("RememberMe", "Saved Password: " + savedPassword);

        if (savedUsername != null && savedPassword != null) {
            Log.d("RememberMe", "Auto-sign in logic here");
            // Perform your automatic sign-in logic here if needed
        } else {
            Log.d("RememberMe", "Proceed with normal sign-in process");
        }
    }
}
