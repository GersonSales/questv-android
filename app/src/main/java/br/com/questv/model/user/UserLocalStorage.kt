package br.com.questv.model.user

import android.content.Context
import br.com.questv.endpoint.ApiClient
import com.auth0.android.jwt.JWT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserLocalStorage(private val context: Context) {
  interface OnUserDetailsConsumptionListener {
    fun onUserDetailsSuccess()
    fun onUserDetailsFail()
  }


  private val SP_NAME = ""

  private val sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)


  fun login(token: JWT, listener: OnUserDetailsConsumptionListener) {
    val userId: String = token.getClaim("id").asString()!!


    val auth = "Bearer $token"
    val detailsCall = ApiClient.instance.getUserById(userId, auth)
    detailsCall.enqueue(object : Callback<UserModel> {
      override fun onFailure(call: Call<UserModel>, t: Throwable) {
        t.printStackTrace()
        listener.onUserDetailsFail()
      }

      override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
        if (response.isSuccessful) {
          response.body().let {
            updateStoredUserInfo(it!!)
            listener.onUserDetailsSuccess()
          }
        } else {
          listener.onUserDetailsFail()
        }
      }
    })
  }

  private fun updateStoredUserInfo(userModel: UserModel) {
    this.sharedPreferences.edit().apply {
      val userAsKeySet = userModel.asKeySet()
      for (key in userAsKeySet.keys) {
        putString(key, userAsKeySet[key])
      }
      apply()
    }
  }


}