package com.zqw.lightfilm.home_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zqw.lightfilm.R;
import com.zqw.lightfilm.oneday.OneDayPagerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 启文 on 2018/2/3.
 */
public class OneDayFragment extends Fragment {

    @Bind(R.id.tab_day)
    TabLayout tabDay;
    @Bind(R.id.vp_day_context)
    ViewPager vpDayContext;

    private List<Fragment> fragmentList;
    private OneDayPagerAdapter adapter;
    private Context context;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    public OneDayFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oneday, container, false);
        ButterKnife.bind(this, view);


        setupViewPager();
        return view;
    }


    private void setupViewPager() {

        //使用适配器将ViewPager与Fragment绑定在一起
        adapter = new OneDayPagerAdapter(getFragmentManager(), context);
        vpDayContext.setAdapter(adapter);


        //将TabLayout与ViewPager绑定在一起
        tabDay.setupWithViewPager(vpDayContext);



        //指定Tab的位置
        one = tabDay.getTabAt(0);
        two = tabDay.getTabAt(1);
        three = tabDay.getTabAt(2);
        four = tabDay.getTabAt(3);


          /*
        viewPager选择监听
         */
        vpDayContext.addOnPageChangeListener(new MyViewPagerChangeListener());



    }


    class MyViewPagerChangeListener implements  ViewPager.OnPageChangeListener{



        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * This method will be invoked when a new page becomes selected. Animation is not
         * necessarily complete.
         *
         * @param position Position index of the new selected page.
         */
        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
