package com.casebeaumonde.fragments.productManagement.productdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.casebeaumonde.R;
import com.casebeaumonde.fragments.productManagement.response.ProductListResponse;

import java.util.ArrayList;
import java.util.Objects;

class ViewPagerAdapter extends PagerAdapter {
  
    // Context object 
    Context context;
      
    // Array of images
    ArrayList<ProductListResponse.Data.Products.Datum.ProductImage> images;
      
    // Layout Inflater
    LayoutInflater mLayoutInflater;
  
  
    // Viewpager Constructor 
    public ViewPagerAdapter(Context context, ArrayList<ProductListResponse.Data.Products.Datum.ProductImage> images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
  
    @Override
    public int getCount() {
        // return the number of images
        return images.size();
    }
  
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }
  
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml 
        View itemView = mLayoutInflater.inflate(R.layout.customviewpager, container, false);
  
        // referencing the image view from the item.xml file 
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);
          
        // setting the image in the imageView
        Glide.with(context).load(images.get(position).getImage()).placeholder(R.drawable.login_banner1).into(imageView);
      //  imageView.setImageResource(Integer.parseInt(images.get(position).getImage().toString()));
  
        // Adding the View
        Objects.requireNonNull(container).addView(itemView);
  
        return itemView;
    }
  
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
          
        container.removeView((LinearLayout) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}