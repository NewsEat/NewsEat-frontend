package com.example.news_eat_fronted.data.interceptor

import com.example.news_eat_fronted.data.token.TokenManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = tokenManager.getRefreshToken() ?: return null
        val newAccessToken = reissueToken(refreshToken) ?: return null

        return response.request.newBuilder()
            .removeHeader(AUTHORIZATION)
            .addHeader(AUTHORIZATION, "Bearer $newAccessToken")
            .build()
    }

    private fun reissueToken(refreshToken: String): String? {
        val accessToken = ""  // 토큰 재발급 API 호출
        return accessToken
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}