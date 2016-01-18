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

import wercker4j.entity.Application;
import wercker4j.entity.ApplicationSummary;
import wercker4j.entity.Build;
import wercker4j.entity.BuildSummary;
import wercker4j.entity.Deploy;
import wercker4j.entity.DeploySummary;
import wercker4j.entity.Token;
import wercker4j.entity.TokenSummary;
import wercker4j.exception.Wercker4jException;
import wercker4j.request.RequestBuilder;
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

import java.util.List;

/**
 * @author yeahlol
 */
public class Wercker4jClient {

    private RequestBuilder request;

    public Wercker4jClient() {
        this.request = new RequestBuilder(this);
    }

    public Wercker4jClient(String username, String password) {
        this();
        this.request.setUserNamePassword(username, password);
    }

    public Wercker4jClient(String token) {
        this();
        this.request.setToken(token);
    }

    /**
     * getBuild will retrieve single build
     *
     * @param option option to call get Build API
     * @return single build
     * @throws Wercker4jException fault statusCode and IO error
     */
    public Build getBuild(GetBuildOption option) throws Wercker4jException {
        return request.get(option).build();
    }

    /**
     * getBuilds will retrieve build summary
     *
     * @param option option to call get Builds API
     * @return list of build summary
     * @throws Wercker4jException fault statusCode and IO error
     */
    public List<BuildSummary> getBuilds(GetBuildsOption option) throws Wercker4jException {
        return request.get(option).builds();
    }

    /**
     * createBuild will create single build;
     *
     * @param option option to call trigger new build API
     * @return build
     * @throws Wercker4jException fault statusCode and IO error
     */
    public Build createBuild(CreateBuildOption option) throws Wercker4jException {
        return request.post(option).build();
    }

    /**
     * getDeploy will retrieve single deploy
     *
     * @param option option to call get deploy API
     * @return single deploy
     * @throws Wercker4jException fault statusCode and IO error
     */
    public Deploy getDeploy(GetDeployOption option) throws Wercker4jException {
        return request.get(option).deploy();
    }

    /**
     * getDeploys will retrieve deploy summary
     *
     * @param option option to call get deploys API
     * @return list of deploy summary
     * @throws Wercker4jException fault statusCode and IO error
     */
    public List<DeploySummary> getDeploys(GetDeploysOption option) throws Wercker4jException {
        return request.get(option).deploys();
    }

    /**
     * getApplication will retrieve application
     *
     * @param option option to call get application API
     * @return application
     * @throws Wercker4jException fault statusCode and IO error
     */
    public Application getApplication(GetApplicationOption option) throws Wercker4jException {
        return request.get(option).application();
    }

    /**
     * getApplications will retrieve applications of user
     *
     * @param option option to call get applications API
     * @return applications of user
     * @throws Wercker4jException fault statusCode and IO error
     */
    public List<ApplicationSummary> getApplications(GetApplicationsOption option)
            throws Wercker4jException {
        return request.get(option).applications();
    }

    /**
     * createToken will create token
     *
     * @param option option to call trigger new token API
     * @return token
     * @throws Wercker4jException fault statusCode and IO error
     */
    public Token createToken(CreateTokenOption option) throws Wercker4jException {
        return request.post(option).token();
    }

    /**
     * getToken will retrieve single token
     *
     * @param option option to call token API
     * @return token
     * @throws Wercker4jException fault statusCode and IO error
     */
    public Token getToken(GetTokenOption option) throws Wercker4jException {
        return request.get(option).token();
    }

    /**
     * getTokens will retrieve token summary
     *
     * @return tokenSummary
     * @throws Wercker4jException fault statusCode and IO error
     */
    public List<TokenSummary> getTokens() throws Wercker4jException {
        return request.get().tokens();
    }

    /**
     * updateToken will update token
     *
     * @param option option to call update token API
     * @return token
     * @throws Wercker4jException fault statusCode and IO error
     */
    public Token updateToken(UpdateTokenOption option) throws Wercker4jException {
        return request.patch(option).token();
    }

    /**
     * deleteToken will delete token
     *
     * @param option option to call delete token API
     * @throws Wercker4jException fault statusCode and IO error
     */
    public void deleteToken(DeleteTokenOption option) throws Wercker4jException {
        request.delete(option);
    }

    public void setRequest(RequestBuilder request) {
        this.request = request;
    }

    public void setToken(String token) {
        this.request.setToken(token);
    }
}

