package com.zqw.lightfilm.detail_movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.detail_movie.Bean.ImageAllBean;
import com.zqw.lightfilm.detail_movie.Bean.MovieDetailBean;
import com.zqw.lightfilm.re.ObservableScrollView;
import com.zqw.lightfilm.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by 启文 on 2018/2/6.
 */
public class DetailMovieActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_movie_img)
    ImageView ivMovieImg;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_en_tilte)
    TextView tvEnTilte;
    @Bind(R.id.tv_release)
    TextView tvRelease;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_long)
    TextView tvLong;
    @Bind(R.id.ll_movie_info)
    LinearLayout llMovieInfo;
    @Bind(R.id.tv_shiguang)
    TextView tvShiguang;
    @Bind(R.id.rl_heard)
    RelativeLayout rlHeard;
    @Bind(R.id.iv_heard_background)
    ImageView ivHeardBackground;
    @Bind(R.id.tv_story)
    TextView tvStory;
    @Bind(R.id.rv_actors)
    RecyclerView rvActors;
    @Bind(R.id.sv_view)
    ObservableScrollView svView;
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.rv_stills)
    RecyclerView rvStills;
    @Bind(R.id.ll_stills)
    LinearLayout llStills;
    @Bind(R.id.rv_prize)
    RecyclerView rvPrize;
    @Bind(R.id.ll_prize)
    LinearLayout llPrize;

    private String moiveId;
    private ActorsAdapter actorsAdapter;
    private StillsAdapter stillsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        initToolbar();

        Bundle b = getIntent().getBundleExtra("movie_id");
        moiveId = b.getString("movie_id");

        getNetData(moiveId);

        svView.setScrollViewListener(new MyScrollChangeListener());


    }


    class MyScrollChangeListener implements ObservableScrollView.ScrollViewListener {

        @Override
        public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {


            int height = rlHeard.getHeight();

            //  Log.i("TAG", "y--->" + y + "    height-->" + height);
            if (y <= 0) {

                toolbar.getBackground().setAlpha(0);

            } else if (y > 0 && y < height) {
                float scale = (float) y / height;
                toolbar.getBackground().setAlpha((int) (255 * scale));
                //      Log.i("TAG", "scale--->" + (255 * scale));
                tvToolbarTitle.setVisibility(View.GONE);
            } else if (y >= height) {
                toolbar.getBackground().setAlpha(255);
                tvToolbarTitle.setVisibility(View.VISIBLE);
            }

        }
    }

    private void initToolbar() {


        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.window_background));
        toolbar.getBackground().setAlpha(0);

        toolbar.bringToFront();

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.top_bar_left_back));
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(DetailMovieActivity.this, MainActivity.class));
                finish();
                toolbar.getBackground().setAlpha(255);
            }
        });
    }

    public void getNetData(final String moiveId) {

        String url = Constants.DETAIL_URL + moiveId;

        if (moiveId != null) {
            OkGo.<String>post(url)
                    .retryCount(2)
                    .cacheTime(5000)
                    .execute(new StringCallback() {

                        /**
                         * 对返回数据进行操作的回调， UI线程
                         */
                        @Override
                        public void onSuccess(Response<String> response) {
                            processData(response.body(), moiveId);
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                        }
                    });
        }

    }

    private void processData(String body, String moiveId) {

        MovieDetailBean movieDetailBean = JSON.parseObject(body, MovieDetailBean.class);

        MovieDetailBean.DataBean data = movieDetailBean.getData();

        /**
         * 初始化电影基本信息。
         */
        tvToolbarTitle.setText(data.getBasic().getName());
        tvToolbarTitle.setVisibility(View.GONE);

        tvTitle.setText(data.getBasic().getName());
        tvEnTilte.setText(data.getBasic().getNameEn());
        tvRelease.setText(data.getBasic().getReleaseDate() + "(" + data.getBasic().getReleaseArea() + ")");

        List<String> type = data.getBasic().getType();
        String s_type = "";
        for (int i = 0; i < type.size(); i++) {
            s_type += type.get(i);
            s_type += "/";
        }
        tvType.setText(s_type);

        tvLong.setText(data.getBasic().getMins());

        tvShiguang.setText("评分: " + data.getBasic().getOverallRating());

        Glide.with(this)
                .load(data.getBasic().getImg())
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(ivHeardBackground);


        Glide.with(this)
                .load(data.getBasic().getImg())
                // .bitmapTransform(new CropCircleTransformation(this))
                .into(ivMovieImg);

        tvStory.setText("     " + data.getBasic().getStory());


        /**
         * 初始化影人列表
         */
        final List<MovieDetailBean.DataBean.BasicBean.ActorsBean> actors = data.getBasic().getActors();
        MovieDetailBean.DataBean.BasicBean.DirectorBean director = data.getBasic().getDirector();
        initActors(actors, director);


        /**
         * 初始化剧照列表
         */
        //  data.getBasic().get
        initStills(moiveId);



    }

    private void initStills(String moiveId) {

        if (moiveId != null) {
            OkGo.<String>post(Constants.STILLS_URL + moiveId)
                    .retryCount(2)
                    .cacheTime(5000)
                    .execute(new StringCallback() {

                        /**
                         * 对返回数据进行操作的回调， UI线程
                         */
                        @Override
                        public void onSuccess(Response<String> response) {
                            String body = response.body();
                            ImageAllBean imageAllBean = JSON.parseObject(body, ImageAllBean.class);

                            List<ImageAllBean.ImagesBean> images = imageAllBean.getImages();
                            List<ImageAllBean.ImagesBean> newImages = new ArrayList<ImageAllBean.ImagesBean>();
                            for(ImageAllBean.ImagesBean i : images){
                                if(i.getType() == 6){
                                    newImages.add(i);
                                }
                            }

                            if(newImages.size() > 6){
                                stillsAdapter = new StillsAdapter(DetailMovieActivity.this, newImages.subList(0, 6), images.size());
                            } else {
                                stillsAdapter = new StillsAdapter(DetailMovieActivity.this, images, images.size());
                            }


                            //设置布局管理器
                            rvStills.setLayoutManager(new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.HORIZONTAL, false));

                            rvStills.setAdapter(stillsAdapter);

                            stillsAdapter.setOnStillsCallBack(new StillsAdapter.OnStillsCallBack() {
                                @Override
                                public void onClickItem(int type, int stills) {
                                    if(type == 0){
                                        //开启查看全部
                                        Toast.makeText(DetailMovieActivity.this,  "全部", Toast.LENGTH_SHORT).show();
                                    }else {
                                        //查看大图
                                        Toast.makeText(DetailMovieActivity.this,  stills+"", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                        }
                    });
        }
    }


    /**
     * 初始化影人列表
     *
     * @param actors
     * @param director
     */
    private void initActors(List<MovieDetailBean.DataBean.BasicBean.ActorsBean> actors, MovieDetailBean.DataBean.BasicBean.DirectorBean director) {
        /**
         * 处理数据，最多显示7个演员
         */
        List<MovieDetailBean.DataBean.BasicBean.ActorsBean> newActor = new ArrayList<>();

        if (actors.size() >= 7) {
            newActor.addAll(actors.subList(0, 7));
        } else {
            newActor.addAll(actors);
        }


        actorsAdapter = new ActorsAdapter(this, newActor, director);

        //设置布局管理器
        rvActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rvActors.setAdapter(actorsAdapter);
        View footer = View.inflate(this, R.layout.item_all_actors, null);
        //  rvActors.addFooterView(footer);


        actorsAdapter.setOnActorsCallBack(new ActorsAdapter.OnActorsCallBack() {
            @Override
            public void onClickItem(int type, int actors) {
                switch (type){

                    case 0:
                        //开启导演详情页面
                        Toast.makeText(DetailMovieActivity.this, actors + "", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //开启演员详情页面
                        Toast.makeText(DetailMovieActivity.this, actors + "", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        //开启查看全部
                        Toast.makeText(DetailMovieActivity.this,  "全部", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        toolbar.getBackground().setAlpha(255);
        super.onBackPressed();
    }
}
