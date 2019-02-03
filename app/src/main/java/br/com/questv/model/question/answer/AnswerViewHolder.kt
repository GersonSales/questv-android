package br.com.questv.model.question.answer

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.questv.R

class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(answerModel: AnswerModel) {
    itemView.findViewById<TextView>(R.id.tv_answer_item_number)!!.text = answerModel.number.toString()
    itemView.findViewById<TextView>(R.id.tv_question_answer_item)!!.text = answerModel.description

  }

}