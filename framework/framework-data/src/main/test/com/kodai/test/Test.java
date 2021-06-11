package com.kodai.test;

import com.alibaba.fastjson.JSONObject;

public class Test {


    public static void main(String[] args) {



//        String json = "[{\"dataObject\":[{\"PAY_ID\":\"\",\"PAY_DT\":\"2020-09-27 15:36:34.0\",\"REAL_TRADE_STATUS\":\"PAYING\",\"SELLS_ID\":\"\",\"PAY_AMT\":\"30516\",\"CALLBACK_DT\":\"2020-10-29 16:27:23.0\"},{\"PAY_ID\":\"2020102222001452611430136577\",\"PAY_DT\":\"2020-10-22 11:03:50.0\",\"REAL_TRADE_STATUS\":\"SUCCESS\",\"SELLS_ID\":\"20102210411084002551934\",\"PAY_AMT\":\"0.01\",\"CALLBACK_DT\":\"2020-10-29 16:27:23.0\"},{\"PAY_ID\":\"2020102222001452611430192237\",\"PAY_DT\":\"2020-10-22 12:21:00.0\",\"REAL_TRADE_STATUS\":\"SUCCESS\",\"SELLS_ID\":\"20102212195016002561479\",\"PAY_AMT\":\"0.01\",\"CALLBACK_DT\":\"2020-10-29 16:27:23.0\"},{\"PAY_ID\":\"\",\"PAY_DT\":\"2020-10-22 12:22:31.0\",\"REAL_TRADE_STATUS\":\"PAYING\",\"SELLS_ID\":\"20102212223020002532072\",\"PAY_AMT\":\"0.01\",\"CALLBACK_DT\":\"2020-10-29 16:27:23.0\"},{\"PAY_ID\":\"\",\"PAY_DT\":\"2020-10-22 12:57:32.0\",\"REAL_TRADE_STATUS\":\"PAYING\",\"SELLS_ID\":\"20102212573261002588572\",\"PAY_AMT\":\"0.01\",\"CALLBACK_DT\":\"2020-10-29 16:27:23.0\"},{\"PAY_ID\":\"\",\"PAY_DT\":\"2020-10-22 13:06:19.0\",\"REAL_TRADE_STATUS\":\"PAYING\",\"SELLS_ID\":\"20102213061835002535834\",\"PAY_AMT\":\"0.01\",\"CALLBACK_DT\":\"2020-10-29 16:27:23.0\"},{\"PAY_ID\":\"\",\"PAY_DT\":\"2020-10-22 13:07:19.0\",\"REAL_TRADE_STATUS\":\"PAYING\",\"SELLS_ID\":\"20102212223020002564711\",\"PAY_AMT\":\"0.01\",\"CALLBACK_DT\":\"2020-10-29 16";
        String json = "{\n" +
                "    \"headers\":{\n" +
                "\n" +
                "    },\n" +
                "    \"body\":{\n" +
                "        \"timestamp\":1603935917290,\n" +
                "        \"status\":500,\n" +
                "        \"error\":\"Internal Server Error\",\n" +
                "        \"message\":\"org/apache/xmlbeans/XmlObject\",\n" +
                "        \"path\":\"/merchandise/product/cloud/order/Ticket/excel\"\n" +
                "    },\n" +
                "    \"statusCodeValue\":500,\n" +
                "    \"statusCode\":\"INTERNAL_SERVER_ERROR\"\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(json);
        String body = jsonObject.getString("body");
        System.out.println(body);


    }
}
