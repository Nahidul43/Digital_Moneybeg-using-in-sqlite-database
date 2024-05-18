package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowData extends AppCompatActivity {

    ListView listView;
    TextView tvTitle;
    OpenDataBaseHelper helper;
    ArrayList<HashMap<String,String>>arrayList;
    HashMap<String,String>hashMap;
    public static boolean EXPENSE=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        listView=findViewById(R.id.listView);
        tvTitle=findViewById(R.id.tvTitle);
        helper=new OpenDataBaseHelper(this);

        if(EXPENSE==true)tvTitle.setText("Showing All Expense");
        else tvTitle.setText("Showing All Income");

            showData();



    }

    public void showData(){
        Cursor cursor=null;
        if(EXPENSE==true)  cursor=helper.GetAllExpense();
        else cursor=helper.GetAllincome();




        if(cursor!=null&&cursor.getCount()>0){
            arrayList=new ArrayList<>();

            while(cursor.moveToNext()){

                int  id=cursor.getInt(0);
                double amount=cursor.getDouble(1);
                String reason=cursor.getString(2);
                long    time=cursor.getLong(3);

                hashMap=new HashMap<>();
                hashMap.put("id",""+id);
                hashMap.put("amount",""+amount);
                hashMap.put("reason",""+reason);
                hashMap.put("time",""+time);
                arrayList.add(hashMap);

            }
            listView.setAdapter(new Adapter());
        }else tvTitle.append("\n No Data Found");
    }


    public class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater=getLayoutInflater();
           View Myview=inflater.inflate(R.layout.showdata,viewGroup,false);

            TextView tvreason=Myview.findViewById(R.id.tvreason);
            TextView tvTotalExpenses=Myview.findViewById(R.id.tvTotalExpenses);
            TextView tvTime=Myview.findViewById(R.id.tvTime);
            TextView delet=Myview.findViewById(R.id.delet);
            TextView update=Myview.findViewById(R.id.update);


            hashMap=arrayList.get(i);
         String id=   hashMap.get("id");
         String sAmount=   hashMap.get("amount");
          String Sreason  =hashMap.get("reason");
           String sTime= hashMap.get("time");

            tvreason.setText(Sreason);
            tvTotalExpenses.setText("BDT "+sAmount);
            tvTime.setText(sTime);

        delet.setOnClickListener(view1 -> {
            if(EXPENSE==true){
                helper.DeleteExpense(id);
                showData();
            }else{
                helper.Deleteincome(id);
                showData();
            }

        });

            update.setOnClickListener(view1 -> {
                UpdateData.EXPENSE=true;
                Intent intent = new Intent(ShowData.this, UpdateData.class);
                intent.putExtra("ID", id);
                startActivity(intent);

            });
            update.setOnClickListener(view1 -> {
                UpdateData.EXPENSE=false;
                Intent intent = new Intent(ShowData.this, UpdateData.class);
                intent.putExtra("ID", id);
                startActivity(intent);

            });


            return Myview;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        showData();
    }
}