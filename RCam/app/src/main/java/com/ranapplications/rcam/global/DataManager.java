package com.ranapplications.rcam.global;

import com.ranapplications.rcam.mainActivity.mainFragment.Cameras;

import java.util.ArrayList;

/**
 * A Singleton class
 * This class will save all the cameras list and if the user chose to activate the movement detection or not
 * The User class holds the information about the user
 */
public class DataManager {
    public User user;
    public boolean isActive;
    public ArrayList<Cameras> cameras;

    private static DataManager single_instance;

    private DataManager() {
        cameras = new ArrayList<>();
        user = new User();
    }

    public static DataManager getInstance(){
        if (single_instance == null)
            single_instance = new DataManager();

        return single_instance;
    }
}
