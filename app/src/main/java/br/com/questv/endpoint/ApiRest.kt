package br.com.questv.endpoint

import br.com.questv.model.analytics.AnalyticsModel
import br.com.questv.model.episode.EpisodeModel
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.season.SeasonModel
import br.com.questv.model.series.SeriesModel
import br.com.questv.model.user.LoginModel
import br.com.questv.model.user.SignUpModel
import br.com.questv.model.user.UserModel
import br.com.questv.ui.contribution.model.ContributionModel
import br.com.questv.ui.user.RankableModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiRest {

  @GET("/series")
  fun getAllSeries(): Call<ArrayList<SeriesModel>>

  @GET("/series/{questionableId}/seasons")
  fun getAllSeasonsBySeries(@Path("questionableId") seriesId: String): Call<ArrayList<SeasonModel>>


  @GET("/series/{questionableId}/seasons/{seasonNumber}/episodes")
  fun getAllEpisodesBySeason(@Path("questionableId") seriesId: String,
                             @Path("seasonNumber") seasonNumber: String,
                             @Header("Authorization") auth: String): Call<ArrayList<EpisodeModel>>

  @GET("/questionable/{questionableId}/questions?recursive=true")
  fun getAllQuestionsOfQuestionable(@Path("questionableId") questionableId: String,
                                    @Header("Authorization") auth: String): Call<ArrayList<QuestionModel>>

  @POST("/login")
  fun login(@Body login: LoginModel): Call<ResponseBody>

  @POST("/users/sign-up")
  fun signUp(@Body signUpModel: SignUpModel): Call<ResponseBody>


  @GET("/users/{userId}")
  fun getUserById(@Path("userId") userId: String,
                  @Header("Authorization") auth: String): Call<UserModel>

  @GET("/users?ranked=true")
  fun getRankedUsers(@Header("Authorization") auth: String): Call<List<RankableModel>>


  @POST("/questionable/{questionableId}/questions")
  fun postQuestion(@Path("questionableId") id: String,
                   @Header("authorization") auth: String,
                   @Body questionModel: QuestionModel): Call<ResponseBody>


  @PUT("/questionable/{questionableId}/questions/{questionId}")
  fun putQuestion(@Path("questionableId") questionableId: String,
                  @Path("questionId") questionId: String,
                  @Body questionModel: QuestionModel,
                  @Header("Authorization") auth: String): Call<ResponseBody>

  @PUT("/series/{seriesId}")
  fun putSeries(@Path("seriesId") seriesId: String,
                @Body seriesModel: SeriesModel,
                @Header("Authorization") auth: String): Call<ResponseBody>

  @GET("/user/{userId}/analytics")
  fun getUserAnalytics(@Path("userId") userId: String,
                       @Header("Authorization") auth: String): Call<AnalyticsModel>
}