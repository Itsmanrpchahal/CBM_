package com.casebeaumonde.activities.questionaries.selectbrands.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.casebeaumonde.R;
import com.casebeaumonde.activities.questionaries.describeyourself.DescribeYourself;
import com.casebeaumonde.activities.questionaries.selectbrands.IF.SelectedBrand_IF;
import com.casebeaumonde.activities.questionaries.selectbrands.SelectBrands;
import com.casebeaumonde.constants.Data;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyHolder> implements SectionIndexer,Comparable{
    List<Data> dataList;
    private ArrayList<Integer> mSectionPositions;
    Context context;

    public DataAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Data data = dataList.get(position);
        holder.title.setText(data.getTitle());


        holder.star.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
            {
                SelectBrands.selectedbrandIf.getID(String.valueOf(position),"1");
            }  else {
                SelectBrands.selectedbrandIf.getID(String.valueOf(position),"0");
            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DescribeYourself.class);
                context.startActivity(intent);
//                context.startActivity(new Intent(context, DescribeYourself.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public Object[] getSections() {
        List<String> sections = new ArrayList<>();
        mSectionPositions = new ArrayList<>();
        for (int i = 0, size = dataList.size(); i < size; i++) {
            String section = String.valueOf(dataList.get(i).getTitle().charAt(0)).toUpperCase();
            if (!sections.contains(section)) {
                sections.add(section);
                mSectionPositions.add(i);
            }
        }
        return sections.toArray(new String[0]);
    }

    @Override
    public int getPositionForSection(int i) {
        return mSectionPositions.get(i);    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView title;
        CheckBox star;
        public MyHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvName);
            star = itemView.findViewById(R.id.star);
        }
    }
}