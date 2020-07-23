package com.ranapplications.rcam.mainActivity.mainFragment.sceneFragment;

public class SceneClass {

    private String title;
    private int iconActive, iconInactive, sceneID;
    private boolean isActivate;

    public static final int OUT = 1;
    public static final int HOME = 2;


    public SceneClass(String title, int iconActive, int iconInactive, int sceneID, boolean isActivate) {
        this.title = title;
        this.iconActive = iconActive;
        this.iconInactive = iconInactive;
        this.sceneID = sceneID;
        this.isActivate = isActivate;
    }

    public int getSceneID() {
        return sceneID;
    }

    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconActive() {
        return iconActive;
    }

    public void setIconActive(int iconActive) {
        this.iconActive = iconActive;
    }

    public int getIconInactive() {
        return iconInactive;
    }

    public void setIconInactive(int iconInactive) {
        this.iconInactive = iconInactive;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }
}
