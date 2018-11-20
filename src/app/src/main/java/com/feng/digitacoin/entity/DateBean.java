package com.feng.digitacoin.entity;

/**
 * @author weixuefeng@lubangame.com
 * @version $Rev$
 * @time: 2018/11/20--8:07 PM
 * @description
 * @copyright (c) 2018 Newton Foundation. All rights reserved.
 */
public class DateBean {

    final String[] strings = new String[] {
            "2000-1-1",
            "2000-1-2",
            "2001-2-1",
            "2001-3-2",
            "2001-3-3",
            "2001-3-4",
            "2001-3-5",
            "2001-3-6",
            "2001-3-7",
            "2002-5-8",
            "2002-5-10",
            "2002-6-1",
            "2002-6-5",
            "2002-7-1",
            "2002-9-14"
    };
    public String[] years = new String[]{};
    public String[] months;
    public String[] days;
    public DateBean() {
        for(int i = 0; i < strings.length; i ++) {
            String[] time = strings[i].split("-");
        }
    }
}
