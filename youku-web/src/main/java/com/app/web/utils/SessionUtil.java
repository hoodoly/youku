package com.app.web.utils;

import com.app.web.dto.SessionDto;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">xiahj</a>
 * Date: 16/9/21
 */
@Component
public final class SessionUtil {

    @Autowired
    private JedisPool jedisPool;

    public SessionDto getSession(String sessionId){

        RuntimeSchema<SessionDto> schema = RuntimeSchema.createFrom(SessionDto.class);
        byte[] bytes = jedisPool.getResource().get(sessionId.getBytes());
        SessionDto session = schema.newMessage();

        if (bytes!=null){
            ProtostuffIOUtil.mergeFrom(bytes, session, schema);
        }
        return session;
    }

    public void putSession(SessionDto session){
        RuntimeSchema<SessionDto> schema = RuntimeSchema.createFrom(SessionDto.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        jedisPool.getResource().setex(session.getSessionId().getBytes(), 30*60, ProtostuffIOUtil.toByteArray(session, schema, buffer));

    }
}
