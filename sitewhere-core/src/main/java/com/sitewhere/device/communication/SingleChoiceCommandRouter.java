/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.device.communication;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.IDeviceAssignment;
import com.sitewhere.spi.device.IDeviceNestingContext;
import com.sitewhere.spi.device.command.IDeviceCommandExecution;
import com.sitewhere.spi.device.command.ISystemCommand;
import com.sitewhere.spi.device.communication.ICommandDestination;
import com.sitewhere.spi.device.communication.IOutboundCommandRouter;

/**
 * Implementation of {@link IOutboundCommandRouter} that assumes a single
 * {@link ICommandDestination} is available and delivers commands to it.
 * 
 * @author Derek
 */
public class SingleChoiceCommandRouter extends OutboundCommandRouter {

	/** Static logger instance */
	private static Logger LOGGER = Logger.getLogger(SingleChoiceCommandRouter.class);

	/** Destinations that will deliver all commands */
	private ICommandDestination<?, ?> destination;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.device.communication.IOutboundCommandRouter#routeCommand(com.
	 * sitewhere.spi.device.command.IDeviceCommandExecution,
	 * com.sitewhere.spi.device.IDeviceNestingContext,
	 * com.sitewhere.spi.device.IDeviceAssignment)
	 */
	@Override
	public void routeCommand(IDeviceCommandExecution execution, IDeviceNestingContext nesting,
			IDeviceAssignment assignment) throws SiteWhereException {
		destination.deliverCommand(execution, nesting, assignment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sitewhere.spi.device.communication.IOutboundCommandRouter#routeSystemCommand
	 * (com.sitewhere.spi.device.command.ISystemCommand,
	 * com.sitewhere.spi.device.IDeviceNestingContext,
	 * com.sitewhere.spi.device.IDeviceAssignment)
	 */
	@Override
	public void routeSystemCommand(ISystemCommand command, IDeviceNestingContext nesting,
			IDeviceAssignment assignment) throws SiteWhereException {
		destination.deliverSystemCommand(command, nesting, assignment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.server.lifecycle.ILifecycleComponent#start()
	 */
	@Override
	public void start() throws SiteWhereException {
		LOGGER.info("Starting single choice command router...");
		if (getDestinations().size() != 1) {
			throw new SiteWhereException("Expected exactly one destination for command routing but found "
					+ getDestinations().size() + ".");
		}
		Iterator<ICommandDestination<?, ?>> it = getDestinations().values().iterator();
		this.destination = it.next();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sitewhere.spi.server.lifecycle.ILifecycleComponent#stop()
	 */
	@Override
	public void stop() throws SiteWhereException {
		LOGGER.info("Stopped single choice command router.");
	}
}