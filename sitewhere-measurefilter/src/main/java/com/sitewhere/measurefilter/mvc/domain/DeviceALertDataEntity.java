package com.sitewhere.measurefilter.mvc.domain;

import com.sitewhere.spi.device.field.domain.IDeviceALertDataEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.DATE)
    @Column(name = "createddate", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createddate;

    @Column(name = " value", nullable = false)
    private Double value;

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
    public DeviceALertDataEntity(String hardwareid, String type, String comments, Date createddate, Double value) {
        this.hardwareid = hardwareid;
        this.type = type;
        this.comments = comments;
        this.createddate = createddate;
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DeviceALertData{" +
                "id=" + id +
                ", hardwareid='" + hardwareid + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                ", createddate=" + createddate +
                ", value=" + value +
                '}';
    }
}
