package br.com.questv.model.series

import android.net.Uri
import br.com.questv.contract.Updatable

class SeriesModel(val id: Long,
                  val name: String,
                  val abbreviation: String,
                  val category: String,
                  var coverImage: String,
                  var coverImageUri: Uri?,
                  val promoImage: String,
                  val promoImageUri: Uri?,
                  val seasons: List<Long>,
                  val questions: List<Long>) : Updatable<SeriesModel> {
  override fun update(update: SeriesModel) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}