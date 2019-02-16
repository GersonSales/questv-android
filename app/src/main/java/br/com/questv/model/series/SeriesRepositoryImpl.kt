package br.com.questv.model.series

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

  override fun findReleases(): List<SeriesModel> {
    val result: MutableList<SeriesModel> = ArrayList()
    for (seriesModel in findAll()) {
      if (seriesModel.isRelease) {
        result.add(seriesModel)
      }

    }
    return result
  }

  override fun findAll(): List<SeriesModel> {
    val result: MutableList<SeriesModel> = ArrayList()
    for (category in this.seriesMap.keys) {
      result.addAll(this.seriesMap[category]!!)
    }
    return result
  }

  override fun findAllCoverImages(): List<String> {
    val result: MutableList<String> = ArrayList()
    for (seriesModel in findAll()) {
      result.add(seriesModel.getCoverImageUrl()!!)
    }
    return result
  }

  override fun findAllPromoImages(): List<String> {
    val result: MutableList<String> = ArrayList()
    for (seriesModel in findAll()) {
      result.add(seriesModel.getPromoImageUrl()!!)
    }
    return result
  }

  override fun findAllCategories(): Set<String> {
    return this.seriesMap.keys
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

  override fun findById(itemId: String): SeriesModel? {
    for (seriesModel in findAll()) {
      if (seriesModel.getId() == itemId) {
        return seriesModel
      }
    }
    return null
  }

  override fun update(item: SeriesModel) {
    this.findById(item.getId())?.update(item)
  }

  override fun updateAll(itemList: List<SeriesModel>) {
    for (seriesModel in itemList) {
      update(seriesModel)
    }
  }

  override fun deleteById(itemId: String) {
    for (category in this.seriesMap.keys) {
      val deletedById: Boolean = deleteById(itemId, this.seriesMap[category])
      if (deletedById) {
        break
      }
    }
  }

  private fun deleteById(itemId: String, seriesModelList: MutableList<SeriesModel>?): Boolean {
    for (seriesModel in seriesModelList!!) {
      if (seriesModel.getId() == itemId) {
        seriesModelList.remove(seriesModel)
        return true
      }
    }
    return false
  }

  override fun deleteAllById(itemIdList: List<String>) {
    for (itemId in itemIdList) {
      deleteById(itemId)
    }
  }
}
