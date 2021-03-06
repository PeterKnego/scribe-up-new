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

import java.io.Serializable;

import org.codehaus.jackson.JsonNode;
import org.scribe.up.profile.JsonObject;
import org.scribe.up.profile.converter.Converters;

/**
 * This class represents a common Google object (value + type).
 * 
 * @author Jerome Leleu
 * @since 1.1.0
 */
public final class GoogleObject extends JsonObject implements Serializable {
    
    private static final long serialVersionUID = -7723176838931594789L;
    
    private String value;
    
    private String type;
    
    public GoogleObject(Object json) {
        super(json);
    }
    
    @Override
    protected void buildFromJson(JsonNode json) {
        this.value = Converters.stringConverter.convertFromJson(json, "value");
        this.type = Converters.stringConverter.convertFromJson(json, "type");
    }
    
    public String getValue() {
        return value;
    }
    
    public String getType() {
        return type;
    }
}
