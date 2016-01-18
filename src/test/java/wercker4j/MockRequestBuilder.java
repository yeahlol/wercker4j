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

package wercker4j;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import wercker4j.exception.Wercker4jException;
import wercker4j.request.RequestBuilder;
import wercker4j.response.ResponseWrapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yeahlol
 */
public class MockRequestBuilder extends RequestBuilder {
    // HTTP METHOD
    private static String GET_METHOD = "GET";
    private static String POST_METHOD = "POST";
    private static String PATCH_METHOD = "PATCH";
    private static String DELETE_METHOD = "DELETE";

    public MockRequestBuilder(Wercker4jClient client) {
        super(client);
    }

    @Override
    protected ResponseWrapper callGetAPI(HttpGet httpGet) throws Wercker4jException {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.response = getJsonStrByMethod(httpGet.getURI().toString(), GET_METHOD);
        return responseWrapper;
    }

    @Override
    protected ResponseWrapper callPostAPI(HttpPost httpPost) throws Wercker4jException {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.response = getJsonStrByMethod(httpPost.getURI().toString(), POST_METHOD);
        return responseWrapper;
    }

    @Override
    protected ResponseWrapper callPatchAPI(HttpPatch httpPatch) throws Wercker4jException {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.response = getJsonStrByMethod(httpPatch.getURI().toString(), PATCH_METHOD);
        return responseWrapper;
    }

    @Override
    protected void callDeleteAPI(HttpDelete httpDelete) throws Wercker4jException {
        getJsonStrByMethod(httpDelete.getURI().toString(), DELETE_METHOD);
    }

    private String getJsonStrByMethod(String url, String method) throws Wercker4jException {
        if (url.endsWith("/builds/0000000000000testBuildId") && GET_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/getBuild.json");
        } else if (url.endsWith("/applications/testUser/testApp/builds") && GET_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/getBuilds.json");
        } else if (url.endsWith("/builds") && POST_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/createBuild.json");
        } else if (url.endsWith("/deploys/000000000000testDeployId") && GET_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/getDeploy.json");
        } else if (url.endsWith("/applications/testUser/testApp/deploys") && GET_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/getDeploys.json");
        } else if (url.endsWith("/testUser/testApp") && GET_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/getApplication.json");
        } else if (url.endsWith("/testUser") && GET_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/getApplications.json");
        } else if (url.endsWith("/tokens/0000000000000testTokenId") && GET_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/getToken.json");
        } else if (url.endsWith("/tokens") && GET_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/getTokens.json");
        } else if (url.endsWith("/tokens") && POST_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/createToken.json");
        } else if (url.endsWith("/tokens/0000000000000testTokenId") && PATCH_METHOD.equals(method)) {
            return convertStrFromJsonFile("json/updateToken.json");
        } else if (url.endsWith("/tokens/0000000000000testTokenId") && DELETE_METHOD.equals(method)) {
            return null;
        }
        throw new Wercker4jException("url: " + url + ", METHOD: " + method + ", error");
    }

    private String convertStrFromJsonFile(String jsonPath) throws Wercker4jException {
        InputStream io = this.getClass().getClassLoader().getResourceAsStream(jsonPath);
        try {
            return IOUtils.toString(io);
        } catch (IOException e) {
            throw new Wercker4jException(e);
        }
    }
}
