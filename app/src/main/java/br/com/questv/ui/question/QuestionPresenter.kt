package br.com.questv.ui.question

import br.com.questv.model.question.QuestionModel

class QuestionPresenter(val view: QuestionView) :
  QuestionInteractor.OnRateSendListener,
  QuestionInteractor.OnDificultySendListener {

  private val interactor = QuestionInteractor()
  fun sendQuestionRate(userId: String, questionModel: QuestionModel, rate: Double, auth: String) {
    if (rate < 0 || rate > 10) {
      this.view.setRateError("Rate must be between 0 and 10.")
    } else {
      questionModel.rate = rate
      interactor.sendQuestionRate(userId, questionModel, auth, this)
    }
  }

  fun sendQuestionDifficulty(userId: String, questionModel: QuestionModel, difficulty: Int, auth: String) {
    if (difficulty < 0 || difficulty > 5) {
      this.view.setRateError("Difficult must be between 0 and 5.")
    } else {
      questionModel.difficult = difficulty
      interactor.sendQuestionDifficulty(userId, questionModel, auth, this)
    }
  }

  override fun onRateSendSuccess() {
    view.showToast("Question rated")
    view.showRateText()
  }

  override fun onRateSendFailure(message: String) {
    view.showToast(message)
    view.showRateText()
  }

  override fun onDificultySendSuccess() {
    view.showToast("Question difficulty updated")
    view.showDifficultyText()
  }

  override fun onDificultySendFailure(message: String) {
    view.showToast(message)
    view.showDifficultyText()
  }
}