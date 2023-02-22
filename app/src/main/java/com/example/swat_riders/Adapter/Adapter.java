package com.example.swat_riders.Adapter;

import androidx.recyclerview.widget.RecyclerView;


    import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swat_riders.Model.ModelClass;
import com.example.swat_riders.R;

import java.util.List;


    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private List<ModelClass> userlist;
        public Adapter (List<ModelClass>userlist){this.userlist = userlist; }

        @NonNull
        @Override
        public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

            int resource = userlist.get(position).getImage();
            String name = userlist.get(position).getTextview();
            String time = userlist.get(position).getTextview1();
            String msg = userlist.get(position).getTextview2();
            String divider = userlist.get(position).getTextview3();

            holder.setData(resource,name,time,msg,divider);
        }

        @Override
        public int getItemCount() {
            return userlist.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private ImageView imageView1;
            private TextView textView;
            private TextView textView1;
            private TextView textView2;
            private TextView textView3;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView1 = itemView.findViewById(R.id.image);
                textView = itemView.findViewById(R.id.txt1);
                textView1 = itemView.findViewById(R.id.txt2);
                textView2 = itemView.findViewById(R.id.txt3);
                textView3 = itemView.findViewById(R.id.txt4);





            }

            public void setData(int resource, String name, String time, String msg, String divider) {
                imageView1.setImageResource(resource);
                textView.setText(name);
                textView1.setText(time);
                textView2.setText(msg);
                textView3.setText(divider);
            }
        }
    }


