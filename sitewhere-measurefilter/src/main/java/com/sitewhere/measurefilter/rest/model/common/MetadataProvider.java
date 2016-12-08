/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.measurefilter.rest.model.common;


import com.sitewhere.measurefilter.spi.SiteWhereException;
import com.sitewhere.measurefilter.spi.common.IMetadataProvider;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds arbitrary metadata associated with an entity.
 *
 * @author dadams
 */
public class MetadataProvider implements IMetadataProvider, Serializable {

    /**
     * Serialization version identifier
     */
    private static final long serialVersionUID = -7708181397230364294L;

    /**
     * Map of metadata entries
     */
    private Map<String, String> entries = new HashMap<String, String>();

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sitewhere.spi.device.IMetadataProvider#addOrReplaceMetadata(java.lang.String,
     * java.lang.String)
     */
    public void addOrReplaceMetadata(String name, String value) throws SiteWhereException {
        if (!name.matches("^[\\w-]+$")) {
            throw new SiteWhereException("Metadata field name contains invalid characters.", null);
        }
        entries.put(name, value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sitewhere.spi.device.IMetadataProvider#removeMetadata(java.lang.String)
     */
    public String removeMetadata(String name) {
        return entries.remove(name);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sitewhere.spi.device.IMetadataProvider#getMetadata(java.lang.String)
     */
    public String getMetadata(String name) {
        return entries.get(name);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sitewhere.spi.device.IMetadataProvider#getMetadata()
     */
    public Map<String, String> getMetadata() {
        return entries;
    }

    public void setMetadata(Map<String, String> entries) {
        this.entries = entries;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sitewhere.spi.common.IMetadataProvider#clearMetadata()
     */
    @Override
    public void clearMetadata() {
        entries.clear();
    }

    /**
     * Copy contents of one metadata provider to another.
     *
     * @param source
     * @param target
     */
    public static void copy(IMetadataProvider source, MetadataProvider target) throws SiteWhereException {
        if (source != null) {
            for (String key : source.getMetadata().keySet()) {
                target.addOrReplaceMetadata(key, source.getMetadata(key));
            }
        }
    }

    /**
     * Copy values from a map into a {@link MetadataProvider}.
     *
     * @param source
     * @param target
     * @throws SiteWhereException
     */
    public static void copy(Map<String, String> source, MetadataProvider target) throws SiteWhereException {
        if (source != null) {
            for (String key : source.keySet()) {
                target.addOrReplaceMetadata(key, source.get(key));
            }
        }
    }
}