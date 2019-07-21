//package sample;
//
//import com.google.gson.Gson;
//import com.*;
//import com.google.gson.JsonObject;
//import com.google.gson.reflect.TypeToken;
//
//import com.google.gson.stream.JsonReader;
//
//
//
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Ese on 12/5/2015.
// */
//public class Melan {
//public static void main(String[] args) {
//    try {
//        Map<String, String> map=new HashMap<>();
//
//        Map<String, Map<String, String>> nnmap=new HashMap<>();
//
//        map.put("name", "Bruno");
//        map.put("password", "password@1");
//
//        nnmap.put("id", map);
//
//        Gson oil=new Gson();
//        String rice=oil.toJson(nnmap);
//        System.out.println(rice);
//        String nmap=oil.toJson(map);
//        System.out.println(nmap);
//
//
//
//
//
//        Gson gson=new Gson();
//        Getclass gs=new Getclass();
//
//        for(int i=0; i<5; i++) {
//            gs.setPassword("password@1");
//            gs.setUsername("Bruno" + i);
//            String me = gson.toJson(gs);
//            System.out.println(me);
//
//        }
//
//    }
//    catch(Exception e)
//    {
//        e.printStackTrace();
//    }
//}
//
//}
//
