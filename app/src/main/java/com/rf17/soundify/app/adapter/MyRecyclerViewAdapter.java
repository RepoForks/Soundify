package com.rf17.soundify.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rf17.soundify.app.model.Message;
import com.rf17.soundifyapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {
    private List<Message> mDataset;
    private static MyClickListener myClickListener;

    static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView label;
        //TextView dateTime;

        DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.txtMessage);
            //dateTime = (TextView) itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener clickListener) {
        myClickListener = clickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<Message> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Message.MYMSG_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_right, parent, false);
                return new DataObjectHolder(view);
            case Message.RECMSG_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_left, parent, false);
                return new DataObjectHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getMsg());
        //holder.dateTime.setText(mDataset.get(position).getmText2());
    }

    public void addItem(Message dataObj) {
        mDataset.add(getItemCount(), dataObj);
        notifyItemInserted(getItemCount());
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }
}