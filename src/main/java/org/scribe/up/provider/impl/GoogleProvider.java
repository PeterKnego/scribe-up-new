/*
  Copyright 2012 Jerome Leleu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.scribe.up.provider.impl;

import org.codehaus.jackson.JsonNode;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.Token;
import org.scribe.up.profile.AttributesDefinitions;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.google.GoogleProfile;
import org.scribe.up.provider.BaseOAuth10Provider;
import org.scribe.up.session.UserSession;

/**
 * This class is the OAuth provider to authenticate user in Google. Scope is not used.<br />
 * Attributes (Java type) available in {@link org.scribe.up.profile.google.GoogleProfile} : profileUrl (String), isViewer (Boolean),
 * thumbnailUrl (String), formatted (String), familyName (String), givenName (String), displayName (String), urls
 * (JsonList&lt;GoogleObject&gt;) and photos (JsonList&lt;GoogleObject&gt;).<br />
 * More information at http://code.google.com/intl/fr-FR/apis/contacts/docs/poco/1.0/developers_guide.html
 * 
 * @author Jerome Leleu
 * @since 1.0.0
 */
@Deprecated
public class GoogleProvider extends BaseOAuth10Provider {
    
    @Override
    protected GoogleProvider newProvider() {
        return new GoogleProvider();
    }
    
    @Override
    protected void internalInit() {
        service = new ServiceBuilder().provider(GoogleApi.class).apiKey(key).apiSecret(secret)
            .scope("http://www-opensocial.googleusercontent.com/api/people/").callback(callbackUrl).build();
    }
    
    @Override
    public String getAuthorizationUrl(final UserSession session) {
        init();
        final Token requestToken = service.getRequestToken();
        logger.debug("requestToken : {}", requestToken);
        // save requestToken in session
        session.setAttribute(getRequestTokenSessionAttributeName(), requestToken);
        final String authorizationUrl = "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token="
                                        + requestToken.getToken();
        logger.debug("authorizationUrl : {}", authorizationUrl);
        return authorizationUrl;
    }
    
    @Override
    protected String getProfileUrl() {
        return "http://www-opensocial.googleusercontent.com/api/people/@me/@self";
    }
    
    @Override
    protected UserProfile extractUserProfile(final String body) {
        final GoogleProfile profile = new GoogleProfile();
        JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            json = json.get("entry");
            if (json != null) {
                profile.setId(JsonHelper.get(json, "id"));
                for (final String attribute : AttributesDefinitions.googleDefinition.getPrincipalAttributes()) {
                    profile.addAttribute(attribute, JsonHelper.get(json, attribute));
                }
                json = json.get("name");
                if (json != null) {
                    for (final String attribute : AttributesDefinitions.googleDefinition.getOtherAttributes()) {
                        profile.addAttribute(attribute, JsonHelper.get(json, attribute));
                    }
                }
            }
        }
        return profile;
    }
}
