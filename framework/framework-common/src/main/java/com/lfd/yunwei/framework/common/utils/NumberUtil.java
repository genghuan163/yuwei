package com.lfd.yunwei.framework.common.utils;


import com.lfd.yunwei.framework.common.constants.NumberConstant;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * @author chuan.jiang
 * @since 2017-07-15
 */
public class NumberUtil extends org.apache.commons.lang3.math.NumberUtils {
    /**
     * 用于生成数字转换用到的字符，随机打乱不让随便猜出来
     * ===* 请不要随意修改下列字符列表 *===
     */
    private final static char[] SYMBOLS = {'b', 'P', 'x', '0', 's', 'X', 'd', 'E', 'q', 'W', 'Z', 'G',
            'I', 'g', 'B', 'w', 'i', '4', 'R', 'Q', 'y', '-', 'K', 'j', 'v', '8', '_', 'e', 'H',
            'c', '9', '2', 'n', 'u', 'Y', 'a', 't', 'J', 'C', '(', 'M', 'k', 'F', 'r', 'A', 'p',
            '1', 'l', 'T', 'D', 'L', 'N', 'S', 'U', '7', 'z', 'V', '5', '6', 'f', 'm', 'h', ')', '3'};

    // 不足指定位数，补齐字符
    private static final char PAD_CHAR = 'o';
    private static final int DEFAULT_RADIX = 64;

    private static final double coefficient = 5.3;

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

    /**
     * 检查两个数字是不是完全不同，
     * 主要用于数据更新时，对两个字段进行检查
     *
     * @param num1 Number
     * @param num2 Number
     * @return true: 两个字符不同
     */
    public static boolean isNotSame(Number num1, Number num2) {
        if (null == num1 || null == num2) {
            return false;
        } else if (num1.equals(num2)) {
            return false;
        }
        return true;
    }

    /**
     * 检查两个数字是不是完全不同，
     * 主要用于数据更新时，对两个字段进行检查
     *
     * @param num1 Number
     * @param num2 Number
     * @return true: 两个数字相同
     */
    public static boolean isSame(Number num1, Number num2) {
        if (null == num1 || null == num2) {
            return false;
        } else if (num1.equals(num2)) {
            return true;
        }
        return false;
    }

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
            return compress(number, 6);
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
            return compress(number, 6);
        }
        return null;
    }

    /**
     * 将数字转换成64进制
     *
     * @param number 数字
     * @param length 长度
     * @return 64进制数字
     */
    public static String compress(long number, int length) {
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
                ? StringUtils.leftPad(results, length, PAD_CHAR)
                : results;
    }

    /**
     * 将64进制数转换成正常数字
     *
     * @param compressed 64进制数
     * @return 正常数字
     */
    public static long uncompress(String compressed) {
        final String originCompressed = compressed;
        try {
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
        } catch (Exception e) {
            //如果转换异常，使用much解密
            return NumberUtilsMuch.uncompress(originCompressed);
        }
    }

    public static String uncompressToStr(String compressed) {
        long uncompress = uncompress(compressed);
        return String.valueOf(uncompress);
    }

    public static Integer uncompressToInt(String compressed) {
        Long uncompress = uncompress(compressed);
        return uncompress.intValue();
    }

    public static void main(String[] args) {
        System.out.println(uncompress("HGwFSb"));
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
     * 计算百分比
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public static String percent(long dividend, long divisor) {
        if (0 == dividend || 0 == divisor) {
            return "";
        }
        long proportion = proportion(dividend, divisor);
        if (NumberUtil.isNotPositive(proportion)) {
            return "";
        }

        return longToPercent(proportion);
    }

    /**
     * 计算比例
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 小数扩大10的10次方倍
     */
    public static long proportion(long dividend, long divisor) {
        if (0 == dividend || 0 == divisor) {
            return 0;
        }
        BigDecimal divide = BigDecimal.valueOf(dividend).divide(BigDecimal.valueOf(divisor), NumberConstant.BIGDECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
        if (Objects.isNull(divide)) {
            return 0L;
        }

        return divide.multiply(BigDecimal.valueOf(NumberConstant.BIGDECIMAL_DECIMALS)).longValue();
    }

    /**
     * long转百分数
     *
     * @param percentNumber 被扩大10的10次方倍的小数
     * @return xx.xx%
     */
    public static String longToPercent(Long percentNumber) {
        if (NumberUtil.isNotPositive(percentNumber)) {
            return "";
        }

        return new BigDecimal(percentNumber).divide(BigDecimal.valueOf(NumberConstant.BIGDECIMAL_DECIMALS / 100),
                NumberConstant.PERCENT_BIGDECIMAL_SCALE, BigDecimal.ROUND_HALF_UP).toString() + "%";
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
     * 倍数 计算
     *
     * @return
     */
    public static int mulriple(Long cardinal, Integer saleNumber) {
        if (null == cardinal) {
            return (int) (123 * coefficient);
        }
        String strCardinal = cardinal.toString();
        int cardinalInt = Integer.parseInt(strCardinal.substring((strCardinal.length() - 3) < 0 ? 0 : strCardinal.length() - 3));
        if (cardinalInt < 100) {
            cardinalInt += 100;
        }
        return (int) (cardinalInt * coefficient) + (null == saleNumber ? 0 : saleNumber.intValue());
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
     * 计算金币
     *
     * @param num1
     * @param num2
     * @return
     */
    public static Double processGoldCoin(Integer num1, Integer num2) {
        if (isNotPositive(num1) || isNotPositive(num2)) {
            return 0.0;
        }
        BigDecimal userScore = new BigDecimal(num1);
        BigDecimal userNumBigDec = new BigDecimal(num2);
        return userScore.divide(userNumBigDec, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
