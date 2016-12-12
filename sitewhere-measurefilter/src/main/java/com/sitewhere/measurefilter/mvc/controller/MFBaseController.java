package com.sitewhere.measurefilter.mvc.controller;

import com.sitewhere.SiteWhere;
import com.sitewhere.rest.ISiteWhereWebConstants;
import com.sitewhere.security.LoginManager;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.SiteWhereSystemException;
import com.sitewhere.spi.error.ErrorCode;
import com.sitewhere.spi.error.ErrorLevel;
import com.sitewhere.spi.server.lifecycle.LifecycleStatus;
import com.sitewhere.spi.server.tenant.ISiteWhereTenantEngine;
import com.sitewhere.spi.tenant.ITenant;
import com.sitewhere.spi.tenant.TenantNotAvailableException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by CQ on 2016/12/11.
 */
public class MFBaseController {

    /**
     * Static logger instance
     */
    private static Logger LOGGER = Logger.getLogger(MFBaseController.class);

    /**
     * Get a tenant based on the authentication token passed. Assume that the current user
     * should be validated for access to the given tenant.
     *
     * @param request
     * @return
     * @throws SiteWhereException
     */
    protected ITenant getTenant(HttpServletRequest request) throws SiteWhereException {
        return getTenant(request, true);
    }

    /**
     * Get a tenant based on the authentication token passed.
     *
     * @param request
     * @param checkAuthUser
     * @return
     * @throws SiteWhereException
     */
    protected ITenant getTenant(HttpServletRequest request, boolean checkAuthUser) throws SiteWhereException {
        String token = getTenantAuthToken(request);
        ITenant match = SiteWhere.getServer().getTenantByAuthToken(token);
        if (match == null) {
            throw new SiteWhereSystemException(ErrorCode.InvalidTenantAuthToken, ErrorLevel.ERROR);
        }
        ISiteWhereTenantEngine engine = SiteWhere.getServer().getTenantEngine(match.getId());
        if (engine == null) {
            LOGGER.error("No tenant engine for tenant: " + match.getName());
            throw new TenantNotAvailableException();
        }
        if (engine.getEngineState().getLifecycleStatus() != LifecycleStatus.Started) {
            LOGGER.error("Engine not started for tenant: " + match.getName());
            throw new TenantNotAvailableException();
        }

        if (checkAuthUser) {
            String username = LoginManager.getCurrentlyLoggedInUser().getUsername();
            if (match.getAuthorizedUserIds().contains(username)) {
                return match;
            }
            throw new SiteWhereSystemException(ErrorCode.NotAuthorizedForTenant, ErrorLevel.ERROR);
        } else {
            return match;
        }
    }

    /**
     * Get tenant authentication token from the servlet request.
     *
     * @param request
     * @return
     * @throws SiteWhereException
     */
    protected String getTenantAuthToken(HttpServletRequest request) throws SiteWhereException {
        String token = request.getHeader(ISiteWhereWebConstants.HEADER_TENANT_TOKEN);
        if (token == null) {
            token = request.getParameter(ISiteWhereWebConstants.REQUEST_TENANT_TOKEN);
            if (token == null) {
                throw new SiteWhereSystemException(ErrorCode.MissingTenantAuthToken, ErrorLevel.ERROR,
                        HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return token;
    }

    protected String getAssignmentTokenByHardwareId(String hardwareId, HttpServletRequest request) throws SiteWhereException {
        return SiteWhere.getServer().getDeviceManagement(getTenant(request)).getDeviceByHardwareId(hardwareId).getAssignmentToken();
    }


}
