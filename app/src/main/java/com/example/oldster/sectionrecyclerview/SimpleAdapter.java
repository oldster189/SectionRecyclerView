package com.example.oldster.sectionrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Old'ster on 24/1/2560.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private final Context mContext;
    private ArrayList<ReplyDao> mData;


    public void add(ReplyDao s, int position) {
        position = position == -1 ? getItemCount() : position;
        mData.add(position, s);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvFbId;
        public final TextView tvComment;
        public final TextView tvTimeStamp;

        public SimpleViewHolder(View view) {
            super(view);
            tvFbId = (TextView) view.findViewById(R.id.tvFbId);
            tvComment = (TextView) view.findViewById(R.id.tvComment);
            tvTimeStamp = (TextView) view.findViewById(R.id.tvTimeStamp);
        }
    }

    public SimpleAdapter(Context context, ArrayList<ReplyDao> data) {
        mContext = context;
        mData = data;
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.section, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        if (position < getItemCount()) {
            holder.tvFbId.setText("FbID: "+mData.get(position).getFbId());
            holder.tvComment.setText(mData.get(position).getReply());
            holder.tvTimeStamp.setText(mData.get(position).getTimestamp());

            holder.tvComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Position =" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (mData == null) return 0;
        return mData.size();
    }
}
