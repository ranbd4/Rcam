package com.ranapplications.rcam;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import static android.content.ContentValues.TAG;

public class GetTheCurrentToken implements OnCompleteListener<InstanceIdResult> {

    private Context context;

    public GetTheCurrentToken(Context context) {
        this.context = context;
        start();
    }

    private void start() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(GetTheCurrentToken.this);

    }

    @Override
    public void onComplete(@NonNull Task<InstanceIdResult> task) {
        if (!task.isSuccessful()) {
            Log.w(TAG, "getInstanceId failed", task.getException());
            return;
        }

        // Get new Instance ID token
        String token = task.getResult().getToken();

        String deviceId = getAndroidId(context);

        if (deviceId.isEmpty()){
            deviceId = token;
        }
        Log.d("getAndroidId()", deviceId);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        DatabaseReference mDataBaseUsers = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mUser = mDataBaseUsers.child("users").child(mAuth.getCurrentUser().getUid());
        mUser.child("tokens").child(deviceId).setValue(token);
    }


    public static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }

}
