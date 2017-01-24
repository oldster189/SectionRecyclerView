package com.example.oldster.sectionrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private SimpleAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private CommentReplyDaoCollection data;
    private List<SimpleSectionedRecyclerViewAdapter.Section> sections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<String> call = HttpManager.getInstance()
                .getService()
                .getTextPlain();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.e(TAG,response.body().toString());
                }else {
                    Log.e(TAG,response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        sections = new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();

        loadDataCommentReply();
    }

    private void loadDataCommentReply() {
        Call<CommentReplyDaoCollection> call = HttpManager.getInstance()
                .getService()
                .getCommentReply();
        call.enqueue(new Callback<CommentReplyDaoCollection>() {
            @Override
            public void onResponse(Call<CommentReplyDaoCollection> call, Response<CommentReplyDaoCollection> response) {
                if (response.isSuccessful()) {

                    ArrayList<ReplyDao> mReply = new ArrayList<ReplyDao>();
                    data = response.body();
                    for (int i = 0; i < data.getReplier().size(); i++) {
                        for (int k = 0; k < data.getReplier().get(i).getReplys().size(); k++) {
                            mReply.add(data.getReplier().get(i).getReplys().get(k));
                        }
                    }
                    int size = 0;
                    for (int i = 0; i < data.getReplier().size(); i++) {
                        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(size, "Section " + data.getReplier().get(i).getPost()));
                        size += data.getReplier().get(i).getReplys().size();

                    }

                    mAdapter = new SimpleAdapter(MainActivity.this, mReply);


                    //Add your adapter to the sectionAdapter
                    SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
                    SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                            SimpleSectionedRecyclerViewAdapter(MainActivity.this, R.layout.dummy_header, R.id.btnDummy, mAdapter);
                    mSectionedAdapter.setSections(sections.toArray(dummy));

                    //Apply this adapter to the RecyclerView
                    mRecyclerView.setAdapter(mSectionedAdapter);
                } else {
                    Log.e(TAG + " onResponse", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<CommentReplyDaoCollection> call, Throwable t) {
                Log.e(TAG + " onFailure", t.toString());
            }
        });
    }
}
