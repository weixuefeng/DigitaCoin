package com.feng.digitacoin.ui.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.feng.digitacoin.R;

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/20--2:56 PM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
public class ChooseDateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);
        recyclerView = findViewById(R.id.dateRecycler);
    }
}
