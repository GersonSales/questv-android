package br.com.questv.endpoint

import br.com.questv.model.series.dto.SeriesDTO
import retrofit2.Call
import retrofit2.http.GET

interface ApiRest {

  @GET("/series")
  fun getAllSeries(): Call<ArrayList<SeriesDTO>>



}