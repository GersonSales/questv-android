package br.com.questv.model.dashboard

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.questv.R

class DashboardViewHolder(itemView: View):
  RecyclerView.ViewHolder(itemView) {


  fun bind() {
    var textView: TextView = itemView.findViewById(R.id.tv_dashboard)
    textView.text = "Dashboard"
  }
}