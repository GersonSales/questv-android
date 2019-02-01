package br.com.questv.model.series

import android.net.Uri
import br.com.questv.contract.SeriesRepository

class SeriesRepositoryImpl : SeriesRepository {
  override fun findByCategory(category: String): List<SeriesModel> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun findByName(name: String): SeriesModel {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun findAllCoverImages(): List<Uri> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun findAllPromoImages(): List<Uri> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun save(t: SeriesModel) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun saveAll(tList: List<SeriesModel>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun findById(tId: Long): SeriesModel {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun update(t: SeriesModel) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun updateAll(tList: List<SeriesModel>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteById(tId: Long) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteAllById(tIdList: List<Long>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}