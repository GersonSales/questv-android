package br.com.questv.ui.analytics

import br.com.questv.ui.analytics.model.AnalyticsModel

interface AnalyticsView {
  fun showProgress()
  fun hideProgress()
  fun populateCharts(analyticsModel: AnalyticsModel)
  fun showErrorMessage(message: String)
}