package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages=
            {
                    {"vitamin b capsule","","","","50"},
                    {"vitamin c capsule","","","","150"},
                    {"vitamin f capsule","","","","250"},
                    {"vitamin h capsule","","","","350"},
                    {"vitamin i capsule","","","","450"},
                    {"vitamin e capsule","","","","520"},
                    {"vitamin a capsule","","","","550"},
                    {"vitamin r capsule","","","","650"},
                    {"vitamin t capsule","","","","750"}
            };
    private  String[] package_details={
            "B-complex supplements usually pack all eight B vitamins into one pill\n"+
                  "B vitamins are water-soluble, which means your body does not store them\n"+
                    "They are typically available in various formulations",
            "C-complex supplements usually pack all eight B vitamins into one pill\n"+
                    "B vitamins are water-soluble, which means your body does not store them\n"+
                    "They are typically available in various formulations",
            "F-complex supplements usually pack all eight B vitamins into one pill\n"+
                    "B vitamins are water-soluble, which means your body does not store them\n"+
                    "They are typically available in various formulations",
            "H-complex supplements usually pack all eight B vitamins into one pill\n"+
                    "B vitamins are water-soluble, which means your body does not store them\n"+
                    "They are typically available in various formulations",
            "I-complex supplements usually pack all eight B vitamins into one pill\n"+
                    "B vitamins are water-soluble, which means your body does not store them\n"+
                    "They are typically available in various formulations",
            "E-complex supplements usually pack all eight B vitamins into one pill\n"+
                    "B vitamins are water-soluble, which means your body does not store them\n"+
                    "They are typically available in various formulations",
            "A-complex supplements usually pack all eight B vitamins into one pill\n"+
                    "B vitamins are water-soluble, which means your body does not store them\n"+
                    "They are typically available in various formulations",
            "R-complex supplements usually pack all eight B vitamins into one pill\n"+
                    "B vitamins are water-soluble, which means your body does not store them\n"+
                    "They are typically available in various formulations",
            "T-complex supplements usually pack all eight B vitamins into one pill\n"+
                    "B vitamins are water-soluble, which means your body does not store them\n"+
                    "They are typically available in various formulations"
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

     //MAPPING
     lst=findViewById(R.id.listViewBMCart);
     btnBack=findViewById(R.id.buttonBMCartBack);
     btnGoToCart=findViewById(R.id.buttonBMGoToCart);

     btnGoToCart.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
         }
     });

     btnBack.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
         }
     });

     //create object
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","total cost:" +packages[i][4]+"/=");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e,});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);

            }
        });
    }


}