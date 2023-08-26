package com.example.vehiclebreakdownbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {
    //This line says we're creating something called "loginActivity" that uses special tools from the Android system.

    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    // 21-23
    //These lines create spaces to put your username, password, a button to click, and a spot to click for signing up.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        //31-34
        // find view by id is helpfully to find all the component by their respected id
        //These lines are like remembering where to find the username, password, button, and signup spot on the screen.
        // find view by id is one of the tool of class AppCompatActivity
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {
                } else {
                    checkUser();
                }
            }
        });
        // the upper code :-40 - 48
        //Here, when you press the button, it checks if you put your username and password. If you did, it tries to see if you can come in.
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, sginupActivity.class);
                startActivity(intent);
            }
        });
    }
    // line 51 to 57
    // intent is basically used to jump from one activity to another activity like from signin to sign up if you are not signup earlier
    //If you haven't signed up yet, this lets you go to a place where you can get ready to enter.
    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Username cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }
    // code line 62 to 71
    // if you not write anything on text-field and press signing then it give error
    //This helps make sure you wrote your username on your "key." It checks if the spot for your username is empty.
    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }
    // this code from 75 to 84
    // to check for password field is not empty .
    public void checkUser(){
        // This function will be used to see if the user is allowed to enter the  place or not means able to sign in or not.
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        // code 87-88
        //Here, the code gets the username and password that you typed into the app, and it removes any extra spaces from them.
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        //code line 93
        //This sets up a connection to a special database where information about users is stored. It's like having a big book with
        // everyone's names, emails, and passwords.
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        // code line 97
        //This is like looking in the big book for a specific name (username) that matches the one
        // you typed. It's finding the page that has your name.
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            //This is like opening the page with your name in the book and checking what's written there. It's doing this just one time.
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //When the app finds the page with your name, it's like reading what's written on that page.
                if (snapshot.exists()){
                    //code line 106
                    //If the page with your name exists (is not empty),
                    loginUsername.setError(null);
                    // code line 108
                    //If your name is in the book, this clears away any mistake messages you might have seen earlier about your name.
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    //code line 112
                    //Now, the app is looking on the page with your name to find the password that's written there.
                    // It's like reading your special secret password from the book.
                    if (passwordFromDB.equals(userPassword)) {
                        //code 116
                        //The app checks if the secret password you typed is the same as the secret password in the book.
                        // If they match, it's like saying you have the right key and it's time to signed in you as a user.!
                        loginUsername.setError(null);
                        //code line 120
                        //This clears away any mistake messages about your name because your name is correct.
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                        //code line 123 - 125
                        //Now, the app is reading more things from the page , like your real name, your email, and even your username.
                        Intent intent = new Intent(loginActivity.this, MainActivity.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                        //code line 128-133
                        // after signing you directed to main activity
                        //This part is like opening the special door for you! It's saying, "Come on in!" and letting you see all the amazing things inside.
                        // It's also taking all the information it got from the book (your name, email, username, and password) and bringing it with you.
                    } else {
                        //code line 138
                        //If your name isn't in the book (the page is empty), it's like finding out that you're not allowed to enter.
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                        //code line 141-142
                        // This part is like saying your key (password) is wrong.
                        // It's showing a message saying, "Uh-oh, you typed the wrong password!" and making sure you're paying attention to fix it.
                    }
                } else {
                    //code line 147
                    //If the page with your name is completely empty (you're not even in the book),
                    // it's like figuring out that you're not allowed because your name isn't even on the list.
                    loginUsername.setError("User does not exist");
                    loginUsername.requestFocus();
                    //code line 151-152
                    //This is saying that your name isn't on the list, like not having the right key at all. It's showing a message saying,
                    // "Sorry, you can't come in because your name is not on the list!" and making sure you're paying attention to fix it.
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //These lines are like saying, "If something goes wrong while we're trying to read the book, don't worry too much.
                // Just keep going and don't get stuck."
            }
        });
        // closing the sign page
    }
}
