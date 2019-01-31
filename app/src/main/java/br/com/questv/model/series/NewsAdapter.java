package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.questv.R;

public class NewsAdapter extends PagerAdapter {

  private int[] GalImages = new int[] {
      R.drawable.twd_slide_01,
      R.drawable.st_slide_01,
      R.drawable.tbbt_slide_02,
      R.drawable.dn_slide_01
  };

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

    final ImageView imageView = view.findViewById(R.id.iv_news_item);
    imageView.setImageResource(GalImages[position%GalImages.length]);
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

    final TextView textView = view.findViewById(R.id.tv_news_desc);
    textView.setText(container.getContext().getString(R.string.series_name));

    container.addView(view, 0);

    return view;

  }



}
