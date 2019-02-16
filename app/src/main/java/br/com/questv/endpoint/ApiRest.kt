package br.com.questv.endpoint

import br.com.questv.model.episode.EpisodeModel
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.season.SeasonModel
import br.com.questv.model.series.SeriesModel
import br.com.questv.model.user.LoginModel
import br.com.questv.model.user.UserModel
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiRest {

  @GET("/series")
  fun getAllSeries(): Call<ArrayList<SeriesModel>>

  @GET("/series/{seriesId}/seasons")
  fun getAllSeasonsBySeries(@Path("seriesId") seriesId: String): Call<ArrayList<SeasonModel>>


  @GET("/seasons/{seasonId}/episodes")
  fun getAllEpisodesBySeason(@Path("seasonId") seasonId: String): Call<ArrayList<EpisodeModel>>

  @GET("/questionables/{questionableId}/questions?recursive=true")
  fun getAllQuestionsOfQuestionable(@Path("questionableId") questionableId: String): Call<ArrayList<QuestionModel>>

  @POST("/login")
  fun login(@Body login: LoginModel): Call<ResponseBody>


  @GET("/users/{userId}")
  fun getUserById(@Path("userId") userId: String,
                  @Header("Authorization") auth: String): Call<UserModel>
}