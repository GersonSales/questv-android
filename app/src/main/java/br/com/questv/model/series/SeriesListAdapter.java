package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.questv.R;
import br.com.questv.contract.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SeriesListAdapter extends RecyclerView.Adapter<SeriesListViewHolder> {


  private final List<String> seriesCategories;
  private final OnItemClickListener<SeriesModel> onItemClickListener;

  public SeriesListAdapter(final OnItemClickListener<SeriesModel> onItemClickListener) {
    this.seriesCategories = new ArrayList<>();
    this.onItemClickListener = onItemClickListener;
    this.seriesCategories.add("none");//TODO('remove')
    this.seriesCategories.addAll(SeriesRepositoryImpl.getInstance().findAllCategories());
  }

  @NonNull
  @Override
  public SeriesListViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup,
                                                 final int viewType) {

    final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
    final View view = inflateView(viewGroup, inflater);
    return new SeriesListViewHolder(view);
  }

  private View inflateView(final ViewGroup viewGroup, final LayoutInflater inflater) {
    return inflater.inflate(R.layout.series_list, viewGroup, false);
  }

  @Override
  public void onBindViewHolder(@NonNull final SeriesListViewHolder seriesListViewHolder, final int position) {
    if (position == 0) {
      seriesListViewHolder.bindViewPager(onItemClickListener);
    } else {
      final String category = this.seriesCategories.get(position);
      seriesListViewHolder.bindRecyclerView(category, onItemClickListener);
    }
  }

  @Override
  public int getItemCount() {
    return this.seriesCategories.size();
  }
}
