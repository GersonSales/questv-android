package br.com.questv.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import br.com.questv.contract.OnItemClickListener
import br.com.questv.model.series.SeriesListAdapter
import br.com.questv.model.series.SeriesModel
import br.com.questv.resource.Strings.HOME_FRAGMENT_TAG
import br.com.questv.resource.Strings.SERIES_KEY
import br.com.questv.ui.user.UserFragment

class HomeFragment : Fragment(), HomeView, OnItemClickListener<SeriesModel> {

  private lateinit var recyclerView: RecyclerView
  private lateinit var presenter: HomePresenter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    setHasOptionsMenu(true)

    val view = inflater.inflate(R.layout.fragment_home, container, false)

    this.recyclerView = view.findViewById(R.id.rv_home)
    this.presenter = HomePresenter(this, HomeInteractor())

    initRecyclerView()
    return view
  }

  override fun initRecyclerView() {
    this.recyclerView.layoutManager = (LinearLayoutManager(context))
    this.recyclerView.adapter = SeriesListAdapter(this)
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_home, menu)
    val menuItem: MenuItem? = menu?.findItem(R.id.sv_series_search)
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
      ?.addToBackStack(HOME_FRAGMENT_TAG)
      ?.replace(R.id.fl_main_frame, UserFragment())
      ?.commit()
  }

  override fun onClick(item: SeriesModel) {
    navigateToSeriesDetails(item)
  }

  override fun navigateToSeriesDetails(seriesModel: SeriesModel) {
    val bundle = Bundle()
    bundle.putSerializable(SERIES_KEY, seriesModel)

    NavHostFragment.findNavController(this).navigate(R.id.seriesFragment, bundle)
//    Navigation.createNavigateOnClickListener(R.id.questionManagerFragment, null)
//
//
//    val seriesFragment = SeriesFragment()
//    val bundle = Bundle()
//    bundle.putSerializable(SERIES_KEY, seriesModel)
//    seriesFragment.arguments = bundle
//
//    fragmentManager
//      ?.beginTransaction()
//      ?.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, 0, 0)
//      ?.add(R.id.fl_main_frame2, seriesFragment)
//      ?.addToBackStack(HOME_FRAGMENT_TAG)
//      ?.commit()
  }

  override fun showProgress() {
    println("Loading...")
  }

  override fun hideProgress() {
    println("Stop loading...")
  }

  override fun navigateToErrorPage() {
  }
}
