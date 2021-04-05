package com.casebeaumonde.activities.questionaries.selectStores;

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
import com.casebeaumonde.activities.questionaries.describeyourself.DescribeYourself;
import com.casebeaumonde.activities.questionaries.reponse.QuestionariesDataResponse;
import com.casebeaumonde.activities.questionaries.selectbrands.SelectBrands;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter1 extends RecyclerView.Adapter<DataAdapter1.MyHolder> implements SectionIndexer,Comparable{
    ArrayList<String> stores;
    private ArrayList<Integer> mSectionPositions;
    Context context;

    public DataAdapter1(ArrayList<String> stores, Context context) {
        this.stores = stores;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String data = stores.get(position);
        holder.title.setText(data);

//        holder.star.setText(data);
        holder.star.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
            {
                SelectStores.selectedstoresIf.getID(stores.get(position),"1");
            }  else {
                SelectStores.selectedstoresIf.getID(stores.get(position),"0");
            }
        });


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
    public int getItemCount() {
        return stores.size();
    }

    @Override
    public Object[] getSections() {
        List<String> sections = new ArrayList<>();
        mSectionPositions = new ArrayList<>();
        for (int i = 0, size = stores.size(); i < size; i++) {

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