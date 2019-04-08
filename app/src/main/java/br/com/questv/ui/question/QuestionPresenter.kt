package br.com.questv.ui.question

import br.com.questv.model.question.QuestionModel

class QuestionPresenter(val view: QuestionView) :
  QuestionInteractor.OnRateSendListener {

  private val interactor = QuestionInteractor()
  fun sendQuestionRate(userId: String, questionModel: QuestionModel, rate: Double, auth: String) {
    if (rate < 0 || rate > 10) {
      this.view.setRateError("Rate must be between 0 and 10.")
    } else {
      questionModel.rate = rate
      interactor.sendQuestionRate(userId, questionModel, auth, this)



    }
  }

  override fun onSuccess() {
    view.showToast("Question rated")
    view.showRateText()
  }

  override fun onFailure(message: String) {
    view.showToast(message)
    view.showRateText()
  }
}