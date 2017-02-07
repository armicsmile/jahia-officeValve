package com.smile.jahia.params.valve;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.jahia.params.valves.AuthValveContext;
import org.jahia.params.valves.AutoRegisteredBaseAuthValve;
import org.jahia.pipelines.PipelineException;
import org.jahia.pipelines.valves.ValveContext;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This valve performs authentication through office-365 as trusted party
 * A user
 */
public class OfficeAuthValve extends AutoRegisteredBaseAuthValve {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfficeAuthValve.class);

    private String authorizationLocation;

    private String applicationId;

    private String redirectUri;


    @Override
    public void invoke(Object o, ValveContext valveContext) throws PipelineException {
        // Get the context
        AuthValveContext authCtx = (AuthValveContext) valveContext;
        // Get request
        HttpServletRequest req = authCtx.getRequest();
        // Get Header value for auth ticket
        String ticket = req.getHeader("authentication-ticket");

        // TODO code something

        // Pass to next valve
        valveContext.invokeNext(authCtx);
    }

    private OAuthClientRequest buildClientRequest() throws OAuthSystemException {
        return OAuthClientRequest
                .authorizationLocation(authorizationLocation)
                .setClientId(applicationId)
                .setRedirectURI(redirectUri)
                .buildQueryMessage();
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public void setAuthorizationLocation(String authorizationLocation)  {
        this.authorizationLocation = authorizationLocation;
    }
}
