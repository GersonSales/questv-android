package br.com.questv.ui.series

import br.com.questv.endpoint.ApiClient
import br.com.questv.model.season.SeasonModel
import br.com.questv.model.series.SeriesModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesInteractor {
  interface OnSeriesConsumptionListener {
    fun onConsumptionSuccess(seasonList: ArrayList<SeasonModel>)
    fun onConsumptionFail(throwable: Throwable)
  }

  fun consumeSeasonApi(seriesId: String, listener: OnSeriesConsumptionListener) {
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


  interface OnSeriesLikeListener {
    fun onSeriesLikeSuccess()
    fun onSeriesLikeFailure(message: String)
  }

  fun likeSeries(seriesModel: SeriesModel, auth: String, listener: OnSeriesLikeListener) {

    val putSeriesCall = ApiClient.instance.putSeries(seriesModel.getId(), seriesModel, auth)
    putSeriesCall.enqueue(object : Callback<ResponseBody> {
      override fun onFailure(
        call: Call<ResponseBody>,
        t: Throwable
      ) {
        listener.onSeriesLikeFailure(t.message.toString())
      }

      override fun onResponse(
        call: Call<ResponseBody>,
        response: Response<ResponseBody>
      ) {
        if (response.isSuccessful) {
          listener.onSeriesLikeSuccess()
        } else {
          listener.onSeriesLikeFailure(response.message())
        }
      }
    })

  }
}