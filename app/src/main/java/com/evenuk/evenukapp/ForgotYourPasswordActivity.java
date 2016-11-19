package com.evenuk.evenukapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ForgotYourPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_your_password);

        mAuth = FirebaseAuth.getInstance();

        final EditText emailText = (EditText) findViewById(R.id.email_text);

        Button forgotYourPasswordButton = (Button) findViewById(R.id.button_forgot_your_password);
        forgotYourPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onForgotYourPassword(emailText.getText().toString());
            }
        });
    }

    private void onForgotYourPassword(final String email) {

        if (!email.isEmpty()) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("register", "sendPasswordResetEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(ForgotYourPasswordActivity.this, "Send password failed: " + task.getException().toString(),
                                        Toast.LENGTH_SHORT).show();

                                //Toast.makeText(ForgotYourPasswordActivity.this, "Send Password failed.",
                                //        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgotYourPasswordActivity.this, "Send Password successful.",
                                        Toast.LENGTH_SHORT).show();

                                TextView messageView = (TextView) findViewById(R.id.message_view);
                                messageView.setText("O email foi enviado para " + email);
                                messageView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        } else {
            Toast.makeText(ForgotYourPasswordActivity.this, "Email is empty.",
                    Toast.LENGTH_SHORT).show();
        }


    }


}
