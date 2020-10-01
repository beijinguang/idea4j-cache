package com.idea4j.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjinguang
 * 全局缓存
 */
public class CacheGlobal {

    public static ConcurrentHashMap<String, Idea4jCache> concurrentHashMap = new ConcurrentHashMap<>();

}
