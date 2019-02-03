package br.com.questv.model.question.manager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.questv.ui.question.QuestionFragment
import br.com.questv.model.question.QuestionModel
import br.com.questv.resource.Strings.QUESTION_KEY

class QuestionManagerAdapter(
  private val questions: List<QuestionModel>,
  fragmentManager: FragmentManager
) :
  FragmentStatePagerAdapter(fragmentManager) {

  private val fragmentList = mutableListOf<Fragment>()

  init {
    feedFragmentList()
  }

  private fun feedFragmentList() {
    for (questionModel in this.questions) {
      val questionFragment = QuestionFragment()
      val bundle = Bundle()
      bundle.putSerializable(QUESTION_KEY, questionModel)
      questionFragment.arguments = bundle
      this.fragmentList.add(questionFragment)
    }
  }

  override fun getItem(position: Int) = this.fragmentList[position]

  override fun getCount() = this.fragmentList.size
}