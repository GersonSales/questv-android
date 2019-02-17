package br.com.questv.ui.main

import java.io.Serializable

interface MainView : Serializable{
  fun highLightCurrentItem(position: Int)
  fun navigateToUserProfile()
}