package com.app.activity.enums;

/**
 * Desc:
 * Author: <a href="xiahj@terminus.io">夏鸿杰</a>
 * Date: 16/9/20
 */
public enum ApplyStatus {
    SYSTEM_FAILED(-1,"系统出错"),
    DUPLICATE_APPLY(0, "重复申请"),
    SUCCESS_APPLY(1, "申请成功");

    private final int value;

    private final String description;

    private ApplyStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int value() {
        return this.value;
    }


    @Override
    public String toString() {
        return description;
    }
}
