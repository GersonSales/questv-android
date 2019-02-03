package br.com.questv.endpoint

import br.com.questv.model.episode.EpisodeModel
import br.com.questv.model.season.SeasonModel
import br.com.questv.model.series.SeriesModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface ApiRest {

  @GET("/series")
  fun getAllSeries(): Call<ArrayList<SeriesModel>>

  @GET("/series/{seriesId}/season")
  fun getAllSeasonsBySeries(@Path("seriesId") seriesId: Long): Call<ArrayList<SeasonModel>>


  @GET("/season/{seasonId}/episode")
  fun getAllEpisodesBySeason(@Path("seasonId") seasonId: Long): Call<ArrayList<EpisodeModel>>
}