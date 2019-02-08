package br.com.questv.ui.question.manager

import br.com.questv.model.question.QuestionModel

interface QuestionManagerView {
  fun initViewPager(questions: List<QuestionModel>)
  fun showProgress()
  fun hideProgress()
  fun showErrorMessage(message: String)
  fun navigateToPreviousQuestion(currentIndex: Int, delayed: Boolean)
  fun navigateToNextQuestion(currentIndex: Int, delayed: Boolean)
  fun disableCurrentQuestion(currentIndex: Int)
  fun navigateToScore()
  fun navigateToItsCaller()
}