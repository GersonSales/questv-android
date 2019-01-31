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


  private final Map<String, List<SeriesDTO>> categories;

  public SeriesListAdapter(final List<SeriesDTO> seriesDTOList) {
    this.categories = getCategoryMap(seriesDTOList);
  }

  private Map<String, List<SeriesDTO>> getCategoryMap(final List<SeriesDTO> seriesDTOList) {
    final Map<String, List<SeriesDTO>> categoriesMap = new HashMap<>();
    for (final SeriesDTO seriesDTO : seriesDTOList) {
      final String category = seriesDTO.getCategory();
      if (categoriesMap.containsKey(category)) {
        Objects.requireNonNull(categoriesMap.get(category)).add(seriesDTO);
      }else {
        final ArrayList<SeriesDTO> seriesDTOArrayList = new ArrayList<>();
        seriesDTOArrayList.add(seriesDTO);
        categoriesMap.put(category, seriesDTOArrayList);
      }
    }
    return categoriesMap;
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
      seriesListViewHolder.bindRecyclerView();

    }
  }

  @Override
  public int getItemCount() {
    return this.categories.keySet().size();
  }
}
