package br.com.questv.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
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

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    ib_back_from_score.setOnClickListener {NavHostFragment.findNavController(this).popBackStack()}

  }
}