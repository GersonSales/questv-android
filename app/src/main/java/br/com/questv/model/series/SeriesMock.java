package br.com.questv.model.series;

import java.util.ArrayList;
import java.util.List;

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
}
