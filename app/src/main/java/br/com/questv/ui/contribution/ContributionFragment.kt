package br.com.questv.ui.contribution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.questv.R
import br.com.questv.ui.contribution.model.ContributionAnswerModel
import br.com.questv.ui.contribution.model.ContributionModel
import kotlinx.android.synthetic.main.contribution_fragment.*

class ContributionFragment:
  Fragment(),
  ContributionView {

  private val presenter = ContributionPresenter(this)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.contribution_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    bt_cont_submit.setOnClickListener {
      val answer1 = ContributionAnswerModel(et_cont_answer_1.text.toString(), cb_is_correct_a1.isChecked)
      val answer2 = ContributionAnswerModel(et_cont_answer_2.text.toString(), cb_is_correct_a2.isChecked)
      val answer3 = ContributionAnswerModel(et_cont_answer_3.text.toString(), cb_is_correct_a3.isChecked)
      val answer4 = ContributionAnswerModel(et_cont_answer_4.text.toString(), cb_is_correct_a4.isChecked)

      val contribution = ContributionModel(et_cont_description.text.toString(), answer1, answer2, answer3, answer4)

      presenter.submitContribution(contribution)
    }
    super.onViewCreated(view, savedInstanceState)
  }

  override fun setDescriptionError(message: String) {
    et_cont_description.error = message
  }

  override fun showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
  }
}