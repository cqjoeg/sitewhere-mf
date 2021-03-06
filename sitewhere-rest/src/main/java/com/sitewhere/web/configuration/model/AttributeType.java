/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.web.configuration.model;

/**
 * Type indicator for attribute.
 * 
 * @author Derek
 */
public enum AttributeType {

	/** String value */
	String,

	/** Whole number value */
	Integer,

	/** Decimal number value */
	Decimal,

	/** Boolean value */
	Boolean,

	/** Reference to a site by token */
	SiteReference,

	/** Reference to a specification by token */
	SpecificationReference;
}
