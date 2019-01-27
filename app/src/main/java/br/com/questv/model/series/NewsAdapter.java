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
      R.drawable.black_2189644_960_720,
      R.drawable.gradiant_vector_comic_book_6,
      R.drawable.ic_android_black_24dp
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

    final TextView textView = view.findViewById(R.id.tv_news_desc);
    textView.setText("Description: ".concat(String.valueOf(imageView.getId())));

    container.addView(view, 0);

    return view;

  }



}
