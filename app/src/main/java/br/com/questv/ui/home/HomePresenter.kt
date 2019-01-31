package br.com.questv.ui.home

import android.support.v7.widget.SearchView
import br.com.questv.model.series.dto.SeriesDTO

class HomePresenter(var homeView: HomeView?, private val homeInteractor: HomeInteractor) :
  HomeInteractor.OnSeriesConsumptionListener{

  init {
    getAllSeries()
  }

  fun setupSearchViewBehavior(searchView: SearchView) {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }

      override fun onQueryTextChange(p0: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }

    })
  }


  private fun getAllSeries() {
    this.homeView?.showProgress()
    this.homeInteractor.consumeSeriesApi(this)
  }


  override fun onConsumptionSuccess(series: List<SeriesDTO>?) {
    homeView?.initRecyclerView(series)
    this.homeView?.hideProgress()
    this.homeInteractor.consumeSeriesCovers(series, this, homeView?.getContext())
  }

  override fun onConsumptionFail() {
    this.homeView?.showErrorPage()
  }

  override fun onCoverConsumptionfail(series: SeriesDTO) {
    TODO("not implemented onCoverConsumptionfail") //To change body of created functions use File | Settings | File Templates.

  }

  override fun onCoverConsumptionSuccess(series: SeriesDTO) {
    TODO("not implemented onCoverConsumptionSuccess") //To change body of created functions use File | Settings | File Templates.
  }
}