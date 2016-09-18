package com.app.search.EsClient;

import com.google.common.collect.Maps;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/17
 */
public class EsClientFactory {

    static Map<String, String> map = Maps.newHashMap();
    static Settings settings = ImmutableSettings.settingsBuilder().put(map)
            .put("cluster.name", "lagou").put("client.transport.sniff", true).build();
    private static TransportClient client;

    static {
        try {
            Class<?> clientClass = Class.forName(TransportClient.class.getName());
            Constructor<?> constructor = clientClass.getDeclaredConstructor(Settings.class);
            constructor.setAccessible(true);
            client = (TransportClient) constructor.newInstance(settings);
            client.addTransportAddress(new InetSocketTransportAddress("192.168.227.128", 9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized TransportClient getTransportClient() {
        return client;
    }

}
