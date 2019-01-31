package br.com.questv.model.series.dto

import br.com.questv.contract.Convertible
import br.com.questv.model.series.SeriesModel

class SeriesDTO(val id: Long,
                val name: String,
                val abbreviation: String,
                val category: String,
                val coverImage: String,
                val promoImage: String,
                val seasons: List<Long>,
                val questions: List<Long>) : Convertible<SeriesModel> {

  override fun convert(): SeriesModel {
    return SeriesModel(name, abbreviation)
  }
}