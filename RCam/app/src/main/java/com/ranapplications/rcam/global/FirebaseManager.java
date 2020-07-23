package com.ranapplications.rcam.global;

import com.google.firebase.database.DataSnapshot;

/**
 * this class will provide a default value if the data from he Firebase will be null
 */
public class FirebaseManager {

    /**
     * @param dataSnapshot = A Firebase DataSnapshot
     * @param child = A String child name of the location in the Firebase database
     * @return If there is a value in the location that provided it will return the value, If there isn't a child it'll return -> ""
     */
    public static String getString(DataSnapshot dataSnapshot, String child){
        if (dataSnapshot.hasChild(child))
            return dataSnapshot.child(child).getValue(String.class);
        return "";
    }


    /**
     * @param dataSnapshot = A Firebase DataSnapshot
     * @param child = A String child name of the location in the Firebase database
     * @return If there is a value in the location that provided it will return the value, If there isn't a child it'll return -> -1
     */
    public static int getInt(DataSnapshot dataSnapshot, String child){
        if (dataSnapshot.hasChild(child))
            return dataSnapshot.child(child).getValue(Integer.class);
        return -1;
    }

    /**
     * @param dataSnapshot = A Firebase DataSnapshot
     * @param child = A String child name of the location in the Firebase database
     * @return If there is a value in the location that provided it will return the value, If there isn't a child it'll return -> -1
     */
    public static long getLong(DataSnapshot dataSnapshot, String child){
        if (dataSnapshot.hasChild(child))
            return dataSnapshot.child(child).getValue(Long.class);
        return -1;
    }


    /**
     * @param dataSnapshot = A Firebase DataSnapshot
     * @param child = A String child name of the location in the Firebase database
     * @return If there is a value in the location that provided it will return the value, If there isn't a child it'll return -> false
     */
    public static boolean getBoolean(DataSnapshot dataSnapshot, String child){
        if (dataSnapshot.hasChild(child))
            return dataSnapshot.child(child).getValue(Boolean.class);
        return false;
    }
}
