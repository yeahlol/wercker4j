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

import org.junit.Before;
import org.junit.Test;
import wercker4j.entity.Application;
import wercker4j.entity.ApplicationSummary;
import wercker4j.entity.Build;
import wercker4j.entity.BuildSummary;
import wercker4j.entity.Deploy;
import wercker4j.entity.DeploySummary;
import wercker4j.entity.Token;
import wercker4j.entity.TokenSummary;
import wercker4j.exception.Wercker4jException;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author yeahlol
 */
public class Wercker4jClientTest {

    private static String DUMMY_TOKEN = "000000000000000000000000000000000000000000000000000000dummyToken";
    public Wercker4jClient client;

    @Before
    public void setup() {
        client = new Wercker4jClient();
        client.setRequest(new MockRequestBuilder(client));
        client.setToken(DUMMY_TOKEN);
    }

    @Test
    public void testGetBuild() {
        try {
            GetBuildOption option = new GetBuildOption("0000000000000testBuildId");
            Build build = client.getBuild(option);
            assertThat(build.id, is("0000000000000testBuildId"));
            assertThat(build.url, is("https://app.wercker.com/api/v3/builds/0000000000000testBuildId"));
            assertThat(build.application.id, is("000000000000000testAppId"));
            assertThat(build.application.url, is("https://app.wercker.com/api/v3/applications/testUser/testApp"));
            assertThat(build.application.name, is("testApp"));
            assertThat(build.application.owner.type, is("wercker"));
            assertThat(build.application.owner.name, is("testUser"));
            assertThat(build.application.owner.avatar.gravatar, is("00000000000000000000testGravatar"));
            assertThat(build.application.owner.userId, is("00000000000000testUserId"));
            assertThat(build.application.owner.meta.username, is("testUser"));
            assertThat(build.application.owner.meta.werckerEmployee, is(false));
            assertThat(build.application.createdAt, is("2015-11-06T14:53:10.023Z"));
            assertThat(build.application.updatedAt, is("2016-01-01T09:48:28.006Z"));
            assertThat(build.application.privacy, is("private"));
            assertThat(build.application.stack, is(5));
            assertThat(build.branch, is("master"));
            assertThat(build.commitHash, is("00000000000000000000000000testCommitHash"));
            assertThat(build.createdAt, is("2015-11-29T14:24:48.919Z"));
            assertThat(build.finishedAt, is("2015-11-29T14:25:36.748Z"));
            assertThat(build.message, is("testMessage"));
            assertThat(build.progress, is(100));
            assertThat(build.result, is("passed"));
            assertThat(build.startedAt, is("2015-11-29T14:24:49.674Z"));
            assertThat(build.status, is("finished"));
            assertThat(build.envVars.size(), is(0));
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetBuilds() {
        try {
            GetBuildsOption option = new GetBuildsOption("testUser", "testApp");
            List<BuildSummary> buildList = client.getBuilds(option);
            assertThat(buildList.get(0).id, is("0000000000000testBuildId"));
            assertThat(buildList.get(0).url, is("https://app.wercker.com/api/v3/builds/0000000000000testBuildId"));
            assertThat(buildList.get(0).branch, is("master"));
            assertThat(buildList.get(0).commitHash, is("00000000000000000000000000testCommitHash"));
            assertThat(buildList.get(0).createdAt, is("2016-01-01T09:47:17.192Z"));
            assertThat(buildList.get(0).finishedAt, is("2016-01-01T09:47:37.162Z"));
            assertThat(buildList.get(0).message, is("testMessage"));
            assertThat(buildList.get(0).progress, is(100));
            assertThat(buildList.get(0).result, is("passed"));
            assertThat(buildList.get(0).startedAt, is("2016-01-01T09:47:18.829Z"));
            assertThat(buildList.get(0).status, is("finished"));
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateBuild() {
        try {
            CreateBuildOption option = new CreateBuildOption("000000000000000testAppId");
            Build build = client.createBuild(option);
            assertThat(build.id, is("0000000000testNewBuildId"));
            assertThat(build.url, is("https://app.wercker.com/api/v3/builds/0000000000testNewBuildId"));
            assertThat(build.application.id, is("000000000000000testAppId"));
            assertThat(build.application.url, is("https://app.wercker.com/api/v3/applications/testUser/testApp"));
            assertThat(build.application.name, is("testApp"));
            assertThat(build.application.owner.type, is("wercker"));
            assertThat(build.application.owner.name, is("testUser"));
            assertThat(build.application.owner.avatar.gravatar, is("00000000000000000testGravatar"));
            assertThat(build.application.owner.userId, is("00000000000000testUserId"));
            assertThat(build.application.owner.meta.username, is("testUser"));
            assertThat(build.application.owner.meta.werckerEmployee, is(false));
            assertThat(build.application.createdAt, is("2015-11-06T14:53:10.023Z"));
            assertThat(build.application.updatedAt, is("2016-01-02T08:33:45.849Z"));
            assertThat(build.application.privacy, is("private"));
            assertThat(build.application.stack, is(5));
            assertThat(build.branch, is("master"));
            assertThat(build.commitHash, is("00000000000000000000000000testCommitHash"));
            assertThat(build.createdAt, is("2016-01-02T08:34:06.309Z"));
            assertThat(build.message, is("testMessage"));
            assertThat(build.progress, is(0));
            assertThat(build.result, is("unknown"));
            assertThat(build.status, is("notstarted"));
            assertThat(build.envVars.size(), is(0));
        } catch (Wercker4jException e) {
            fail(String.valueOf(e.getCode()));
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetDeploy() {
        try {
            GetDeployOption option = new GetDeployOption("000000000000testDeployId");
            Deploy deploy = client.getDeploy(option);
            assertThat(deploy.id, is("000000000000testDeployId"));
            assertThat(deploy.url, is("https://app.wercker.com/api/v3/deploys/000000000000testDeployId"));
            assertThat(deploy.status, is("finished"));
            assertThat(deploy.result, is("passed"));
            assertThat(deploy.createdAt, is("2015-11-29T14:25:36.791Z"));
            assertThat(deploy.progress, is(100));
            assertThat(deploy.application.id, is("000000000000000testAppId"));
            assertThat(deploy.application.url, is("https://app.wercker.com/api/v3/applications/testUser/testApp"));
            assertThat(deploy.application.name, is("testApp"));
            assertThat(deploy.application.owner.type, is("wercker"));
            assertThat(deploy.application.owner.name, is("testUser"));
            assertThat(deploy.application.owner.avatar.gravatar, is("00000000000000000testGravatar"));
            assertThat(deploy.application.owner.userId, is("00000000000000testUserId"));
            assertThat(deploy.application.owner.meta.username, is("testUser"));
            assertThat(deploy.application.owner.meta.werckerEmployee, is(false));
            assertThat(deploy.application.createdAt, is("2015-11-06T14:53:10.023Z"));
            assertThat(deploy.application.updatedAt, is("2016-01-01T09:48:28.006Z"));
            assertThat(deploy.application.privacy, is("private"));
            assertThat(deploy.application.stack, is(5));
            assertThat(deploy.build.id, is("0000000000000testBuildId"));
            assertThat(deploy.build.url, is("https://app.wercker.com/api/v3/builds/0000000000000testBuildId"));
            assertThat(deploy.build.branch, is("master"));
            assertThat(deploy.build.commitHash, is("00000000000000000000000000testCommitHash"));
            assertThat(deploy.build.createdAt, is("2015-11-29T14:24:48.919Z"));
            assertThat(deploy.build.finishedAt, is("2015-11-29T14:25:36.748Z"));
            assertThat(deploy.build.message, is("testMessage"));
            assertThat(deploy.build.progress, is(100));
            assertThat(deploy.build.result, is("passed"));
            assertThat(deploy.build.startedAt, is("2015-11-29T14:24:49.674Z"));
            assertThat(deploy.build.status, is("finished"));
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetDeploys() {
        try {
            GetDeploysOption option = new GetDeploysOption("testUser", "testApp");
            List<DeploySummary> deployList = client.getDeploys(option);
            assertThat(deployList.get(0).id, is("000000000000testDeployId"));
            assertThat(deployList.get(0).url, is("https://app.wercker.com/api/v3/deploys/000000000000testDeployId"));
            assertThat(deployList.get(0).status, is("finished"));
            assertThat(deployList.get(0).result, is("passed"));
            assertThat(deployList.get(0).createdAt, is("2016-01-01T09:47:37.228Z"));
            assertThat(deployList.get(0).progress, is(100));
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetApplication() {
        try {
            GetApplicationOption option = new GetApplicationOption("testUser", "testApp");
            Application application = client.getApplication(option);
            assertThat(application.id, is("000000000000000testAppId"));
            assertThat(application.url, is("https://app.wercker.com/api/v3/applications/testUser/testApp"));
            assertThat(application.name, is("testApp"));
            assertThat(application.owner.type, is("wercker"));
            assertThat(application.owner.name, is("testUser"));
            assertThat(application.owner.avatar.gravatar, is("00000000000000000testGravatar"));
            assertThat(application.owner.userId, is("00000000000000testUserId"));
            assertThat(application.owner.meta.username, is("testUser"));
            assertThat(application.owner.meta.werckerEmployee, is(false));
            assertThat(application.builds, is("https://app.wercker.com/api/v3/applications/testUser/testApp/builds"));
            assertThat(application.deploys, is("https://app.wercker.com/api/v3/applications/testUser/testApp/deploys"));
            assertThat(application.scm.type, is("git"));
            assertThat(application.scm.owner, is("testUser"));
            assertThat(application.scm.domain, is("bitbucket.org"));
            assertThat(application.scm.repository, is("testApp"));
            assertThat(application.createdAt, is("2015-11-06T14:53:10.023Z"));
            assertThat(application.updatedAt, is("2016-01-01T09:48:28.006Z"));
            assertThat(application.privacy, is("private"));
            assertThat(application.stack, is(5));
            assertThat(application.ignoredBranches.size(), is(0));
            assertThat(application.allowedActions.size(), is(72));
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetApplications() {
        try {
            GetApplicationsOption option = new GetApplicationsOption("testUser");
            List<ApplicationSummary> applicationList = client.getApplications(option);
            assertThat(applicationList.get(0).id, is("000000000000000testAppId"));
            assertThat(applicationList.get(0).url, is("https://app.wercker.com/api/v3/applications/testUser/testApp"));
            assertThat(applicationList.get(0).name, is("testApp"));
            assertThat(applicationList.get(0).owner.type, is("wercker"));
            assertThat(applicationList.get(0).owner.name, is("testUser"));
            assertThat(applicationList.get(0).owner.avatar.gravatar, is("00000000000000000testGravatar"));
            assertThat(applicationList.get(0).owner.userId, is("00000000000000testUserId"));
            assertThat(applicationList.get(0).owner.meta.username, is("testUser"));
            assertThat(applicationList.get(0).owner.meta.werckerEmployee, is(false));
            assertThat(applicationList.get(0).createdAt, is("2015-11-06T14:53:10.023Z"));
            assertThat(applicationList.get(0).updatedAt, is("2016-01-01T09:48:28.006Z"));
            assertThat(applicationList.get(0).privacy, is("private"));
            assertThat(applicationList.get(0).stack, is(5));
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetToken() {
        try {
            GetTokenOption option = new GetTokenOption("0000000000000testTokenId");
            Token token = client.getToken(option);
            assertThat(token.id, is("0000000000000testTokenId"));
            assertThat(token.url, is("https://app.wercker.com/api/v3/tokens/0000000000000testTokenId"));
            assertThat(token.name, is("testTokenName"));
            assertNull(token.token);
            assertThat(token.hashedToken, is("0000000000000000000000000000000000000000000000000testHashedToken"));
            assertThat(token.lastCharacters, is("testLast"));
            assertThat(token.createdAt, is("2015-12-07T13:29:08.323Z"));
            assertThat(token.lastUsedAt, is("2016-01-02T07:02:13.629Z"));
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetTokens() {
        try {
            List<TokenSummary> tokenList = client.getTokens();
            assertThat(tokenList.get(0).id, is("0000000000000testTokenId"));
            assertThat(tokenList.get(0).url, is("https://app.wercker.com/api/v3/tokens/0000000000000testTokenId"));
            assertThat(tokenList.get(0).name, is("testTokenName"));
            assertNull(tokenList.get(0).token);
            assertThat(tokenList.get(0).hashedToken, is("0000000000000000000000000000000000000000000000000testHashedToken"));
            assertThat(tokenList.get(0).lastCharacters, is("testLast"));
            assertThat(tokenList.get(0).createdAt, is("2015-12-07T13:29:08.323Z"));
            assertThat(tokenList.get(0).lastUsedAt, is("2016-01-02T07:15:26.313Z"));
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateToken() {
        try {
            CreateTokenOption option = new CreateTokenOption("testNewTokenName");
            Token token = client.createToken(option);
            assertThat(token.id, is("0000000000testNewTokenId"));
            assertThat(token.url, is("https://app.wercker.com/api/v3/tokens/0000000000testNewTokenId"));
            assertThat(token.name, is("testNewTokenName"));
            assertThat(token.token, is("0000000000000000000000000000000000000000000000000000testNewToken"));
            assertThat(token.hashedToken, is("0000000000000000000000000000000000000000000000testNewHashedToken"));
            assertThat(token.lastCharacters, is("tNewLast"));
            assertThat(token.createdAt, is("2016-01-02T07:33:12.883Z"));
            assertNull(token.lastUsedAt);
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testUpdateToken() {
        try {
            UpdateTokenOption option = new UpdateTokenOption("0000000000000testTokenId");
            option.name = "testUpdateTokenName";
            Token token = client.updateToken(option);
            assertThat(token.id, is("0000000000000testTokenId"));
            assertThat(token.url, is("https://app.wercker.com/api/v3/tokens/0000000000000testTokenId"));
            assertThat(token.name, is("testUpdateTokenName"));
            assertNull(token.token);
            assertThat(token.hashedToken, is("0000000000000000000000000000000000000000000000000testHashedToken"));
            assertThat(token.lastCharacters, is("testLast"));
            assertThat(token.createdAt, is("2016-01-01T03:25:24.814Z"));
            assertNull(token.lastUsedAt);
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDeleteToken() {
        try {
            DeleteTokenOption option = new DeleteTokenOption("0000000000000testTokenId");
            client.deleteToken(option);
        } catch (Wercker4jException e) {
            fail(e.getMessage());
        }
    }
}
