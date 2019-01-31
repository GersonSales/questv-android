package br.com.questv.ui.home

import br.com.questv.endpoint.ApiClient
import br.com.questv.model.series.dto.SeriesDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class HomeInteractor {
  interface OnSeriesConsumptionListener {
    fun onConsumptionSuccess(series: List<SeriesDTO>?)
    fun onConsumptionFail()
  }

  fun consumeSeriesApi(listener: OnSeriesConsumptionListener) {
    val consumptionCall: Call<ArrayList<SeriesDTO>> = ApiClient.instance.getAllSeries()
    consumptionCall.enqueue(object : Callback<ArrayList<SeriesDTO>> {
      override fun onFailure(call: Call<ArrayList<SeriesDTO>>, t: Throwable) {
//        listener.onConsumptionFail()
        t.printStackTrace()
        throw RuntimeException(t)
      }

      override fun onResponse(call: Call<ArrayList<SeriesDTO>>,
                              response: Response<ArrayList<SeriesDTO>>) {
        val series: ArrayList<SeriesDTO>? = response.body()
        listener.onConsumptionSuccess(series)
      }
    })
  }
}