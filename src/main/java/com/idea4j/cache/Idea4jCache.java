package com.idea4j.cache;


import lombok.Getter;
import lombok.Setter;

/**
 * @author wangjinguang
 */
@Getter
@Setter
public class Idea4jCache implements Comparable<Idea4jCache> {

    /**
     * 缓存key值
     */
    private Object key;

    /**
     * 缓存value值
     */
    private Object value;

    /**
     * 缓存最后访问时间
     */
    private long lastTime;

    /**
     * 缓存创建时间
     */
    private long writeTime;

    /**
     * 缓存失效时间
     */
    private long expireTime;

    /**
     * 缓存命中次数
     */
    private Integer hitCount;



    @Override
    public int compareTo(Idea4jCache o) {
        return hitCount.compareTo(o.hitCount);
    }
}
