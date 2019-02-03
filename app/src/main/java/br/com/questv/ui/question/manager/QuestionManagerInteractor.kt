package br.com.questv.ui.question.manager

import br.com.questv.endpoint.ApiClient
import br.com.questv.model.question.QuestionModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionManagerInteractor {
  interface OnQuestionConsumptionListener {
    fun onConsumptionSuccess(questions: ArrayList<QuestionModel>)
    fun onConsumptionFail(throwable: Throwable)
  }

  fun consumeQuestionsApi(ownerId: Long, listener: OnQuestionConsumptionListener) {
    val consumptionCall: Call<ArrayList<QuestionModel>> = ApiClient.instance.getAllQuestions()
    consumptionCall.enqueue(object : Callback<ArrayList<QuestionModel>> {
      /**
       * Invoked when a network exception occurred talking to the server or when an unexpected
       * exception occurred creating the request or processing the response.
       */
      override fun onFailure(call: Call<ArrayList<QuestionModel>>, throwable: Throwable) {
        listener.onConsumptionFail(throwable)
      }

      /**
       * Invoked for a received HTTP response.
       *
       *
       * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
       * Call [Response.isSuccessful] to determine if the response indicates success.
       */
      override fun onResponse(
        call: Call<ArrayList<QuestionModel>>,
        response: Response<ArrayList<QuestionModel>>
      ) {

        response.body()?.let {
          listener.onConsumptionSuccess(it)
        }
      }
    })


  }


}
