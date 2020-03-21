(function (window) {
    window.__env = window.__env || {};
  
    // API url
    window.__env.apiUrl = 'http://localhost:8080';
  
    // Whether or not to enable debug mode
    // Setting this to false will disable console output
    window.__env.enableDebug = true;

    window.__env.oktaConfig = {
        issuer: 'https://dev-769725.okta.com/oauth2/default',
        redirectUri: window.location.origin + '/implicit/callback',
        clientId: '0oa45v45oMraZVrYA4x6',
        pkce: true
      }
}(this));