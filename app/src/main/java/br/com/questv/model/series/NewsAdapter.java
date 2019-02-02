package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.questv.R;
import br.com.questv.contract.OnItemClickListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class NewsAdapter extends PagerAdapter {

  private final OnItemClickListener<SeriesModel> onItemClickListener;

  private List<SeriesModel> releases = SeriesRepositoryImpl.getInstance().findReleases();

  public NewsAdapter(final OnItemClickListener<SeriesModel> onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  @Override
  public int getCount() {
    return Integer.MAX_VALUE;
  }

  @Override
  public boolean isViewFromObject(@NonNull final View view, @NonNull final Object object) {
    return view == object;
  }

  @Override
  public void destroyItem(@NonNull final ViewGroup container, final int position,
                          @NonNull final Object object) {
    container.removeView((View) object);
    unbindDrawables((View) object);
  }

  private void unbindDrawables(View view) {
    if (view.getBackground() != null) {
      view.getBackground().setCallback(null);
    }
    if (view instanceof ViewGroup) {
      for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
        unbindDrawables(((ViewGroup) view).getChildAt(i));
      }
      ((ViewGroup) view).removeAllViews();
    }
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
    final LayoutInflater inflater = LayoutInflater.from(container.getContext());
    final View view = inflater.inflate(R.layout.news_item, container, false);
    if (this.releases.size() == 0) return view;
    final SeriesModel seriesModel = this.releases.get(getRealPosition(position));

    final ImageView imageView = view.findViewById(R.id.iv_news_item);
    imageView.setOnClickListener((i) -> onItemClickListener.onClick(seriesModel));
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

    final ImageLoader imageLoader = ImageLoader.getInstance();
    final String url = seriesModel.getPromoImageUrl();
    if (url != null)
      imageLoader.displayImage(url.replace("localhost", "10.0.2.2"), imageView);

    final TextView textView = view.findViewById(R.id.tv_news_desc);
    textView.setText(seriesModel.getName());

    final TextView releaseCategory = view.findViewById(R.id.tv_release_category);
    releaseCategory .setText(seriesModel.getCategory());

    container.addView(view, 0);

    return view;
  }

  private int getRealPosition(int position) {
    return position%this.releases.size();
  }


}
