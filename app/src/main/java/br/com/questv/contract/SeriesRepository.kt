package br.com.questv.contract

import br.com.questv.model.series.SeriesModel

interface SeriesRepository : Repository<SeriesModel> {
  fun findByCategory(category: String): List<SeriesModel>
  fun findByName(name: String): SeriesModel?
  fun findReleases(): List<SeriesModel>
  fun findAllCoverImages(): List<String>
  fun findAllPromoImages(): List<String>
  fun findAllCategories(): Set<String>

}