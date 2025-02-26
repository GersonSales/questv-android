package br.com.questv.model.question.answer

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R
import br.com.questv.model.user.TempUser

class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  interface OnAnsweredQuestionListener {
    fun onCorrectAnswer()
    fun onWrongAnswer()
    fun onAnswered(answerId: Long)
  }

  fun bind(answerModel: AnswerModel, listener: OnAnsweredQuestionListener) {
    itemView.findViewById<TextView>(R.id.tv_answer_item_number)!!.text = answerModel.number.toString()
    itemView.findViewById<TextView>(R.id.tv_question_answer_item)!!.text = answerModel.description
    val answerRow: ConstraintLayout = itemView.findViewById(R.id.cl_answer_row)

    val userHasAnswered = TempUser.hasAnswered(answerModel)

    if (userHasAnswered) {


      if (answerModel.isCorrect) {
        val colorCorrect = ContextCompat.getColor(itemView.context, R.color.colorGreen)
        answerRow.setBackgroundColor(colorCorrect)
      } else {
        val colorWrong = ContextCompat.getColor(itemView.context, R.color.colorRed)
        answerRow.setBackgroundColor(colorWrong)
      }
    } else {

      answerRow.setOnClickListener {
        TempUser.attachAnsweredQuestion(answerModel)

        if (answerModel.isCorrect) {
          val colorCorrect = ContextCompat.getColor(itemView.context, R.color.colorGreen)
          answerRow.setBackgroundColor(colorCorrect)
          listener.onCorrectAnswer()

        } else {
          val colorWrong = ContextCompat.getColor(itemView.context, R.color.colorRed)
          answerRow.setBackgroundColor(colorWrong)
          listener.onWrongAnswer()
        }

        listener.onAnswered(answerModel.id)
      }
    }
  }

}