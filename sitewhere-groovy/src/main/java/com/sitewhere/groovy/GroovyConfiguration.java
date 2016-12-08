/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.groovy;

import groovy.util.GroovyScriptEngine;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.log4j.Logger;

import com.sitewhere.SiteWhere;
import com.sitewhere.configuration.FileSystemGlobalConfigurationResolver;
import com.sitewhere.server.lifecycle.TenantLifecycleComponent;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.server.lifecycle.IDiscoverableTenantLifecycleComponent;
import com.sitewhere.spi.server.lifecycle.LifecycleComponentType;
import com.sitewhere.spi.server.tenant.ISiteWhereTenantEngine;

/**
 * Global configuration for Groovy scripting support.
 * 
 * @author Derek
 */
public class GroovyConfiguration extends TenantLifecycleComponent implements
		IDiscoverableTenantLifecycleComponent {

	/** Static logger instance */
	private static Logger LOGGER = Logger.getLogger(GroovyConfiguration.class);

	/** Bean name where global Groovy configuration is expected */
	public static final String GROOVY_CONFIGURATION_BEAN = "swGroovyConfiguration";

	/** Path relative to scripts root where Groovy scripts are stored */
	private static final String GROOVY_REL_SCRIPT_PATH = "groovy";

	/** Groovy script engine */
	private GroovyScriptEngine groovyScriptEngine;

	/** Configures script root to an external URL */
	private String externalScriptRoot;

	/** Field for setting GSE verbose flag */
	private boolean verbose = false;

	/** Field for setting GSE debug flag */
	private boolean debug = false;

	public GroovyConfiguration() {
		super(LifecycleComponentType.Other);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.server.lifecycle.ILifecycleComponent#start()
	 */
	@Override
	public void start() throws SiteWhereException {
		try {
			if (getExternalScriptRoot() != null) {
				groovyScriptEngine = new GroovyScriptEngine(getExternalScriptRoot());
				LOGGER.info("Groovy will load scripts relative to external URL: " + getExternalScriptRoot());
			} else {
				// Handle global scripts.
				URI scriptsUri = null;
				if (getTenant() == null) {
					URI root = SiteWhere.getServer().getConfigurationResolver().getConfigurationRoot();
					File global =
							new File(new File(root), FileSystemGlobalConfigurationResolver.GLOBAL_FOLDER_NAME);
					if (!global.exists()) {
						global.mkdir();
					}
					scriptsUri =
							new File(global, FileSystemGlobalConfigurationResolver.SCRIPTS_FOLDER_NAME).toURI();
					LOGGER.info("Starting global Groovy configuration with scripts loading from: "
							+ scriptsUri);
				}

				// Handle tenant scripts.
				else {
					ISiteWhereTenantEngine engine =
							SiteWhere.getServer().getTenantEngine(getTenant().getId());
					scriptsUri = engine.getTenantConfigurationResolver().getScriptResourcesRoot();
					LOGGER.info("Starting Groovy configuration for '" + getTenant().getName()
							+ "' with scripts loading from: " + scriptsUri
							+ ". Global scripts will be overridden.");
				}
				File scripts = new File(scriptsUri);
				if (!scripts.exists()) {
					scripts.mkdir();
				}
				File groovy = new File(scripts, GROOVY_REL_SCRIPT_PATH);
				if (!groovy.exists()) {
					groovy.mkdir();
				}
				groovyScriptEngine = new GroovyScriptEngine(groovy.getAbsolutePath());
			}

			groovyScriptEngine.getConfig().setVerbose(isVerbose());
			groovyScriptEngine.getConfig().setDebug(isDebug());
			LOGGER.info("Groovy script engine configured with (verbose:" + isVerbose() + ") (debug:"
					+ isDebug() + ").");
		} catch (IOException e) {
			throw new SiteWhereException("Unable to configure Groovy script engine.", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.server.lifecycle.ILifecycleComponent#stop()
	 */
	@Override
	public void stop() throws SiteWhereException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.server.lifecycle.ILifecycleComponent#getLogger()
	 */
	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	public GroovyScriptEngine getGroovyScriptEngine() {
		return groovyScriptEngine;
	}

	public void setGroovyScriptEngine(GroovyScriptEngine groovyScriptEngine) {
		this.groovyScriptEngine = groovyScriptEngine;
	}

	public String getExternalScriptRoot() {
		return externalScriptRoot;
	}

	public void setExternalScriptRoot(String externalScriptRoot) {
		if ((externalScriptRoot != null) && (!externalScriptRoot.endsWith("/"))) {
			externalScriptRoot += "/";
		}
		this.externalScriptRoot = externalScriptRoot;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}