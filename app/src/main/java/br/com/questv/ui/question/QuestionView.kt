package br.com.questv.ui.question

interface QuestionView {
  fun showRateText()
  fun showDifficultyText()
  fun setRateError(message: String)
  fun showToast(message: String)
}