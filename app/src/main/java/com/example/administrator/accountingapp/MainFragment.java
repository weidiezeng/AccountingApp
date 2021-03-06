package com.example.administrator.accountingapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;


/**
 *
 */
@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements AdapterView.OnItemLongClickListener {
    private View rootView;
    private TextView textView;
    private ListView listView;
    private ListeViewAdapter listviewAdapter;
    private LinkedList<RecordBean>records=new LinkedList<>();
    private String date="";

    public MainFragment(String date){
        this.date=date;
        try{
            records=GlobalUtil.getInstance().databaseHelper.readrRecord(date);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_main,container,false);
        initView();
        return rootView;
    }


    /**
     *重新加载ListView
     */
    public void reload(){
        try{
            records=GlobalUtil.getInstance().databaseHelper.readrRecord(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(listviewAdapter==null){
            listviewAdapter=new ListeViewAdapter(getActivity().getApplicationContext());
        }
           listviewAdapter.setData(records);
           listView.setAdapter(listviewAdapter);

        if(listviewAdapter.getCount()>0){
            rootView.findViewById(R.id.no_record_layout).setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 初始化FragMent
     */
    private void initView(){
        textView=rootView.findViewById(R.id.day_text);
        listView=rootView.findViewById(R.id.list_view);
        textView.setText(date);

        //设置ListView适配器
        listviewAdapter=new ListeViewAdapter(getContext());
        listviewAdapter.setData(records);
        listView.setAdapter(listviewAdapter);

        if(listviewAdapter.getCount()>0){
            rootView.findViewById(R.id.no_record_layout).setVisibility(View.INVISIBLE);
        }
        textView.setText(DateUtil.getDateTitle(date));
        listView.setOnItemLongClickListener(this);
    }



    /**
     * @return 返回总花费
     */
    public double getTotalCost(){
        double totalCost=0;
        for(RecordBean recordBean:records){
            if (recordBean.getType()==1){
                totalCost-= recordBean.getAmount();
            }else if(recordBean.getType()==2){
                totalCost+=recordBean.getAmount();
            }
        }
        return totalCost;
    }

    /**监听长按事件，并显示对话框
     * @param parent
     * @param view
     * @param position
     * @param id
     * @return
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        showDialog(position);
        return false;
    }

    /**
     * 显示对话框dialog
     * @param index
     */
    private void showDialog(int index){
        final String[] options={"Remove","Edit"};
        final RecordBean selectedRecord = records.get(index);
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.create();

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0){
                    //remove
                    String uuid = selectedRecord.getUuid();
                    GlobalUtil.getInstance().databaseHelper.removeRecord(uuid);
                    reload();
                    GlobalUtil.getInstance().mainActivity.updateHeader();
                }else if (which==1){
                    //edit
                    Intent intent = new Intent(getActivity(),AddRecordActivity.class);
                    Bundle extra = new Bundle();
                    extra.putSerializable("record",selectedRecord);
                    intent.putExtras(extra);
                    startActivityForResult(intent,1);
                }

            }
        });
        //设置取消
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }
}
