package br.com.questv.ui.splash

import br.com.questv.endpoint.ApiClient
import br.com.questv.model.series.SeriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashInteractor {
  interface OnSeriesConsumptionListener {
    fun onSeriesConsumptionSuccess(series: List<SeriesModel>?)
    fun onSeriesConsumptionFail()
    fun onUnavailableService(message: String)
  }

  fun consumeSeriesApi(listener: OnSeriesConsumptionListener) {
    val consumptionCall: Call<ArrayList<SeriesModel>> = ApiClient.instance.getAllSeries()
    consumptionCall.enqueue(object : Callback<ArrayList<SeriesModel>> {
      override fun onFailure(call: Call<ArrayList<SeriesModel>>, t: Throwable) {
        t.printStackTrace()
        listener.onUnavailableService(t.message.toString())
      }

      override fun onResponse(call: Call<ArrayList<SeriesModel>>, response: Response<ArrayList<SeriesModel>>) {
        if (response.isSuccessful) {
          val series: ArrayList<SeriesModel>? = response.body()
          listener.onSeriesConsumptionSuccess(series)

        } else {
          listener.apply {
            when(response.code()) {
              503 -> onUnavailableService(response.message())
            }
          }
        }
      }
    })
  }
}