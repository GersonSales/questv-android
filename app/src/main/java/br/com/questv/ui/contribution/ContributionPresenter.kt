package br.com.questv.ui.contribution

import br.com.questv.ui.contribution.model.ContributionModel

class ContributionPresenter(private val view: ContributionView) {

  private val interactor = ContributionInteractor()


  fun submitContribution(contribution: ContributionModel) {
    if (!contribution.isValid()) {
      this.view.showToast(contribution.getInvalidCauseMessage())
    } else {
      this.interactor.submitContribution(contribution)
    }
  }
}