package br.com.questv.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R
import br.com.questv.contract.OnItemClickListener
import br.com.questv.model.series.SeriesListAdapter
import br.com.questv.model.series.SeriesModel
import br.com.questv.resource.Strings.SERIES_KEY

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

  override fun onClick(item: SeriesModel) {
    navigateToSeriesDetails(item)
  }

  override fun navigateToSeriesDetails(seriesModel: SeriesModel) {
    val bundle = Bundle()
    bundle.putSerializable(SERIES_KEY, seriesModel)
    NavHostFragment.findNavController(this).navigate(R.id.seriesFragment, bundle)
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
