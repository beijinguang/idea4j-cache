package com.idea4j.cache;


import java.util.concurrent.TimeUnit;


/**
  * 过期缓存检测线程
  */
public class ExpireThread implements Runnable {


    @Override
    public void run() {
        while (true) {
            try {
                //每10秒检查一次
                TimeUnit.SECONDS.sleep(10);
                //缓存检查和清除方法
                expireCache();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void expireCache() {

        for (String key : CacheGlobal.concurrentHashMap.keySet()) {

            Idea4jCache idea4jCache = CacheGlobal.concurrentHashMap.get(key);

            long timoutTime = System.currentTimeMillis() - idea4jCache.getWriteTime();
            if (idea4jCache.getExpireTime() > timoutTime) {
                continue;
            }
            CacheGlobal.concurrentHashMap.remove(key);

        }
    }
}
