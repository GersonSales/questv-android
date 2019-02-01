package br.com.questv.ui.home

interface HomeView {
  fun navigateToUserAccount()
  fun showProgress()
  fun hideProgress()
  fun initRecyclerView()
  fun navigateToErrorPage()
}