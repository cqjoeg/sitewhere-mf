package com.sitewhere.measurefilter;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by CQ on 2016/11/18.
 */
public class MFMAIN {


    public static void main(String[] args) {
//
//        MFField a = new MFField();
//        a.setType("aaa");
//        List<String> tmplist = new ArrayList<String>();
//        tmplist.add("bbb");
//        tmplist.add("ddd");
//        a.setKey(tmplist);
//        Gson gson = new Gson();
//
//        String jsonStr = gson.toJson(a);
//        System.out.println(jsonStr);
//
//        Gson gson1 = new Gson();
//        MFField fromJsonStr = gson1.fromJson(jsonStr, MFField.class);

        List<String> list = new ArrayList<>();
        list.add("B");
        list.add("D");

        Map<String, Double> tmpMap = new HashMap<>();
        tmpMap.put("A", 1.0);
        tmpMap.put("B", 2.0);
        tmpMap.put("C", 3.0);
        tmpMap.put("D", 4.0);
        tmpMap.put("E", 5.0);

        for (Map.Entry<String, Double> s : tmpMap.entrySet()) {
            System.out.println("键值对:" + s);
        }

//
//        for (String key : tmpMap.keySet()) {
//            if (!list.contains(key)) {
//               //不包含该字符串
//                tmpMap.remove(key);
//            }
//        }

        Iterator iterator = tmpMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();
            if (!list.contains(key)) {
                iterator.remove();
            }
        }

        System.out.println("*****");
        for (Map.Entry<String, Double> s : tmpMap.entrySet()) {
            System.out.println("键值对:" + s);
        }


    }

}

class MFField {
    private String type;
    private List<String> key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }
}