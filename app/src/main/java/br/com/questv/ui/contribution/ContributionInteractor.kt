package br.com.questv.ui.contribution

import br.com.questv.endpoint.ApiClient
import br.com.questv.ui.contribution.model.ContributionModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContributionInteractor {
  interface OnPostContributionListener {
    fun onSuccess()
    fun onFailure(message: String)

  }


  fun submitContribution(
    questionableId: String,
    auth: String,
    contribution: ContributionModel,
    listener: OnPostContributionListener
  ) {

    val postCall = ApiClient.instance.postQuestion(questionableId, auth, contribution)
    postCall.enqueue(object : Callback<ResponseBody> {
      override fun onFailure(
        call: Call<ResponseBody>,
        t: Throwable
      ) {
        listener.onFailure(t.message.toString())
      }

      override fun onResponse(
        call: Call<ResponseBody>,
        response: Response<ResponseBody>
      ) {
        if (response.isSuccessful) {
          listener.onSuccess()
        } else {
          listener.onFailure(response.message())
        }
      }
    })


  }
}