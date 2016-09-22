package com.app.user.dao;

import com.app.user.module.User;
import io.terminus.common.mysql.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">xiahj</a>
 * Date: 16/9/21
 */

@Repository
public class UserDao extends MyBatisDao<User> {

    public List<User> queryByParams(Map<String, Object> param){
        return this.getSqlSession().selectList("queryByParams", param);
    }

}
