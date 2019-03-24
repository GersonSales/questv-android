package br.com.questv.ui.signup

import br.com.questv.endpoint.ApiClient
import br.com.questv.model.user.SignUpModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpInteractor {


  interface OnSignUpFinishedListener {
    fun onFirstNameError(message: String)
    fun onlastNameError(message: String)
    fun onUsernameError(message: String)
    fun onEmailError(message: String)
    fun onPasswordError(message: String)
    fun onFailure(message: String)
    fun onSuccess()
  }


  fun registerUser(
    signUpModel: SignUpModel,
    listener: OnSignUpFinishedListener
  ) {

    when {
      signUpModel.firstName.isEmpty() -> listener.onFirstNameError("First Name cannot be empty.")
      signUpModel.lastName.isEmpty() -> listener.onlastNameError("Last Name cannot be empty.")
      signUpModel.username.isEmpty() -> listener.onUsernameError("Username cannot be empty.")
      signUpModel.email.isEmpty() -> listener.onEmailError("Email cannot be empty.")
      signUpModel.password.isEmpty() -> listener.onPasswordError("Password cannot be empty.")
      else -> registerUserOnServer(signUpModel, listener)
    }

  }

  private fun registerUserOnServer(
    signUpModel: SignUpModel,
    listener: OnSignUpFinishedListener
  ) {
    val signUpCall = ApiClient.instance.signUp(signUpModel)
    signUpCall.enqueue(object : Callback<ResponseBody> {
      override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        listener.onUsernameError(t.message!!)
      }

      override fun onResponse(
        call: Call<ResponseBody>,
        response: Response<ResponseBody>
      ) {
        if (response.isSuccessful) {
          listener.onSuccess()
        }else {
          listener.onFailure(response.code().toString())
        }
      }
    })
  }


}