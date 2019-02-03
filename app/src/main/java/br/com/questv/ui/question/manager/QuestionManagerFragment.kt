package br.com.questv.ui.question.manager

import android.os.Bundle
import android.os.Handler
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
  private lateinit var questionManagerAdapter: QuestionManagerAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {


    val questionOwnerId: Long = arguments!!.getLong(QUESTION_OWNER_ID)

    presenter.fetchAllQuestions(questionOwnerId)

    return inflater.inflate(R.layout.fragment_question_manager, container, false)
  }

  override fun initViewPager(questions: List<QuestionModel>) {
    questionManagerAdapter = QuestionManagerAdapter(questions, fragmentManager!!, this)
    vp_question_swapper.adapter = questionManagerAdapter
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

  override fun navigateToPreviousQuestion(currentIndex: Int) {
    val previous = currentIndex - 1
    if (isAValidIndex(previous)) {
      navigateToIndexDelayed(previous)
    }else {
      navigateToItsCaller()
    }
  }

  override fun navigateToNextQuestion(currentIndex: Int) {
    val nextIndex = currentIndex + 1
    if (isAValidIndex(nextIndex)) {
      navigateToIndexDelayed(nextIndex)
    }
  }

  private fun navigateToIndexDelayed(index: Int) {
    Handler().postDelayed(
      object : Thread() {
        override fun run() {
          vp_question_swapper.currentItem = index
        }
      }, 0
    )
  }

  override fun navigateToScore() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun navigateToItsCaller() {
    fragmentManager?.popBackStack()
  }

  override fun disableCurrentQuestion(currentIndex: Int) {
  }


  private fun isAValidIndex(index: Int): Boolean {
    val count = this.questionManagerAdapter.count
    return count > 0
        && (index in 0..(count - 1))
  }
}