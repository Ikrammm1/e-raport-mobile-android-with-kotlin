package id.e_raportsdmuhammadiyah.network

import id.e_raportsdmuhammadiyah.model.ResponseDataInfoSekolah
import id.e_raportsdmuhammadiyah.model.ResponseDataNilai
import id.e_raportsdmuhammadiyah.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET("get_profile_sekolah.php")
    fun get_profile_sekolah(): Call<ResponseDataInfoSekolah>

    @FormUrlEncoded
    @POST("login_service.php")
    fun login(
        @Field("nisn") nisn : String,
        @Field("token") token : String,
    ):Call<ResponseLogin>

    @FormUrlEncoded
    @POST("get_nilai_siswa.php")
    fun get_data_nilai(
        @Field("id") id : String,
        @Field("tipe") tipe : String,
        @Field("semester") semester : String,
        @Field("kelas") kelas : String,
    ):Call<ArrayList<ResponseDataNilai>>




}