package com.ranapplications.rcam.mainActivity.mainFragment.sceneFragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ranapplications.rcam.R;
import com.ranapplications.rcam.global.DataManager;

import java.util.ArrayList;

public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.SceneViewHolder>{

    private LayoutInflater layoutInflater;
    private ArrayList<SceneClass> sceneClasses;
    private SceneAdapterCallBack callBack;

    public SceneAdapter(Context context, ArrayList<SceneClass> sceneClasses,  SceneAdapterCallBack callBack){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.sceneClasses = sceneClasses;
        this.callBack = callBack;
    }

    @Override
    public SceneAdapter.SceneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= layoutInflater.inflate(R.layout.scene_row,parent,false);
        SceneAdapter.SceneViewHolder viewHolder= new SceneAdapter.SceneViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SceneAdapter.SceneViewHolder holder, int position) {
        SceneClass oneRow = sceneClasses.get(position);

        holder.setupRow(oneRow);
    }

    @Override
    public int getItemCount() {
        return sceneClasses.size();
    }



    class SceneViewHolder extends RecyclerView.ViewHolder{
        private View v;


        SceneViewHolder(View itemView) {
            super(itemView);
            v = itemView;
        }

        void setupRow(SceneClass oneRow){
            setBackgroundImage(oneRow.isActivate(), oneRow.getIconActive(), oneRow.getIconInactive());
            setText(oneRow.getTitle(), oneRow.isActivate());
            setOnClick(callBack, oneRow.getSceneID());
        }

        private void setOnClick(final SceneAdapterCallBack callBack, final int sceneID) {
            ConstraintLayout constraintLayout = v.findViewById(R.id.constraintLayout);

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean result = true;
                    if (sceneID == SceneClass.HOME)
                        result = false;

                    DataManager.getInstance().isActive = result;

                    updateFirebase(result);
                    callBack.onUserClick();
                }
            });
        }

        private void updateFirebase(boolean result) {
            DatabaseReference mData = FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(DataManager.getInstance().user.getUid())
                    .child("isActive");

            mData.setValue(result);
        }


        private void setBackgroundImage(boolean isOn, int iconActive, int iconInactive){
            int backgroundImage;
            int innerCircleImage;

            if(isOn){
                backgroundImage = R.drawable.scene_on;
                innerCircleImage = R.drawable.scene_on_inner_circle;
                Glide.with(v.getContext()).load(iconActive).into((ImageView) v.findViewById(R.id.imageViewIcon));

            }else{
                backgroundImage = R.drawable.scene_off;
                innerCircleImage = R.drawable.scene_off_inner_circle;
                Glide.with(v.getContext()).load(iconInactive).into((ImageView) v.findViewById(R.id.imageViewIcon));
            }

            Glide.with(v.getContext()).load(backgroundImage).into((ImageView) v.findViewById(R.id.imageViewBackground));
            Glide.with(v.getContext()).load(innerCircleImage).into((ImageView) v.findViewById(R.id.imageViewInnerCircle));

        }

        private void setText(String text, boolean isOn){
            TextView textViewTitle = v.findViewById(R.id.textViewTitle);
            textViewTitle.setText(text);

            if (isOn)
                textViewTitle.setTextColor(Color.WHITE);
            else
                textViewTitle.setTextColor(ContextCompat.getColor(v.getContext(), R.color.textColor));
        }

    }

    public interface SceneAdapterCallBack{
        void onUserClick();
    }
}
