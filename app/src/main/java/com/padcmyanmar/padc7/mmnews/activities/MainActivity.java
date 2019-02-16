package com.padcmyanmar.padc7.mmnews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.adapters.NewsAdapter;
import com.padcmyanmar.padc7.mmnews.delegates.NewsItemDelegate;

public class MainActivity extends AppCompatActivity implements NewsItemDelegate {

    private BottomSheetBehavior<NestedScrollView> mBottomSheetBehavior;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_latest_news);

        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_lastest_news:
                        Toast.makeText(getApplicationContext(), getString(R.string.title_latest_news), Toast.LENGTH_SHORT).show();
                        mToolbar.setTitle(R.string.title_latest_news);
                        break;
                    case R.id.menu_news_just_for_you:
                        Toast.makeText(getApplicationContext(), getString(R.string.title_news_just_for_you), Toast.LENGTH_SHORT).show();
                        mToolbar.setTitle(R.string.title_news_just_for_you);
                        break;
                    case R.id.menu_favorite_news:
                        Toast.makeText(getApplicationContext(), getString(R.string.title_favorite_news), Toast.LENGTH_SHORT).show();
                        mToolbar.setTitle(R.string.title_favorite_news);
                        break;
                }
                for (int index = 0; index < mNavigationView.getMenu().size(); index++) {
                    mNavigationView.getMenu().getItem(index).setChecked(false);
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */

                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        RecyclerView rvNews = findViewById(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        NewsAdapter newsAdapter = new NewsAdapter(this);
        rvNews.setAdapter(newsAdapter);

        NestedScrollView nsvBottomSheet = findViewById(R.id.nsv_bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(nsvBottomSheet);

        mBottomSheetBehavior.setPeekHeight(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapNewsItem() {
        Intent intent = NewsDetailsActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }
}
