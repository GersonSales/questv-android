package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import br.com.questv.R;

public class SeriesListViewHolder extends RecyclerView.ViewHolder {


  private final RecyclerView seriesItemRecyclerView;

  public SeriesListViewHolder(@NonNull final View itemView) {
    super(itemView);
    seriesItemRecyclerView = itemView.findViewById(R.id.rv_series_list);
    seriesItemRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
        LinearLayoutManager.HORIZONTAL, false));
  }

  private void initRecyclerView() {
    seriesItemRecyclerView.setAdapter(new SeriesItemAdapter());
  }

  public void bind() {
    initRecyclerView();
  }
}
