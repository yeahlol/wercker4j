# Wercker4J

Wercker4J is java client library for [wercker API](http://devcenter.wercker.com/api/index.html).

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