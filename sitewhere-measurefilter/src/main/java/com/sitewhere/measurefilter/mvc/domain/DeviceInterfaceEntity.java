package com.sitewhere.measurefilter.mvc.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by CQ on 2016/11/19.
 */
@Entity
@Table(name = "tb_interface")
public class DeviceInterfaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "hardwareid", nullable = false)
    private String hardwareid;

    @Column(name = "comments", length = 50)
    private String comments;

    @Temporal(TemporalType.DATE)
    @Column(name = "createddate", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createddate;

    @Column(name = "methodname", nullable = false)
    private String methodname;

    @Column(name = "definition")
    private String definition;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "script")
    private String script;

    /**
     * Constructor
     */
    public DeviceInterfaceEntity() {
    }

    /**
     * Constructor
     *
     * @param hardwareid
     * @param comments
     * @param createddate
     * @param methodname
     * @param definition
     * @param deleted
     * @param script
     */
    public DeviceInterfaceEntity(String hardwareid, String comments, Date createddate, String methodname, String definition, Boolean deleted, String script) {
        this.hardwareid = hardwareid;
        this.comments = comments;
        this.createddate = createddate;
        this.methodname = methodname;
        this.definition = definition;
        this.deleted = deleted;
        this.script = script;
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

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public String toString() {
        return "DeviceInterfaceEntity{" +
                "id=" + id +
                ", hardwareid='" + hardwareid + '\'' +
                ", comments='" + comments + '\'' +
                ", createddate=" + createddate +
                ", methodname='" + methodname + '\'' +
                ", definition='" + definition + '\'' +
                ", deleted=" + deleted +
                ", script='" + script + '\'' +
                '}';
    }
}
