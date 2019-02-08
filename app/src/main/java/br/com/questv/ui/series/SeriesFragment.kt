package br.com.questv.ui.series

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import br.com.questv.contract.Questionable
import br.com.questv.model.season.SeasonAdapter
import br.com.questv.model.season.SeasonModel
import br.com.questv.model.season.SeasonViewHolder
import br.com.questv.model.series.SeriesModel
import br.com.questv.resource.Strings.*
import br.com.questv.ui.home.HomeFragment
import br.com.questv.ui.question.manager.QuestionManagerFragment
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

  override fun navigateToHome() {
//    fragmentManager?.beginTransaction()
//      ?.remove(this)
//      ?.setCustomAnimations(R.animator.fade_out, R.animator.fade_out, 0, 0)
//      ?.replace(R.id.fl_main_frame2, HomeFragment())
//      ?.commit()
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
//    val questionManagerFragment = QuestionManagerFragment()
//    val bundle = Bundle()
//    bundle.putSerializable(QUESTIONABLE_ID, seriesModel)
//    bundle.putAll(arguments)
//    questionManagerFragment.arguments = bundle
//
//    fragmentManager
//      ?.beginTransaction()
//      ?.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, 0, 0)
//      ?.replace(R.id.fl_main_frame2, questionManagerFragment)
//      ?.addToBackStack(SERIES_FRAGMENT_TAG)
//      ?.commit()
  }

  override fun onClickPlayListener(seasonModel: SeasonModel) {
    navigateToQuestionManager(seasonModel)
  }
}
