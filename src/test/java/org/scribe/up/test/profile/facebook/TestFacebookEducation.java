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
package org.scribe.up.test.profile.facebook;

import junit.framework.TestCase;

import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.facebook.FacebookEducation;
import org.scribe.up.profile.facebook.FacebookObject;

/**
 * This class tests the {@link org.scribe.up.profile.facebook.FacebookEducation} class.
 * 
 * @author Jerome Leleu
 * @since 1.0.0
 */
public final class TestFacebookEducation extends TestCase {
    
    private static final String ID = "12345";
    
    private static final String NAME = "name";
    
    private static final String FACEBOOK_OBJECT = "{\"id\": \"" + ID + "\", \"name\": \"" + NAME + "\"}";
    
    private static final String TYPE = "type";
    
    private static final String BAD_JSON = "{ }";
    
    private static final String GOOD_JSON = "{\"school\": " + FACEBOOK_OBJECT + ", \"degree\": " + FACEBOOK_OBJECT
                                            + ", \"year\": " + FACEBOOK_OBJECT + ", \"concentration\": ["
                                            + FACEBOOK_OBJECT + "],\"type\": \"" + TYPE + "\" }";
    
    public void testNull() {
        FacebookEducation facebookEducation = new FacebookEducation(null);
        assertNull(facebookEducation.getSchool());
        assertNull(facebookEducation.getDegree());
        assertNull(facebookEducation.getYear());
        assertNull(facebookEducation.getConcentration());
        assertNull(facebookEducation.getType());
    }
    
    public void testBadJson() {
        FacebookEducation facebookEducation = new FacebookEducation(BAD_JSON);
        assertNull(facebookEducation.getSchool());
        assertNull(facebookEducation.getDegree());
        assertNull(facebookEducation.getYear());
        assertNull(facebookEducation.getConcentration());
        assertNull(facebookEducation.getType());
    }
    
    public void testGoodJson() {
        FacebookEducation facebookEducation = new FacebookEducation(GOOD_JSON);
        FacebookObject facebookObject = new FacebookObject(JsonHelper.getFirstNode(FACEBOOK_OBJECT));
        assertEquals(facebookObject.toString(), facebookEducation.getSchool().toString());
        assertEquals(facebookObject.toString(), facebookEducation.getDegree().toString());
        assertEquals(facebookObject.toString(), facebookEducation.getConcentration().get(0).toString());
        assertEquals(facebookObject.toString(), facebookEducation.getYear().toString());
        assertEquals(TYPE, facebookEducation.getType());
    }
}
