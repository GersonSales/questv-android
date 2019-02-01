package br.com.questv.ui.splash

import android.content.Context
import br.com.questv.endpoint.ApiClient
import br.com.questv.model.series.dto.SeriesDTO
import br.com.questv.util.FileUtil
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashInteractor {
  interface OnSeriesConsumptionListener {
    fun onSeriesConsumptionSuccess(series: List<SeriesDTO>?)
    fun onSeriesConsumptionFail()
    fun onSeriesCoverConsumptionFail(series: SeriesDTO)
    fun onSeriesCoverConsumptionSuccess(series: SeriesDTO)
    fun onSeriesCoverConsumptionFinished()
  }

  fun consumeSeriesApi(listener: SplashInteractor.OnSeriesConsumptionListener) {
    val consumptionCall: Call<ArrayList<SeriesDTO>> = ApiClient.instance.getAllSeries()
    consumptionCall.enqueue(object : Callback<ArrayList<SeriesDTO>> {
      override fun onFailure(call: Call<ArrayList<SeriesDTO>>, t: Throwable) {
        t.printStackTrace()
        listener.onSeriesConsumptionFail()
      }

      override fun onResponse(
        call: Call<ArrayList<SeriesDTO>>,
        response: Response<ArrayList<SeriesDTO>>
      ) {
        if (response.isSuccessful) {
          val series: ArrayList<SeriesDTO>? = response.body()
          listener.onSeriesConsumptionSuccess(series)

        } else {
          listener.onSeriesConsumptionFail()
        }

      }
    })
  }

  fun consumeSeriesCovers(
    series: List<SeriesDTO>?,
    listener: SplashInteractor.OnSeriesConsumptionListener,
    context: Context?
  ) {
    for (seriesItem in series!!) {
      if (!seriesItem.coverImage.isEmpty()) {

        val coverConsumptionCall: Call<ResponseBody> = ApiClient.instance.getSeriesCover(seriesItem.id.toString())
        coverConsumptionCall.enqueue(object : Callback<ResponseBody> {
          override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
            listener.onSeriesCoverConsumptionFail(seriesItem)
          }

          override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
              seriesItem.coverImageUri = FileUtil.writeResponseBodyToDisk(response.body(), context)!!
              println("Here: " + seriesItem.coverImage)
            } else {
              listener.onSeriesCoverConsumptionFail(seriesItem)
            }
          }
        })
      }
    }
    listener.onSeriesCoverConsumptionFinished()
  }
}