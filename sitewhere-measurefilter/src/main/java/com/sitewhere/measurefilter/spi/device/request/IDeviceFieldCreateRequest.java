package com.sitewhere.measurefilter.spi.device.request;


import com.sitewhere.measurefilter.spi.common.IMetadataProviderEntity;

/**
 * Created by CQ on 2016/11/20.
 */
public interface IDeviceFieldCreateRequest extends IMetadataProviderEntity {
    /**
     * Get the unique hardware id of the device.
     *
     * @return
     */
    public String getHardwareId();

    /**
     * Get the Field type for device
     *
     * @return
     */
    public String getType();

    /**
     * Get the Comments
     *
     * @return
     */
    public String getComments();

    /**
     * Get Definition
     *
     * @return
     */
    public Object getDefinition();

    /**
     * Get started
     *
     * @return
     */
    public Boolean getStarted();

}
