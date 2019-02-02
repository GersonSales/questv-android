package br.com.questv.contract

interface OnItemClickListener<T> {
  fun onClick(item: T)
}