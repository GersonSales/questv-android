package br.com.questv.ui.series

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import br.com.questv.R
import br.com.questv.model.season.SeasonAdapter
import br.com.questv.model.season.SeasonModel
import br.com.questv.model.series.SeriesModel
import br.com.questv.resource.Strings.SERIES_KEY
import br.com.questv.ui.home.HomeFragment
import com.nostra13.universalimageloader.core.ImageLoader
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
    val seriesModel: SeriesModel = arguments?.get(SERIES_KEY) as SeriesModel
    initView(view, seriesModel)
    this.presenter.fetchAllSeasons(seriesModel)

    return view
  }

  private fun initView(view: View?, seriesModel: SeriesModel) {
    setHasOptionsMenu(true)
    val seriesPromo: ImageView = view!!.findViewById(R.id.iv_series_details_promo)
    val imageLoader = ImageLoader.getInstance()
    val imageUrl = when {
      !seriesModel.promoImageUrl!!.isEmpty() -> seriesModel.promoImageUrl
      else -> seriesModel.coverImageUrl
    }

    imageLoader.displayImage(imageUrl!!.replace("localhost", "10.0.2.2"), seriesPromo)
    seriesPromo.scaleType = ImageView.ScaleType.CENTER_CROP

    val seriesName: TextView = view.findViewById(R.id.tv_series_details_name)
    seriesName.text = seriesModel.name

    val seriesCategory: TextView = view.findViewById(R.id.tv_series_details_category)
    seriesCategory.text = seriesModel.category

    this.progressBar = view.findViewById(R.id.pb_season_recycler)
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
    rv_series_seasons_details.setHasFixedSize(true)
  }
}
