package com.example.lab_1_2_niharikareddymainampati_c0836194_android;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText prodId, prodPrice, prodNam, prodDesc;
    Button btnPrevious, btnNext,btnUpdate,btnDelete,btnAdd;
    List<Products> prodList = new ArrayList<>();
    int productViewing = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prodId = findViewById(R.id.pid);
        prodNam = findViewById(R.id.tv_name);
        prodPrice = findViewById(R.id.pp);
        prodDesc = findViewById(R.id.tv_des);
        btnNext = findViewById(R.id.bt_next);
        btnPrevious = findViewById(R.id.bt_prev);
        btnUpdate = findViewById(R.id.bt_update);
        btnDelete = findViewById(R.id.bt_delete);
        btnAdd = findViewById(R.id.bt_add);
        //prodId.setEnabled(false);
        data();
    }

    private void data() {
        db = new DatabaseHelper(this);
        db.insertProductData(1, "Battery", "Electronics", 200, 22, 33);
        db.insertProductData(2, "HeadPhone", "Electronics ", 150, 2, 54);
        db.insertProductData(3, "Stick", "Wood", 25, 12, 11);
        db.insertProductData(4, "Gold chain", "Jewellery ", 1000, 34, 98);
        db.insertProductData(5, "Silver ring", "Jewellery ", 100, 45, 66);
        db.insertProductData(6, "Mobile", "electronic ", 130, 26, 15);
        db.insertProductData(7, "Remote", "electronic", 20, 34, 23);

        Cursor res = db.getdata();


        while (res.moveToNext()) {
            Products mProduct = new Products(res.getInt(0), res.getString(1), res.getString(2), res.getInt(3), res.getInt(4), res.getInt(5));
            prodList.add(mProduct);
        }
        productViewing = 0;
        prodId.setText(String.valueOf(prodList.get(productViewing).getProdId()));
        prodNam.setText(String.valueOf(prodList.get(productViewing).getProdName()));
        prodDesc.setText(String.valueOf(prodList.get(productViewing).getProdDesc()));
        prodPrice.setText(String.valueOf(prodList.get(productViewing).getProdPrice()));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prodList.size() > productViewing + 1) {
                    productViewing += 1;
                    prodId.setText(String.valueOf(prodList.get(productViewing).getProdId()));
                    prodNam.setText(String.valueOf(prodList.get(productViewing).getProdName()));
                    prodDesc.setText(String.valueOf(prodList.get(productViewing).getProdDesc()));
                    prodPrice.setText(String.valueOf(prodList.get(productViewing).getProdPrice()));

                } else {
                    Toast.makeText(MainActivity.this, "No more products", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (0 < productViewing) {
                    productViewing -= 1;
                    prodId.setText(String.valueOf(prodList.get(productViewing).getProdId()));
                    prodNam.setText(String.valueOf(prodList.get(productViewing).getProdName()));
                    prodDesc.setText(String.valueOf(prodList.get(productViewing).getProdDesc()));
                    prodPrice.setText(String.valueOf(prodList.get(productViewing).getProdPrice()));

                } else {
                    Toast.makeText(MainActivity.this, "No Previous Product", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a =  Integer.parseInt(btnUpdate.getTag().toString());
                Log.d("myTag", String.valueOf(a) );
                if(a == 0) {
                    btnUpdate.setText("UPDATE PRODUCT");
                    btnDelete.setVisibility(View.GONE);
                    btnNext.setVisibility(View.GONE);
                    btnPrevious.setVisibility(View.GONE);
                    btnAdd.setVisibility(View.GONE);
                    prodNam.setEnabled(true);
                    prodDesc.setEnabled(true);
                    prodPrice.setEnabled(true);
                    Toast.makeText(MainActivity.this, "Update Product details", Toast.LENGTH_SHORT).show();
                    btnUpdate.setTag(1);
                }
                else{
                    String idTXT = prodId.getText().toString();
                    String nameTXT = prodNam.getText().toString();
                    String descTXT = prodDesc.getText().toString();
                    String priceTXT = prodPrice.getText().toString();
                    boolean result = db.updatedata(idTXT, nameTXT, descTXT, priceTXT);

                    if(result){
                        Log.d("Database","Updated");
                        Toast.makeText(MainActivity.this, "Product Details are Updated", Toast.LENGTH_SHORT).show();
                        btnUpdate.setTag(0);
                        btnUpdate.setText("Edit Product Details");
                        prodNam.setEnabled(false);
                        prodDesc.setEnabled(false);
                        prodPrice.setEnabled(false);
                        btnDelete.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.VISIBLE);
                        btnPrevious.setVisibility(View.VISIBLE);
                        btnAdd.setVisibility(View.VISIBLE);


                    }
                }
            }


        });



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = prodId.getText().toString();
                Boolean checkdeletedata = db.deletedata(idTXT);

                if(checkdeletedata){
                    int i = 0;
                    for(Products p: prodList){
                        if(String.valueOf(p.getProdId()).equals(idTXT)){
                            prodNam.setEnabled(false);
                            prodDesc.setEnabled(false);
                            prodPrice.setEnabled(false);
                            btnUpdate.setText("Edit The Product details");
                            if(prodList.size()>productViewing+1){
                                productViewing += 1;
                                prodId.setText(String.valueOf(prodList.get(productViewing).getProdId()));
                                prodNam.setText(String.valueOf(prodList.get(productViewing).getProdName()));
                                prodDesc.setText(String.valueOf(prodList.get(productViewing).getProdDesc()));
                                prodPrice.setText(String.valueOf(prodList.get(productViewing).getProdPrice()));
                            }
                            else{
                                prodNam.setText("");
                                prodDesc.setText("");
                                prodPrice.setText("");
                                prodId.setText("");
                            }
                            prodList.remove(i);

                            break;

                        }
                        i++;
                    }
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }}
