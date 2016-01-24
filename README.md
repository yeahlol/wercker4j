# Wercker4J
[![wercker status](https://app.wercker.com/status/3d4135d4bb8386531ef55e817318b6bb/s "wercker status")](https://app.wercker.com/project/bykey/3d4135d4bb8386531ef55e817318b6bb)

Wercker4J is java client library for [wercker API](http://devcenter.wercker.com/api/index.html).<br>
JavaDoc is [here](http://www.javadoc.io/doc/org.wercker4j/wercker4j/1.0.0).

## Version
1.0.0

## Install
```
<dependency>
  <groupId>org.wercker4j</groupId>
  <artifactId>wercker4j</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Usage
```
// Create a new client
String token = "..."
Wercker4jClient client = new Wercker4jClient(token);
```

### Application
```
// Get a appliation detail
String owner = "...";
String appName = "...";
Application application = client.getApplication(new GetApplicationOption(owner, appName));

// Get all appliations of user
List<ApplicationSummary> applicationList = client.getApplications(new GetApplicationsOption(owner));
```


### Build
```
// Trigger a new build for an application
String applicationId = "...";
client.createBuild(new CreateBuildOption(applicationId));

// Get a build detail of an application
String buildId = "...";
Build build = client.getBuild(new GetBuildOption(buildId));

// Get all builds of an application
String owner = "...";
String appName = "...";
List<BuildSummary> buildList = client.getBuilds(new GetBuildsOption(owner, appName));
```

### Deploy
```
// Get a deploy detail of an application
String deployId = "...";
Deploy deploy = client.getDeploy(new GetDeployOption(deployId));

// Get all deploys of an application
String owner = "...";
String appName = "...";
List<DeploySummary> deployList = client.getDeploys(new GetDeploysOption(owner, appName));
```

### Token
```
// Trigger a new token
String newTokenName = "...";
Token token = client.createToken(new CreateTokenOption(newTokenName));

// Get a token detail
String tokenId = "...";
Token token = client.getToken(new GetTokenOption(tokenId));

// Get all tokens
 List<TokenSummary> tokenList = client.getTokens();
```

## License
Please see [LICENSE](https://github.com/yeahlol/wercker4j/blob/master/LICENSE.txt).
