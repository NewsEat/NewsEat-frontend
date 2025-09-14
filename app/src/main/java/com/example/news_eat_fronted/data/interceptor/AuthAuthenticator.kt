package com.example.news_eat_fronted.data.interceptor

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.news_eat_fronted.data.token.TokenManager
import com.example.news_eat_fronted.domain.usecase.auth.ReissueTokenUseCase
import com.example.news_eat_fronted.presentation.ui.login.LoginActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tokenManager: TokenManager,
    private val reissueTokenUseCase: ReissueTokenUseCase
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
        return try {
            val response = runBlocking {
                reissueTokenUseCase(refreshToken = refreshToken)
            } ?: return null

            response.accessToken.let { tokenManager.saveAccessToken(it) }

            response.accessToken
        } catch (e: Exception) {
            // 토큰 재발급 실패 시 로그인 화면 이동
            Handler(Looper.getMainLooper()).post {
                val intent = Intent(context, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra("EXTRA_SESSION_EXPIRED", true)
                }
                context.startActivity(intent)
            }
            null
        }
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}