package com.anirudhv.android.ims;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Main3Activity extends AppCompatActivity {

    private TextView user;
    private FirebaseAuth FBA;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        FBA = FirebaseAuth.getInstance();
        username = FBA.getCurrentUser().toString();
        user =(TextView) findViewById(R.id.user);
        user.setText(username);
    }
}
