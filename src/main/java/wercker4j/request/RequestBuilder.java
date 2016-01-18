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

package wercker4j.request;

import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import wercker4j.Wercker4jClient;
import wercker4j.exception.Wercker4jException;
import wercker4j.http.DefaultHttpConfig;
import wercker4j.request.param.CreateBuildOption;
import wercker4j.request.param.CreateTokenOption;
import wercker4j.request.param.DeleteTokenOption;
import wercker4j.request.param.GetApplicationOption;
import wercker4j.request.param.GetApplicationsOption;
import wercker4j.request.param.GetBuildOption;
import wercker4j.request.param.GetBuildsOption;
import wercker4j.request.param.GetDeployOption;
import wercker4j.request.param.GetDeploysOption;
import wercker4j.request.param.GetTokenOption;
import wercker4j.request.param.UpdateTokenOption;
import wercker4j.response.ResponseWrapper;

/**
 * @author yeahlol
 */
public class RequestBuilder {

    private String endpoint = "https://app.wercker.com/api/v3";
    private String token;
    private String username;
    private String password;
    private Wercker4jClient client;

    public RequestBuilder(Wercker4jClient client) {
        this.client = client;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserNamePassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * get will call build API to get
     *
     * @param option option to call get Build API
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper get(GetBuildOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/builds{/buildId}")
                .set("buildId", option.buildId)
                .expand();
        return callGetAPI(new HttpGet(url));
    }

    /**
     * get will call builds API to get
     *
     * @param option option to call get Builds API
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper get(GetBuildsOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint
                + "/applications{/owner,name}"
                + "/builds{?branch,commit,limit,result,skip,sort,stack,status}")
                .set("owner", option.owner)
                .set("name", option.name)
                .set("branch", option.branch)
                .set("commit", option.commit)
                .set("limit", option.limit == 0 ? null : option.limit)
                .set("result", option.result)
                .set("skip", option.skip == 0 ? null : option.skip)
                .set("sort", option.sort)
                .set("stack", option.stack)
                .set("status", option.status)
                .expand();
        return callGetAPI(new HttpGet(url));
    }

    /**
     * post will call build API to create
     *
     * @param option option to call trigger new build API
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper post(CreateBuildOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/builds")
                .expand();
        HttpPost httpPost = new HttpPost(url);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        try {
            httpPost.setEntity(new StringEntity(mapper.writeValueAsString(option), "UTF-8"));
            return callPostAPI(httpPost);
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    /**
     * get will call deploy API to get
     *
     * @param option option to call get deploy APIw
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper get(GetDeployOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/deploys{/deployId}")
                .set("deployId", option.deployId)
                .expand();
        return callGetAPI(new HttpGet(url));
    }

    /**
     * get will call deploys API to get
     *
     * @param option option to call get deploys API
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper get(GetDeploysOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint
                + "/applications{/owner,name}"
                + "/deploys{?buildId,result,stack,status,limit,skip,sort}")
                .set("owner", option.owner)
                .set("name", option.name)
                .set("buildId", option.buildId)
                .set("result", option.result)
                .set("stack", option.stack)
                .set("limit", option.limit == 0 ? null : option.limit)
                .set("skip", option.skip == 0 ? null : option.skip)
                .set("sort", option.sort)
                .expand();
        return callGetAPI(new HttpGet(url));
    }

    /**
     * get will call application API to get
     *
     * @param option option to call get application API
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper get(GetApplicationOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/applications{/owner,name}")
                .set("owner", option.owner)
                .set("name", option.name)
                .expand();
        return callGetAPI(new HttpGet(url));
    }

    /**
     * get will call user applications API to get
     *
     * @param option option to call get applications API
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper get(GetApplicationsOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/applications{/owner}")
                .set("owner", option.owner)
                .expand();
        return callGetAPI(new HttpGet(url));
    }

    /**
     * post will call token API to create
     *
     * @param option option to call trigger new token API
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper post(CreateTokenOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/tokens")
                .expand();
        HttpPost httpPost = new HttpPost(url);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        try {
            httpPost.setEntity(new StringEntity(mapper.writeValueAsString(option), "UTF-8"));
            return callPostAPI(httpPost);
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    /**
     * get will call token API to get
     *
     * @param option option to call get token API
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper get(GetTokenOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/tokens{/tokenId}")
                .set("tokenId", option.tokenId)
                .expand();
        return callGetAPI(new HttpGet(url));
    }

    /**
     * get will call tokens API to get
     *
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper get() throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/tokens")
                .expand();
        return callGetAPI(new HttpGet(url));
    }

    /**
     * patch will call token API to update
     *
     * @param option option to call update token API
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    public ResponseWrapper patch(UpdateTokenOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/tokens{/tokenId}")
                .set("tokenId", option.tokenId)
                .expand();
        HttpPatch httpPatch = new HttpPatch(url);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        try {
            httpPatch.setEntity(new StringEntity(mapper.writeValueAsString(option), "UTF-8"));
            return callPatchAPI(httpPatch);
        } catch (Exception e) {
            throw new Wercker4jException(e);
        }
    }

    /**
     * delete will call token API to delete
     *
     * @param option option to call delete token API
     * @throws Wercker4jException fault statusCode and IO error
     */
    public void delete(DeleteTokenOption option) throws Wercker4jException {
        String url = UriTemplate.fromTemplate(endpoint + "/tokens{/tokenId}")
                .set("tokenId", option.tokenId)
                .expand();
        callDeleteAPI(new HttpDelete(url));
    }

    /**
     * get will do HTTP GET
     *
     * @param httpGet
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    protected ResponseWrapper callGetAPI(HttpGet httpGet) throws Wercker4jException {
        CloseableHttpClient httpClient = getHttpClientWithAuthInfo();

        if (httpClient != null) {
            int statusCode;
            try {
                CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
                statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 400) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    ResponseWrapper responseWrapper = new ResponseWrapper();
                    responseWrapper.response = EntityUtils.toString(httpEntity);
                    responseWrapper.setClient(client);
                    return responseWrapper;
                }
            } catch (Exception e) {
                throw new Wercker4jException(e);
            }
            throw new Wercker4jException(statusCode, "error");
        }

        return null;
    }

    /**
     * post will do HTTP POST
     *
     * @param httpPost
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    protected ResponseWrapper callPostAPI(HttpPost httpPost) throws Wercker4jException {
        CloseableHttpClient httpClient = getHttpClientWithAuthInfo();

        if (httpClient != null) {
            int statusCode;
            try {
                CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
                statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 400) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    ResponseWrapper responseWrapper = new ResponseWrapper();
                    responseWrapper.response = EntityUtils.toString(httpEntity);
                    responseWrapper.setClient(client);
                    return responseWrapper;
                }
            } catch (Exception e) {
                throw new Wercker4jException(e);
            }
            throw new Wercker4jException(statusCode, "error");
        }

        return null;
    }

    /**
     * patch will do HTTP PATCH
     *
     * @param httpPatch
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    protected ResponseWrapper callPatchAPI(HttpPatch httpPatch) throws Wercker4jException {
        CloseableHttpClient httpClient = getHttpClientWithAuthInfo();

        if (httpClient != null) {
            int statusCode;
            try {
                CloseableHttpResponse httpResponse = httpClient.execute(httpPatch);
                statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 400) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    ResponseWrapper responseWrapper = new ResponseWrapper();
                    responseWrapper.response = EntityUtils.toString(httpEntity);
                    responseWrapper.setClient(client);
                    return responseWrapper;
                }
            } catch (Exception e) {
                throw new Wercker4jException(e);
            }
            throw new Wercker4jException(statusCode, "error");
        }

        return null;
    }

    /**
     * delete will do HTTP DELETE
     *
     * @param httpDelete
     * @return responseWrapper
     * @throws Wercker4jException fault statusCode and IO error
     */
    protected void callDeleteAPI(HttpDelete httpDelete) throws Wercker4jException {
        CloseableHttpClient httpClient = getHttpClientWithAuthInfo();

        if (httpClient != null) {
            int statusCode;
            try {
                CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);
                statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode < 400) {
                    return;
                }
            } catch (Exception e) {
                throw new Wercker4jException(e);
            }
            throw new Wercker4jException(statusCode, "error");
        }
    }

    /**
     * get HttpClient with authentication info
     *
     * @return closeableHttpClient
     */
    private CloseableHttpClient getHttpClientWithAuthInfo() {
        if (StringUtils.isNotEmpty(token)) {
            return DefaultHttpConfig.createHttpClient(token);
        }
        return null;
    }
}
