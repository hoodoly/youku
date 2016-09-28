package com.app.search.service;

import com.app.jobinfo.module.Jobinfo;
import com.app.search.EsClient.EsClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/3
 */

@Service
@Slf4j
public class SearchJobinfoReadServiceImpl implements SearchJobinfoReadService{

    public List<Jobinfo> search(String keyWord){
        List<Jobinfo> jobInfos = Lists.newArrayList();
        try {
            Client client = EsClientFactory.getTransportClient();
            SearchResponse response = client.prepareSearch("lagou")
                    .setTypes("jobInfo")
                    //.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    //.setQuery(QueryBuilders.multiMatchQuery("java", "jobName", "city", "companyName"))       // Query
                    //.setQuery(QueryBuilders.matchQuery("city", "杭州"))
                    //.setPostFilter(FilterBuilders.rangeFilter("age").from(12).to(18))   // Filter
                    .setQuery(QueryBuilders.boolQuery().should(QueryBuilders.multiMatchQuery(keyWord, "jobName")).should(QueryBuilders.multiMatchQuery(keyWord, "city")))
                    .setFrom(0).setSize(10).setExplain(true)   // page
                    .execute()
                    .actionGet();

            SearchHit hits[] = response.getHits().getHits();

            if (hits!=null) {
                for (SearchHit hit : hits) {

                    ObjectMapper mapper = new ObjectMapper();
                    Jobinfo jobInfo = mapper.readValue(hit.getSourceAsString(), Jobinfo.class);
                    jobInfos.add(jobInfo);
                }
            }
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }
        return jobInfos;
    }
}
