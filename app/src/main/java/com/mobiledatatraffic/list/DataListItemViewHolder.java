package com.mobiledatatraffic.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mobiledatatraffic.R;

import static android.view.View.GONE;

public class DataListItemViewHolder extends RecyclerView.ViewHolder {

    TextView yearText;
    TextView volumeText;
    ImageView decreaseInVolumeImage;

    private OnImageClickedCallback onImageClickedCallback;

    public DataListItemViewHolder(View itemView) {
        super(itemView);
        yearText = itemView.findViewById(R.id.year);
        volumeText = itemView.findViewById(R.id.volume);
        decreaseInVolumeImage = itemView.findViewById(R.id.low_image);
    }

    public void bindView(final DataListItemViewModel viewModel) {
        yearText.setText(viewModel.getYear());
        volumeText.setText(viewModel.getVolume());
        if(viewModel.getFrom() !=null)
        {
            decreaseInVolumeImage.setVisibility(View.VISIBLE);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClickedCallback.onImageClicked(viewModel.getFrom(), viewModel.getTo(), viewModel.getQuarter());
            }
        });
    }

    public void setOnImageClickedCallback(OnImageClickedCallback onImageClickedCallback) {
        this.onImageClickedCallback = onImageClickedCallback;
    }

    public interface OnImageClickedCallback {

        void onImageClicked(String from, String to, String quarter);
    }
}

