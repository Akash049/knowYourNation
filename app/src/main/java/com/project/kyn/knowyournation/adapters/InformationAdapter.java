package com.project.kyn.knowyournation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.kyn.knowyournation.R;
import com.project.kyn.knowyournation.models.Country;

import java.util.List;

/**
 * Created by akashchandra on 11/28/17.
 */

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.MyViewHolder>{

    private List<String> headList,infoList;
    private Context context;

    public InformationAdapter(List<String> headList, List<String> infoList, Context context) {
        this.headList = headList;
        this.infoList = infoList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView head,info;

        public MyViewHolder(View view) {
            super(view);
            head = (TextView)view.findViewById(R.id.head_info);
            info = (TextView)view.findViewById(R.id.info);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_row_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.head.setText(headList.get(position));
        holder.info.setText(infoList.get(position));
    }

    @Override
    public int getItemCount() {
        return headList.size();
    }

}
