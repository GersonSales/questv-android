package br.com.questv.model.series;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.questv.R;
import org.w3c.dom.Text;

public class SeriesListViewHolder extends RecyclerView.ViewHolder {


  private final ViewPager viewPager;

  private final TextView textView;
  private final ImageView imageView;
  private final RecyclerView seriesItemRecyclerView;
  private final Context context;

  public SeriesListViewHolder(@NonNull final View itemView) {
    super(itemView);
    this.context = itemView.getContext();
    this.viewPager = itemView.findViewById(R.id.vp_news);
    this.seriesItemRecyclerView = itemView.findViewById(R.id.rv_series_list);
    this.seriesItemRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
        LinearLayoutManager.HORIZONTAL, false));
    this.textView = itemView.findViewById(R.id.tv_category_name);
    this.imageView = itemView.findViewById(R.id.iv_category_menu);
  }

  public void bindRecyclerView() {
    this.seriesItemRecyclerView.setAdapter(new SeriesItemAdapter());
    this.viewPager.setVisibility(View.GONE);
  }

  /*default*/ void bindViewPager() {
    this.viewPager.setVisibility(View.VISIBLE);
    textView.setVisibility(View.GONE);
    imageView.setVisibility(View.GONE);
    seriesItemRecyclerView.setVisibility(View.GONE);
    final NewsAdapter adapter = new NewsAdapter();
    viewPager.setAdapter(adapter);
    this.viewPager.setCurrentItem(adapter.getCount() / 2);

  }
}
