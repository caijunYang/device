package com.itplayer.core.system.query;

import com.itplayer.core.base.query.BaseQueryModel;
import com.itplayer.core.system.entity.Area;

import java.util.Date;

public class AreaQueryModel extends BaseQueryModel<Area> {

//    public ExampleMatcher buildMatcher() {
//        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
//        if (StrUtils.isNotNull(area.getAreaName())) {
//            exampleMatcher = ExampleMatcher.matching().withMatcher("areaName", ExampleMatcher.GenericPropertyMatchers.contains());
//        }
//
//        return exampleMatcher;
//    }
//
//    public Example<Area> buildExample() {
//        Example<Area> example = Example.of(area, buildMatcher());
//        return example;
//    }

    private String areaName;

    private Date startDate;

    private Date endDate;



    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


}
