package com.lfd.yunwei.framework.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;


/**
 * 功能描述:
 * @Param:
 * @Return:
 * @Author: Hogon.Gh
 * @Date: 2021/1/13 17:39
 */
@JsonFilter("CustomerJsonFilter")
public class CustomerJsonFilter extends FilterProvider {

    /**
     * 需要保留的字段
     */
    private Map<Class<?>, Set<String>> includeMap = Maps.newHashMap();
    /**
     * 需要过滤的字段
     */
    private Map<Class<?>, Set<String>> excludeMap = Maps.newHashMap();


    public void include(Class<?> clz,String [] fields) {
        Set<String> existedFields = includeMap.getOrDefault(clz, Sets.newHashSet());
        existedFields.addAll(Arrays.asList(fields));
        includeMap.put(clz,existedFields);
    }


    public void exclude(Class<?> clz,String [] fields) {
        Set<String> existedFields = excludeMap.getOrDefault(clz, Sets.newHashSet());
        existedFields.addAll(Arrays.asList(fields));
        excludeMap.put(clz,existedFields);
    }
    @Override
    public BeanPropertyFilter findFilter(Object filterId) {
        throw new UnsupportedOperationException("Access to deprecated filters not support");
    }

    @Override
    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {
        return new SimpleBeanPropertyFilter(){
            @Override
            public void serializeAsField(Object pojo, JsonGenerator generator, SerializerProvider provider, PropertyWriter writer) throws Exception {
                if (apply(pojo.getClass(),writer.getName())) {
                    writer.serializeAsField(pojo,generator,provider);
                } else if (!generator.canOmitFields()) {
                    writer.serializeAsOmittedField(pojo,generator,provider);
                }
            }
        };
    }



    private boolean apply(Class<?> type,String name) {
        Set<String> includes = includeMap.get(type);
        Set<String> excludes = excludeMap.get(type);
        if (includes != null && includes.contains(name)) {
            return true;
        } else  if (excludes != null && excludes.contains(name)) {
            return true;
        } else if (includes == null && excludes == null) {
            return true;
        }
        return false;
    }
}
