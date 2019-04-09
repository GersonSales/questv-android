package br.com.questv.model.series

import br.com.questv.contract.Questionable
import br.com.questv.contract.Updatable
import java.io.Serializable

class SeriesModel(private var id: String,
                  var name: String,
                  var abbreviation: String,
                  var category: String,
                  var isRelease: Boolean,
                  var rate: Double,
                  var coverImage: String,
                  private var coverImageUrl: String?,
                  var promoImage: String,
                  private var promoImageUrl: String?,
                  var seasons: List<String>,
                  var questions: List<String>) : Updatable<SeriesModel>, Serializable, Questionable {

  override fun update(update: SeriesModel) {
    name = update.name
    abbreviation = update.abbreviation
    category = update.category
    isRelease = update.isRelease
    coverImage = update.coverImage
    coverImageUrl = update.coverImageUrl
    promoImage = update.promoImage
    promoImageUrl = update.promoImageUrl
    seasons = update.seasons
    questions = update.questions
  }

  override fun getId() = id

  override fun getPromoImageUrl() = promoImageUrl

  override fun getCoverImageUrl() = coverImageUrl

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as SeriesModel

    if (id != other.id) return false

    return true
  }

  override fun hashCode(): Int {
    return id.hashCode()
  }


}