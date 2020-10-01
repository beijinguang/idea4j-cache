package com.idea4j.cache;

import org.apache.commons.lang3.StringUtils;

public class CacheUtils{

    public void put(String key, Object value, long expire) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        // 当缓存存在时，更新缓存
        if (CacheGlobal.concurrentHashMap.containsKey(key)) {
            Idea4jCache idea4jCache = CacheGlobal.concurrentHashMap.get(key);
            idea4jCache.setHitCount(idea4jCache.getHitCount()+1);
            idea4jCache.setWriteTime(System.currentTimeMillis());
            idea4jCache.setLastTime(System.currentTimeMillis());
            idea4jCache.setExpireTime(expire);
            idea4jCache.setValue(value);
            return;
        }

        Idea4jCache idea4jCache = new Idea4jCache();

        idea4jCache.setKey(key);
        idea4jCache.setValue(value);
        idea4jCache.setWriteTime(System.currentTimeMillis());
        idea4jCache.setLastTime(System.currentTimeMillis());
        idea4jCache.setHitCount(1);
        idea4jCache.setExpireTime(expire);
        CacheGlobal.concurrentHashMap.put(key, idea4jCache);
    }

    public Object get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        if (CacheGlobal.concurrentHashMap.isEmpty()) {
            return null;
        }
        if (!CacheGlobal.concurrentHashMap.containsKey(key)) {
            return null;
        }

        Idea4jCache idea4jCache = CacheGlobal.concurrentHashMap.get(key);
        if (idea4jCache == null) {
            return null;
        }
        //惰性删除
        long timeoutTime = System.currentTimeMillis()-idea4jCache.getWriteTime();

        if (idea4jCache.getExpireTime() <= timeoutTime) {
            CacheGlobal.concurrentHashMap.remove(key);
            return null;
        }
        idea4jCache.setHitCount(idea4jCache.getHitCount() + 1);
        idea4jCache.setLastTime(System.currentTimeMillis());
        return idea4jCache.getValue();
    }
}
