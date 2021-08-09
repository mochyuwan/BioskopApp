package ac.id.pei.rpl.bioskopapp


import retrofit2.Call
import retrofit2.http.*

interface ServiceInterface {
    @GET("Kursi")
    fun getData(): Call<List<KursiData>>

    @GET("Film")
    fun getDataFilm(): Call<List<FilmData>>

    @POST("Kursi")
    fun postKontak(@Body KursiData: KursiData): Call<KursiData>

    @POST("Film")
    fun postFilm(@Body FilmData: FilmData): Call<FilmData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Kursi", hasBody = true)
    fun updateKontak(
        @Field("id") id: Int,
        @Field("nama") nama: String,
        @Field("teater_id") teater_id: String,
    ): Call<KursiData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Film", hasBody = true)
    fun updateFilm(
        @Field("id") id: Int,
        @Field("Judul") judul: String,
        @Field("deskripsi") deskripsi: String,
        @Field("rating") rating: String,
    ): Call<FilmData>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Kursi", hasBody = true)
    fun deleteKontak(@Field("id") id: Int): Call<KursiData>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Film", hasBody = true)
    fun deleteFilm(@Field("id") id: Int): Call<FilmData>

}
