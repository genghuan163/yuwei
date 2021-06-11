package com.lfd.yunwei.framework.constants;


import org.springframework.http.MediaType;

public interface WebConstant {
    // API 返回的数据格式, 请用 MEDIA_JSON_TYPE 替换
    @Deprecated
    String MEDIA_TYPE = MediaType.APPLICATION_JSON_UTF8_VALUE;

    // API 返回JSON格式
    String MEDIA_JSON_TYPE = MediaType.APPLICATION_JSON_UTF8_VALUE;

    // API 返回纯文本格式
    String MEDIA_TEXT_TYPE = MediaType.TEXT_PLAIN_VALUE;


    String GLOBAL_PAGE_URL = "com.kodai.mall";
}
