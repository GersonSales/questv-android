package br.com.questv.ui.series

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.questv.R
import br.com.questv.contract.Questionable
import br.com.questv.model.season.SeasonAdapter
import br.com.questv.model.season.SeasonModel
import br.com.questv.model.season.SeasonViewHolder
import br.com.questv.model.series.SeriesModel
import br.com.questv.resource.Strings.QUESTIONABLE_ID
import br.com.questv.resource.Strings.SERIES_KEY
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.series_details.*

class SeriesFragment : Fragment(), SeriesView, SeasonViewHolder.OnInteractionListener {


  private val presenter = SeriesPresenter(this, SeriesInteractor())
  private lateinit var progressBar: ProgressBar
  private lateinit var seriesModel: SeriesModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.series_details, container, false)
    seriesModel = arguments?.get(SERIES_KEY) as SeriesModel
    initView(view, seriesModel)
    this.presenter.fetchAllSeasons(seriesModel)

    return view
  }

  private fun initView(view: View, seriesModel: SeriesModel) {
    setHasOptionsMenu(true)
    initViewImageView(view)
    val playSeriesQuestions = view.findViewById<ImageButton>(R.id.ib_play_series_questions)
    playSeriesQuestions.setOnClickListener { navigateToQuestionManager(seriesModel) }


    val seriesName: TextView = view.findViewById(R.id.tv_series_details_name)
    seriesName.text = seriesModel.name

    val seriesCategory: TextView = view.findViewById(R.id.tv_series_details_category)
    seriesCategory.text = seriesModel.category

    this.progressBar = view.findViewById(R.id.pb_season_recycler)
  }


  private fun initViewImageView(view: View) {
    val seriesPromo: ImageView = view.findViewById(R.id.iv_series_details_promo2)
    val imageLoader = ImageLoader.getInstance()
    val imageUrl = when {
      !seriesModel.getPromoImageUrl()!!.isEmpty() -> seriesModel.getPromoImageUrl()
      else -> seriesModel.getCoverImageUrl()
    }

    imageLoader.displayImage(imageUrl!!.replace("localhost", "10.0.2.2"), seriesPromo)
    seriesPromo.scaleType = ImageView.ScaleType.CENTER_CROP
  }


  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_series_details, menu)
    super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item!!.itemId) {
      R.id.mi_contribution -> navigateToSeriesContribution()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun navigateToHome() {
    println("navigateToHome")
  }


  override fun navigateToSeriesContribution() {
    NavHostFragment.findNavController(this).navigate(R.id.nvi_contribution)
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
    rv_series_seasons_details.adapter = SeasonAdapter(seasonList, this)
    rv_series_seasons_details.setHasFixedSize(true)
  }

  override fun navigateToQuestionManager(questionable: Questionable) {
    val bundle = Bundle()
    bundle.putSerializable(QUESTIONABLE_ID, questionable)
    bundle.putAll(arguments)

    NavHostFragment.findNavController(this).navigate(R.id.questionManagerFragment, bundle)
  }

  override fun onClickPlayListener(seasonModel: SeasonModel) {
    navigateToQuestionManager(seasonModel)
  }
}
