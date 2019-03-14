package br.com.questv.ui.question.manager

import br.com.questv.model.question.QuestionModel

class QuestionManagerPresenter(
  private val questionManagerView: QuestionManagerView,
  private val questionManagerInteractor: QuestionManagerInteractor
) :
  QuestionManagerInteractor.OnQuestionConsumptionListener {

  fun fetchAllQuestions(questionOwnerId: String, auth: String) {
    this.questionManagerInteractor.consumeQuestionsApi(questionOwnerId, this, auth)
  }

  override fun onConsumptionSuccess(questions: ArrayList<QuestionModel>) {
    this.questionManagerView.initViewPager(questions)
    this.questionManagerView.hideProgress()
  }

  override fun onConsumptionFail(throwable: Throwable) {
    this.questionManagerView.hideProgress()
    this.questionManagerView.showErrorMessage(throwable.message!!)
  }
}