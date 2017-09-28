package com.example.user.magangbdv;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class NewsEventActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager pager;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_event);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("News & Events");
        email = getIntent().getStringExtra("emailMember");

        pager = (ViewPager)findViewById(R.id.pager);
        tabs = (TabLayout)findViewById(R.id.tabs);
        pager.setAdapter(new TabNewsEventPagerAdapter(getSupportFragmentManager()));

        tabs.setTabTextColors(getResources().getColor(android.R.color.white),
                getResources().getColor(R.color.yellow));
        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        int status = getIntent().getIntExtra("stat",0);

        if (status == 0){
            new AlertDialog.Builder(this)
                    .setTitle("Bandung Digital Valley")
                    .setMessage(getResources().getString(R.string.detail_registered))
                    .setIcon(R.drawable.bdv_logo)
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                        }})
                    .show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_profil :
                Intent intent = new Intent(getApplicationContext(),ProfilActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
                finish();
                break;
            case android.R.id.home :
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
