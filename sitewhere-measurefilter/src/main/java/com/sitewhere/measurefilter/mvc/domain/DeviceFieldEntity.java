package com.sitewhere.measurefilter.mvc.domain;

import com.sitewhere.spi.device.field.domain.IDeviceFieldEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * DeviceField 设备属性包括 measeruments 定义，和 alert范围的定义
 *
 * @author Joeg
 */
@Entity
@Table(name = "tb_field")
public class DeviceFieldEntity implements IDeviceFieldEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "hardwareid", nullable = false)
    private String hardwareid;

    /**
     * This column represents Field's type
     *
     * 1.Measurements
     * 2.Alert
     */
    @Column(name = "type", nullable = false, updatable = false, length = 50)
    private String type;

    @Column(name = "comments", length = 50)
    private String comments;

    @Temporal(TemporalType.DATE)
    @Column(name = "createddate", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createddate;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    /**
     * This column represents’ Field's Integerface  structs
     *
     * example:1.measurements:  {"type":"measurements","key":[aaa,bbb]}
     *         2.alert:         {"type":"alert","ranges":[{from:10,to:40},{from:30,to:20}]}
     * */
    @Column(name = "definition", nullable = true)
    private String definition;

    @Column(name = "started")
    private Boolean started;

    /**
     * Constructor
     */
    public DeviceFieldEntity() {
    }

    /**
     * Constructor
     *
     * @param hardwareid
     * @param type
     * @param comments
     * @param createddate
     * @param deleted
     * @param definition
     * @param started
     */
    public DeviceFieldEntity(String hardwareid, String type, String comments, Date createddate, Boolean deleted, String definition, Boolean started) {
        this.hardwareid = hardwareid;
        this.type = type;
        this.comments = comments;
        this.createddate = createddate;
        this.deleted = deleted;
        this.definition = definition;
        this.started = started;
    }

    //getter setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHardwareid() {
        return hardwareid;
    }

    public void setHardwareid(String hardwareid) {
        this.hardwareid = hardwareid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    @Override
    public String toString() {
        return "DeviceField{" +
                "id=" + id +
                ", hardwareid='" + hardwareid + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                ", createddate=" + createddate +
                ", deleted=" + deleted +
                ", definition='" + definition + '\'' +
                ", started=" + started +
                '}';
    }
}
