package com.example.harshupatel.inviko;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.harshupatel.inviko.Fragment.FiveFragment;
import com.example.harshupatel.inviko.Fragment.OneFragment;
import com.example.harshupatel.inviko.Fragment.ThreeFragment;
import com.example.harshupatel.inviko.Fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Dashboard");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.dashboard, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Search");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.search2, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabThree);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Notification");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.notification, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabTwo);

        TextView tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFive.setText("Options");
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.menu, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFive);
    }

    private void setupViewPager(ViewPager viewPager) {
        HomePage.ViewPagerAdapter adapter = new HomePage.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "Dashboard");
        adapter.addFrag(new ThreeFragment(), "Search");
        adapter.addFrag(new TwoFragment(), "Notification");
        adapter.addFrag(new FiveFragment(), "More");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
