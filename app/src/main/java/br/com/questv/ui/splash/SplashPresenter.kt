package br.com.questv.ui.splash

import br.com.questv.model.series.SeriesMock
import br.com.questv.model.series.SeriesModel

class SplashPresenter(var splashView: SplashView?,
                      private val splashInteractor: SplashInteractor) : SplashInteractor.OnSeriesConsumptionListener {


  private fun fetchAllSeries() {
    this.splashView?.showProgress()
    this.splashInteractor.consumeSeriesApi(this)
  }

  override fun onSeriesConsumptionSuccess(series: List<SeriesModel>?) {
    SeriesMock.getInstance().addAll(series)
    this.splashInteractor.consumeSeriesCovers(series, this, splashView?.getContext())
  }

  override fun onSeriesConsumptionFail() {
    this.splashView?.hideProgress()
    this.splashView?.navigateToErrorPage()
  }

  override fun onSeriesCoverConsumptionFail(series: SeriesModel) {
  }

  override fun onSeriesCoverConsumptionSuccess(series: SeriesModel) {
    SeriesMock.getInstance().update(series)
  }

  override fun onSeriesCoverConsumptionFinished() {
    this.splashView?.hideProgress()
    this.splashView?.navigateToMainActivity()
  }
}