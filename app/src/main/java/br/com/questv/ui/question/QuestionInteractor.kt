package br.com.questv.ui.question

import br.com.questv.endpoint.ApiClient
import br.com.questv.model.question.QuestionModel
import br.com.questv.ui.question.model.AnsweredQuestionModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionInteractor {
  interface OnRateSendListener {
    fun onRateSendSuccess()
    fun onRateSendFailure(message: String)
  }

  interface OnDificultySendListener {
    fun onDificultySendSuccess()
    fun onDificultySendFailure(message: String)
  }

  interface OnAttachAnsweredQuestionListener {
    fun onAttachmentSuccess()
    fun onAttachmentFailure(message: String)
  }

  fun sendQuestionRate(
    userId: String,
    questionModel: QuestionModel,
    auth: String,
    listener: OnRateSendListener
  ) {
    val putCall = ApiClient.instance.putQuestion(questionModel.questionableId, questionModel.id, questionModel, auth)
    putCall.enqueue(object : Callback<ResponseBody> {
      override fun onFailure(
        call: Call<ResponseBody>,
        t: Throwable
      ) {
        listener.onRateSendFailure(t.message.toString())
      }

      override fun onResponse(
        call: Call<ResponseBody>,
        response: Response<ResponseBody>
      ) {
        if (response.isSuccessful) {
          listener.onRateSendSuccess()
        } else {
          listener.onRateSendFailure(response.message())
        }
      }
    })

  }

  fun sendQuestionDifficulty(
    userId: String,
    questionModel: QuestionModel,
    auth: String,
    listener: OnDificultySendListener
  ) {
    val putCall = ApiClient.instance.putQuestion(questionModel.questionableId, questionModel.id, questionModel, auth)
    putCall.enqueue(object : Callback<ResponseBody> {
      override fun onFailure(
        call: Call<ResponseBody>,
        t: Throwable
      ) {
        listener.onDificultySendFailure(t.message.toString())
      }

      override fun onResponse(
        call: Call<ResponseBody>,
        response: Response<ResponseBody>
      ) {
        if (response.isSuccessful) {
          listener.onDificultySendSuccess()
        } else {
          listener.onDificultySendFailure(response.message())
        }
      }
    })

  }

  fun attachAnsweredQuestion(
    userId: String,
    auth: String,
    answeredQuestionModel: AnsweredQuestionModel,
    listener: OnAttachAnsweredQuestionListener
  ) {
    val attachCall = ApiClient.instance.postAnsweredQuestion(userId, answeredQuestionModel, auth)
    attachCall.enqueue(object : Callback<Void> {
      override fun onFailure(
        call: Call<Void>,
        t: Throwable
      ) {
        listener.onAttachmentFailure(t.message!!)
      }

      override fun onResponse(
        call: Call<Void>, response: Response<Void>
      ) {
        if (response.isSuccessful) {
          listener.onAttachmentSuccess()
        } else {
          listener.onAttachmentFailure(response.message())
        }
      }
    })
  }


}