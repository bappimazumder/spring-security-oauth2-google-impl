/**
 * @version 1.0
 * @author Bappi Mazumder
 * @since 7/22/2023
 * Project Name : spring-security-OAuth2-Google-impl
 */

package com.bappi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @PreAuthorize("hasAnyAuthority('SCOPE_profile','SCOPE_openid')")
    public String getJwtToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var accessToken = getAccessToken(authentication);
        var refreshToken = getRefreshToken(authentication);
        return String.format("Access Token = %s <br><br><br> Refresh Token = %s",
                accessToken.getTokenValue(), refreshToken.getTokenValue());
    }

    public OAuth2AccessToken getAccessToken (Authentication authentication) {
        var authorizedClient = this.getAuthorizedClient(authentication);
        if (authorizedClient != null) {
            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
            if (accessToken != null) {
                return accessToken;
            }
        }
        return null;
    }
    public OAuth2RefreshToken getRefreshToken(Authentication authentication) {
        var authorizedClient = this.getAuthorizedClient(authentication);
        if (authorizedClient != null) {
            OAuth2RefreshToken refreshToken = authorizedClient.getRefreshToken();
            if (refreshToken != null) {
                return refreshToken;
            }
        }
        return null;
    }

    private OAuth2AuthorizedClient getAuthorizedClient(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
            String principalName = oauthToken.getName();
            return oAuth2AuthorizedClientService
                    .loadAuthorizedClient(clientRegistrationId, principalName);
        }
        return null;
    }
}
