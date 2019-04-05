package br.com.questv.ui.contribution

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import br.com.questv.R
import kotlinx.android.synthetic.main.contribution_fragment.*

class ContributionFragment: Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.contribution_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    bt_add_answer.setOnClickListener {
      val editText = EditText(context)
      editText.width = ll_contribution_answers.width / 2
      editText.maxWidth = ll_contribution_answers.width / 2
      editText.setTextColor(Color.WHITE)

      val spinner = Spinner(context)

      ll_contribution_answers.addView(editText)
      ll_contribution_answers.addView(spinner)
    }
    super.onViewCreated(view, savedInstanceState)
  }
}