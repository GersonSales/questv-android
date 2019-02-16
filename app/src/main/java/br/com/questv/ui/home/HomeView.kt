package br.com.questv.ui.home

import br.com.questv.model.series.SeriesModel

interface HomeView {
  fun showProgress()
  fun hideProgress()
  fun initRecyclerView()
  fun navigateToErrorPage()
  fun navigateToSeriesDetails(seriesModel: SeriesModel)
}