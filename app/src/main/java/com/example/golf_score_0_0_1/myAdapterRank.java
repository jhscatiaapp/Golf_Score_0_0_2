package com.example.golf_score_0_0_1;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapterRank extends RecyclerView.Adapter<myAdapterRank.myViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public static final String COL_RANK = "RANK";
    public static final String COL_PLAYER = "PLAYER";
    public static final String COL_SCORE = "SCORE";
    public static final String COL_THRU = "THRU";
    public static final String COL_SHOT = "SHOT";

    private OnItemClickListener mListener;

    /** to make interface for clickable adapter */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public myAdapterRank(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_rank, parent, false);
        return new myViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String rank = mCursor.getString(mCursor.getColumnIndex(COL_RANK));
        String player = mCursor.getString(mCursor.getColumnIndex(COL_PLAYER));
        String total = mCursor.getString(mCursor.getColumnIndex(COL_SCORE));
        String thru = mCursor.getString(mCursor.getColumnIndex(COL_THRU));
        String shot = mCursor.getString(mCursor.getColumnIndex(COL_SHOT));

        holder.rankView.setText(rank);
        holder.playerView.setText(player);
        holder.scoreView.setText(total);
        holder.thruView.setText(thru);
        holder.shotView.setText(shot);

        long id = mCursor.getLong(mCursor.getColumnIndex(COL_PLAYER));
        holder.itemView.setTag(id);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView rankView;
        public TextView playerView;
        public TextView scoreView;
        public TextView thruView;
        public TextView shotView;

        public myViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            rankView = itemView.findViewById(R.id.rank_rank);
            playerView = itemView.findViewById(R.id.rank_name);
            scoreView = itemView.findViewById(R.id.rank_total);
            thruView = itemView.findViewById(R.id.rank_thru);
            shotView = itemView.findViewById(R.id.rank_shot);

            /** Make onClickListener RecyclerView  */
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }



}
