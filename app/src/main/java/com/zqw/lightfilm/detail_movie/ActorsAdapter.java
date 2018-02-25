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
import com.zqw.lightfilm.detail_movie.Bean.MovieDetailBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 启文 on 2018/2/11.
 */
public class ActorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieDetailBean.DataBean.BasicBean.ActorsBean> actors;
    private MovieDetailBean.DataBean.BasicBean.DirectorBean director;
    private Context context;

    private static final int MAXNUM = 7;
    private static final int ALL = 0;
    private static final int ACTORSLIST = 1;



    public ActorsAdapter(Context context, List<MovieDetailBean.DataBean.BasicBean.ActorsBean> actors, MovieDetailBean.DataBean.BasicBean.DirectorBean director) {
        this.context = context;
        this.actors = actors;
        this.director = director;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == ALL){
            return  new AllViewHolder(View.inflate(context, R.layout.item_all_actors, null));
        }else if(viewType == ACTORSLIST){
            return new ActorsListViewHolder(View.inflate(context, R.layout.item_detail_actors, null));
        }

        return null;

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 0){


            ActorsListViewHolder actorsViewHolder = (ActorsListViewHolder) holder;

            actorsViewHolder.tvName.setText(director.getName());
            actorsViewHolder.tvRoleName.setText("导演");

            Glide.with(context).load(director.getImg()).into(actorsViewHolder.ivActorsImage);

            actorsViewHolder.llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onActorsCallBack != null){
                        onActorsCallBack.onClickItem(0, director.getDirectorId());
                    }
                }
            });



        }else if(getItemViewType(position) == ALL){
            AllViewHolder allViewHolder = (AllViewHolder) holder;

            allViewHolder.tvAllNum.setText("9人");

            allViewHolder.llAllActors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onActorsCallBack != null){
                        onActorsCallBack.onClickItem(3, 0);
                    }
                }
            });


        }else {

            ActorsListViewHolder actorsViewHolder = (ActorsListViewHolder) holder;
            final int i = position-1;
            actorsViewHolder.tvName.setText(actors.get(i).getName());
            actorsViewHolder.tvRoleName.setText("饰: " + actors.get(i).getRoleName());

            Glide.with(context)
                    .load(actors.get(i).getImg())
                    .into(actorsViewHolder.ivActorsImage);

            actorsViewHolder.llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onActorsCallBack != null){
                        onActorsCallBack.onClickItem(1, actors.get(i).getActorId());
                    }
                }
            });

        }
    }








    @Override
    public int getItemCount() {

        if(actors.size() == MAXNUM){
            return MAXNUM +2;
        }else {
            return actors.size() + 2;  //加上导演和全部按钮
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(isShowAddItem(position)){
            return ALL;
        }else {
            return ACTORSLIST;
        }
    }

    private boolean isShowAddItem(int position) {
        int size = actors.size() == 0 ? 0 : actors.size();
        return position == size + 1;
    }


    class AllViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.ll_all_actors)
        LinearLayout llAllActors;
        @Bind(R.id.tv_all_num)
        TextView tvAllNum;

        public AllViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }



    class ActorsListViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_actors_image)
        ImageView ivActorsImage;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_role_name)
        TextView tvRoleName;
        @Bind(R.id.ll_root)
        LinearLayout llRoot;

        public ActorsListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }



    private OnActorsCallBack onActorsCallBack;

    public interface OnActorsCallBack{
        void onClickItem(int type, int actors);

    }

    public void setOnActorsCallBack(OnActorsCallBack onActorsCallBack){
        this.onActorsCallBack = onActorsCallBack;
    }
}
