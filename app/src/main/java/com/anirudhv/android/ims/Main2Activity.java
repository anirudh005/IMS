package com.anirudhv.android.ims;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity  {

    private EditText username;
    private EditText password;
    private TextView signup;
    private Button login;
    private Button back;
    private FirebaseAuth FBA;
    private ProgressDialog Progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        username = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        signup = (TextView) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.loginbtn);
        Progress = new ProgressDialog(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText(null);
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                Main2Activity.this.startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FBA = FirebaseAuth.getInstance();
                Progress.setMessage("Plese Wait");
                Progress.show();
                FBA.signInWithEmailAndPassword(username.getText().toString().trim(), password.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    password.setText(null);
                                    Toast.makeText(Main2Activity.this,"Successful",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                                    Main2Activity.this.startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(Main2Activity.this,"Fail",Toast.LENGTH_SHORT).show();
                                }
                                Progress.hide();
                            }
                        });
            }
        });
    }
}
