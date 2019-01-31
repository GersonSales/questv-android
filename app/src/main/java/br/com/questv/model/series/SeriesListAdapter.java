package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.questv.R;
import br.com.questv.model.series.dto.SeriesDTO;

import java.util.*;

public class SeriesListAdapter extends RecyclerView.Adapter<SeriesListViewHolder> {


  private final List<SeriesByCategory> seriesByCategories;

  public SeriesListAdapter(final List<SeriesDTO> seriesDTOList) {
    this.seriesByCategories = new ArrayList<>();
    initSeriesByCategory(seriesDTOList);
  }

  private void initSeriesByCategory(final List<SeriesDTO> seriesDTOList) {
    this.seriesByCategories.add(new SeriesByCategory("view-holder-spot", new ArrayList<>()));
    for (final SeriesDTO seriesDTO : seriesDTOList) {
      attachOnCategory(seriesDTO);
    }
  }

  private void attachOnCategory(final SeriesDTO seriesDTO) {
    for (final SeriesByCategory seriesByCategory : this.seriesByCategories) {
      if (seriesByCategory.getCategory().equalsIgnoreCase(seriesDTO.getCategory())) {
        seriesByCategory.getSeries().add(seriesDTO);
        return;
      }
    }
    ArrayList<SeriesDTO> series = new ArrayList<>();
    series.add(seriesDTO);
    this.seriesByCategories.add(new SeriesByCategory(seriesDTO.getCategory(), series));
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
      seriesListViewHolder.bindViewPager();
    } else {
      final SeriesByCategory seriesByCategory = this.seriesByCategories.get(position);
      seriesListViewHolder.bindRecyclerView(seriesByCategory);
    }
  }

  @Override
  public int getItemCount() {
    return this.seriesByCategories.size();
  }
}
