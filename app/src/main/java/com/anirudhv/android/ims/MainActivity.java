package com.anirudhv.android.ims;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Register;
    private EditText UserName;
    private EditText Password;
    private TextView signUp;
    private ProgressDialog Progress;
    private FirebaseAuth FBA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FBA = FirebaseAuth.getInstance();
        Progress = new ProgressDialog(this);
        Register = (Button) findViewById(R.id.Register);
        UserName = (EditText) findViewById(R.id.UserName);
        Password = (EditText) findViewById(R.id.Password);
        Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    if(view==Register)
    {
        RegisterUser();
    }
    }

    private void RegisterUser() {
        String user = UserName.getText().toString().trim();
        String pass = Password.getText().toString().trim();

        if((TextUtils.isEmpty(user))|| (TextUtils.isEmpty(pass)))
        {
            Toast.makeText(this, "Please Enter the details",Toast.LENGTH_SHORT).show();
            return;
        }
        Progress.setMessage("Plese Wait");
        Progress.show();
        FBA.createUserWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        MainActivity.this.startActivity(intent);
                    }
                        else
                    {
                        Toast.makeText(MainActivity.this,"Fail",Toast.LENGTH_SHORT).show();
                    }
                        Progress.hide();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}