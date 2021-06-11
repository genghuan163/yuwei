package com.lfd.yunwei.framework.common.convert;

import com.lfd.yunwei.framework.common.constants.NumberConstant;
import com.lfd.yunwei.framework.common.enums.AmountUnit;
import com.lfd.yunwei.framework.common.utils.NumberUtil;
import com.lfd.yunwei.framework.common.utils.SimpleDateFormatThreadLocal;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hogon.Gh
 */
public class ConvertUtils {
    /**
     * 将字符串转换成数字(Integer)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static Integer toInteger(String value, Integer defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    /**
     * 将字符串转换成数字(Integer)
     *
     * @param value 需要转换成数字的字符串
     * @return 转换后的数字
     * @deprecated {@link NumberUtil#toInt(String)}
     */
    public static Integer toInteger(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 将字符串转换成数字(Long)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static Long toLong(String value, Long defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成数字(Long)
     *
     * @param value 需要转换成数字的字符串
     * @return 转换后的数字
     */
    public static Long toLong(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 字符串转int数字
     *
     * @param value
     * @return
     */
    public static Integer toInt(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer toInt(String value, Integer defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成数字(Float)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static Float toFloat(String value, Float defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }

        try {
            return Float.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    /**
     * 将字符串转换成数字(double)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static Double toDouble(String value, Double defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成数字(BigDecimal)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成数字的字符串
     * @param defaultValue 默认值
     * @return 转换后的数字
     */
    public static BigDecimal toBigDecimal(String value, BigDecimal defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成布尔值(boolean)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成布尔值的字符串
     * @param defaultValue 默认值
     * @return 转换后的布尔值
     */
    public static boolean toBoolean(String value, boolean defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        if ("on".equals(value)) {
            return true;
        }
        if ("off".equals(value)) {
            return false;
        }
        if ("1".equals(value)) {
            return true;
        }
        if ("0".equals(value)) {
            return false;
        }
        try {
            return Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换成布尔值(Boolean)，如果字符串不合法将返回默认值
     *
     * @param value        需要转换成布尔值的字符串
     * @param defaultValue 默认值
     * @return 转换后的布尔值
     */
    public static Boolean toBoolean(String value, Boolean defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        if ("on".equalsIgnoreCase(value)) {
            return Boolean.TRUE;
        }
        if ("off".equalsIgnoreCase(value)) {
            return Boolean.FALSE;
        }
        if ("1".equals(value)) {
            return Boolean.TRUE;
        }
        if ("0".equals(value)) {
            return Boolean.FALSE;
        }
        try {
            return Boolean.valueOf(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 转换日期为字符串
     *
     * @param value 需要转换的日期
     * @return 转换出来的日期字符串
     */
    public static String toString(Date value) {
        if (value == null) {
            return "";
        }
        return SimpleDateFormatThreadLocal.get().format(value);
    }

    /**
     * 转换日期为字符串
     *
     * @param value  需要转换的日期
     * @param format 日期转换格式
     * @return 转换出来的日期字符串
     */
    public static String toString(Date value, String format) {
        if (value == null) {
            return "";
        }
        if (StringUtils.isBlank(format)) {
            return toString(value);
        }
        return SimpleDateFormatThreadLocal.get(format).format(value);
    }

    /**
     * 将外部元的金额转换成 Long的数据
     *
     * @param strPrice 字符串的价格
     * @return 返回以 Long 型的金额
     */
    public static Long priceToLong(String strPrice) {

        if (StringUtils.isEmpty(strPrice)) {
            return null;
        }
        try {
            BigDecimal price = toBigDecimal(strPrice, null);
            if (null == price) {
                return 0L;
            }
            return price.multiply(BigDecimal.valueOf(NumberConstant.PRICE_RATIO)).longValue();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将数据库里面的价格转换到以元为单位
     *
     * @param price 需要转换的价格
     * @return 返回以元为单位的字符串
     */
    public static String convertToYuan(Long price) {
        if (NumberUtil.isNull(price)) {
            return StringUtils.EMPTY;
        }
        if (price.intValue() == 0) {
            return "0";
        }
        return removeZero(convertToBigDecimal(price, AmountUnit.Yuan).toString());
    }

    /**
     * 去掉小数点后面的0
     *
     * @param s
     * @return
     */
    public static String removeZero(String s) {
        if (null == s || s.isEmpty()) {
            return "";
        }
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 返回没有小数点的数字
     *
     * @param price
     * @return
     */
    public static String convertToYuanNoPoint(Long price) {
        if (NumberUtil.isNull(price)) {
            return StringUtils.EMPTY;
        }
        if (price.intValue() == 0) {
            return "0";
        }
        return String.valueOf(convertToBigDecimal(price, AmountUnit.Yuan).intValue());
    }

    /**
     * 将数据库中long类型的金额 转化为分 元 等单位
     *
     * @param price
     * @return
     */
    public static BigDecimal convertToBigDecimal(Long price, AmountUnit unit) {
        BigDecimal result = BigDecimal.ZERO;

        if (null != price && price == 0) {
            return result;
        }

        if (NumberUtil.isNotPositive(price)) {
            return result;
        }
        try {
            switch (unit) {
                case Fen:
                    result = (new BigDecimal(price).divide(BigDecimal.valueOf(NumberConstant.PRICE_RATIO_FEN), NumberConstant.BIGDECIMAL_SCALE, BigDecimal.ROUND_HALF_UP))
                            .setScale(NumberConstant.BIGDECIMAL_FEN_SCALE, BigDecimal.ROUND_HALF_UP);
                    break;
                case Yuan:
                    result = (new BigDecimal(price).divide(BigDecimal.valueOf(NumberConstant.PRICE_RATIO), NumberConstant.BIGDECIMAL_SCALE, BigDecimal.ROUND_HALF_UP))
                            .setScale(NumberConstant.BIGDECIMAL_YUAN_SCALE, BigDecimal.ROUND_HALF_UP);
                    break;
                default:
            }
            return result;
        } catch (Exception ex) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Long转String，为空就返回null
     *
     * @param value
     * @return
     */
    public static String toString(Long value) {
        if (null == value) {
            return null;
        }

        return String.valueOf(value);
    }

    /**
     * 将字符串转换成日期
     *
     * @param value 需要转换的字符串
     * @return 转换出来的日期
     */
    public static Date toDate(String value) throws ParseException {
        if (value.indexOf(':') == -1) {
            value += " 00:00:00";
        }
        try {
            return SimpleDateFormatThreadLocal.get().parse(value);
        } catch (ParseException e) {
            throw new ParseException("转换出错",500);
        }
    }

    /**
     * 将字符串转换成日期
     *
     * @param value  需要转换的字符串
     * @param format 日期转换格式
     * @return 转换出来的日期
     */
    public static Date toDate(String value, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            return toDate(value);
        }
        try {
            return SimpleDateFormatThreadLocal.get(format).parse(value);
        } catch (ParseException e) {
            throw new ParseException("转换出错",500);
        }
    }

    /**
     * 将字符串转换成日期
     *
     * @param value        需要转换的字符串
     * @param format       日期转换格式
     * @param defaultValue 默认日期
     * @return 转换出来的日期
     */
    public static Date toDate(String value, String format, Date defaultValue) {
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        try {
            return StringUtils.isBlank(format) ? toDate(value) : toDate(value, format);
        } catch (ParseException e) {
            return defaultValue;
        }
    }


    /**
     * 将集合转换成数组
     *
     * @param ids 需要转换的IDs
     * @return 转换后的数字数组
     */
    public static Integer[] toIntArray(Iterable<? extends Integer> ids) {
        if (null != ids) {
            Set<Integer> idSet = new HashSet<>();
            ids.forEach(id -> {
                if (NumberUtil.isPositive(id)) {
                    idSet.add(id);
                }
            });

            if (CollectionUtils.isNotEmpty(idSet)) {
                return idSet.toArray(new Integer[idSet.size()]);
            }
        }
        return new Integer[0];
    }

    /**
     * 将加密数字，解密成 Int 数字
     *
     * @param encryptedIds 加密ID数字
     * @return 解密后的数字
     */
    public static Integer[] toIntArray(String... encryptedIds) {
        if (ArrayUtils.isEmpty(encryptedIds)) {
            return new Integer[0];
        }

        Set<Integer> intIds = Arrays.stream(encryptedIds)
                .filter(StringUtils::isNotBlank)
                .map(id -> (int) NumberUtil.uncompress(id))
                .collect(Collectors.toSet());

        return CollectionUtils.isEmpty(intIds)
                ? new Integer[0]
                : intIds.toArray(new Integer[intIds.size()]);
    }

    /**
     * 将加密数字，解密成 Int 数字
     *
     * @param ids 逗号分割数字字符串
     * @return 解密后的数字
     */
    public static Integer[] toIntArray(String ids) {
        if (StringUtils.isBlank(ids)) {
            return null;
        }
        return Arrays.stream(ids.split(",")).map(id -> toInteger(id, null)).filter(NumberUtil::isPositive).toArray(Integer[]::new);
    }

    /**
     * 将加密数字，解密成 Long 数字
     *
     * @param ids 逗号分割数字字符串
     * @return 解密后的数字
     */
    public static Long[] toLongArray(String ids) {
        if (StringUtils.isBlank(ids)) {
            return null;
        }
        return Arrays.stream(ids.split(",")).map(id -> toLong(id, null)).filter(NumberUtil::isPositive).toArray(Long[]::new);
    }

    /**
     * 将集合转换成数组
     *
     * @param ids 需要转换的IDs
     * @return 转换后的数字数组
     */
    public static Long[] toLongArray(Iterable<? extends Long> ids) {
        if (null != ids) {
            Set<Long> idSet = new HashSet<>();
            ids.forEach(id -> {
                if (NumberUtil.isPositive(id)) {
                    idSet.add(id);
                }
            });

            if (CollectionUtils.isNotEmpty(idSet)) {
                return idSet.toArray(new Long[idSet.size()]);
            }
        }
        return new Long[0];
    }

    /**
     * 将加密数字，解密成 Int 数字
     *
     * @param encryptedIds 加密ID数字
     * @return 解密后的数字
     */
    public static Long[] toLongArray(String... encryptedIds) {
        if (ArrayUtils.isEmpty(encryptedIds)) {
            return new Long[0];
        }

        return Arrays.stream(encryptedIds)
                .filter(StringUtils::isNotBlank)
                .map(NumberUtil::uncompress)
                .filter(NumberUtil::isPositive)
                .distinct()
                .toArray(Long[]::new);
    }

    public static Long getSysPrice(String amount) {
        return Long.valueOf(changeY2F(amount)) * NumberConstant.PRICE_RATIO_FEN;
    }

    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static String changeY2F(String amount) {
        String currency = amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong.toString();
    }

    /**
     * 将分转化成元
     *
     * @param money
     * @return
     */
    public static String fenConvertToYuan(String money) {
        if (money == null) {
            return "0.00";
        }
        try {
            Long fen = Long.valueOf(money);
            return BigDecimal.valueOf(fen).divide(new BigDecimal(100)).setScale(2).toString();
        } catch (Exception e) {
            return "0.00";
        }

    }

}
