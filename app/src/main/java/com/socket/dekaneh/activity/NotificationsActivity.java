package com.socket.dekaneh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.socket.dekaneh.R;
import com.socket.dekaneh.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends BaseActivity {

    @BindView(R.id.notificationsToolbar)
    Toolbar toolbar;
    @BindView(R.id.notificationsRV)
    RecyclerView notificationsRV;

    public static void start(Context context) {
        Intent starter = new Intent(context, NotificationsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getActivityComponent() != null) {
            getActivityComponent().inject(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
