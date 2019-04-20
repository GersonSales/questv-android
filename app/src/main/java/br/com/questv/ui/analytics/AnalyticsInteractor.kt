package br.com.questv.ui.analytics

import br.com.questv.endpoint.ApiClient

import br.com.questv.ui.analytics.model.AnalyticsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnalyticsInteractor {
  interface OnAnalyticsGathering {
    fun onAnalyticsGatheringSuccess(analyticsModel: AnalyticsModel)
    fun onAnalyticsGatheringFailure(message: String)
  }


  fun gatheringAnalytics(userId: String, auth: String, listener: OnAnalyticsGathering) {
    val gatheringCall = ApiClient.instance.getUserAnalytics(userId, auth)
    gatheringCall.enqueue(object : Callback<AnalyticsModel> {
      override fun onFailure(
        call: Call<AnalyticsModel>,
        t: Throwable
      ) {
        listener.onAnalyticsGatheringFailure(t.message!!)
      }

      override fun onResponse(
        call: Call<AnalyticsModel>,
        response: Response<AnalyticsModel>
      ) {
        if (response.isSuccessful) {
          response.body().let {
            listener.onAnalyticsGatheringSuccess(it!!)
          }
        } else {
          listener.onAnalyticsGatheringFailure(response.message())
        }
      }
    })


  }


}