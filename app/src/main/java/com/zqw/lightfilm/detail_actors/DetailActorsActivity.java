package com.zqw.lightfilm.detail_actors;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.re.ObservableScrollView;
import com.zqw.lightfilm.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import jaydenxiao.com.expandabletextview.ExpandableTextView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class DetailActorsActivity extends AppCompatActivity {

    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_heard_background)
    ImageView ivHeardBackground;
    @Bind(R.id.iv_movie_img)
    ImageView ivMovieImg;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_en_name)
    TextView tvEnName;
    @Bind(R.id.ll_movie_info)
    LinearLayout llMovieInfo;
    @Bind(R.id.rl_heard)
    RelativeLayout rlHeard;
    @Bind(R.id.tv_profession)
    TextView tvProfession;
    @Bind(R.id.tv_birth)
    TextView tvBirth;
    @Bind(R.id.tv_height)
    TextView tvHeight;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_movieCount)
    TextView tvMovieCount;
    @Bind(R.id.tv_actors_info)
    ExpandableTextView tvActorsInfo;
    @Bind(R.id.sv_view)
    ObservableScrollView svView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_actors);
        ButterKnife.bind(this);


        initToolbar();

        Bundle b = getIntent().getBundleExtra("actors_id");

        getNetData(b.getString("actors_id"));   //网络请求

        svView.setScrollViewListener(new MyScrollChangeListener());
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


    public void getNetData(final String actorsId) {

        String url = Constants.ACTORS_INFO__URL + actorsId + Constants.ACTORS_INFO__URL_2;

        if (actorsId != null) {
            OkGo.<String>post(url)
                    .retryCount(2)
                    .cacheTime(5000)
                    .execute(new StringCallback() {

                        /**
                         * 对返回数据进行操作的回调， UI线程
                         */
                        @Override
                        public void onSuccess(Response<String> response) {
                            processData(response.body(), actorsId);
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                        }
                    });
        }

    }


    private void processData(String body, String actorsId) {

        ActorsInfoBean actorsInfoBean = JSON.parseObject(body, ActorsInfoBean.class);

        ActorsInfoBean.DataBean.BackgroundBean info = actorsInfoBean.getData().getBackground();

        /**
         * 初始化基本信息
         */
        tvName.setText(info.getNameCn());
        tvEnName.setText(info.getNameEn());

        Glide.with(this)
                .load(info.getImage())
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(ivHeardBackground);

        Glide.with(this)
                .load(info.getImage())
                // .bitmapTransform(new CropCircleTransformation(this))
                .into(ivMovieImg);

        tvProfession.setText("职业： " + info.getProfession());
        tvBirth.setText("生日： " + info.getBirthYear()+ "-" + info.getBirthMonth()+ "-" + info.getBirthDay());
        tvHeight.setText("身高： " + info.getHeight());
        tvAddress.setText("出生地： " + info.getAddress());
        tvMovieCount.setText("参与作品： " + info.getMovieCount());

        tvActorsInfo.setText("" + info.getContent());

    }
}
