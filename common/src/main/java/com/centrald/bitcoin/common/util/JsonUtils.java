package com.centrald.bitcoin.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;

/**
 * @author jiayan
 */
public class JsonUtils {

    // fastjson 的序列化配置
    private final static SerializeConfig fastjson_serializeConfig_time = new SerializeConfig();

    // 默认打出所有属性(即使属性值为null)|属性排序输出,为了配合历史记录
    private final static SerializerFeature[] fastJsonFeatures = {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteEnumUsingToString,
            SerializerFeature.SortField,
            SerializerFeature.DisableCircularReferenceDetect
    };

    static {
        fastjson_serializeConfig_time.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    public static String toJSONString(Object object) {
        if (null == object) {
            return "";
        }
        return JSON.toJSONString(object, fastjson_serializeConfig_time, fastJsonFeatures);

    }
}
