package br.com.questv.model.question.manager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.questv.model.question.QuestionModel
import br.com.questv.resource.Strings.*
import br.com.questv.ui.question.QuestionFragment
import br.com.questv.ui.question.manager.QuestionManagerView
import java.io.Serializable

class QuestionManagerAdapter(
  private val questions: List<QuestionModel>,
  fragmentManager: FragmentManager,
  private val questionManagerView: QuestionManagerView
) :
  FragmentStatePagerAdapter(fragmentManager),
  Serializable,
  QuestionFragment.OnQuestionInteractionListener {

  private val fragmentList = mutableListOf<Fragment>()

  init {
    feedFragmentList()
  }

  private fun feedFragmentList() {
    for ((position, questionModel) in this.questions.withIndex()) {
      val questionFragment = QuestionFragment()
      val bundle = Bundle()
      bundle.putSerializable(QUESTION_KEY, questionModel)
      bundle.putSerializable(QUESTION_MANAGER_FRAGMENT_KEY, this)
      bundle.putInt(QUESTION_FRAGMENT_POSITION, position)

      questionFragment.arguments = bundle
      this.fragmentList.add(questionFragment)
    }
  }

  override fun getItem(position: Int) = this.fragmentList[position]

  override fun getCount() = this.fragmentList.size

  override fun onCorrectAnswered(currentIndex: Int) {
    this.questionManagerView.disableCurrentQuestion(currentIndex)
    this.questionManagerView.navigateToNextQuestion(currentIndex)
    println("onCorrectAnswered")
  }

  override fun onWrongAnswered(currentIndex: Int) {
    this.questionManagerView.disableCurrentQuestion(currentIndex)
    this.questionManagerView.navigateToNextQuestion(currentIndex)
    println("onWrongAnswered")
  }

  override fun onClickPreviousQuestion(currentIndex: Int) {
    this.questionManagerView.navigateToPreviousQuestion(currentIndex)
    println("onClickPreviousQuestion")
  }

  override fun onClickNextQuestion(currentIndex: Int) {
    this.questionManagerView.navigateToNextQuestion(currentIndex)
    println("onClickNextQuestion")
  }


}