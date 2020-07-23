package com.ranapplications.rcam.mainActivity.mainFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ranapplications.rcam.R;

import java.util.ArrayList;

public class CamerasListAdapter extends RecyclerView.Adapter<CamerasListAdapter.CamerasListViewHolder>{

    private ArrayList<Cameras> cameras;
    private LayoutInflater layoutInflater;
    private CamerasListAdapterCallback callback;
    private final static int TITLE_CALL = 0;
    private final static int CAMERA_CELL = 2;

    public CamerasListAdapter(Context context, ArrayList<Cameras> cameras, CamerasListAdapterCallback callback) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cameras = cameras;
        this.callback = callback;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TITLE_CALL)
            return TITLE_CALL;
        else
            return CAMERA_CELL;
    }

    @NonNull
    @Override
    public CamerasListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (viewType == TITLE_CALL)
            v = layoutInflater.inflate(R.layout.first_row_for_cameras_list,parent,false);
        else
            v = layoutInflater.inflate(R.layout.camera_row,parent,false);

        CamerasListAdapter.CamerasListViewHolder viewHolder= new CamerasListAdapter.CamerasListViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CamerasListViewHolder holder, int position) {
        if (getItemViewType(position) == CAMERA_CELL){
            Cameras oneRow = cameras.get(position - 1);

            holder.setTextViewAlarmNotifications(oneRow.getNewAlerts());
            holder.setCameraName(oneRow.getName());
            holder.setCameraStatus(oneRow.isActive());
            holder.setOnClick(oneRow, position-1);
        }
    }

    @Override
    public int getItemCount() {
        return cameras.size() + 1;
    }

    class CamerasListViewHolder extends RecyclerView.ViewHolder{
        private View v;

        public CamerasListViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
        }

        void setTextViewAlarmNotifications(int newAlerts){
            TextView textViewAlarmNotifications = v.findViewById(R.id.textViewAlarmNotifications);
            textViewAlarmNotifications.setText(""+ newAlerts);
        }

        void setCameraName(String cameraName){
            TextView textViewCameraName = v.findViewById(R.id.textViewCameraName);
            textViewCameraName.setText(""+ cameraName);
        }

        void setCameraStatus(boolean isActive){
            int image;
            String text;

            if (isActive){
                image = R.drawable.camera_connected;
                text = v.getContext().getString(R.string.connected);
            }else{
                image = R.drawable.camera_offline;
                text = v.getContext().getString(R.string.offline);
            }
            Glide.with(v.getContext()).load(image).into((ImageView) v.findViewById(R.id.imageViewCameraStatus));

            TextView textViewCameraStatus = v.findViewById(R.id.textViewCameraStatus);
            textViewCameraStatus.setText(text);
        }

        void setOnClick(final Cameras camera, final int position){
            View view = v.findViewById(R.id.view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClickRow(camera,position);
                }
            });
        }
    }

    public interface CamerasListAdapterCallback{
        void onClickRow(Cameras camera, int position);
    }
}
