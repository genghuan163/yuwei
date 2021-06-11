package com.lfd.yunwei.framework.common.utils;

import java.util.Optional;


/**
 * @author Hogon.Gh
 */
public class NumberUtilsMuch extends org.apache.commons.lang3.math.NumberUtils {

    /**
     * 用于生成数字转换用到的字符，随机打乱不让随便猜出来
     * ===* 请不要随意修改下列字符列表 *===
     */
    private final static char[] SYMBOLS = {'v', 'w', 'y', 'x', '2', '0', 'G', 'h', 'f', 'n', 'U',
            '-', 'z', '1', 'J', 'm', '_', 'A', 'F', 'C', '5', 'T', 'P', '(', 'E', 'H', 's', 'W', '6',
            '9', 'R', 'X', '8', 'b', 'N', 'I', 'Q', 'e', 'd', 'a', 'c', 'D', '7', ')', 'K', 'V', 'q',
            '3', 'l', 'p', 'S', 'k', 'Y', 'Z', '4', 'L', 'u', 'i', 'r', 't', 'B', 'g', 'M', 'j'};

    // 不足指定位数，补齐字符
    private static final char PAD_CHAR = 'o';
    private static final int DEFAULT_RADIX = 64;

    /**
     * Null == value
     *
     * @param value 需要检查的数字
     * @return 检查的数据为 NULL
     */
    public static boolean isNull(Number value) {
        return null == value;
    }

    /**
     * Null != value
     *
     * @param value 需要检查的数字
     * @return 检查的数据不是 NULL
     */
    public static boolean isNotNull(Number value) {
        return null != value;
    }

    /**
     * Null != value && value > 0
     *
     * @param value
     * @return
     */
    public static boolean isPositive(Number value) {
        if (isNotNull(value)) {
            if (value instanceof Integer) {
                return value.intValue() > 0;
            } else if (value instanceof Long) {
                return value.longValue() > 0;
            } else if (value instanceof Byte) {
                return value.byteValue() > 0;
            } else if (value instanceof Double) {
                return value.doubleValue() > 0;
            } else if (value instanceof Float) {
                return value.floatValue() > 0;
            } else if (value instanceof Short) {
                return value.shortValue() > 0;
            }
        }
        return false;
    }

    /**
     * Null == value || value < 1
     *
     * @param value
     * @return
     */
    public static boolean isNotPositive(Number value) {
        if (isNull(value)) {
            return true;
        }
        if (value instanceof Integer) {
            return value.intValue() < 1;
        } else if (value instanceof Long) {
            return value.longValue() < 1;
        } else if (value instanceof Byte) {
            return value.byteValue() < 1;
        } else if (value instanceof Double) {
            return value.doubleValue() < 1;
        } else if (value instanceof Float) {
            return value.floatValue() < 1;
        } else if (value instanceof Short) {
            return value.shortValue() < 1;
        }
        return true;
    }
///////////////////////////////////////将数字转换成64进制 开始/////////////////////////////////////////////////////////////////////////////

    /**
     * 将数字转换成64进制
     *
     * @param number 数字
     * @return 64进制数字
     */
    public static String compress(Long number) {
        if (isPositive(number)) {
            return compress(number, 6);
        }
        return null;
    }

    public static String compress(Integer number) {
        if (isPositive(number)) {
            return compress(number.longValue(), 6);
        }
        return null;
    }

    /**
     * 将数字转换成64进制
     *
     * @param number 数字
     * @return 64进制数字
     */
    public static String compress6(Long number) {
        if (isPositive(number)) {
            return compress(number, 6);
        }
        return null;
    }

    public static String compress6(Integer number) {
        if (isPositive(number)) {
            return compress(number.longValue(), 6);
        }
        return null;
    }

    /**
     * 将数字转换成64进制
     *
     * @param number 数字
     * @return 64进制数字
     */
    public static String compress(long number,int length) {
        char[] buf = new char[DEFAULT_RADIX];
        int charPos = DEFAULT_RADIX;
        int radix = 1 << 6;
        long mask = radix - 1;
        do {
            buf[--charPos] = SYMBOLS[(int) (number & mask)];
            number >>>= 6;
        } while (number != 0);

        String results = new String(buf, charPos, (DEFAULT_RADIX - charPos));
        results = getRedundancyCode(results) + results;

        if (0 == length || Integer.MAX_VALUE == length) {
            return results;
        }
        return results.length() < length
                ? org.apache.commons.lang3.StringUtils.leftPad(results, length, PAD_CHAR)
                : results;
    }
///////////////////////////////////////将数字转换成64进制 结束//////////////////////////////////////////////////////////////////////////////



    /**
     * 将64进制数转换成正常数字
     *
     * @param compressed 64进制数
     * @return 正常数字
     */
    public static Long uncompress(String compressed) {
        compressed = compressed.trim();
        int padIndex = compressed.lastIndexOf(PAD_CHAR);
        char checkCode;
        try {
            if (padIndex < 0) {
                checkCode = compressed.charAt(0);
                compressed = compressed.substring(1);
            } else {
                checkCode = compressed.charAt(padIndex + 1);
                compressed = compressed.substring(padIndex + 2);
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("compressed number is not correct");
        }

        if (checkCode != getRedundancyCode(compressed)) {
            throw new NumberFormatException("check code does not match");
        }

        long result = 0;
        for (int i = compressed.length() - 1; i >= 0; i--) {
            for (int j = 0; j < SYMBOLS.length; j++) {
                if (compressed.charAt(i) == SYMBOLS[j]) {
                    result += ((long) j) << 6 * (compressed.length() - 1 - i);
                }
            }
        }
        return result;
    }

    /**
     * 获取相反数
     *
     * @param number
     * @return
     */
    public static int opposite(int number) {
        return -1 * number;
    }

    public static long opposite(long number) {
        return -1 * number;
    }


    /**
     * 生成冗余验证码
     *
     * @param compressed 压缩过的数字
     * @return 验证码的值
     */
    private static char getRedundancyCode(String compressed) {
        compressed = compressed.toLowerCase();
        int sum = 0;
        for (int i = 0; i < compressed.length(); i++) {
            sum += compressed.charAt(i);
        }
        return SYMBOLS[sum % SYMBOLS.length];
    }


    /**
     * 取得数值对应的值
     *
     * @param numberVal
     * @return
     */
    public static long getNumberValue(Long numberVal) {
        return Optional.ofNullable(numberVal).orElse(0L);
    }

    /**
     * Integer 与 Long 的值比较
     *
     * @param v1 Integer
     * @param v2 Long
     * @return 值相等待则 true, 否则 false
     */
    public static boolean equals(Integer v1, Long v2) {
        if (null == v1 || null == v2) {
            return false;
        }
        return v1.longValue() == v2.longValue();
    }
}
