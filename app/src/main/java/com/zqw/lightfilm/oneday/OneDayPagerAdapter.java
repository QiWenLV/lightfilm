package com.zqw.lightfilm.oneday;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zqw.lightfilm.oneday.fragment.PushFragment;
import com.zqw.lightfilm.oneday.fragment.ReadFragment;
import com.zqw.lightfilm.oneday.fragment.WelFareFragment;

/**
 * Created by 启文 on 2018/2/3.
 */
public class OneDayPagerAdapter extends FragmentPagerAdapter {



    private String[] mTitle = new String[]{"每日推荐","福利","干货","安卓"};
    private Context context;

    public OneDayPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 1){
            return new WelFareFragment(context);
        } else if(position == 2){
            return new ReadFragment();
        } else if(position == 3){
            return new PushFragment();
        } else{
            return new PushFragment();
        }
    }


    @Override
    public int getCount() {
        return mTitle.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
