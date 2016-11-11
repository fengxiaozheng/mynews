package com.example.fengxiaozheng.mynews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.entity.TouTiaoVideoResult;
import com.example.fengxiaozheng.mynews.widget.CircleImageView;

import java.util.List;

/**
 * Created by fengxiaozheng on 2016/11/9.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder>{

    private List<TouTiaoVideoResult.DataBean> data;
    private Context context;

    public HomeListAdapter(List<TouTiaoVideoResult.DataBean> data){
        this.data = data;
    }

    @Override
    public HomeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeListAdapter.ViewHolder holder, int position) {
        if(data != null ){

            TouTiaoVideoResult.DataBean dataBean = data.get(position);

            holder.itemView.setTag(dataBean);

            holder.comment.setText(dataBean.getDatetime());

            if (null != dataBean.getMedia_avatar_url() &&
                    !TextUtils.isEmpty(dataBean.getMedia_avatar_url())) {
                Glide.with(context).load(dataBean.getMedia_avatar_url()).into(holder.image_url);
            }

            holder.source.setText(dataBean.getSource());

            holder.title.setText(dataBean.getTitle());

            Glide.with(context).load(dataBean.getImage_url()).into(holder.thumb);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView thumb;
        private  TextView title;
        private  CircleImageView image_url;
        private  TextView source;
        private  TextView comment;

        public ViewHolder(View itemView) {
            super(itemView);
            thumb = (ImageView) itemView.findViewById(R.id.thumb);

            title = (TextView) itemView.findViewById(R.id.title);

            image_url = (CircleImageView) itemView.findViewById(R.id.image_url);

            source = (TextView) itemView.findViewById(R.id.source);

            comment = (TextView) itemView.findViewById(R.id.comment);
        }
    }

    public  List<TouTiaoVideoResult.DataBean> getDataList(){
        return data;
    }
}
