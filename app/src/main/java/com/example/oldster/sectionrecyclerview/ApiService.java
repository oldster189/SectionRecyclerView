package com.example.oldster.sectionrecyclerview;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
        @GET("test/comment/all")
        Call<CommentReplyDaoCollection> getCommentReply();
        @GET("test/text")
        Call<String> getTextPlain();
}
