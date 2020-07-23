package com.ranapplications.rcam.cameraFragment;

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
import com.ranapplications.rcam.global.Global;

import java.util.ArrayList;

/**
 * This class is the adapter for the RecyclerView
 * It will present all the alerts from the camera (about a specific camera)
 */

public class CameraFragmentAdapter extends RecyclerView.Adapter<CameraFragmentAdapter.CameraFragmentViewHolder>{

    private LayoutInflater layoutInflater;
    private ArrayList<CameraAlertsClass> cameraAlertsClasses;

    public CameraFragmentAdapter(Context context, ArrayList<CameraAlertsClass> cameraAlertsClasses) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cameraAlertsClasses = cameraAlertsClasses;
    }

    @NonNull
    @Override
    public CameraFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.alert_row,parent,false);
        CameraFragmentAdapter.CameraFragmentViewHolder viewHolder= new CameraFragmentAdapter.CameraFragmentViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CameraFragmentViewHolder holder, int position) {
        CameraAlertsClass oneRow = cameraAlertsClasses.get(position);

        holder.setDateAndTime(oneRow.getTime());
        holder.setConfidence(oneRow.getConfidence());
        holder.setIconAndTitle(oneRow.getReason());
    }

    @Override
    public int getItemCount() {
        return cameraAlertsClasses.size();
    }

    class CameraFragmentViewHolder extends RecyclerView.ViewHolder{
        private View v;

        public CameraFragmentViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
        }

        void setDateAndTime(long time){
            TextView textViewContent = v.findViewById(R.id.textViewContent);
            String text = Global.getDateFromTimeTimestamp(time) + "  " + Global.getTimeFromTimeTimestamp(time);
            textViewContent.setText(text);
        }

        void setConfidence(int confidence){
            TextView textViewConfidence = v.findViewById(R.id.textViewConfidence);
            textViewConfidence.setText(""+confidence + "%");
        }

        void setIconAndTitle(String reason){
            int icon;
            String title;
            switch (reason){
                case "humans":
                    icon = R.drawable.human_motion_detector_icon;
                    title = v.getContext().getResources().getString(R.string.humanMovement);
                    break;
                case "cat":
                    icon = R.drawable.cat_motion_detector_icon;
                    title = v.getContext().getResources().getString(R.string.catMovement);
                    break;
                case "dog":
                    icon = R.drawable.dog_motion_detector_icon;
                    title = v.getContext().getResources().getString(R.string.dogMovement);
                    break;
                    default:
                        icon = R.drawable.human_motion_detector_icon;
                        title = v.getContext().getResources().getString(R.string.aMovement);
                        break;
            }

            TextView textViewTitle = v.findViewById(R.id.textViewTitle);
            textViewTitle.setText(title);
            Glide.with(v.getContext()).load(icon).into((ImageView) v.findViewById(R.id.imageViewIcon));

        }

    }
}
