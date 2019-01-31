package br.com.questv.ui.home

import android.content.Context
import br.com.questv.model.series.dto.SeriesDTO

interface HomeView {
  fun navigateToUserAccount()
  fun showProgress()
  fun hideProgress()
  fun initRecyclerView(series: List<SeriesDTO>?)
  fun showErrorPage()
  fun getContext(): Context
}