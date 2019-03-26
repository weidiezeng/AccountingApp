package com.example.administrator.accountingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryViewHolder>{

    private LayoutInflater mInflater;
    private Context context;
    private LinkedList<CategoryResBean>cellList=GlobalUtil.getInstance().costRes;

    public String getSelected() {
        return selected;
    }

    private String selected="";
    public void setOnCategoryClickListener(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }

    private OnCategoryClickListener onCategoryClickListener;

    public CategoryRecyclerAdapter(Context context){
        //GlobalUtil.getInstance().setContext(context);
        this.context=context;
        mInflater=LayoutInflater.from(context);
        selected=cellList.get(0).title;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=mInflater.inflate(R.layout.cell_category,viewGroup,false);
        CategoryViewHolder viewHolder=new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        final CategoryResBean res=cellList.get(i);
        categoryViewHolder.imageView.setImageResource(res.resBlack);
        categoryViewHolder.textView.setText(res.title);

        categoryViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = res.title;
                notifyDataSetChanged();

                if (onCategoryClickListener!=null){
                    onCategoryClickListener.onClick(res.title);
                }

            }
        });
        if(categoryViewHolder.textView.getText().toString().equals(selected)){
            categoryViewHolder.background.setBackgroundResource(R.drawable.bg_edit_text);
        }else{
            categoryViewHolder.background.setBackgroundResource(R.color.colorPrimary);
        }
    }

    public void changeType(RecordBean.RecordType type){
        if(type==RecordBean.RecordType.RECORD_TYPE_EXPENSE){
            cellList=GlobalUtil.getInstance().costRes;
        }else {
            cellList=GlobalUtil.getInstance().earnRes;
        }
        selected=cellList.get(0).title;
        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return cellList.size();
    }
    public interface OnCategoryClickListener{
        void onClick(String category);
    }
}
class CategoryViewHolder extends RecyclerView.ViewHolder{
    RelativeLayout background;
    ImageView imageView;
    TextView textView;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        background=itemView.findViewById(R.id.cell_background);
        imageView=itemView.findViewById(R.id.imageview_category2);
        textView=itemView.findViewById(R.id.textView_category);
    }
}
