package com.anirudhv.android.ims;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Main3Activity extends AppCompatActivity {

    private TextView user;
    private Button signout;
    private FirebaseAuth FBA;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        FBA = FirebaseAuth.getInstance();
        username = FBA.getCurrentUser().getEmail().toString();
        user =(TextView) findViewById(R.id.user);
        user.setText(username);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        signout = (Button) findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FBA.signOut();
                finish();
                Intent intent = new Intent(Main3Activity.this,Main2Activity.class);
                Main3Activity.this.startActivity(intent);
            }
        });
    }
}
