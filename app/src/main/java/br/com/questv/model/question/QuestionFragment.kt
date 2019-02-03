package br.com.questv.model.question

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.questv.R
import br.com.questv.resource.Strings.QUESTION_KEY

class QuestionFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val questionModel: QuestionModel = arguments!!.get(QUESTION_KEY) as QuestionModel
    val view = inflater.inflate(R.layout.fragment_question, container, false)
    initView(questionModel, view)
    return view
  }

  private fun initView(questionModel: QuestionModel, view: View) {
    val questionDescription: TextView = view.findViewById(R.id.tv_question_description)
    questionDescription.text = questionModel.description
  }
}