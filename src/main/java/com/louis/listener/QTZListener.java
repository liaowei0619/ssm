package com.louis.listener;


import com.louis.utilTools.DateUtils;

public class QTZListener {

    private static int counter = 0;


    protected void execute() {
        System.out.println("(" + counter++ + ")" + DateUtils.getDate());
    }


}
