package br.com.questv.model.series

import br.com.questv.contract.Questionable
import br.com.questv.contract.Updatable
import java.io.Serializable

class SeriesModel(private val id: String,
                  val name: String,
                  val abbreviation: String,
                  val category: String,
                  val isRelease: Boolean,
                  var coverImage: String,
                  private var coverImageUrl: String?,
                  val promoImage: String,
                  private var promoImageUrl: String?,
                  val seasons: List<String>,
                  val questions: List<String>) : Updatable<SeriesModel>, Serializable, Questionable {

  override fun update(update: SeriesModel) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getId() = id

  override fun getPromoImageUrl() = promoImageUrl

  override fun getCoverImageUrl() = coverImageUrl
}