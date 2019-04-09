package br.com.questv.ui.series

import br.com.questv.model.season.SeasonModel
import br.com.questv.model.series.SeriesModel

class SeriesPresenter(
  private var seriesView: SeriesView,
  private val seriesInteractor: SeriesInteractor
) :
  SeriesInteractor.OnSeriesConsumptionListener,
  SeriesInteractor.OnSeriesLikeListener {
  fun fetchAllSeasons(seriesModel: SeriesModel) {
    seriesView.showProgress()
    this.seriesInteractor.consumeSeasonApi(seriesModel.getId(), this)
  }

  override fun onConsumptionSuccess(seasonList: ArrayList<SeasonModel>) {
    seriesView.hideProgress()
    seriesView.initSeasonRecycler(seasonList)
  }

  override fun onConsumptionFail(throwable: Throwable) {
    seriesView.hideProgress()
    seriesView.showErrorMessage(throwable.message)
    seriesView.navigateToHome()
  }

  fun likeSeries(seriesModel: SeriesModel, auth: String) {
    this.seriesInteractor.likeSeries(seriesModel, auth, this)
  }


  override fun onSeriesLikeSuccess() {
    this.seriesView.showToast("success")
  }

  override fun onSeriesLikeFailure(message: String) {
    this.seriesView.showToast(message)
  }
}