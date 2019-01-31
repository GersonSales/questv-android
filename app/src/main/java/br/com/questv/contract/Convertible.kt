package br.com.questv.contract

interface Convertible<T> {
  fun convert(): T
}