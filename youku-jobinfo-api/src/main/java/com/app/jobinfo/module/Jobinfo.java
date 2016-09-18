package com.app.jobinfo.module;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/8/29
 */
@Data
public class Jobinfo implements Serializable{

    private Long id;
    private Long companyId;
    private String companyName;

    private String jobCategory;
    private String jobName;
    private Integer status;

    private String city;
    private Integer salaryHigh;
    private Integer salaryLow;
    private String experience;
    private String education;

    private String content;
    private String detail;
    private String address;
    private String extra;
    private Date createAt;
    private Date updateAt;

}
