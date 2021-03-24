package com.casebeaumonde.activities.questionaries.selectbrands.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse;
import com.casebeaumonde.activities.questionaries.selectbrands.IF.SelectedBrand_IF;
import com.casebeaumonde.activities.questionaries.selectbrands.SelectBrands;
import com.casebeaumonde.constants.Data;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyHolder> implements SectionIndexer,Comparable{
    ArrayList<QuestionariesDataResponse.Data.Customer.Brand> brands;
    private ArrayList<Integer> mSectionPositions;
    Context context;

    public DataAdapter(ArrayList<QuestionariesDataResponse.Data.Customer.Brand> brandArrayList, Context context) {
        this.brands = brandArrayList;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String data = brands.get(position).getName();
        holder.title.setText(data);


        holder.star.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
            {
                SelectBrands.selectedbrandIf.getID(String.valueOf(brands.get(position).getId()),"1");
            }  else {
                SelectBrands.selectedbrandIf.getID(String.valueOf(brands.get(position).getId()),"0");
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
        return brands.size();
    }

    @Override
    public Object[] getSections() {
        List<String> sections = new ArrayList<>();
        mSectionPositions = new ArrayList<>();
        for (int i = 0, size = brands.size(); i < size; i++) {

//            String section = String.valueOf(dataList.get(i).charAt(0));
//            Log.d("section",""+section);
//            if (!sections.contains(section)) {
//                sections.add(section);
//                mSectionPositions.add(i);
//            }
        }

        return sections.toArray(new String[0]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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