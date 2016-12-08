package com.sitewhere.measurefilter.mvc.service;

import java.util.List;

/**
 * BaseDao interface
 *
 * @author  Joeg
 */
public interface IBaseService<T> {

    /**
     * batch insert interface
     *
     * @param list
     */
    public void batchInsert(List<T> list);

    /**
     * batch update interface
     *
     * @param list
     */
    public void batchUpdate(List<T> list);

    /**
     * batch delete interface
     *
     * @param list
     */
    public void batacDelete(List<T> list);

}
