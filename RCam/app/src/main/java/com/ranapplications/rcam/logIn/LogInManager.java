package com.ranapplications.rcam.logIn;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.ContentValues.TAG;

public class LogInManager implements OnCompleteListener<AuthResult>{

    private LogInManagerCallBack callBack;
    private FirebaseAuth mAuth;

    public LogInManager(LogInManagerCallBack callBack, String email, String password) {
        this.callBack = callBack;

        start(email, password);
    }

    private void start(String email, String password) {
        mAuth = FirebaseAuth.getInstance();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LogInManager.this);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            // Sign in success, update UI with the signed-in user's information
            Log.d(TAG, "signInWithEmail:success");
            callBack.onDone(true);
        } else {
            // If sign in fails, display a message to the user.
            Log.w(TAG, "signInWithEmail:failure", task.getException());
            callBack.onDone(false);
            // ...
        }
    }

    public interface LogInManagerCallBack{
        void onDone(boolean loginSucceeded);
    }
}
