package br.com.questv.ui.series

import br.com.questv.endpoint.ApiClient
import br.com.questv.model.season.SeasonModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesInteractor {
  interface OnSeriesConsumptionListener {
    fun onConsumptionSuccess(seasonList: ArrayList<SeasonModel>)
    fun onConsumptionFail(throwable: Throwable)
  }


  fun consumeSeasonApi(seriesId: Long, listener: OnSeriesConsumptionListener) {
    val consumptionCall: Call<ArrayList<SeasonModel>> = ApiClient.instance.getAllSeasonsBySeries(seriesId)
    consumptionCall.enqueue(object : Callback<ArrayList<SeasonModel>> {
      override fun onFailure(call: Call<ArrayList<SeasonModel>>, throwable: Throwable) {
        listener.onConsumptionFail(throwable)
      }

      override fun onResponse(
        call: Call<ArrayList<SeasonModel>>,
        response: Response<ArrayList<SeasonModel>>
      ) {

        response.body()?.let {
          listener.onConsumptionSuccess(it)
        }
      }
    })
  }
}