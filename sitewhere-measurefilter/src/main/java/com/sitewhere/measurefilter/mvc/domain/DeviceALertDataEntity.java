package com.sitewhere.measurefilter.mvc.domain;

import com.sitewhere.spi.device.field.domain.IDeviceALertDataEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * Created by CQ on 2016/11/19.
 */
@Entity
@Table(name = "tb_alert")
public class DeviceALertDataEntity implements IDeviceALertDataEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "hardwareid", nullable = false)
    private String hardwareid;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "comments", length = 50)
    private String comments;

    @CreationTimestamp
    @Column(name = "createddate", nullable = false, updatable = false)
    private Timestamp createddate;

    @Column(name = " value", nullable = false)
    private Double value;

    @Column(name = "range", length = 50)
    private String range;

    @Column(name = "assignmenttoken", length = 50)
    private String assignmenttoken;

    /**
     * Constructor
     */
    public DeviceALertDataEntity() {

    }

    /**
     * Constructor
     * @param hardwareid
     * @param type
     * @param comments
     * @param createddate
     * @param value
     */
//    public DeviceALertDataEntity(String hardwareid, String type, String comments, Date createddate, Double value) {
//        this.hardwareid = hardwareid;
//        this.type = type;
//        this.comments = comments;
//        this.createddate = createddate;
//        this.value = value;
//    }

    public DeviceALertDataEntity(String hardwareid, String type, String comments, Timestamp createddate, Double value, String range, String assignmenttoken) {
        this.hardwareid = hardwareid;
        this.type = type;
        this.comments = comments;
        this.createddate = createddate;
        this.value = value;
        this.range = range;
        this.assignmenttoken = assignmenttoken;
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

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getAssignmenttoken() {
        return assignmenttoken;
    }

    public void setAssignmenttoken(String assignmenttoken) {
        this.assignmenttoken = assignmenttoken;
    }


    @Override
    public String toString() {
        return "DeviceALertDataEntity{" +
                "id=" + id +
                ", hardwareid='" + hardwareid + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                ", createddate=" + createddate +
                ", value=" + value +
                ", range='" + range + '\'' +
                ", assignmenttoken='" + assignmenttoken + '\'' +
                '}';
    }
}
