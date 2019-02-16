package br.com.questv.contract

interface Repository<T> {
  fun save(item: T)
  fun saveAll(itemList: List<T>)
  fun findAll(): List<T>
  fun findById(itemId: String): T?
  fun update(item: T)
  fun updateAll(itemList: List<T>)
  fun deleteById(itemId: String)
  fun deleteAllById(itemIdList: List<String>)
}
