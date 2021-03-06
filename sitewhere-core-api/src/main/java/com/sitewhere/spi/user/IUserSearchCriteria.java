/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.user;

/**
 * Criteria used when searching for users.
 * 
 * @author Derek Adams
 */
public interface IUserSearchCriteria {

	/**
	 * Indicates whether deleted records should be returned.
	 * 
	 * @return
	 */
	public boolean isIncludeDeleted();
}