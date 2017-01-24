package com.example.oldster.sectionrecyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Old'ster on 24/1/2560.
 */

public class ReplierDao {
    @SerializedName("post")
    @Expose
    private String post;
    @SerializedName("replys")
    @Expose
    private List<ReplyDao> replys = null;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public List<ReplyDao> getReplys() {
        return replys;
    }

    public void setReplys(List<ReplyDao> replys) {
        this.replys = replys;
    }
}
