package br.com.questv.ui.question.manager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.questv.R
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.question.manager.QuestionManagerAdapter
import br.com.questv.resource.Strings.QUESTION_OWNER_ID
import kotlinx.android.synthetic.main.fragment_question_manager.*

class QuestionManagerFragment : Fragment(), QuestionManagerView {

  private val presenter = QuestionManagerPresenter(this, QuestionManagerInteractor())

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {


    val questionOwnerId: Long = arguments!!.getLong(QUESTION_OWNER_ID)

    presenter.fetchAllQuestions(questionOwnerId)

    return inflater.inflate(R.layout.fragment_question_manager, container, false)
  }

  override fun initViewPager(questions: List<QuestionModel>) {
    vp_question_swapper.adapter = QuestionManagerAdapter(questions, fragmentManager!!)
  }

  override fun showProgress() {
    print("Loading...")
  }

  override fun hideProgress() {
    print("Stop Loading...")
  }

  override fun showErrorMessage(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
  }
}