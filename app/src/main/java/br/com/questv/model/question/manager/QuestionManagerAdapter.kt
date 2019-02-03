package br.com.questv.model.question.manager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.questv.model.question.QuestionModel
import br.com.questv.resource.Strings.QUESTION_KEY
import br.com.questv.resource.Strings.QUESTION_MANAGER_FRAGMENT_KEY
import br.com.questv.ui.question.QuestionFragment
import java.io.Serializable

class QuestionManagerAdapter(
  private val questions: List<QuestionModel>,
  fragmentManager: FragmentManager
) :
  FragmentStatePagerAdapter(fragmentManager),
  Serializable,
  QuestionFragment.OnQuestionInteractionListener {

  private val fragmentList = mutableListOf<Fragment>()

  init {
    feedFragmentList()
  }

  private fun feedFragmentList() {
    for (questionModel in this.questions) {
      val questionFragment = QuestionFragment()
      val bundle = Bundle()
      bundle.putSerializable(QUESTION_KEY, questionModel)
      bundle.putSerializable(QUESTION_MANAGER_FRAGMENT_KEY, this)
      questionFragment.arguments = bundle
      this.fragmentList.add(questionFragment)
    }
  }

  override fun getItem(position: Int) = this.fragmentList[position]

  override fun getCount() = this.fragmentList.size

  override fun onCorrectAnswered() {
    println("onCorrectAnswered")
  }

  override fun onWrongAnswered() {
    println("onWrongAnswered")
  }

  override fun onClickPreviousQuestion() {
    println("onClickPreviousQuestion")
  }

  override fun onClickNextQuestion() {
    println("onClickNextQuestion")
  }
}