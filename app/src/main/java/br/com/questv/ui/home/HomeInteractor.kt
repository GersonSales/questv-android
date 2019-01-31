package br.com.questv.ui.home

import android.content.Context
import android.os.AsyncTask
import br.com.questv.endpoint.ApiClient
import br.com.questv.model.series.dto.SeriesDTO
import br.com.questv.util.FileUtil
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeInteractor {
  interface OnSeriesConsumptionListener {
    fun onConsumptionSuccess(series: List<SeriesDTO>?)
    fun onConsumptionFail()
    fun onCoverConsumptionfail(series: SeriesDTO)
    fun onCoverConsumptionSuccess(series: SeriesDTO)
  }

  fun consumeSeriesApi(listener: OnSeriesConsumptionListener) {
    val consumptionCall: Call<ArrayList<SeriesDTO>> = ApiClient.instance.getAllSeries()
    consumptionCall.enqueue(object : Callback<ArrayList<SeriesDTO>> {
      override fun onFailure(call: Call<ArrayList<SeriesDTO>>, t: Throwable) {
        t.printStackTrace()
        listener.onConsumptionFail()
      }

      override fun onResponse(
        call: Call<ArrayList<SeriesDTO>>,
        response: Response<ArrayList<SeriesDTO>>
      ) {
        if (response.isSuccessful) {
          val series: ArrayList<SeriesDTO>? = response.body()
          listener.onConsumptionSuccess(series)

        } else {
          listener.onConsumptionFail()
        }

      }
    })
  }

  fun consumeSeriesCovers(series: List<SeriesDTO>?, listener: OnSeriesConsumptionListener, context: Context?) {
    for (seriesItem in series!!) {
      if (!seriesItem.coverImage.isEmpty()) {

        val coverConsumptionCall: Call<ResponseBody> = ApiClient.instance.getSeriesCover(seriesItem.id.toString())
        coverConsumptionCall.enqueue(object : Callback<ResponseBody> {
          override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
            listener.onCoverConsumptionfail(seriesItem)
          }

          override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
              seriesItem.categoryUri = FileUtil.writeResponseBodyToDisk(response.body(), context).path!!
              println("Here: " + seriesItem.coverImage)
            } else {
              listener.onCoverConsumptionfail(seriesItem)
            }
          }
        })
      }
    }
  }
}