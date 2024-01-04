package com.bluestork.flutter_okta_auth_sdk.okta.operations

import android.content.Context
import com.bluestork.flutter_okta_auth_sdk.HttpClientImpl
import com.bluestork.flutter_okta_auth_sdk.okta.entities.Errors
import com.bluestork.flutter_okta_auth_sdk.okta.entities.OktaClient
import com.bluestork.flutter_okta_auth_sdk.okta.entities.OktaRequestParameters
import com.bluestork.flutter_okta_auth_sdk.okta.entities.PendingOperation
import com.okta.oidc.CustomConfiguration
import com.okta.oidc.OIDCConfig
import com.okta.oidc.Okta
import com.okta.oidc.storage.SharedPreferenceStorage

fun createConfig(arguments: Map<String, Any>, context: Context) {
    try {

        val params = processOktaRequestArguments(arguments)
        val custConfig = CustomConfiguration.Builder()
            .tokenEndpoint(params.tokenEndpoint!!)
            .authorizationEndpoint(params.authorizationEndpoint!!)
            .create()
        var config = OIDCConfig.Builder()
            .clientId(params.clientId)
            .redirectUri(params.redirectUri)
            .endSessionRedirectUri(params.endSessionRedirectUri)
            .scopes(*params.scopes.toTypedArray())
            .customConfiguration(custConfig)
            .create()

        val webClient = Okta.WebAuthBuilder()
            .withConfig(config)
            .withContext(context)
            .withStorage(SharedPreferenceStorage(context))
            .supportedBrowsers("com.android.chrome", "org.mozilla.firefox")
            .withOktaHttpClient(HttpClientImpl(params.userAgentTemplate))
            .setRequireHardwareBackedKeyStore(params.requireHardwareBackedKeyStore)
            .create()

        val authClient = Okta.AuthBuilder()
            .withConfig(config)
            .withContext(context)
            .withStorage(SharedPreferenceStorage(context))
            .withOktaHttpClient(HttpClientImpl(params.userAgentTemplate))
            .setRequireHardwareBackedKeyStore(params.requireHardwareBackedKeyStore)
            .create()

        OktaClient.init(config, webClient, authClient)
        PendingOperation.success(true)
    } catch (ex: java.lang.Exception) {
        ex.printStackTrace();

        PendingOperation.error(Errors.OKTA_OIDC_ERROR)
    }
}

private fun processOktaRequestArguments(arguments: Map<String, Any>): OktaRequestParameters {
    return OktaRequestParameters(
        clientId = (arguments["clientId"] as String?)!!,
        discoveryUri = (arguments["discoveryUrl"] as String?)!!,
        endSessionRedirectUri = (arguments["endSessionRedirectUri"] as String?)!!,
        redirectUri = (arguments["redirectUrl"] as String?)!!,
        requireHardwareBackedKeyStore = (arguments["requireHardwareBackedKeyStore"] as Boolean?)
            ?: false,
        scopes = arguments["scopes"] as ArrayList<String>,
        userAgentTemplate = (arguments["userAgentTemplate"] as String?) ?: "",
        tokenEndpoint = (arguments["tokenEndpoint"] as String?) ?: "",
        authorizationEndpoint = (arguments["authorizationEndpoint"] as String?) ?: "",
    )
}
