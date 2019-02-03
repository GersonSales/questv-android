package br.com.questv.ui.question.manager

import br.com.questv.model.question.QuestionModel

interface QuestionManagerView {
  fun initViewPager(questions: List<QuestionModel>)
  fun showProgress()
  fun hideProgress()
  fun showErrorMessage(message: String)
}