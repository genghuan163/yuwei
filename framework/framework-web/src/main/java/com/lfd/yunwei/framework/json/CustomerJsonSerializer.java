package com.lfd.yunwei.framework.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.lfd.yunwei.framework.annotation.JsonResult;
import com.lfd.yunwei.framework.annotation.JsonResults;
import com.lfd.yunwei.framework.common.constants.DateConstant;
import com.lfd.yunwei.framework.common.convert.ConvertUtils;
import com.lfd.yunwei.framework.common.utils.SimpleDateFormatThreadLocal;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @author Hogon.Gh
 * @description: json返回值 序列化拦截器
 */
public class CustomerJsonSerializer {
    private final String INCLUDE_NAME = "yunwei_json_include";
    private final String EXCLUDE_NAME = "yunwei_json_exclude";
    private final LongDateConverter LONG_TIME_OBJECT_MAPPER = new LongDateConverter();
    private final ShortDateConverter SHORT_TIME_OBJECT_MAPPER = new ShortDateConverter();

    private CustomerJsonFilter propertyFilter = new CustomerJsonFilter();
    private ObjectMapper objectMapper = new ObjectMapper();

    @JsonFilter("yunwei_json_include")
    interface DynamicInclude {
    }

    @JsonFilter("yunwei_json_exclude")
    interface DynamicExclude {
    }

    public void filter(Class<?> clz, boolean shortDateFormat, String[] includes, String[] excludes) {
        if (clz == null) {
            return;
        }

        //需要包含的字段
        if (ArrayUtils.isNotEmpty(includes)) {
            propertyFilter.include(clz, includes);
        }

        //需要排除的字段
        if (ArrayUtils.isNotEmpty(excludes)) {
            propertyFilter.exclude(clz, excludes);
        }

        objectMapper.setDateFormat(shortDateFormat ? SimpleDateFormatThreadLocal.get(DateConstant.DATE_FORMAT) : SimpleDateFormatThreadLocal.get(DateConstant.DATE_TIME_FORMAT));

        objectMapper.addMixIn(clz, propertyFilter.getClass());
    }



    public void serializer(Class<?> clz, boolean shortDateFormat, String[] includes, String[] excludes) {
        if (clz == null) {
            return;
        }

        ObjectMapper objectMapper = shortDateFormat ? SHORT_TIME_OBJECT_MAPPER : LONG_TIME_OBJECT_MAPPER;
        if (ArrayUtils.isNotEmpty(includes)) {
            objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter(INCLUDE_NAME,
                    SimpleBeanPropertyFilter.filterOutAllExcept(includes)));
            objectMapper.addMixIn(clz, DynamicInclude.class);
        } else if (ArrayUtils.isNotEmpty(excludes)) {
            objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter(EXCLUDE_NAME,
                    SimpleBeanPropertyFilter.serializeAllExcept(excludes)));
            objectMapper.addMixIn(clz, DynamicExclude.class);
        }
    }
    String toJson(Object object) throws JsonProcessingException {
        objectMapper.setFilterProvider(propertyFilter);
        return objectMapper.writeValueAsString(object);
    }
    public String toJson(Object object, boolean shortDateFormat) throws JsonProcessingException {
        ObjectMapper objectMapper = shortDateFormat ? SHORT_TIME_OBJECT_MAPPER : LONG_TIME_OBJECT_MAPPER;
        return objectMapper.writeValueAsString(object);
    }

    // 短时间格式：yyyy-MM-dd
    static class ShortDateConverter extends ObjectMapper {
        ShortDateConverter() {
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(Date.class, new JsonSerializer<Date>() {
                @Override
                public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
                    jsonGenerator.writeString(ConvertUtils.toString(value, DateConstant.DATE_FORMAT));
                }
            });
            this.registerModule(simpleModule);
        }
    }

    // 长时间格式：yyyy-MM-dd HH:mm:ss
    static class LongDateConverter extends ObjectMapper {
        LongDateConverter() {
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(Date.class, new JsonSerializer<Date>() {
                @Override
                public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
                    jsonGenerator.writeString(ConvertUtils.toString(value, DateConstant.DATE_TIME_FORMAT));
                }
            });
            this.registerModule(simpleModule);
        }
    }

    void filter(JsonResult rule) {
        this.serializer(rule.type(), rule.shortDateFormat(), rule.include(), rule.exclude());
    }

    void filter(JsonResults rule) {
        Stream.of(rule.value()).forEach(this::filter);
    }
}
