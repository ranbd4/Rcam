package com.ranapplications.rcam.cameraFragment;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ranapplications.rcam.global.DataManager;
import com.ranapplications.rcam.global.FirebaseManager;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class will get the information from Firebase to a specific camera
 *
 */
public class GetCameraAlerts {

    private GetCameraAlertsCallback callback;

    /**
     *
     * @param callback  = A GetCameraAlertsCallback -> return an ArrayList =  ArrayList<CameraAlertsClass> cameraAlertsClasses
     * @param cameraID = A specific camera that the user want to get all the alerts from it
     */
    public GetCameraAlerts(GetCameraAlertsCallback callback, String cameraID) {
        this.callback = callback;
        start(cameraID);
    }

    private void start(String cameraID) {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(DataManager.getInstance().user.getUid())
                .child("Cameras")
                .child(cameraID)
                .child("Alerts");

        mData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    callback.onDone(null);
                    return;
                }
                ArrayList<CameraAlertsClass> cameraAlertsClasses = new ArrayList<>();

                for (DataSnapshot oneRow: dataSnapshot.getChildren()) {
                    String reason = FirebaseManager.getString(oneRow, "reason");
                    long time = FirebaseManager.getLong(oneRow, "time");
                    int confidence = FirebaseManager.getInt(oneRow, "confidence");
                    String pushId = oneRow.getKey();

                    cameraAlertsClasses.add(new CameraAlertsClass(reason, pushId, time, confidence));
                }
                
                Collections.reverse(cameraAlertsClasses);

                callback.onDone(cameraAlertsClasses);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface GetCameraAlertsCallback{
        void onDone(ArrayList<CameraAlertsClass> cameraAlertsClasses);
    }
}
