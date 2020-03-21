export const environment = {
  production: true,
  oktaConfig: {
    issuer: 'https://dev-769725.okta.com/oauth2/default',
    redirectUri: window.location.origin + '/implicit/callback',
    clientId: '0oa45v45oMraZVrYA4x6',
    pkce: true
  }
};
