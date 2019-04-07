package br.com.questv.ui.contribution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import br.com.questv.model.series.SeriesModel
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.resource.Strings.SERIES_KEY
import br.com.questv.ui.contribution.model.ContributionAnswerModel
import br.com.questv.ui.contribution.model.ContributionModel
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.contribution_fragment.*
import kotlinx.android.synthetic.main.fragment_login.*

class ContributionFragment:
  Fragment(),
  ContributionView {

  private val presenter = ContributionPresenter(this)
  private  lateinit var seriesModel: SeriesModel
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
      val answer1 = ContributionAnswerModel(et_cont_answer_1.text.toString(), cb_is_correct_a1.isChecked)
      val answer2 = ContributionAnswerModel(et_cont_answer_2.text.toString(), cb_is_correct_a2.isChecked)
      val answer3 = ContributionAnswerModel(et_cont_answer_3.text.toString(), cb_is_correct_a3.isChecked)
      val answer4 = ContributionAnswerModel(et_cont_answer_4.text.toString(), cb_is_correct_a4.isChecked)

      val contribution = ContributionModel(et_cont_description.text.toString(), 1, answer1, answer2, answer3, answer4)

      val auth = UserLocalStorage(context!!).getLoggedUserToken()
      presenter.submitContribution(seriesModel.getId(), auth, contribution)
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