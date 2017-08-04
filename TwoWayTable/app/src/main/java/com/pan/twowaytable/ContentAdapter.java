package com.pan.twowaytable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gao on 2017/8/3.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {
    private List<ContentBean> contentBeanList;
    private Context context;

    public ContentAdapter(List<ContentBean> contentBeanList, Context context) {
        this.contentBeanList = contentBeanList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_content, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.t0.setText(contentBeanList.get(position).getT0() + "");
        holder.t1.setText(contentBeanList.get(position).getT1() + "");
        holder.t2.setText(contentBeanList.get(position).getT2() + "");
        holder.t3.setText(contentBeanList.get(position).getT3() + "");
        holder.t4.setText(contentBeanList.get(position).getT4() + "");
        holder.t5.setText(contentBeanList.get(position).getT5() + "");
        holder.t6.setText(contentBeanList.get(position).getT6() + "");
        holder.t7.setText(contentBeanList.get(position).getT7() + "");
        holder.t8.setText(contentBeanList.get(position).getT8() + "");
    }

    @Override
    public int getItemCount() {
        return contentBeanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t0, t1, t2, t3, t4, t5, t6, t7, t8;

        public MyViewHolder(View itemView) {
            super(itemView);
            t0 = (TextView) itemView.findViewById(R.id.t0);
            t1 = (TextView) itemView.findViewById(R.id.t1);
            t2 = (TextView) itemView.findViewById(R.id.t2);
            t3 = (TextView) itemView.findViewById(R.id.t3);
            t4 = (TextView) itemView.findViewById(R.id.t4);
            t5 = (TextView) itemView.findViewById(R.id.t5);
            t6 = (TextView) itemView.findViewById(R.id.t6);
            t7 = (TextView) itemView.findViewById(R.id.t7);
            t8 = (TextView) itemView.findViewById(R.id.t8);
        }
    }
}
