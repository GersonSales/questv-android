package br.com.questv.ui.contribution

import android.os.Bundle
import android.text.BoringLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.series.SeriesModel
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.resource.Strings.SERIES_KEY
import br.com.questv.ui.contribution.model.ContributionAnswerModel
import br.com.questv.ui.contribution.model.ContributionModel
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.contribution_fragment.*
import kotlinx.android.synthetic.main.fragment_login.*

class ContributionFragment :
  Fragment(),
  ContributionView {

  private val presenter = ContributionPresenter(this)
  private lateinit var seriesModel: SeriesModel
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.contribution_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    seriesModel = arguments?.getSerializable(SERIES_KEY) as SeriesModel
    initBgImage()
    bindSubmitListener()
    super.onViewCreated(view, savedInstanceState)
  }

  private fun bindSubmitListener() {
    bt_cont_submit.setOnClickListener {
      val _answers: HashMap<Long, HashMap<String, Boolean>> = HashMap()

      val answer1: HashMap<String, Boolean> = HashMap()
      val answer1Text = et_cont_answer_1.text.toString()
      if (answer1Text.isNotEmpty())
        answer1[answer1Text] = cb_is_correct_a1.isChecked

      val answer2: HashMap<String, Boolean> = HashMap()
      val answer2Text = et_cont_answer_2.text.toString()
      if (answer2Text.isNotEmpty())
        answer2[answer2Text] = cb_is_correct_a2.isChecked

      val answer3: HashMap<String, Boolean> = HashMap()
      val answer3Text = et_cont_answer_3.text.toString()
      if (answer3Text.isNotEmpty())
        answer3[answer3Text] = cb_is_correct_a3.isChecked

      val answer4: HashMap<String, Boolean> = HashMap()
      val answer4Text = et_cont_answer_4.text.toString()
      if (answer3Text.isNotEmpty())
        answer4[answer3Text] = cb_is_correct_a4.isChecked

      _answers[1L] = answer1
      _answers[2L] = answer2
      _answers[3L] = answer3
      _answers[4L] = answer4

      val description = et_cont_description.text.toString()
      val question = QuestionModel(1L, seriesModel.getId(), description, 1, 1L, 1.0, _answers, false)

      val auth = UserLocalStorage(context!!).getLoggedUserToken()
      presenter.submitContribution(auth!!, question)
    }
  }

  private fun initBgImage() {
    val url = seriesModel.getCoverImageUrl()?.replace("localhost", "10.0.2.2")

    val imageLoader = ImageLoader.getInstance()
    imageLoader.displayImage(url, iv_contribution_bg)
    iv_login_bg?.scaleType = ImageView.ScaleType.CENTER_CROP
  }

  override fun setDescriptionError(message: String) {
    et_cont_description.error = message
  }

  override fun showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
  }

  override fun navigateToBack() {
    NavHostFragment.findNavController(this).popBackStack()
  }
}