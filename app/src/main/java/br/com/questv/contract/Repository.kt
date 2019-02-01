package br.com.questv.contract

interface Repository<T> {
  fun save(t: T)
  fun saveAll(tCollection: Collection<T>)
  fun findById(tId: Long): T
  fun update(t: T)
  fun updateAll(tCollection: Collection<T>)
  fun deleteById(tId: Long)
  fun deleteAllById(tIdCollection: Collection<Long>)
}
