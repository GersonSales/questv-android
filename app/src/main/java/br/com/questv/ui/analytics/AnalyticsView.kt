package br.com.questv.ui.analytics

import br.com.questv.model.analytics.AnalyticsModel

interface AnalyticsView {
  fun showProgress()
  fun hideProgress()
  fun populateCharts(analyticsModel: AnalyticsModel)
  fun showErrorMessage(message: String)
}