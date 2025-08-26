package com.example.news_eat_fronted.data.interceptor

import android.content.Context
import com.example.news_eat_fronted.data.token.TokenManager
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    @ApplicationContext val context: Context,
    val tokenManager: TokenManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getAccessToken()

        val request = chain.request().newBuilder()
            .header(AUTHORIZATION, "Bearer $token")
            .build()

        return try {
            chain.proceed(request)
        } catch (e: IOException) {
            errorResponse(request)
        }
    }

    private fun errorResponse(request: Request): Response = Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_2)
        .code(CODE_ERROR)
        .message("Network error")
        .body(ResponseBody.create(null, ""))
        .build()

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val CODE_ERROR = 401
    }
}