package br.com.questv.login

interface LoginView {
    fun showProgress();
    fun hideProgress();
    fun serUserNameError();
    fun setUserPasswordError();
    fun navigateToHome();
}