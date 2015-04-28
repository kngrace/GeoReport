/*
 * Copyright (c) SPRING15.
 * TCSS450A - Mobile App Programming
 * team6 - Unlimited Budget Works
 * Romero, Kevingil kjudoy
 * Miraflor, Crystal mirafcry
 * Grace, Kirsten kngrace
 * Stump, James stumpj
 */

package ubw6.com.georeport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by kjudoy on 4/10/2015.
 * @author kjudoy
 *
 * Class for Register Activity
 */
public class RegisterActivity extends Activity {

    private EditText txtEmail, txtPass, txtPassConf, txtSecretQ, txtSecretA;
    private Button btnSubmit;

    //creates register activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //t = (TextView) findViewById(R.id.mainText);
        btnSubmit = (Button) findViewById(R.id.btn_register_submit);

        /**
         * if all fields are appropriately filled out and next button is clicked,
         * user is taken to terms and agreement screen to accept
         * warning if fields are not filled out correctly
         */
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmail = (EditText) findViewById(R.id.txt_register_email);
                txtPass = (EditText) findViewById(R.id.txt_register_pass);
                txtPassConf = (EditText) findViewById(R.id.txt_register_passConf);
                txtSecretQ = (EditText) findViewById(R.id.txt_register_secretQ);
                txtSecretA = (EditText) findViewById(R.id.txt_register_secretA);

                StringBuilder builder = new StringBuilder();
                int errorCount = 0;
                if (txtEmail.getText().toString().matches("")) {
                    errorCount++;
                    builder.append("Email is Empty\n");
                }
                if (txtPass.getText().toString().matches("")) {
                    errorCount++;
                    builder.append("Password is Empty\n");
                }
                if (txtSecretQ.getText().toString().matches("")) {
                    errorCount++;
                    builder.append("Secret Question is Empty\n");
                }
                if (txtSecretA.getText().toString().matches("")) {
                    errorCount++;
                    builder.append("Secret Answer is Empty\n");
                }
                if (!txtPass.getText().toString().equals(txtPassConf.getText().toString())) {
                    errorCount++;
                    builder.append("Passwords didn't match\n");
                }
                if (txtPass.getText().toString().length() < 6) {
                    errorCount++;
                    builder.append("Passwords needs to be at least 6 in length\n");
                }
                //errorCount = 0;
                if (errorCount == 0) {
                    //Toast.makeText(v.getContext(), "Success!", Toast.LENGTH_LONG).show();
                    Intent intent;
                    intent = new Intent(v.getContext(), RegisterTermActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("email", txtEmail.getText().toString());
                    extras.putString("pass", txtPass.getText().toString());
                    //extras.putString("secQ", txtSecretQ.getText().toString());
                    //extras.putString("secA", txtSecretA.getText().toString());
                    intent.putExtras(extras);
                    startActivity(intent); //code 1001 register part 1
                    //finish();
                } else {
                    String strError = "Errors: " + errorCount;
                    builder.append(strError);
                    Toast.makeText(v.getContext(), builder.toString(), Toast.LENGTH_LONG).show();
                    //return;
                }

            }
        });
    }

    //when back is pressed, user is returned to login screen
    @Override
    public void onBackPressed() {
        Intent intent;
        intent = new Intent(this, AppLoginActivity.class);
        // This clears all the previous activities just so the user cannot go back to prev activities
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}