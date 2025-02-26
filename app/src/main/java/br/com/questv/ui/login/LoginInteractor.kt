package br.com.questv.ui.login

import br.com.questv.endpoint.ApiClient
import br.com.questv.model.user.LoginModel
import br.com.questv.security.SecurityConstants.AUTH_TAG
import com.auth0.android.jwt.JWT
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class LoginInteractor {
  interface OnLoginFinishedListener {
    fun onUserNameError()
    fun onUserPasswordError()
    fun onSuccess(token: JWT)
  }

  fun login(username: String, password: String, listener: OnLoginFinishedListener) {
    when {
      username.isEmpty() -> listener.onUserNameError()
      password.isEmpty() -> listener.onUserPasswordError()
      else -> attemptToServerLogin(username, password, listener)
    }
  }

  private fun attemptToServerLogin(username: String, password: String, listener: OnLoginFinishedListener) {
    val attemptCall = ApiClient.instance.login(LoginModel(username, password))
    attemptCall.enqueue(object : Callback<ResponseBody> {
      override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        t.printStackTrace()
        listener.onUserNameError()
      }

      override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
        if (response.isSuccessful) {
          listener.onSuccess(JWT(response.headers()[AUTH_TAG]!!))
        } else {
          listener.onUserPasswordError()
        }
      }
    })
  }
}