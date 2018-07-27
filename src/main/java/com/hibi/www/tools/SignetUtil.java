package com.hibi.www.tools;


import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

public class SignetUtil {

    private static final String SECRET_KEY = "ea6efad3f42c44f33cb64b9fc9b70098";
    private static final String APP_SECRET_KEY = "47a7560d5f382c5670fb0643a86cbfd0";
    private static final String APP_KEY = "0052702304a357e40e6727ef30452303";
    private static final String AUTH_TYPE = "1";

    private static void sortMapByValues(Map<String, String> aMap){
        Set<Map.Entry<String,String>> mapEntries = aMap.entrySet();
//        System.out.println("Values and Keys before sorting ");
//        for(Map.Entry<String,String> entry : mapEntries)
//            System.out.println(entry.getKey() + " - "+ entry.getValue());
        //use LinkedList to sort, because insertion of elements in linked list is faster than ArrayList.
        List<Map.Entry<String,String>> aList = new LinkedList<Map.Entry<String,String>>(mapEntries);
        // sorting the List
        Collections.sort(aList, new Comparator<Map.Entry<String,String>>(){
            @Override
            public int compare(Map.Entry<String, String> ele1, Map.Entry<String, String> ele2){
                return ele1.getKey().compareTo(ele2.getKey());
            }
        });
        // Storing the list into Linked HashMap to preserve the order of insertion.
        Map<String,String> aMap2 = new LinkedHashMap<String, String>();
        for(Map.Entry<String,String> entry: aList){
            aMap2.put(entry.getKey(), entry.getValue());
        }
        // printing values after sorting of map
//        System.out.println("Values and Keys after sorting ");
//        for(Map.Entry<String,String> entry : aMap2.entrySet()){
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
    }



    public static String getSignet(){
        String value = "",sign="";
        Long signed = System.currentTimeMillis();
        Map<String,String> params = new HashMap<String,String>();
        params.put("app_key",APP_KEY);
        params.put("auth_type",AUTH_TYPE);
        params.put("signed_at",String.valueOf(signed));
        params.put("webinar_id","866188553");
        sortMapByValues(params);
        for(Map.Entry<String,String> entry : params.entrySet()){
            value+=entry.getKey()+entry.getValue();
        }
        sign = getMD5(SECRET_KEY+value+SECRET_KEY);
        System.out.print("md5:"+sign);
        return  sign;
    }


    /**
     * 对字符串md5加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        getSignet();
    }
}
