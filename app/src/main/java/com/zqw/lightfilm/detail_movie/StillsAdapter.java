package com.zqw.lightfilm.detail_movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.detail_movie.Bean.ImageAllBean;

import java.util.List;

/**
 * Created by 启文 on 2018/2/14.
 */
public class StillsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<ImageAllBean.ImagesBean> imageData;
    private int size;

    private static final int MAXNUM = 6;
    private static final int ALL = 0;
    private static final int STILLSLIST = 1;

    public StillsAdapter(Context context, List<ImageAllBean.ImagesBean> imageData, int size) {
        this.context = context;
        this.imageData = imageData;
        this.size = size;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ALL){
            return new ALLViewHolder(View.inflate(context, R.layout.item_all_stills, null));
        }else {
            return new StillsListViewHolder(View.inflate(context, R.layout.item_detail_stills, null));
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(getItemViewType(position) == ALL){
            ALLViewHolder allViewHolder = (ALLViewHolder) holder;
            allViewHolder.tvAllNum.setText(size+"");

            allViewHolder.ll_all_stills.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onStillsCallBack != null){
                        onStillsCallBack.onClickItem(0, 0);
                    }
                }
            });

        }else {

            StillsListViewHolder stillsListViewHolder = (StillsListViewHolder) holder;

            Glide.with(context).load(imageData.get(position).getImage()).into(stillsListViewHolder.ivStillsImage);

            stillsListViewHolder.ivStillsImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onStillsCallBack != null){
                        onStillsCallBack.onClickItem(1, imageData.get(position).getId());
                    }
                }
            });

        }
    }


    @Override
    public int getItemCount() {

        if(imageData.size() == MAXNUM){
            return MAXNUM + 1;
        }else {
            return imageData.size() + 1;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(isShowAddItem(position)){
            return ALL;
        }else {
            return STILLSLIST;
        }
    }

    private boolean isShowAddItem(int position) {
        int size = imageData.size() == 0 ? 0 : imageData.size();
        return position == size;
    }


    class ALLViewHolder extends RecyclerView.ViewHolder{

        TextView tvAllNum;
        LinearLayout ll_all_stills;

        public ALLViewHolder(View itemView) {
            super(itemView);
            tvAllNum = itemView.findViewById(R.id.tv_all_num);
            ll_all_stills = itemView.findViewById(R.id.ll_all_stills);
        }
    }

    class StillsListViewHolder extends RecyclerView.ViewHolder{

        ImageView ivStillsImage;

        public StillsListViewHolder(View itemView) {
            super(itemView);
            ivStillsImage = itemView.findViewById(R.id.iv_stills_image);
        }
    }



    private OnStillsCallBack onStillsCallBack;

    public interface OnStillsCallBack{
        void onClickItem(int type, int stills);

    }

    public void setOnStillsCallBack(OnStillsCallBack onStillsCallBack){
        this.onStillsCallBack = onStillsCallBack;
    }

}
