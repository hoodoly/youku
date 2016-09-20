package com.app.activity.module;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/20
 */
@Data
public class ActivityApply implements Serializable {
    Long id;
    Long activityId;
    String activityName;
    String phone;
    Date createAt;
    Date updateAt;

}
