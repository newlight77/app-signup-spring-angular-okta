(function (window) {
  window.__env = window.__env || {};

  window.__env = {
    apiUrl: 'http://localhost:8080/api',
    allowedOrigins: ['http://localhost', 'http://localhost:4200'],
    oktaUrl: 'https://dev-769725.okta.com',
    oktaConfig: {
      issuer: 'https://dev-769725.okta.com/oauth2/default',
      redirectUri: window.location.origin + '/implicit/callback',
      clientId: '0oa45v45oMraZVrYA4x6', // used  to run locally with ng serve
      pkce: true,
    },
  };
})(this);
