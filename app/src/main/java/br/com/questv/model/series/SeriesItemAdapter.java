package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import br.com.questv.R;

import java.util.List;

public class SeriesItemAdapter extends RecyclerView.Adapter<SeriesItemViewHolder> {

  private final List<SeriesModel> series;
  private ImageView imageView;

  /*default*/ SeriesItemAdapter(final String category) {
    this.series = SeriesRepositoryImpl.getInstance().findByCategory(category);
  }

  @NonNull
  @Override
  public SeriesItemViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
    final View view = inflateView(viewGroup, inflater);
    return new SeriesItemViewHolder(view);
  }

  private View inflateView(final ViewGroup viewGroup, final LayoutInflater inflater) {
    return inflater.inflate(R.layout.series_item, viewGroup, false);
  }

  @Override
  public void onBindViewHolder(@NonNull final SeriesItemViewHolder seriesItemViewHolder, final int position) {
    seriesItemViewHolder.bind(getByPosition(position));
  }

  @Override
  public int getItemCount() {
        return this.series.size();
  }


  private SeriesModel getByPosition(final int position) {
    return this.series.get(position);
  }


}
