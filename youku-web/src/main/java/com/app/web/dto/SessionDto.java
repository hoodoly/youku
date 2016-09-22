package com.app.web.dto;

import lombok.Data;

import java.util.Date;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">xiahj</a>
 * Date: 2016/9/22
 */
@Data
public class SessionDto {
    private String sessionId;
    private Long userId;
    private String username;
    private String phone;
    private Boolean isValid;
    private Date expiredTime;
}
