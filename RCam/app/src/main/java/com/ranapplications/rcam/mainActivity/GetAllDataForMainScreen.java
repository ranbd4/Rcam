package com.ranapplications.rcam.mainActivity;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ranapplications.rcam.global.DataManager;
import com.ranapplications.rcam.global.FirebaseManager;
import com.ranapplications.rcam.mainActivity.mainFragment.Cameras;

public class GetAllDataForMainScreen {

    private GetAllDataForMainScreenCallBack callBack;

    public GetAllDataForMainScreen(GetAllDataForMainScreenCallBack callBack) {
        this.callBack = callBack;

        start();
    }

    private void start() {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDataBaseUsers = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mUser = mDataBaseUsers.child("users").child(mAuth.getCurrentUser().getUid());

        mUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild("Cameras")){
                    callBack.dataForMainScreenCallBackEmptyCameraList();
                    return;
                }

                DataManager dataManager = DataManager.getInstance();

                String email = FirebaseManager.getString(dataSnapshot, "email");
                String imageUrl = FirebaseManager.getString(dataSnapshot, "imageUrl");
                boolean isActive = FirebaseManager.getBoolean(dataSnapshot, "isActive");
                String userLastName = FirebaseManager.getString(dataSnapshot, "userLastName");
                String userFirstName = FirebaseManager.getString(dataSnapshot, "userFirstName");

                dataManager.user.setup(email,imageUrl,mAuth.getCurrentUser().getUid(),userFirstName, userLastName);
                dataManager.isActive = isActive;

                dataManager.cameras.clear();

                for (DataSnapshot one: dataSnapshot.child("Cameras").getChildren()) {
                    int newAlerts = FirebaseManager.getInt(one, "newAlerts");
                    int sensitivityLevel = FirebaseManager.getInt(one, "sensitivityLevel");
                    String cameraID = one.getKey();
                    String name = FirebaseManager.getString(one, "name");
                    boolean isActiveCamera = FirebaseManager.getBoolean(one, "isActive");

                    dataManager.cameras.add(new Cameras(cameraID, name, isActiveCamera, newAlerts, sensitivityLevel));
                }


                callBack.dataForMainScreenCallBack();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface GetAllDataForMainScreenCallBack{
        void dataForMainScreenCallBack();
        void dataForMainScreenCallBackEmptyCameraList();
    }
}
