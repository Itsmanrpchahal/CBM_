package com.casebeaumonde.activities.b_questionaries.selectbrands.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SectionIndexer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.casebeaumonde.R;
import com.casebeaumonde.activities.b_questionaries.selectbrands.B_SelectBrands;
import com.casebeaumonde.activities.b_questionaries.tellusmore.B_Tell_US_MORE;
import com.casebeaumonde.activities.questionaries.describeyourself.DescribeYourself;
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse;

import java.util.ArrayList;
import java.util.List;

public class B_DataAdapter extends RecyclerView.Adapter<B_DataAdapter.MyHolder> implements SectionIndexer,Comparable{
    ArrayList<QuestionariesDataResponse.Brand> brands;
    private ArrayList<Integer> mSectionPositions;
    Context context;

    public B_DataAdapter(ArrayList<QuestionariesDataResponse.Brand> brandArrayList, Context context) {
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
                B_SelectBrands.bB_SelectedBrand_IF.getID(String.valueOf(brands.get(position).getId()),"1");
            }  else {
                B_SelectBrands.bB_SelectedBrand_IF.getID(String.valueOf(brands.get(position).getId()),"0");
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, B_Tell_US_MORE.class);
//                context.startActivity(intent);
////                context.startActivity(new Intent(context, DescribeYourself.class));
//            }
//        });

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