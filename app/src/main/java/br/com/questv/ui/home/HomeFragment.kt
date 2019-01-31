package br.com.questv.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import br.com.questv.R
import br.com.questv.model.series.SeriesListAdapter
import br.com.questv.model.series.dto.SeriesDTO
import br.com.questv.ui.user.UserFragment

class HomeFragment : Fragment(), HomeView {

  private lateinit var recyclerView: RecyclerView
  private var presenter = HomePresenter(this, HomeInteractor())

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    val view = inflater.inflate(R.layout.activity_home, container, false)
    this.recyclerView = view.findViewById(R.id.rv_home)
    setHasOptionsMenu(true)
    return view
  }

  override fun initRecyclerView(series:  List<SeriesDTO>?) {
    this.recyclerView.layoutManager = (LinearLayoutManager(context))
    this.recyclerView.adapter = SeriesListAdapter(series)
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_home, menu)
    val menuItem:MenuItem? = menu?.findItem(R.id.sv_series_search)
    val searchView: SearchView = menuItem?.actionView as SearchView
    presenter.setupSearchViewBehavior(searchView)
    super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when {
      item?.itemId == R.id.mi_user_menu -> navigateToUserAccount()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun navigateToUserAccount() {
    fragmentManager?.beginTransaction()
      ?.setCustomAnimations(R.animator.slide_in_top, R.animator.slide_out_bottom, 0, 0)
      ?.replace(R.id.fm_main_frame, UserFragment())
      ?.commit()
  }

  override fun showProgress() {
    println("Loading...")
  }

  override fun hideProgress() {
    println("Stop loading...")
  }

  override fun showErrorPage() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
