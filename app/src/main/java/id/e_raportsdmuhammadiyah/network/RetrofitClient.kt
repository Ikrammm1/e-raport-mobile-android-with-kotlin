package id.e_raportsdmuhammadiyah.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    val instance: ApiClient by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.13/e-raport-admin/API/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiClient::class.java)
    }

}