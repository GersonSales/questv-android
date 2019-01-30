package br.com.questv.ui.home

import android.support.v7.widget.SearchView

class HomePresenter(var homeView: HomeView?, private val homeInteractor: HomeInteractor) {
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