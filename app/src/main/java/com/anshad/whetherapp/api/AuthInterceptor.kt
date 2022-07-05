package com.anshad.whetherapp.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor ( private val baseUrl: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        return chain.proceed(builder.build())
    }

}