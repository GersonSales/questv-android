package br.com.questv.ui.splash

import br.com.questv.model.series.SeriesModel
import br.com.questv.model.series.SeriesRepositoryImpl

class SplashPresenter(
  var splashView: SplashView?,
  private val splashInteractor: SplashInteractor
) : SplashInteractor.OnSeriesConsumptionListener {


  fun fetchAllSeries() {
    this.splashView?.showProgress()
    this.splashInteractor.consumeSeriesApi(this)
  }

  override fun onSeriesConsumptionSuccess(series: List<SeriesModel>?) {
    SeriesRepositoryImpl.instance.saveAll(series!!)
    this.splashInteractor.consumeSeriesCovers(series, this, splashView?.getContext())
  }

  override fun onSeriesConsumptionFail() {
    this.splashView?.hideProgress()
    this.splashView?.navigateToErrorPage()
  }

  override fun onSeriesCoverConsumptionFail(series: SeriesModel) {
  }

  override fun onSeriesCoverConsumptionSuccess(series: SeriesModel) {
    SeriesRepositoryImpl.instance.update(series)
  }

  override fun onSeriesCoverConsumptionFinished() {
    this.splashView?.hideProgress()
    this.splashView?.navigateToMainActivity()
  }
}