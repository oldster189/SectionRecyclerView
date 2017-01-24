package com.example.oldster.sectionrecyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Old'ster on 24/1/2560.
 */

public class ReplyDao {
    @SerializedName("fbId")
    @Expose
    private String fbId;
    @SerializedName("reply")
    @Expose
    private String reply;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
