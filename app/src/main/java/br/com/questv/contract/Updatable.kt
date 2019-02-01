package br.com.questv.contract

interface Updatable<T> {
  fun update(update: T)
}