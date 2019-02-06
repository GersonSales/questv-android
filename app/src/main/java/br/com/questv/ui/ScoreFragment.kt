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
import kotlinx.android.synthetic.main.fragment_score.*

class ScoreFragment : Fragment() {


  override fun onAttach(context: Context?) {
    println("ScoreFragment: onAttach")
    super.onAttach(context)
  }

  override fun onAttach(activity: Activity?) {
    println("ScoreFragment: onAttach")
    super.onAttach(activity)
  }


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?, savedInstanceState: Bundle?
  ):
      View? {
    val view = inflater.inflate(R.layout.fragment_score, container, false)
    println("ScoreFragment: onCreateView")
    view.findViewById<TextView>(R.id.textView2).setOnClickListener {
      fragmentManager
        ?.beginTransaction()
        ?.remove(this)
        ?.commit()
    }
    return view
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    println("ScoreFragment: onActivityCreated")
    super.onActivityCreated(savedInstanceState)
  }

  override fun onStart() {
    println("ScoreFragment: onStart")
    super.onStart()
  }

  override fun onResume() {
    println("ScoreFragment: onResume")
    super.onResume()
  }

  override fun onPause() {
    println("ScoreFragment: onPause")
    super.onPause()
  }

  override fun onStop() {
    println("ScoreFragment: onStop")
    super.onStop()
  }

  override fun onDestroyView() {
    println("ScoreFragment: onDestroyView")
    super.onDestroyView()
  }

  override fun onDestroy() {
    println("ScoreFragment: onDestroy")
    super.onDestroy()
  }

  override fun onDetach() {
    println("ScoreFragment: onDetach")
    super.onDetach()
  }


}