package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.questv.R;
import br.com.questv.contract.OnItemClickListener;
import com.nostra13.universalimageloader.core.ImageLoader;

/*default*/ final class SeriesItemViewHolder extends RecyclerView.ViewHolder {


  private final TextView seriesName;
  private final ImageView seriesCover;

  /*default*/ SeriesItemViewHolder(@NonNull final View itemView) {
    super(itemView);
    this.seriesName = itemView.findViewById(R.id.tv_series_name);
    this.seriesCover = itemView.findViewById(R.id.iv_series_cover);
  }


  /*default*/ void bind(final SeriesModel seriesModel, final OnItemClickListener<SeriesModel> onItemClickListener) {
    this.seriesCover.setOnClickListener(v -> onItemClickListener.onClick(seriesModel));

    this.seriesName.setText(seriesModel.getName());

    ImageLoader imageLoader = ImageLoader.getInstance();
    String url = seriesModel.getCoverImageUrl();
    if (url != null && !url.isEmpty()) {
      imageLoader.displayImage(url.replace("localhost", "10.0.2.2"), this.seriesCover);
      this.seriesCover.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

  }


}
