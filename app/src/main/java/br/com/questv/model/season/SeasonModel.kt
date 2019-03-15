package br.com.questv.model.season

import br.com.questv.contract.Questionable

class SeasonModel(
  private val id: String,
  val ownerId: String,
  val number: Int,
  val name: String,
  var isExpanded: Boolean
) : Questionable {

  override fun getId() = id

  override fun getPromoImageUrl() = ""

  override fun getCoverImageUrl() = ""

}