package br.com.questv.ui.question

import br.com.questv.endpoint.ApiClient
import br.com.questv.model.question.QuestionModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionInteractor {
  interface OnRateSendListener {
    fun onSuccess()
    fun onFailure(message: String)
  }

  fun sendQuestionRate(userId: String, questionModel: QuestionModel, auth: String, listener: OnRateSendListener) {
    val putCall = ApiClient.instance.putQuestion(questionModel.questionableId, questionModel.id, questionModel, auth)
    putCall.enqueue(object : Callback<ResponseBody> {
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