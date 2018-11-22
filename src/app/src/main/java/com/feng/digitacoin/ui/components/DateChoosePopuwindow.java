package com.feng.digitacoin.ui.components;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.feng.digitacoin.R;
import com.feng.digitacoin.ui.me.adapter.CardLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/21--11:57 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
public class DateChoosePopuwindow extends PopupWindow implements View.OnClickListener {

    private OnSelectedListener listener;

    private ViewGroup view;

    private RecyclerView yearRecycler;
    private RecyclerView monthRecycler;
    private RecyclerView dayRecycler;

    private ArrayList<String> dateSource;
    private String currentYear;
    private SortedList<String> monthSet;
    private String currentMonth;
    private String currentDayKey;
    private SortedList<String> daySet;
    private String currentDay;
    private SortedList<String> yearSet;
    private TextView cancelTextView;
    private TextView confirmTextView;
    private ConstraintLayout rootView;
    private LinearLayout containerLayout;
    private HashMap<String, SortedList<String>> monthMap;
    private HashMap<String, SortedList<String>> dayMap;
    private DateAdapter yearAdapter;
    private DateAdapter monthAdapter;
    private DateAdapter dayAdapter;
    private CardLayoutManager yearManager;
    private CardLayoutManager monthManager;
    private CardLayoutManager dayManager;
    private Context mContext;

    public DateChoosePopuwindow(Context context, ArrayList<String> data) {
        this.dateSource = data;
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) ((Activity) context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = (ViewGroup) inflater.inflate(R.layout.popuwindow_date_choose, null);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        initView();
    }

    public void setSelectedListener(OnSelectedListener selectedListener) {
        this.listener = selectedListener;
    }

    private void initView() {
        yearRecycler = view.findViewById(R.id.yearRecycler);
        monthRecycler = view.findViewById(R.id.monthRecycler);
        dayRecycler = view.findViewById(R.id.dayRecycler);
        cancelTextView = view.findViewById(R.id.cancelTextView);
        confirmTextView = view.findViewById(R.id.confirmTextView);
        rootView = view.findViewById(R.id.date_choose_pop);
        containerLayout = view.findViewById(R.id.containerLayout);
        cancelTextView.setOnClickListener(this);
        confirmTextView.setOnClickListener(this);
        rootView.setOnClickListener(this);
        containerLayout.setOnClickListener(this);

        DateChooseBean dateChooseBean = new DateChooseBean(dateSource.toArray(new String[]{}));

        monthMap = dateChooseBean.getMonthMap();
        dayMap = dateChooseBean.getDayMap();

        yearSet = dateChooseBean.getYearSet();
        currentYear = yearSet.get(0);

        monthSet = monthMap.get(currentYear);
        currentMonth = monthSet.get(0);

        currentDayKey = currentYear + "-" + currentMonth;
        daySet = dayMap.get(currentDayKey);
        currentDay = daySet.get(0);

        yearAdapter = new DateAdapter(yearSet);
        monthAdapter = new DateAdapter(monthSet);
        dayAdapter = new DateAdapter(daySet);
        yearManager = new CardLayoutManager(LinearLayout.VERTICAL);
        monthManager = new CardLayoutManager(LinearLayout.VERTICAL);
        dayManager = new CardLayoutManager(LinearLayout.VERTICAL);

        yearManager.setOnItemSelectedListener(new CardLayoutManager.OnItemSelectedListener() {
            @Override
            public void onSelected(int position) {
                currentYear = yearSet.get(position);
                monthSet = monthMap.get(currentYear);
                monthAdapter.setDataSource(monthSet);
                monthManager.scrollToPosition(0);
                currentMonth = monthSet.get(0);

                currentDayKey = currentYear + "-" + currentMonth;
                daySet = dayMap.get(currentDayKey);
                dayAdapter.setDataSource(daySet);
                dayManager.scrollToPosition(0);
                currentDay = daySet.get(0);
            }
        });


        monthManager.setOnItemSelectedListener(new CardLayoutManager.OnItemSelectedListener() {
            @Override
            public void onSelected(int position) {
                currentMonth = monthSet.get(position);

                currentDayKey = currentYear + "-" + currentMonth;
                daySet = dayMap.get(currentDayKey);
                dayAdapter.setDataSource(daySet);
                dayManager.scrollToPosition(0);
                currentDay = daySet.get(0);
            }
        });

        dayManager.setOnItemSelectedListener(new CardLayoutManager.OnItemSelectedListener() {
            @Override
            public void onSelected(int position) {
                currentDay = daySet.get(position);
            }
        });

        yearRecycler.setLayoutManager(yearManager);
        monthRecycler.setLayoutManager(monthManager);
        dayRecycler.setLayoutManager(dayManager);

        yearRecycler.setAdapter(yearAdapter);
        monthRecycler.setAdapter(monthAdapter);
        dayRecycler.setAdapter(dayAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelTextView:
            case R.id.date_choose_pop:
                dismiss();
                break;
            case R.id.confirmTextView:
                if(listener != null) {
                    listener.onSelectedDate(currentYear + "-" + currentMonth + "-" + currentDay);
                }
                dismiss();
                break;
            case R.id.containerLayout:
                break;
        }
    }

    public interface OnSelectedListener {
        void onSelectedDate(String date);
    }
}
