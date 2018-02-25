package com.zqw.lightfilm.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.zqw.lightfilm.R;
import com.zqw.lightfilm.home_fragment.HomeFragment;
import com.zqw.lightfilm.home_fragment.MyDataFragment;
import com.zqw.lightfilm.home_fragment.OneDayFragment;
import com.zqw.lightfilm.home_fragment.RecommendFragment;
import com.zqw.lightfilm.re.NoScrollViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.vp_context)
    NoScrollViewPager vpContext;
    @Bind(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    //  private Fragment[] fragments;
    private MenuItem lastItem; // 上一个选中的item
    private MainViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        toolbar.getBackground().setAlpha(255);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.window_background));
        //    toolbar.setBackgroundColor(getColor(R.color.colorAccent));

        initView();

    }

    private void initView() {

        /*
        初始化适配器，填充fragment
         */
        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        if(this != null){
            adapter.addFragemnt(new HomeFragment(this));
            adapter.addFragemnt(new RecommendFragment());
            adapter.addFragemnt(new OneDayFragment(this));
            adapter.addFragemnt(new MyDataFragment());
            vpContext.setAdapter(adapter);
        }


        /*
        viewPager选择监听
         */
        vpContext.addOnPageChangeListener(new MyViewPagerChangeListener());

        /*
        底部菜单选择点击事件
         */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (lastItem != item) { // 判断当前点击是否为item自身

                    onTabItemSelected(item.getItemId());
                    return true;
                }

                return false;
            }
        });

        boolean shown = bottomNavigationView.isShown();
        Log.i("TAG", "" + shown);
    }


    /**
     * viewPager选择监听
     */
    class MyViewPagerChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }


        @Override
        public void onPageSelected(int position) {
            // 当 ViewPager 滑动后设置BottomNavigationView 选中相应选项
            bottomNavigationView.getMenu().getItem(position).setChecked(true);
            if(position == 2){
                tvTitle.setText("精彩推荐");
            }

        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 判断用户点击的菜单项，并开启fragment
     *
     * @param id
     */
    private void onTabItemSelected(int id) {
        switch (id) {
            case R.id.tab_menu_home:
                vpContext.setCurrentItem(0);
                break;
            case R.id.tab_menu_discovery:
                vpContext.setCurrentItem(1);
                break;
            case R.id.tab_menu_attention:
                vpContext.setCurrentItem(2);
                break;
            case R.id.tab_menu_profile:
                vpContext.setCurrentItem(3);
                break;
        }
    }
}
