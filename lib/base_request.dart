class BaseRequest {
  /// The client id.
  String clientId;

  /// The URL of where the discovery document can be found.
  String discoveryUrl;

  /// The redirect URL when session ended.
  String endSessionRedirectUri;

  /// The redirect URL.
  String redirectUrl;

  /// The request scopes.
  List<String> scopes;

  String? userAgentTemplate;

  String? tokenEndpoint;
  String? authorizationEndpoint;
  String? endSessionEndpoint;

  bool requireHardwareBackedKeyStore;

  String issuer;

  BaseRequest({required this.clientId,
    required this.issuer,
    required this.discoveryUrl,
    required this.endSessionRedirectUri,
    required this.redirectUrl,
    required this.scopes,
    this.userAgentTemplate,
    this.tokenEndpoint,
    this.authorizationEndpoint,
    this.endSessionEndpoint,
    this.requireHardwareBackedKeyStore = false});
}

Map<String, Object?> convertBaseRequestToMap(BaseRequest baseRequest) {
  return <String, Object?>{
    'clientId': baseRequest.clientId,
    'issuer': baseRequest.issuer,
    'discoveryUrl': baseRequest.discoveryUrl,
    'endSessionRedirectUri': baseRequest.endSessionRedirectUri,
    'redirectUrl': baseRequest.redirectUrl,
    'scopes': baseRequest.scopes,
    'userAgentTemplate': baseRequest.userAgentTemplate,
    'requireHardwareBackedKeyStore': baseRequest.requireHardwareBackedKeyStore,
    'requireHardwareBackedKeyStore': baseRequest.requireHardwareBackedKeyStore,
    'requireHardwareBackedKeyStore': baseRequest.requireHardwareBackedKeyStore,
    'tokenEndpoint': baseRequest.tokenEndpoint,
    'authorizationEndpoint': baseRequest.authorizationEndpoint,
    'endSessionEndpoint': baseRequest.endSessionEndpoint,
  };
}
