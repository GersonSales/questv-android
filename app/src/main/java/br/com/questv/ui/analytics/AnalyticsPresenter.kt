package br.com.questv.ui.analytics

import br.com.questv.ui.analytics.model.AnalyticsModel

class AnalyticsPresenter(private val itemView: AnalyticsView) : AnalyticsInteractor.OnAnalyticsGathering {
  private var interactor = AnalyticsInteractor()


  fun gatheringUserAnalytics(userId: String, auth: String){
    this.itemView.showProgress()
    this.interactor.gatheringAnalytics(userId, auth, this)
  }


  override fun onAnalyticsGatheringSuccess(
    analyticsModel: AnalyticsModel
  ) {
    this.itemView.hideProgress()
    this.itemView.populateCharts(analyticsModel)
  }

  override fun onAnalyticsGatheringFailure(
    message: String
  ) {
    this.itemView.hideProgress()
    this.itemView.showErrorMessage(message)
  }
}