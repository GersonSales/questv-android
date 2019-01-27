package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import br.com.questv.R;

public class SeriesItemViewHolder extends RecyclerView.ViewHolder {


    private final TextView seriesName;
    private final TextView seriesAbbreviation;

    public SeriesItemViewHolder(@NonNull final View itemView) {
        super(itemView);
        this.seriesName = itemView.findViewById(R.id.tv_series_name);
        this.seriesAbbreviation = itemView.findViewById(R.id.tv_series_abbreviation);
    }



    public void bind(final SeriesModel seriesModel) {
        seriesName.setText(seriesModel.getName());
        seriesAbbreviation.setText(seriesModel.getAbbreviation());

    }
}
