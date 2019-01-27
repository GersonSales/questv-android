package br.com.questv.model.series;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import br.com.questv.R;

public class NewsAdapter extends PagerAdapter {

  private int[] GalImages = new int[] {
      R.drawable.black_2189644_960_720
  };


  @Override
  public int getCount() {
    return 2;
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
    final ImageView imageView = new ImageView(container.getContext());


    imageView.setImageResource(GalImages[0]);

    container.addView(imageView);
    return imageView;
  }

}
