package com.welldressedmen.nari.data.remote

import android.util.Log
import com.google.gson.GsonBuilder
import com.welldressedmen.nari.lib.BASE_URL
import com.welldressedmen.nari.data.remote.service.UserService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// retrofit 인스턴스 생성
object RetrofitClient {

    val retrofit = createRetrofit(BASE_URL)

    // 서비스들
    val userService = retrofit.create(UserService::class.java)

}

class CommonInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // HTTP 응답 코드를 확인하여 오류 처리
        if (!response.isSuccessful) {
            val errorCode = response.code()
            Log.d("jiwon", "${errorCode}, ${response}")
            // 오류 처리 로직 추가
            // 예: println("API 호출 오류 - Error code: $errorCode")
        }

        return response
    }
}

fun createRetrofit(baseUrl: String): Retrofit {
    val commonInterceptor = CommonInterceptor()

    val client = OkHttpClient.Builder()
        .addInterceptor(commonInterceptor) // 공통 Interceptor 추가
        .build()

    var gson = GsonBuilder().setLenient().create()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}