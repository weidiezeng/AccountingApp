package com.example.administrator.accountingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class ListeViewAdapter extends BaseAdapter {

    private LinkedList<RecordBean>records=new LinkedList<>();

    private LayoutInflater inflater;
    private Context context;
    public ListeViewAdapter(Context context){
        this.context=context;

        inflater=LayoutInflater.from(context);

    }
    public void setData(LinkedList<RecordBean>records){
        this.records=records;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return records.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    /**
     * 绑定ListView
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.cell_list_view,null);
            RecordBean recordBean= (RecordBean) getItem(position);
            holder=new ViewHolder(convertView,recordBean);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        return convertView;
    }
}
class ViewHolder{
    TextView remarkTV;
    TextView amountTV;
    TextView timeTV;
    ImageView categoryIcone;

    public ViewHolder(View itemView,RecordBean records){
        remarkTV=itemView.findViewById(R.id.textview_remark);
        amountTV=itemView.findViewById(R.id.textview_amount);
        timeTV=itemView.findViewById(R.id.textview_time);
        categoryIcone=itemView.findViewById(R.id.imageview_category);

        remarkTV.setText(records.getRemark());

        if (records.getType() == 1){
            amountTV.setText("- "+records.getAmount());
        } else {
            amountTV.setText("+ "+records.getAmount());
        }

        timeTV.setText(DateUtil.getFormattedTime(records.getTimeStamp()));
        categoryIcone.setImageResource(GlobalUtil.getInstance().getResourceIcon(records.getCategory()));
    }
}
