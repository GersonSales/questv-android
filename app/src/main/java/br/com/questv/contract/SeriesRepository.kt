package br.com.questv.contract

import android.net.Uri
import br.com.questv.model.series.SeriesModel

interface SeriesRepository : Repository<SeriesModel> {
  fun findByCategory(category: String): List<SeriesModel>
  fun findByName(name: String): SeriesModel
  fun findAllCoverImages(): List<Uri>
  fun findAllPromoImages(): List<Uri>
}