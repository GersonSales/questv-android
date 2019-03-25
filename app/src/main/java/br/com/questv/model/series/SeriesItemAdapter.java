package br.com.questv.model.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import br.com.questv.R;
import br.com.questv.contract.OnItemClickListener;

import java.util.List;

public class SeriesItemAdapter extends RecyclerView.Adapter<SeriesItemViewHolder> {

  private final List<SeriesModel> series;
  private final OnItemClickListener<SeriesModel> onItemClickListener;

  /*default*/ SeriesItemAdapter(final String category, final OnItemClickListener<SeriesModel> onItemClickListener) {
    this.series = SeriesRepositoryImpl.getInstance().findByCategory(category);
    this.onItemClickListener = onItemClickListener;
  }

  @Override
  public SeriesItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
    final View view = inflateView(viewGroup, inflater);
    return new SeriesItemViewHolder(view);
  }

  private View inflateView(final ViewGroup viewGroup, final LayoutInflater inflater) {
    return inflater.inflate(R.layout.series_item, viewGroup, false);
  }

  @Override
  public void onBindViewHolder(final SeriesItemViewHolder seriesItemViewHolder, final int position) {
    seriesItemViewHolder.bind(getByPosition(position), onItemClickListener);
  }

  @Override
  public int getItemCount() {
        return this.series.size();
  }


  private SeriesModel getByPosition(final int position) {
    return this.series.get(position);
  }





}
