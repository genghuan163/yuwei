package com.lfd.yunwei.framework.common.utils;


import com.lfd.yunwei.framework.common.constants.DateConstant;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hogon.Gh
 */
public class SimpleDateFormatThreadLocal {
    private static ThreadLocal<Map<String, SimpleDateFormat>> simpleDateFormatThreadLocal = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        protected synchronized Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<>();
        }
    };

    public static SimpleDateFormat get() {
        return get(DateConstant.DATE_FORMAT);
    }

    public static SimpleDateFormat get(String dateFormat) {
        return simpleDateFormatThreadLocal.get().computeIfAbsent(dateFormat, SimpleDateFormat::new);
    }

    public static void clear() {
        simpleDateFormatThreadLocal.remove();
    }
}
