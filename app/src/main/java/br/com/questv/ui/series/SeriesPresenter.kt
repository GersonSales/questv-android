package br.com.questv.ui.series

import br.com.questv.model.season.SeasonModel
import br.com.questv.model.series.SeriesModel

class SeriesPresenter(private var seriesView: SeriesView,
                      private val seriesInteractor: SeriesInteractor) : SeriesInteractor.OnSeriesConsumptionListener{
  fun fetchAllSeasons(seriesModel: SeriesModel) {
    seriesView.showProgress()
    this.seriesInteractor.consumeSeasonApi(seriesModel.id, this)
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
}