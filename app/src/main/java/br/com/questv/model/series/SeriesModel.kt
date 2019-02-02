package br.com.questv.model.series

import br.com.questv.contract.Updatable

class SeriesModel(val id: Long,
                  val name: String,
                  val abbreviation: String,
                  val category: String,
                  val isRelease: Boolean,
                  var coverImage: String,
                  var coverImageUrl: String?,
                  val promoImage: String,
                  var promoImageUrl: String?,
                  val seasons: List<Long>,
                  val questions: List<Long>) : Updatable<SeriesModel> {

  override fun update(update: SeriesModel) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}