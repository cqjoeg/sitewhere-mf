package com.sitewhere.measurefilter.spi;

import org.hibernate.exception.DataException;

import java.sql.SQLException;

/**
 * Created by CQ on 2016/11/21.
 */
public class MFSqlException extends DataException{

    /** Serial version UID */
    private static final long serialVersionUID = 1L;

    /** error msg*/
    private String errorMsg;

    public MFSqlException(String message, SQLException root) {
        super(message, root);
    }

    public MFSqlException(String message, SQLException root, String sql) {
        super(message, root, sql);
    }

    public MFSqlException(String message, SQLException root, String sql, String errorMsg) {
        super(message, root, sql);
        this.errorMsg = errorMsg;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

}
