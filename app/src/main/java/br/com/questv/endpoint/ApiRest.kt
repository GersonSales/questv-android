package br.com.questv.endpoint

import br.com.questv.model.series.dto.SeriesDTO
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface ApiRest {

  @GET("/series")
  fun getAllSeries(): Call<ArrayList<SeriesDTO>>

  @Streaming
  @GET("/series/{seriesId}/cover")
  fun getSeriesCover(@Path("seriesId") seriesId: String) : Call<ResponseBody>



}