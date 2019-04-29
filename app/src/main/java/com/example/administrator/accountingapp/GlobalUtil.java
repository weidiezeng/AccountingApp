package com.example.administrator.accountingapp;

import android.content.Context;


import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;



public class GlobalUtil {
    private static GlobalUtil instance;
    public RecordDatabaseHelper databaseHelper;
    public LinkedList<CategoryResBean> costRes = new LinkedList<>();
    public LinkedList<CategoryResBean> earnRes = new LinkedList<>();
    //public LinkedList<SearchBean>searchBeans=new LinkedList<>();
    private Context context;
    public MainActivity mainActivity;

    //支出标题
    private static String[] costTitle = {"General", "Food", "Drinks","Groceries", "Shopping", "Personal","Entertain","Movies", "Social", "Transport",
            "App Store","Mobile","Computer","Gifts", "Housing", "Travel","Tickets","Books", "Medical","Transfer"};


    //存储支出白色图片
    private static int [] costIconRes = {
            R.drawable.icon_general_white,
            R.drawable.icon_food_white,
            R.drawable.icon_drinking_white,
            R.drawable.icon_groceries_white,
            R.drawable.icon_shopping_white,
            R.drawable.icon_personal_white,
            R.drawable.icon_entertain_white,
            R.drawable.icon_movie_white,
            R.drawable.icon_social_white,
            R.drawable.icon_transport_white,
            R.drawable.icon_appstore_white,
            R.drawable.icon_mobile_white,
            R.drawable.icon_computer_white,
            R.drawable.icon_gift_white,
            R.drawable.icon_house_white,
            R.drawable.icon_travel_white,
            R.drawable.icon_ticket_white,
            R.drawable.icon_book_white,
            R.drawable.icon_medical_white,
            R.drawable.icon_transfer_white
    };
    //存储支出消费黑色图片
    private static int [] costIconResBlack = {
            R.drawable.icon_general,
            R.drawable.icon_food,
            R.drawable.icon_drinking,
            R.drawable.icon_groceries,
            R.drawable.icon_shopping,
            R.drawable.icon_presonal,
            R.drawable.icon_entertain,
            R.drawable.icon_movie,
            R.drawable.icon_social,
            R.drawable.icon_transport,
            R.drawable.icon_appstore,
            R.drawable.icon_mobile,
            R.drawable.icon_computer,
            R.drawable.icon_gift,
            R.drawable.icon_house,
            R.drawable.icon_travel,
            R.drawable.icon_ticket,
            R.drawable.icon_book,
            R.drawable.icon_medical,
            R.drawable.icon_transfer
    };
    //存储收入白色图片
    private static int[] earnIconRes = {
            R.drawable.icon_general_white,
            R.drawable.icon_reimburse_white,
            R.drawable.icon_salary_white,
            R.drawable.icon_redpocket_white,
            R.drawable.icon_parttime_white,
            R.drawable.icon_bonus_white,
            R.drawable.icon_investment_white};

    //存储收入黑色图片
    private static int[] earnIconResBlack = {
            R.drawable.icon_general,
            R.drawable.icon_reimburse,
            R.drawable.icon_salary,
            R.drawable.icon_redpocket,
            R.drawable.icon_parttime,
            R.drawable.icon_bonus,
            R.drawable.icon_investment};
    //存储收入标题
    private static String[] earnTitle = {"General", "Reimburse", "Salary","RedPocket","Part-time", "Bonus","Investment"};

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        databaseHelper=new RecordDatabaseHelper(context,RecordDatabaseHelper.DB_NAME,null,1);
        for(int i=0;i<costTitle.length;i++){
            CategoryResBean res=new CategoryResBean();
            res.title=costTitle[i];
            res.resBlack=costIconResBlack[i];
            res.reswhite=costIconRes[i];
            costRes.add(res);
        }
        for(int i=0;i<earnTitle.length;i++){
            CategoryResBean res=new CategoryResBean();
            res.title=earnTitle[i];
            res.resBlack=earnIconResBlack[i];
            res.reswhite=earnIconRes[i];
            earnRes.add(res);
        }
        //
       /* Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int month =calendar.get( Calendar.MONTH)+1;
        int year=calendar.get(Calendar.YEAR);
        searchBeans.clear();
        for(int i=1;i<=month;i++){

            SearchBean searchBean=new SearchBean();
            searchBean.year=year;
            searchBean.month=i;
            searchBean.amount=databaseHelper.Search(i);
            searchBeans.add(searchBean);
        }*/

    }




    public static GlobalUtil getInstance(){
        if(instance==null){
            instance=new GlobalUtil();
        }
        return instance;
    }

    /**获取资源图片
     * @param category
     * @return
     */
    public int getResourceIcon(String category){
        for(CategoryResBean res:costRes){
            if(res.title.equals(category)){
                return res.reswhite;
            }
        }
        for(CategoryResBean res:earnRes){
            if(res.title.equals(category)){
                return res.reswhite;
            }
        }
        return costRes.get(0).reswhite;
    }
}
