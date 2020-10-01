package com.idea4j.cache;

public class CacheTest {

    public static void main(String[] args) {
        CacheUtils cache = new CacheUtils();
        //  存入缓存
        cache.put("key1", "markee", 1000);
        //  查询缓存
        String val = (String) cache.get("key1");
        System.out.println(val);
        //  查询不存在的缓存
        String noval = (String) cache.get("kate");
        System.out.println(noval);
    }
}
