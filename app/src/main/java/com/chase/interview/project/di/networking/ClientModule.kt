package com.chase.interview.project.di.networking

import com.chase.interview.project.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.*
import timber.log.Timber
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [APIServiceProvider::class])
object ClientModule {
    private const val READ_TIMEOUT_TIME: Long = 1000
    private const val CONNECTION_TIMEOUT_TIME: Long = 1000
    @Singleton
    @Provides
    internal fun provideOkHttpClient(): Call.Factory {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        return OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_TIME, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_TIME, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor { chain: Interceptor.Chain ->
                val originalRequest: Request = chain.request()
                val request: Request = originalRequest.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Host", "data.cityofnewyork.us")
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .method(originalRequest.method(), originalRequest.body())
                    .build()
                val response: Response = chain.proceed(request)
                if (BuildConfig.DEBUG) {
                    Timber.e("NYCSchools API Response Code: %s", response.code())
                }
                try {
                    if (response.code() == 401) {
                        return@addInterceptor response
                    }
                } catch (e: Exception) {
                    if (BuildConfig.DEBUG) {
                        Timber.tag("RetrofitError: ").d(e.localizedMessage!!)
                    }
                } finally {
                    if (response.body() != null) {
                        response.body()!!.close()
                    }
                }
                response
            }
            .build()
    }
    @Provides
    @Named("base_url")
    internal fun provideBaseUrl(): String {
        return "https://data.cityofnewyork.us/resource/"
    }
}