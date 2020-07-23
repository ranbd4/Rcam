package com.ranapplications.rcam.cameraFragment;

public class CameraAlertsClass {
    private String reason, pushId;
    private long time;
    private int confidence;

    public CameraAlertsClass(String reason, String pushId, long time, int confidence) {
        this.reason = reason;
        this.pushId = pushId;
        this.time = time;
        this.confidence = confidence;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }
}
