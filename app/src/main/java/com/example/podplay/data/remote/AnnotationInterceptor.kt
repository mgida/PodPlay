package com.example.podplay.data.remote

import com.example.podplay.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation

private const val X_LISTEN_API_KEY = "X-ListenAPI-Key"

class AnnotationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val invocation =
            chain.request().tag(Invocation::class.java) ?: return chain.proceed(chain.request())
        containedOnInvocation(invocation).forEach { annotation ->
            request = handleAnnotation(annotation, request)
        }
        return chain.proceed(request)
    }

    private fun handleAnnotation(annotation: Annotation, request: Request): Request {
        return when (annotation) {
            is Authorized -> {
                addAuthHeader(request)
            }

            else -> request
        }
    }

    private fun addAuthHeader(request: Request): Request {
        return request.newBuilder().addHeader(X_LISTEN_API_KEY, API_KEY).build()
    }

    private fun containedOnInvocation(invocation: Invocation): Set<Annotation> {
        return invocation.method().annotations.toSet()
    }

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }
}