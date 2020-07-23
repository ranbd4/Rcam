package com.ranapplications.rcam.mainActivity.mainFragment.sceneFragment;

import android.content.res.Resources;

import com.ranapplications.rcam.R;
import com.ranapplications.rcam.global.DataManager;

import java.util.ArrayList;

public class SceneFragmentHelper {

    private ArrayList<SceneClass> sceneClasses;

    private static SceneFragmentHelper single_instance;

    public static SceneFragmentHelper getInstance(){
        if (single_instance == null)
            single_instance = new SceneFragmentHelper();

        return single_instance;
    }


    private SceneFragmentHelper() {
        sceneClasses = new ArrayList<>();
        updateSceneClasses();
    }


    private void updateSceneClasses(){
        sceneClasses.removeAll(sceneClasses);
        sceneClasses.add(new SceneClass(Resources.getSystem().getString(R.string.Out), R.drawable.out_door_on_icon, R.drawable.out_door_off_icon, SceneClass.OUT,DataManager.getInstance().isActive));
        sceneClasses.add(new SceneClass(Resources.getSystem().getString(R.string.Home), R.drawable.home_off_icon, R.drawable.home_on_icon, SceneClass.HOME, !DataManager.getInstance().isActive));
    }


    public ArrayList<SceneClass> getSceneClasses(){
        updateSceneClasses();
        return sceneClasses;
    }
}
