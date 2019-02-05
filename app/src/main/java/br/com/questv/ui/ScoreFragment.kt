package br.com.questv.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R

class ScoreFragment : Fragment() {


  override fun onAttach(context: Context?) {
    println("onAttach")
    super.onAttach(context)
  }

  override fun onAttach(activity: Activity?) {
    println("onAttach")
    super.onAttach(activity)
  }


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?, savedInstanceState: Bundle?
  ):
      View? {
    println("onCreateView")
    return inflater.inflate(R.layout.fragment_score, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    println("onActivityCreated")
    super.onActivityCreated(savedInstanceState)
  }

  override fun onStart() {
    println("onStart")
    super.onStart()
  }

  override fun onResume() {
    println("onResume")
    super.onResume()
  }

  override fun onPause() {
    println("onPause")
    super.onPause()
  }

  override fun onStop() {
    println("onStop")
    super.onStop()
  }

  override fun onDestroyView() {
    println("onDestroyView")
    super.onDestroyView()
  }

  override fun onDestroy() {
    println("onDestroy")
    super.onDestroy()
  }

  override fun onDetach() {
    println("onDetach")
    super.onDetach()
  }


}