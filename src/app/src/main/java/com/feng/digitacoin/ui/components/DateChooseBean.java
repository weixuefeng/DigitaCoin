package com.feng.digitacoin.ui.components;

import android.support.v7.util.SortedList;

import java.util.HashMap;

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/21--11:47 AM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
public class DateChooseBean extends SortedList.Callback<String>{

    private SortedList<String> yearSet = new SortedList<String>(String.class, this);

    private HashMap<String, SortedList<String>> monthMap = new HashMap<>();

    private HashMap<String, SortedList<String>> dayMap = new HashMap<>();

    public SortedList<String> getYearSet() {
        return yearSet;
    }

    public HashMap<String, SortedList<String>> getMonthMap() {
        return monthMap;
    }

    public HashMap<String, SortedList<String>> getDayMap() {
        return dayMap;
    }

    public DateChooseBean(String[] timeSet) {
        if(timeSet != null && timeSet.length > 0) {
            for(int i = 0; i < timeSet.length; i ++) {
                String times = timeSet[i];
                String[] time = times.split("-");
                String year = time[0];
                String month = time[1];
                String day = time[2];
                String dayKey = year + "-" + month;

                yearSet.add(year);

                if(monthMap.containsKey(year)) {
                    monthMap.get(year).add(month);
                }else{
                    SortedList<String> monthSet = new SortedList<String>(String.class, this);
                    monthSet.add(month);
                    monthMap.put(year,monthSet);
                }

                if(dayMap.containsKey(dayKey)) {
                    dayMap.get(dayKey).add(day);
                }else{
                    SortedList<String> daySet = new SortedList<String>(String.class, this);
                    daySet.add(day);
                    dayMap.put(dayKey, daySet);
                }
            }
        }
    }

    @Override
    public int compare(String o1, String o2) {
        // 倒序
        return Integer.valueOf(o2).compareTo(Integer.valueOf(o1));

    }

    @Override
    public void onChanged(int position, int count) {

    }

    @Override
    public boolean areContentsTheSame(String oldItem, String newItem) {
        return Integer.valueOf(oldItem).equals(Integer.valueOf(newItem));
    }

    @Override
    public boolean areItemsTheSame(String item1, String item2) {
        return Integer.valueOf(item1).equals(Integer.valueOf(item2));
    }

    @Override
    public void onInserted(int position, int count) {

    }

    @Override
    public void onRemoved(int position, int count) {

    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {

    }
}
