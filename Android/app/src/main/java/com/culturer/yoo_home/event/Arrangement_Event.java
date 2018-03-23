package com.culturer.yoo_home.event;

import com.culturer.yoo_home.bean.Arrangement;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class Arrangement_Event {

    public static final int Arrangement_NEW = 0 ;
    public static final int Arrangement_DEL = 0 ;

    public int type;

    public Arrangement_Event(int type, Arrangement arrangement) {
        this.type = type;
        this.arrangement = arrangement;
    }

    public Arrangement arrangement;
}
