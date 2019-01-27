package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import br.com.questv.R;

public class SeriesListViewHolder extends RecyclerView.ViewHolder {


  private final ViewPager viewPager;

  private final RecyclerView seriesItemRecyclerView;

  public SeriesListViewHolder(@NonNull final View itemView) {
    super(itemView);
    this.viewPager = itemView.findViewById(R.id.vp_news);
    this.seriesItemRecyclerView = itemView.findViewById(R.id.rv_series_list);
    this.seriesItemRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
        LinearLayoutManager.HORIZONTAL, false));
  }

  public void bindRecyclerView() {
    this.seriesItemRecyclerView.setAdapter(new SeriesItemAdapter());
    this.viewPager.setVisibility(View.GONE);
  }

  /*default*/ void bindViewPager() {
    seriesItemRecyclerView.setVisibility(View.GONE);
    final NewsAdapter adapter = new NewsAdapter();
    viewPager.setAdapter(adapter);
    this.viewPager.setCurrentItem(adapter.getCount() / 2);

  }
}
