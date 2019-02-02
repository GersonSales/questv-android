package br.com.questv.ui.series

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import br.com.questv.R
import br.com.questv.model.season.SeasonAdapter
import br.com.questv.model.season.SeasonModel
import br.com.questv.model.series.SeriesModel
import br.com.questv.resource.Strings.SERIES_KEY
import br.com.questv.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_series.*

class SeriesFragment : Fragment(), SeriesView {

  private val presenter = SeriesPresenter(this, SeriesInteractor())
  private lateinit var progressBar: ProgressBar

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_series, container, false)
    val seriesName: TextView = view.findViewById(R.id.tv_series_details_name)

    this.progressBar = view.findViewById(R.id.pb_season_recycler)


    val seriesModel: SeriesModel = arguments?.get(SERIES_KEY) as SeriesModel

    seriesName.text = seriesModel.name
    setHasOptionsMenu(true)
    this.presenter.fetchAllSeasons(seriesModel)

    return view
  }


  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_series_details, menu)
    super.onPrepareOptionsMenu(menu)
  }

  override fun navigateToHome() {
    fragmentManager?.beginTransaction()
      ?.remove(this)
      ?.setCustomAnimations(R.animator.fade_out, R.animator.fade_out, 0, 0)
      ?.replace(R.id.fm_main_frame2, HomeFragment())
      ?.commit()
  }

  override fun showProgress() {
    progressBar.visibility = VISIBLE
  }

  override fun hideProgress() {
    progressBar.visibility = GONE
  }

  override fun showErrorMessage(message: String?) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
  }

  override fun initSeasonRecycler(seasonList: ArrayList<SeasonModel>) {
    rv_series_seasons_details.layoutManager = (LinearLayoutManager(context))
    rv_series_seasons_details.adapter = SeasonAdapter(seasonList)
  }
}
