package br.com.questv.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import br.com.questv.R
import br.com.questv.model.series.SeriesListAdapter

class HomeFragment : Fragment(), HomeView {

  private lateinit var recyclerView: RecyclerView
  private var presenter = HomePresenter(this, HomeInteractor())

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    val view = inflater.inflate(R.layout.activity_home, container, false)
    this.recyclerView = view.findViewById(R.id.rv_home)
    setHasOptionsMenu(true)
    initRecyclerView()
    return view
  }

  private fun initRecyclerView() {
    this.recyclerView.layoutManager = (LinearLayoutManager(context))
    this.recyclerView.adapter = SeriesListAdapter()
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_main, menu)
    val menuItem:MenuItem? = menu?.findItem(R.id.sv_series_search)
    val searchView: SearchView = menuItem?.actionView as SearchView
    presenter.setupSearchViewBehavior(searchView)
    super.onPrepareOptionsMenu(menu)
  }

}
