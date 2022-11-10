package com.ganesh.parkking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpActivity extends AppCompatActivity {


    private static final String TAG = "SignUpActivity";
    public FirebaseAuth mAuth;
    Button signUpButton;
    EditText signUpEmailTextInput;
    EditText signUpPasswordTextInput,repass;
    CheckBox agreementCheckBox;
    TextView errorView;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth = FirebaseAuth.getInstance();

        signUpEmailTextInput = findViewById(R.id.signUpEmailTextInput);
        signUpPasswordTextInput = findViewById(R.id.signUpPasswordTextInput);
        signUpButton = findViewById(R.id.signUpButton);
        agreementCheckBox = findViewById(R.id.agreementCheckbox);
        errorView=findViewById(R.id.error);
        repass=findViewById(R.id.rpass);




        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=signUpEmailTextInput.getText().toString();
                String pass=signUpPasswordTextInput.getText().toString();
                String rpass=repass.getText().toString();

//                if (signUpEmailTextInput.getText().toString().contentEquals("")) {
//
//
//                    errorView.setText("Email cannot be empty");
//
//
//                } else if (signUpPasswordTextInput.getText().toString().contentEquals("")) {
//
//
//                    errorView.setText("Password cannot be empty");
//
//
//                } else if (!agreementCheckBox.isChecked()) {
//
//                    errorView.setText("Please agree to terms and Condition");
//
//
//                }
//                else if (pass!=rpass)
//                {
//                    errorView.setText("andha hai kaya l....");
//
//                }
                if(mail.isEmpty())
                {
                    signUpEmailTextInput.setError("email can't be an empty");
                    signUpEmailTextInput.requestFocus();
                }
                else if (!mail.matches(emailPattern))
                {
                    Toast.makeText(SignUpActivity.this, "please Enter proper Email", Toast.LENGTH_SHORT).show();
                }
                else if (pass.isEmpty())
                {
                    signUpPasswordTextInput.setError("please insert password");
                    signUpPasswordTextInput.requestFocus();
                }

                else if (pass.length()<6)
                {
                    signUpPasswordTextInput.setError("password must be 6 length");
                    signUpPasswordTextInput.requestFocus();
                }
                else if(!rpass.matches(pass))
                {
                    repass.setError("andha hai kaya ..");
                }


               else {


                    mAuth.createUserWithEmailAndPassword(signUpEmailTextInput.getText().toString(), signUpPasswordTextInput.getText().toString()).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                try {
                                    if (user != null)
                                        user.sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d(TAG, "Email sent.");

                                                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                                    SignUpActivity.this);

                                                            // set title
                                                            alertDialogBuilder.setTitle("Please Verify Your EmailID");

                                                            // set dialog message
                                                            alertDialogBuilder
                                                                    .setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                                                    .setCancelable(false)
                                                                    .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int id) {

                                                                            Intent signInIntent = new Intent(SignUpActivity.this, SignInActivity.class);
                                                                            SignUpActivity.this.finish();
                                                                        }
                                                                    });

                                                            // create alert dialog
                                                            AlertDialog alertDialog = alertDialogBuilder.create();

                                                            // show it
                                                            alertDialog.show();


                                                        }
                                                    }
                                                });

                                } catch (Exception e) {
                                    errorView.setText(e.getMessage());
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                                if (task.getException() != null) {
                                    errorView.setText(task.getException().getMessage());
                                }

                            }

                        }
                    });

                }

            }
        });



    }
}
