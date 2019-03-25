package br.com.questv.ui.ranking

import br.com.questv.contract.Rankable

interface RankingView {
  fun showProgress()
  fun hideProgress()
  fun initRecyclerView(list: List<Rankable>)
  fun showToastMessage(message: String)
  fun navigateToHome()
}