package br.com.questv.ui.ranking

import br.com.questv.contract.Rankable
import br.com.questv.endpoint.ApiClient
import br.com.questv.ui.user.RankableModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankingInteractor {
  interface OnRankingConsumptionListener {
    fun onSuccess(list: List<Rankable>)
    fun onFailure(message: String)
  }


  fun consumeUserRanking(auth: String, listener: OnRankingConsumptionListener) {
    val rankedCall = ApiClient.instance.getRankedUsers(auth)
    rankedCall.enqueue(object : Callback<List<RankableModel>> {
      override fun onFailure(call: Call<List<RankableModel>>, t: Throwable) {
        listener.onFailure("onRateSendFailure $t.message.toString()")
      }

      override fun onResponse(
        call: Call<List<RankableModel>>,
        response: Response<List<RankableModel>>
      ) {
        if (response.isSuccessful) {
          listener.onSuccess(response.body()!!)
        } else {
          listener.onFailure("!isSuccessful $response.message()")
        }
      }
    })
  }
}