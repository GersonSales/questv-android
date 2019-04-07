package br.com.questv.ui.contribution

import br.com.questv.ui.contribution.model.ContributionModel

class ContributionPresenter(private val view: ContributionView) :
  ContributionInteractor.OnPostContributionListener {

  private val interactor = ContributionInteractor()


  fun submitContribution(questionableId: String, auth: String, contribution: ContributionModel) {
    if (!contribution.isValid()) {
      this.view.showToast(contribution.getInvalidCauseMessage())
    } else {
      this.interactor.submitContribution(questionableId, auth, contribution, this)
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