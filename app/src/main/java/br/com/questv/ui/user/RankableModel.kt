package br.com.questv.ui.user

import br.com.questv.contract.Rankable

class RankableModel(private val username: String,
                    private val points: Int): Rankable{
  override fun getUsername() = username

  override fun getPoints() = points

}