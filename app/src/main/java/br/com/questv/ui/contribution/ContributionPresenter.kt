package br.com.questv.ui.contribution

import br.com.questv.model.question.QuestionModel
import br.com.questv.ui.contribution.model.ContributionModel

class ContributionPresenter(private val view: ContributionView) :
  ContributionInteractor.OnPostContributionListener {

  private val interactor = ContributionInteractor()


  fun submitContribution(auth: String, questionModel: QuestionModel) {
    if (!questionModel.isDescriptionValid()) {
      this.view.showToast(questionModel.getInvalidDescriptionCauseMessage())
    } else {
      this.interactor.submitContribution(auth, questionModel, this)
    }
  }

  override fun onSuccess() {
    this.view.showToast("Contribution sent!")
    this.view.navigateToBack()
  }

  override fun onFailure(message: String) {
    this.view.showToast("Error: $message")
    this.view.navigateToBack()
  }
}