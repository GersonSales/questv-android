package br.com.questv.ui.ranking

import br.com.questv.contract.Rankable

class RankingPresenter(private val rankingView: RankingView): RankingInteractor.OnRankingConsumptionListener {

  private val interactor = RankingInteractor()

  fun consumeUserRanking(auth: String) {
    this.rankingView.showProgress()
    interactor.consumeUserRanking(auth, this)
  }

  override fun onSuccess(list: List<Rankable>) {
    this.rankingView.apply {
      hideProgress()
      initRecyclerView(list)
    }
  }

  override fun onFailure(message: String) {
    this.rankingView.apply {
      hideProgress()
      showToastMessage(message)
      navigateToHome()
    }
  }
}