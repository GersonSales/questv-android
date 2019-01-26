package br.com.questv.login

class LoginInteractor {
  interface OnLoginFinishedListener {
    fun onUserNameError();
    fun onUserPasswordError();
    fun onSuccess();
  }

  fun login(username: String, password: String, listener: OnLoginFinishedListener) {
    when {
      username.isEmpty() -> listener.onUserNameError();
      password.isEmpty() -> listener.onUserPasswordError();
      else -> listener.onSuccess();
    }
  }
}