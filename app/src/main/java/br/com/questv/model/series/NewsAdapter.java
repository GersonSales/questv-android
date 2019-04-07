package br.com.questv.model.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import br.com.questv.R;
import br.com.questv.contract.OnItemClickListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
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
  public boolean isViewFromObject(final View view, final Object object) {
    return view == object;
  }

  @Override
  public void destroyItem(final ViewGroup container, final int position,
                          final Object object) {
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

  @Override
  public Object instantiateItem(final ViewGroup container, final int position) {
    final LayoutInflater inflater = LayoutInflater.from(container.getContext());
    final View view = inflater.inflate(R.layout.news_item, container, false);
    if (this.releases.size() == 0) return view;
    final SeriesModel seriesModel = this.releases.get(getRealPosition(position));

    final ImageView imageView = view.findViewById(R.id.iv_news_item);
    imageView.setOnClickListener((i) -> onItemClickListener.onClick(seriesModel));
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

    DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .considerExifParams(true)
        .build();

    final ImageLoader imageLoader = ImageLoader.getInstance();
    final String url = seriesModel.getPromoImageUrl();
    if (url != null)
      imageLoader.displayImage(url.replace("localhost", "10.0.2.2"), imageView, imageOptions);

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
