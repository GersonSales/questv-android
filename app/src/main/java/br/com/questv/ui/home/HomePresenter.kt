package br.com.questv.ui.home

import androidx.appcompat.widget.SearchView

class HomePresenter(private var homeView: HomeView?, private val homeInteractor: HomeInteractor) :
  HomeInteractor.OnSeriesConsumptionListener{

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

}