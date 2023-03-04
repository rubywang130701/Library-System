// TODO This code exports an object oktaConfig which represents the configuration for a Single Sign-On (SSO) implementation using Okta. The properties of this object include:clientId: The client identifier used by the OAuth 2.0 client to identify itself to the authorization server (Okta in this case).issuer: The URL of the authorization server's authorization endpoint. This is the endpoint where the client will send the user for authentication and authorization.redirectUri: The URI where the authorization server will redirect the user after authentication and authorization. The client must match this URI exactly.scopes: The OAuth 2.0 scopes that the client is requesting access to on behalf of the user. The openid, profile, and email scopes are common scopes for SSO implementations.pkce: A boolean that indicates whether to use the Proof Key for Code Exchange (PKCE) extension to the OAuth 2.0 authorization code flow. PKCE is an extra security measure for public clients (like a browser-based JavaScript client) that helps prevent authorization code interception attacks.disableHttpsCheck: A boolean that indicates whether to disable the SSL certificate check. It's generally recommended to leave this as false, but setting it to true may be necessary during development or testing.
export const oktaConfig = {
    clientId: '0oa859rjdkBOUaGLo5d7',
    issuer: 'https://dev-69731296.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}