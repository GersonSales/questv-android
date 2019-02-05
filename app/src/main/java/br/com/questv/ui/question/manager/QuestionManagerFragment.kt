package br.com.questv.ui.question.manager

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import br.com.questv.R
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.question.manager.QuestionManagerAdapter
import br.com.questv.resource.Strings
import br.com.questv.resource.Strings.*
import br.com.questv.ui.ScoreFragment
import br.com.questv.ui.home.HomeFragment
import br.com.questv.ui.series.SeriesBgFragment
import br.com.questv.ui.series.SeriesFragment
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

    val view = inflater.inflate(R.layout.fragment_question_manager, container, false)
    initNavigatorButtons(view)
    return view
  }

  override fun initViewPager(questions: List<QuestionModel>) {
    questionManagerAdapter = QuestionManagerAdapter(questions, fragmentManager!!, this)
    vp_question_swapper.adapter = questionManagerAdapter
  }

  private fun initNavigatorButtons(view: View) {
    val previousQuestionButton: ImageButton = view.findViewById(R.id.ib_previous_question)
    previousQuestionButton.setOnClickListener { navigateToPreviousQuestion(vp_question_swapper.currentItem) }

    val nextQuestionButton: ImageButton = view.findViewById(R.id.ib_next_question)
    nextQuestionButton.setOnClickListener { navigateToNextQuestion(vp_question_swapper.currentItem) }
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
    } else {
      navigateToItsCaller()
    }
  }

  override fun navigateToNextQuestion(currentIndex: Int) {
    val nextIndex = currentIndex + 1
    if (isAValidIndex(nextIndex)) {
      navigateToIndexDelayed(nextIndex)
    } else {
      navigateToScore()
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
    fragmentManager
      ?.beginTransaction()
      ?.replace(R.id.fl_series_screen, ScoreFragment())
      ?.commit()
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