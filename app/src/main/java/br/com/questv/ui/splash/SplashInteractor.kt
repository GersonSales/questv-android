package br.com.questv.ui.splash

import android.content.Context
import br.com.questv.endpoint.ApiClient
import br.com.questv.model.series.SeriesModel
import br.com.questv.util.FileUtil
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashInteractor {
  interface OnSeriesConsumptionListener {
    fun onSeriesConsumptionSuccess(series: List<SeriesModel>?)
    fun onSeriesConsumptionFail()
  }

  fun consumeSeriesApi(listener: SplashInteractor.OnSeriesConsumptionListener) {
    val consumptionCall: Call<ArrayList<SeriesModel>> = ApiClient.instance.getAllSeries()
    consumptionCall.enqueue(object : Callback<ArrayList<SeriesModel>> {
      override fun onFailure(call: Call<ArrayList<SeriesModel>>, t: Throwable) {
        t.printStackTrace()
        listener.onSeriesConsumptionFail()
      }

      override fun onResponse(
        call: Call<ArrayList<SeriesModel>>,
        response: Response<ArrayList<SeriesModel>>
      ) {
        if (response.isSuccessful) {
          val series: ArrayList<SeriesModel>? = response.body()
          listener.onSeriesConsumptionSuccess(series)

        } else {
          listener.onSeriesConsumptionFail()
        }
      }
    })
  }


  fun consumeSeriesFiles(
    series: List<SeriesModel>?,
    listener: SplashInteractor.OnSeriesConsumptionListener,
    context: Context?
  ) {
    for (seriesModel in series!!) {
      consumeSeriesCovers(seriesModel, context)
      consumePromoImages(seriesModel, context)
    }
  }


  private fun consumeSeriesCovers(seriesModel: SeriesModel, context: Context?) {
    if (!seriesModel.coverImage.isEmpty()) {
      val coverConsumptionCall: Call<ResponseBody> = ApiClient.instance.getSeriesCover(seriesModel.id.toString())
      coverConsumptionCall.enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
          t.printStackTrace()
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
          if (response.isSuccessful) {
            val fileName = response.headers().get("Content-Name")
//            seriesModel.coverImageUrl = FileUtil.writeResponseBodyToDisk(response.body(), fileName!!, context)!!
          }
        }
      })
    }
  }

  private fun consumePromoImages(seriesModel: SeriesModel, context: Context?) {
    if (!seriesModel.promoImage.isEmpty()) {
      val promoImageConsumptionCall: Call<ResponseBody> = ApiClient.instance.getSeriesPromoImage(seriesModel.id.toString())
      promoImageConsumptionCall.enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
          t.printStackTrace()
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
          if (response.isSuccessful) {
            val fileName = response.headers().get("Content-Name")
            seriesModel.promoImageUri = FileUtil.writeResponseBodyToDisk(response.body(), fileName!!, context)!!
          }
        }
      })
    }
  }
}