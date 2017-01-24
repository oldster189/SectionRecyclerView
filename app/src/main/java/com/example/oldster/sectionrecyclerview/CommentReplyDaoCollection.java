package com.example.oldster.sectionrecyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Old'ster on 24/1/2560.
 */

public class CommentReplyDaoCollection {
    @SerializedName("fbIdOwner")
    @Expose
    private String fbIdOwner;
    @SerializedName("replier")
    @Expose
    private List<ReplierDao> replier = null;

    public String getFbIdOwner() {
        return fbIdOwner;
    }

    public void setFbIdOwner(String fbIdOwner) {
        this.fbIdOwner = fbIdOwner;
    }

    public List<ReplierDao> getReplier() {
        return replier;
    }

    public void setReplier(List<ReplierDao> replier) {
        this.replier = replier;
    }
}
