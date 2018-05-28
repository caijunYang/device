package com.itplayer.core.system.query;

import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.system.entity.SystemLog;

public class SystemLogQueryModel extends BaseQueryModel<SystemLog> {
    private String name;

    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
