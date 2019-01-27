package br.com.questv.model.series;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.questv.R;

public class SeriesListAdapter extends RecyclerView.Adapter<SeriesListViewHolder> {


    @NonNull
    @Override
    public SeriesListViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup,
                                                   final int viewType) {

        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final View view = inflateView(viewGroup, inflater);
        return new SeriesListViewHolder(view);
    }

    private View inflateView(final ViewGroup viewGroup, final LayoutInflater inflater) {
        return inflater.inflate(R.layout.series_list, viewGroup, false);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesListViewHolder seriesListViewHolder, int position) {
        seriesListViewHolder.bind();

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
