package com.example.router.viewrouter.example.model;

import java.util.List;

/**
 * @date: 2019-08-03
 * @autror: guojian
 * @description:
 */
public class Bezier {

    private int articalCount;
    private int month;

    public Bezier(int articalCount, int month) {
        this.articalCount = articalCount;
        this.month = month;
    }

    public int getArticalCount() {
        return articalCount;
    }

    public void setArticalCount(int articalCount) {
        this.articalCount = articalCount;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
