package com.ranapplications.rcam.mainActivity.mainFragment;

public class Cameras {
    private String cameraID, name;
    private boolean isActive;
    private int newAlerts, sensitivityLevel;

    public Cameras(String cameraID, String name, boolean isActive, int newAlerts, int sensitivityLevel) {
        this.cameraID = cameraID;
        this.name = name;
        this.isActive = isActive;
        this.newAlerts = newAlerts;
        this.sensitivityLevel = sensitivityLevel;
    }


    public String getCameraID() {
        return cameraID;
    }

    public void setCameraID(String cameraID) {
        this.cameraID = cameraID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getNewAlerts() {
        return newAlerts;
    }

    public void setNewAlerts(int newAlerts) {
        this.newAlerts = newAlerts;
    }

    public int getSensitivityLevel() {
        return sensitivityLevel;
    }

    public void setSensitivityLevel(int sensitivityLevel) {
        this.sensitivityLevel = sensitivityLevel;
    }
}
