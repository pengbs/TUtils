package com.boy.tutils;

public class ButtonClickUtils {
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 500) {   
            return true;   
        }   
        lastClickTime = time;   
        return false;   
    }
}
