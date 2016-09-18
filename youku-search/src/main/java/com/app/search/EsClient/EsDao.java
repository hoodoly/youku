package com.app.search.EsClient;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/17
 */
public class EsDao {

    public static void createIndex(String index){
        Client client = EsClientFactory.getTransportClient();
        client.admin().indices().prepareCreate(index).execute().actionGet();
    }

    public static void deleteIndex(String index){
        Client client = EsClientFactory.getTransportClient();
        client.admin().indices().prepareDelete(index).execute().actionGet();
    }

    public static void createMapping(String indices,String mappingType)throws Exception{
        new XContentFactory();
        XContentBuilder builder= XContentFactory.jsonBuilder()
                .startObject()
                .startObject(indices)
                .startObject("properties")
                .startObject("id").field("type", "integer").field("store", "yes").endObject()
                .startObject("kw").field("type", "string").field("store", "yes").field("indexAnalyzer", "ik").field("searchAnalyzer", "ik").endObject()
                .startObject("edate").field("type", "date").field("store", "yes").field("indexAnalyzer", "ik").field("searchAnalyzer", "ik").endObject()
                .endObject()
                .endObject()
                .endObject();
        Client client = EsClientFactory.getTransportClient();
        PutMappingRequest mapping = Requests.putMappingRequest(indices).type(mappingType).source(builder);
        client.admin().indices().putMapping(mapping).actionGet();
        client.close();
    }
}
