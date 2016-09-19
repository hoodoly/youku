package com.app.search.service;

import com.app.jobinfo.module.Jobinfo;
import com.app.jobinfo.service.JobinfoReadService;
import com.app.search.EsClient.EsClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.collect.Maps;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/8/29
 */
@Slf4j
@Service
public class SearchJobinfoWriteServiceImpl implements SearchJobinfoWriteService{

    @Autowired
    JobinfoReadService jobinfoReadService;


    //索引数据
    public boolean indicesJobInfo(){

        Map<String, Object> param = Maps.newHashMap();
        List<Jobinfo> jobinfos = jobinfoReadService.getJobinfosByParam(param);

        //获取客户端
        Client client = EsClientFactory.getTransportClient();
        SearchResponse searchResponse = client.prepareSearch("lagou").setTypes("jobInfo").setSize(Integer.MAX_VALUE).execute().actionGet();
        SearchHit hits[] = searchResponse.getHits().getHits();
        for (SearchHit searchHit : hits){
            DeleteResponse response = client.prepareDelete("lagou", "jobInfo", searchHit.getId()).execute().actionGet();
        }

        try {
            for (Jobinfo jobinfo : jobinfos) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonValue = mapper.writeValueAsString(jobinfo);
                IndexResponse indexResponse = client.prepareIndex("lagou", "jobInfo", jobinfo.getId().toString()).setSource(jsonValue).execute().actionGet();
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("index.jobInfo.failed");
        }
        return true;
    }
}
