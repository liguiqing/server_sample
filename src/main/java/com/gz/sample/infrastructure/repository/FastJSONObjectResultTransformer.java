package com.gz.sample.infrastructure.repository;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将EntityManager查询结果转换为 {@link JSONObject}
 *
 * @author liguiqing
 */
public class FastJSONObjectResultTransformer extends AliasedTupleSubsetResultTransformer {

    private final static Pattern CAMEL_PATTERN = Pattern.compile("([A-Za-z\\d]+)(_)?");
    private final static Pattern UNDERLINE_PATTERN = Pattern.compile("[A-Z]([a-z\\d]+)?");

    public final static FastJSONObjectResultTransformer INSTANCE = new FastJSONObjectResultTransformer();

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        JSONObject result = new JSONObject(tuple.length);
        for (int i = 0; i < tuple.length; i++) {
            String alias = aliases[i];
            if (alias != null) {
                Object value = tuple[i];
                if(value instanceof BigInteger){
                    value = value.toString();
                }
                result.put(underlineToCamel(alias.toLowerCase(),true), value);
            }
        }
        return result;
    }

    /**
     * 下划线转驼峰法
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    private String underlineToCamel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        var sb = new StringBuffer();

        var matcher = CAMEL_PATTERN.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camelToUnderline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();

        Matcher matcher = UNDERLINE_PATTERN.matcher(line);

        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();

    }

    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }


}
