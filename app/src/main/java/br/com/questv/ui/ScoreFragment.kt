package br.com.questv.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.questv.R
import br.com.questv.resource.Strings
import br.com.questv.resource.Strings.CORRECT_ANSWERED_QUESTIONS
import br.com.questv.resource.Strings.TOTAL_OF_QUESTIONS
import kotlinx.android.synthetic.main.fragment_score.*

class ScoreFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?, savedInstanceState: Bundle?
  ):
      View? {
    val view = inflater.inflate(R.layout.fragment_score, container, false)


    val totalOfQuestions = arguments?.getInt(TOTAL_OF_QUESTIONS)
    val correctAnsweredQuestions = arguments?.getInt(CORRECT_ANSWERED_QUESTIONS)


    val text = view.findViewById<TextView>(R.id.textView2)
    val count = "$correctAnsweredQuestions/$totalOfQuestions"
    text.text = count
    text.textSize = 48f
    return view
  }


}