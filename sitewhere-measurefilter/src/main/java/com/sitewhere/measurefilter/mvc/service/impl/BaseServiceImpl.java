package com.sitewhere.measurefilter.mvc.service.impl;

import com.sitewhere.measurefilter.mvc.service.IBaseService;

import java.util.List;

/**
 * Created by CQ on 2016/11/21.
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T>{
    @Override
    public void batchInsert(List<T> list) {

    }

    @Override
    public void batchUpdate(List<T> list) {

    }

    @Override
    public void batacDelete(List<T> list) {

    }
}
