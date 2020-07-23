package com.ranapplications.rcam.mainActivity.mainFragment;

import android.os.Handler;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ranapplications.rcam.global.DataManager;
import com.ranapplications.rcam.global.FirebaseManager;

public class UpdateCameraStatus {

    private UpdateCameraStatusCallback callback;

    public UpdateCameraStatus(UpdateCameraStatusCallback callback) {
        this.callback = callback;
        onPreExecution();
    }

    private void onPreExecution() {
        for (Cameras oneCamera: DataManager.getInstance().cameras) {

            DatabaseReference mData = FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(DataManager.getInstance().user.getUid())
                    .child("Cameras")
                    .child(oneCamera.getCameraID())
                    .child("isActive");

            mData.setValue(false);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                checkCamerasStatus();
            }
        }, 5000);   //5 seconds

    }

    private void checkCamerasStatus(){
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(DataManager.getInstance().user.getUid())
                .child("Cameras");

        mData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataManager.getInstance().cameras.clear();

                for (DataSnapshot one: dataSnapshot.getChildren()) {
                    int newAlerts = FirebaseManager.getInt(one, "newAlerts");
                    int sensitivityLevel = FirebaseManager.getInt(one, "sensitivityLevel");
                    String cameraID = one.getKey();
                    String name = FirebaseManager.getString(one, "name");
                    boolean isActiveCamera = FirebaseManager.getBoolean(one, "isActive");

                    DataManager.getInstance().cameras.add(new Cameras(cameraID, name, isActiveCamera, newAlerts, sensitivityLevel));
                }

                callback.onUpdateCameraStatusDone();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void resetNewAlertsCount(String cameraUid){
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(DataManager.getInstance().user.getUid())
                .child("Cameras")
                .child(cameraUid)
                .child("newAlerts");

        mData.setValue(0);
    }

    public interface UpdateCameraStatusCallback{
        void onUpdateCameraStatusDone();
    }
}
