package kz.mobile.fragmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TabActivity extends AppCompatActivity implements FirstFragment.SendMessage{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MainViewPagerAdapter viewPagerAdapter;
    private RecyclerView recycleView;
    private FragAdapter fragAdapter;
    private int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), 1);
        viewPager.setAdapter(viewPagerAdapter);



        //tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle","onStart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cnt = savedInstanceState.getInt("cnt");
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        SecondFragment f = (SecondFragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(String.valueOf(cnt));
        Log.d("lifecycle", "onRestoreInstanceState" + cnt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle", "onStop");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("cnt", cnt);
        Log.d("lifecycle", "onSaveInstanceState" + cnt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "onDestroy");
    }

    @Override
    public void sendData(String counter) {
        cnt ++;
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        SecondFragment fragment = (SecondFragment)
                getSupportFragmentManager().findFragmentByTag(tag);
        fragment.displayReceivedData(counter);
    }


    class MainViewPagerAdapter extends FragmentPagerAdapter {

        public MainViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new FirstFragment();
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("name", "Daniyar Sansyzbayev");
                return SecondFragment.newInstance(bundle);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
