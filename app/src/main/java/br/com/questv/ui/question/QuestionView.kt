package br.com.questv.ui.question

interface QuestionView {
  fun showRateText()
  fun setRateError(message: String)
  fun showToast(message: String)
}