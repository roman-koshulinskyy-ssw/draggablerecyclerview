package com.example.sgubar.draggableview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sgubar.draggableview.entities.Device;
import com.example.sgubar.draggableview.repositoires.DevicesRepository;

import java.util.List;

/**
 * Created by sgubar on 11/21/17.
 */

public class DevicesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private DevicesRepository mDevicesRepository;
    private List<Device> mData;
    private LayoutInflater mLayoutInflater;
    private static final int TYPE_NOT_EMPTY = 1;
    private static final int TYPE_EMPTY = 0;

    public DevicesRecyclerAdapter(Context ctx) {
        mDevicesRepository = new DevicesRepository(ctx);
        mData = mDevicesRepository.getDevices();
        mLayoutInflater = LayoutInflater.from(ctx);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_NOT_EMPTY:
                View notEmptyView = mLayoutInflater.inflate(R.layout.device_not_empty_item, parent, false);
                return new DeviceHolderNotEmpty(notEmptyView);
            default:
                View emptyView = mLayoutInflater.inflate(R.layout.device_empty_item, parent, false);
                return new DeviceHolderEmpty(emptyView);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_NOT_EMPTY:
                DeviceHolderNotEmpty notEmptyHolder = (DeviceHolderNotEmpty) holder;
                notEmptyHolder.bind(mData.get(position));
                break;
            case TYPE_EMPTY:
                DeviceHolderEmpty holderEmpty = (DeviceHolderEmpty) holder;
                // TODO : Some bind here
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) == null) {
            return TYPE_EMPTY;
        } else {
            return TYPE_NOT_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class DeviceHolderNotEmpty extends RecyclerView.ViewHolder {
        private TextView mPortNumberTextView;
        private TextView mNameTextView;
        private ImageView mPictureImageView;

        public DeviceHolderNotEmpty(View itemView) {
            super(itemView);
            mPortNumberTextView = itemView.findViewById(R.id.device_port_number_text_view);
            mNameTextView = itemView.findViewById(R.id.device_name_text_view);
            mPictureImageView = itemView.findViewById(R.id.device_picture_image_view);
        }

        public void bind(Device device) {
            mPortNumberTextView.setText(String.valueOf(device.getPortNumber()));
            mNameTextView.setText(device.getDeviceName());
            mPictureImageView.setImageResource(device.getPicturePath());
        }
    }
    class DeviceHolderEmpty extends RecyclerView.ViewHolder {

        public DeviceHolderEmpty(View itemView) {
            super(itemView);
            //TODO : Add some logic here, etc change port number
        }
    }

}



