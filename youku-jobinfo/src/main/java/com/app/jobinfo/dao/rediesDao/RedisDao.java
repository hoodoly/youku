package com.app.jobinfo.dao.rediesDao;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import io.terminus.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/19
 */
public abstract class RedisDao<T> {

    @Autowired
    JedisPool jedisPool;


    public T get(Long id){
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        RuntimeSchema<T> schema = RuntimeSchema.createFrom(clazz);
        String key = clazz.getName() + ":" + id;
        byte[] bytes = jedisPool.getResource().get(key.getBytes());
        T t = null;
        if (bytes!=null){
            t = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, t, schema);
        }
        return t;
    }

    public boolean put(T t){
        try {
            Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            RuntimeSchema<T> schema = RuntimeSchema.createFrom(clazz);
            Method method = clazz.getDeclaredMethod("getId");
            Long objId = (Long) method.invoke(t);
            String key = clazz.getName() + ":" + objId;
            LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
            jedisPool.getResource().setex(key.getBytes(), 60*60, ProtostuffIOUtil.toByteArray(t, schema, buffer));
        }catch (Exception e){
            throw new ServiceException("put.obj.to.redis.failed");
        }
        return true;
    }
}
