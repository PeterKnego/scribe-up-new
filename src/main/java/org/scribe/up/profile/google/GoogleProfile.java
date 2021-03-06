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
package org.scribe.up.profile.google;

import java.util.List;
import java.util.Map;

import org.scribe.up.profile.AttributesDefinition;
import org.scribe.up.profile.AttributesDefinitions;
import org.scribe.up.profile.UserProfile;

/**
 * This class is the user profile for Google with appropriate getters.
 * 
 * @author Jerome Leleu
 * @since 1.1.0
 */
@SuppressWarnings("unchecked")
public class GoogleProfile extends UserProfile {
    
    private static final long serialVersionUID = -8416978575072383925L;
    
    protected AttributesDefinition getAttributesDefinition() {
        return AttributesDefinitions.googleDefinition;
    }
    
    public GoogleProfile() {
        super();
    }
    
    public GoogleProfile(Object id) {
        super(id);
    }
    
    public GoogleProfile(Object id, Map<String, Object> attributes) {
        super(id, attributes);
    }
    
    public String getProfileUrl() {
        return (String) attributes.get(GoogleAttributesDefinition.PROFILE_URL);
    }
    
    public boolean isViewer() {
        return getSafeBoolean((Boolean) attributes.get(GoogleAttributesDefinition.IS_VIEWER));
    }
    
    /**
     * Indicate if the isViewer attribute exists.
     * 
     * @return if the isViewer attribute exists
     */
    public boolean isViewerDefined() {
        return attributes.get(GoogleAttributesDefinition.IS_VIEWER) != null;
    }
    
    public String getThumbnailUrl() {
        return (String) attributes.get(GoogleAttributesDefinition.THUMBNAIL_URL);
    }
    
    public String getFormatted() {
        return (String) attributes.get(GoogleAttributesDefinition.FORMATTED);
    }
    
    public String getFamilyName() {
        return (String) attributes.get(GoogleAttributesDefinition.FAMILY_NAME);
    }
    
    public String getGivenName() {
        return (String) attributes.get(GoogleAttributesDefinition.GIVEN_NAME);
    }
    
    public String getDisplayName() {
        return (String) attributes.get(GoogleAttributesDefinition.DISPLAY_NAME);
    }
    
    public List<GoogleObject> getUrls() {
        return (List<GoogleObject>) attributes.get(GoogleAttributesDefinition.URLS);
    }
    
    public List<GoogleObject> getPhotos() {
        return (List<GoogleObject>) attributes.get(GoogleAttributesDefinition.PHOTOS);
    }
}
