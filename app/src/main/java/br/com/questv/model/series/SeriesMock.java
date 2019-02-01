package br.com.questv.model.series;

import br.com.questv.model.series.dto.SeriesDTO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeriesMock {

  private static SeriesMock instance;
  private final List<SeriesModel> seriesModelList;


  public static SeriesMock getInstance() {
    if (instance == null) {
      instance = new SeriesMock();
    }
    return instance;
  }


  private SeriesMock() {
    this.seriesModelList = new ArrayList<>();
    feedList();
  }

  private void feedList() {
    for (int i = 0; i < 10; i++) {
      this.seriesModelList.add(new SeriesModel("Series " + i, "Abbreviation " + i, null));
    }
  }

  public SeriesModel getByPosition(final int position) {
    if (position < seriesModelList.size() && position >= 0) {
      return seriesModelList.get(position);
    }
    return null;
  }

  public int size() {
    return this.seriesModelList.size();
  }

  public void addAll(@Nullable List<SeriesDTO> series) {
    for (final SeriesDTO seriesDTO : Objects.requireNonNull(series)) {
      seriesModelList.add(seriesDTO.convert());
    }
  }

  public void update(@NotNull SeriesDTO series) {
    final SeriesModel seriesModel = findById(series.getId());
//    seriesModel.update(series.convert()); TODO


  }

  private SeriesModel findById(final Long id) {
    for(final SeriesModel seriesModel : this.seriesModelList) {
      if (seriesModel.getName().equalsIgnoreCase("id")) {
        return seriesModel;
      }
    }

    return null;
  }

  public List<String> getCategories() {
    return new ArrayList<>();
  }

  public List<SeriesModel> getByCategory(String category) {
    return new ArrayList<>();
  }

//  private void initSeriesByCategory(final List<SeriesDTO> seriesDTOList) {
//    this.seriesCategories.add(new SeriesByCategory("view-holder-spot", new ArrayList<>()));
//    for (final SeriesDTO seriesDTO : seriesDTOList) {
//      attachOnCategory(seriesDTO);
//    }
//  }
//
//  private void attachOnCategory(final SeriesDTO seriesDTO) {
//    for (final SeriesByCategory seriesByCategory : this.seriesCategories) {
//      if (seriesByCategory.getCategory().equalsIgnoreCase(seriesDTO.getCategory())) {
//        seriesByCategory.getSeries().add(seriesDTO);
//        return;
//      }
//    }
//    ArrayList<SeriesDTO> series = new ArrayList<>();
//    series.add(seriesDTO);
//    this.seriesCategories.add(new SeriesByCategory(seriesDTO.getCategory(), series));
//  }
}
