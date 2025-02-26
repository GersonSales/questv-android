package br.com.questv.ui.series

import br.com.questv.contract.Questionable
import br.com.questv.model.season.SeasonModel

interface SeriesView {
  fun navigateToHome()
  fun showProgress()
  fun hideProgress()
  fun showErrorMessage(message: String?)
  fun initSeasonRecycler(seasonList: ArrayList<SeasonModel>)
  fun navigateToQuestionManager(questionable: Questionable)
  fun navigateToSeriesContribution()
  fun showToast(message: String)
}