package com.anirudhv.android.ims;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private TextView user;
    private TextView svr;
    private Button signout;
    private Button save;
    private FirebaseAuth FBA;
    private String username;
    private ListView liv;
    private ArrayAdapter<String> AA;
    private ArrayList<String> AL = new ArrayList<String>();
    FirebaseDatabase FBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        FBA = FirebaseAuth.getInstance();
        username = FBA.getCurrentUser().getEmail().toString();
        user =(TextView) findViewById(R.id.user);
        svr = (TextView) findViewById(R.id.editText);
        save = (Button) findViewById(R.id.button2);
        liv = (ListView) findViewById(R.id.li);
        FBD = FirebaseDatabase.getInstance();
        final DatabaseReference DR = FBD.getReference();
        AA = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AL);
        liv.setAdapter(AA);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DR.child("IMS MAIN").push().setValue(svr.getText().toString());
            }
        });

        user.setText(username);
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
