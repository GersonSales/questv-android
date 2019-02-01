package br.com.questv.contract

interface Repository<T> {
  fun save(t: T)
  fun saveAll(tList: List<T>)
  fun findById(tId: Long): T
  fun update(t: T)
  fun updateAll(tList: List<T>)
  fun deleteById(tId: Long)
  fun deleteAllById(tIdList: List<Long>)
}
