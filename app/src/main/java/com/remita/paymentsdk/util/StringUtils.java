package com.remita.paymentsdk.util;

public class StringUtils
{
    public static final String EMPTY = "";

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }
    public static String generateId() {
        int a = (int) (Math.random() * 10000);
        String id = String.valueOf(a);
        if (id.length() < 4)
            id = generateId();
        return id;
    }
}
