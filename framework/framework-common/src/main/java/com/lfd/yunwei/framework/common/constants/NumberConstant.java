package com.lfd.yunwei.framework.common.constants;

public interface NumberConstant {

    //  小数扩大10的10次方倍
    long BIGDECIMAL_DECIMALS = 1000 * 1000 * 1000 * 10L;

    // 百分数小数后面精确位数
    int PERCENT_BIGDECIMAL_SCALE = 2;


    // 除法精确位数
    int BIGDECIMAL_SCALE = 10;


    // 金额单位分保留位数
    int BIGDECIMAL_FEN_SCALE = 0;


    // 转换比例(以分为单位进行比例转换，精确到分后面3位有效)
    long PRICE_RATIO_FEN = 1000L;

    // 转换比例(以元为单位进行比例转换，精确到分后面3位有效)
    long PRICE_RATIO = 10 * 10 * 1000L;

    // 金额单位元保留位数
    int BIGDECIMAL_YUAN_SCALE = 2;


    int ZERO = 0;
    int ONE = 1;
    int TWO = 2;
}
