package com.bluestork.flutter_okta_auth_sdk.okta.entities

data class OktaRequestParameters(
    var clientId: String,
    var redirectUri: String,
    var endSessionRedirectUri: String,
    var discoveryUri: String,
    var scopes: ArrayList<String>,
    var userAgentTemplate: String = "",
    var requireHardwareBackedKeyStore: Boolean = false,
    var tokenEndpoint: String?,
    var authorizationEndpoint: String?,
    var endSessionEndpoint: String?

)