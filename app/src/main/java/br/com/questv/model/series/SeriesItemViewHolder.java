package br.com.questv.model.series;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.questv.R;

import java.io.File;

public class SeriesItemViewHolder extends RecyclerView.ViewHolder {


  private final TextView seriesName;
  private final ImageView seriesCover;

  /*default*/ SeriesItemViewHolder(@NonNull final View itemView) {
    super(itemView);
    this.seriesName = itemView.findViewById(R.id.tv_series_name);
    this.seriesCover = itemView.findViewById(R.id.iv_series_cover);
  }


  /*default*/ void bind(final SeriesModel seriesModel) {
    this.seriesName.setText(seriesModel.getName());
    final Uri imageCoverUri = seriesModel.getImageCoverUri();
    if (imageCoverUri != null) {
      this.seriesCover.setImageURI(imageCoverUri);
    }


  }
}
