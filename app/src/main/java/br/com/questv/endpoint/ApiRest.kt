package br.com.questv.endpoint

import br.com.questv.model.series.SeriesModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface ApiRest {

  @GET("/series")
  fun getAllSeries(): Call<ArrayList<SeriesModel>>

  @Streaming
  @GET("/series/{seriesId}/cover")
  fun getSeriesCover(@Path("seriesId") seriesId: String) : Call<ResponseBody>

  @Streaming
  @GET("/series/{seriesId}/promoImage")
  fun getSeriesPromoImage(@Path("seriesId") seriesId: String): Call<ResponseBody>


}