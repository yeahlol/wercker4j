/*
 * Copyright 2016 yeahlol
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wercker4j.response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import wercker4j.Wercker4jClient;
import wercker4j.entity.Application;
import wercker4j.entity.ApplicationSummary;
import wercker4j.entity.Build;
import wercker4j.entity.BuildSummary;
import wercker4j.entity.Deploy;
import wercker4j.entity.DeploySummary;
import wercker4j.entity.Token;
import wercker4j.entity.TokenSummary;
import wercker4j.exception.Wercker4jException;

import java.util.List;

/**
 * @author yeahlol
 */
public class ResponseWrapper {

    public Wercker4jClient client;

    public String response;

    public void setClient(Wercker4jClient client) {
        this.client = client;
    }

    public Build build() throws Wercker4jException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, Build.class);
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    public List<BuildSummary> builds() throws Wercker4jException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, new TypeReference<List<BuildSummary>>() {});
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    public Deploy deploy() throws Wercker4jException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, Deploy.class);
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    public List<DeploySummary> deploys() throws Wercker4jException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, new TypeReference<List<DeploySummary>>() {});
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    public Application application() throws Wercker4jException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, Application.class);
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    public List<ApplicationSummary> applications() throws Wercker4jException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, new TypeReference<List<ApplicationSummary>>() {});
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    public Token token() throws  Wercker4jException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, Token.class);
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    public List<TokenSummary> tokens() throws Wercker4jException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, new TypeReference<List<TokenSummary>>() {});
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }

    }
}
