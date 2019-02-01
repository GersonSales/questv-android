package br.com.questv.model.series

import android.net.Uri
import br.com.questv.contract.SeriesRepository

class SeriesRepositoryImpl private constructor() : SeriesRepository {
  companion object {
    @JvmStatic
    val instance = SeriesRepositoryImpl()
  }

  private var seriesMap: MutableMap<String, MutableList<SeriesModel>> = HashMap()


  override fun findByCategory(category: String): List<SeriesModel> {
    return this.seriesMap.getValue(category)
  }

  override fun findByName(name: String): SeriesModel? {
    for (seriesModel in findAll()) {
      if (seriesModel.name == name) {
        return seriesModel
      }
    }
    return null
  }

  override fun findAll(): List<SeriesModel> {
    val result: MutableList<SeriesModel> = ArrayList()
    for (category in this.seriesMap.keys) {
      result.addAll(this.seriesMap[category]!!)
    }
    return result
  }

  override fun findAllCoverImages(): List<Uri> {
    val result: MutableList<Uri> = ArrayList()
    for (seriesModel in findAll()) {
      result.add(seriesModel.coverImageUri!!)
    }
    return result
  }

  override fun findAllPromoImages(): List<Uri> {
    val result: MutableList<Uri> = ArrayList()
    for (seriesModel in findAll()) {
      result.add(seriesModel.promoImageUri!!)
    }
    return result
  }

  override fun save(item: SeriesModel) {
    val category: String = item.category
    if (this.seriesMap.containsKey(category)) {
      this.seriesMap[category]?.add(item)
    } else {
      val list: MutableList<SeriesModel> = ArrayList()
      list.add(item)
      this.seriesMap[category] = list
    }
  }

  override fun saveAll(itemList: List<SeriesModel>) {
    for (seriesModel in itemList) {
      save(seriesModel)
    }
  }

  override fun findById(itemId: Long): SeriesModel? {
    for (seriesModel in findAll()) {
      if (seriesModel.id == itemId) {
        return seriesModel
      }
    }
    return null
  }

  override fun update(item: SeriesModel) {
    this.findById(item.id)?.update(item)
  }

  override fun updateAll(itemList: List<SeriesModel>) {
    for (seriesModel in itemList) {
      update(seriesModel)
    }
  }

  override fun deleteById(itemId: Long) {
    for (category in this.seriesMap.keys) {
      val deletedById: Boolean = deleteById(itemId, this.seriesMap[category])
      if (deletedById) {
        break
      }
    }
  }

  private fun deleteById(itemId: Long, seriesModelList: MutableList<SeriesModel>?) : Boolean {
    for (seriesModel in seriesModelList!!) {
      if (seriesModel.id == itemId) {
        seriesModelList.remove(seriesModel)
        return true
      }
    }
    return false
  }

  override fun deleteAllById(itemIdList: List<Long>) {
    for (itemId in itemIdList) {
      deleteById(itemId)
    }
  }
}
