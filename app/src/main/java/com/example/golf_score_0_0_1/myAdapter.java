package com.example.golf_score_0_0_1;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public static final String COL_HOLE = "HOLE";
    public static final String COL_PAR = "PAR";
    public static final String COL_SCORE1 = "SCORE1";
    public static final String COL_SCORE2 = "SCORE2";
    public static final String COL_SCORE3 = "SCORE3";
    public static final String COL_SCORE4 = "SCORE4";

    private OnItemClickListener mListener;

    /** to make interface for clickable adapter */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public myAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_score, parent, false);
        return new myViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String holeInfo = mCursor.getString(mCursor.getColumnIndex(COL_HOLE));
        String parInfo = mCursor.getString(mCursor.getColumnIndex(COL_PAR));
        String p1Score = mCursor.getString(mCursor.getColumnIndex(COL_SCORE1));
        String p2Score = mCursor.getString(mCursor.getColumnIndex(COL_SCORE2));
        String p3Score = mCursor.getString(mCursor.getColumnIndex(COL_SCORE3));
        String p4Score = mCursor.getString(mCursor.getColumnIndex(COL_SCORE4));

/*        if (position == 9*//* || position == 19 || position == 20*//*) {
            holder.holeView.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.holeView.setTextColor(Color.WHITE);
            holder.parView.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.parView.setTextColor(Color.BLACK);
            holder.scoreView1.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.scoreView1.setTextColor(Color.BLACK);
            holder.scoreView2.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.scoreView2.setTextColor(Color.BLACK);
            holder.scoreView3.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.scoreView3.setTextColor(Color.BLACK);
            holder.scoreView4.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.scoreView4.setTextColor(Color.BLACK);
        }

        if (position == 19) {
            holder.holeView.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.holeView.setTextColor(Color.WHITE);
            holder.parView.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.parView.setTextColor(Color.BLACK);
            holder.scoreView1.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.scoreView1.setTextColor(Color.BLACK);
            holder.scoreView2.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.scoreView2.setTextColor(Color.BLACK);
            holder.scoreView3.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.scoreView3.setTextColor(Color.BLACK);
            holder.scoreView4.setBackground(mContext.getResources().getDrawable(R.drawable.in_out_ttl_border));
            holder.scoreView4.setTextColor(Color.BLACK);
        }*/

        holder.holeView.setText(holeInfo);
        holder.parView.setText(parInfo);

        holder.scoreView1.setText(p1Score);
        holder.scoreView2.setText(p2Score);
        holder.scoreView3.setText(p3Score);
        holder.scoreView4.setText(p4Score);

        long id = mCursor.getLong(mCursor.getColumnIndex(COL_HOLE));
        holder.itemView.setTag(id);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView holeView;
        public TextView parView;
        public TextView scoreView1;
        public TextView scoreView2;
        public TextView scoreView3;
        public TextView scoreView4;

        public myViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            holeView = itemView.findViewById(R.id.hole_no);
            parView = itemView.findViewById(R.id.par_no);
            scoreView1 = itemView.findViewById(R.id.player1_score);
            scoreView2 = itemView.findViewById(R.id.player2_score);
            scoreView3 = itemView.findViewById(R.id.player3_score);
            scoreView4 = itemView.findViewById(R.id.player4_score);

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
