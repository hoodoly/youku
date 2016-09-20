package com.app.activity.dao;

import com.app.activity.module.ActivityApply;
import io.terminus.common.mysql.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/20
 */
@Repository
public class ActivityApplyDao extends MyBatisDao<ActivityApply>{

    public void applyActivityProcedure(Map<String, Object> param){
        this.getSqlSession().selectOne("applyActivityProcedure", param);
    }

}
