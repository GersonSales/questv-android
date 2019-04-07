package br.com.questv.ui.contribution

interface ContributionView {
  fun setDescriptionError(message: String)
  fun showToast(message: String)
  fun navigateToBack()

}