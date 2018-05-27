package com.example.infante.crud_bbdd;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NuevaSangria extends Activity {

    int billetes_1000;
    int billetes_500;
    int billetes_200;
    int billetes_100;
    int billetes_50;

    EditText edt_1000, edt_500, edt_200, edt_100, edt_50;
    Button boton_calcular, boton_guardar;
    TextView tv_subtotal_1000, tv_subtotal_500, tv_subtotal_200, tv_subtotal_100, tv_subtotal_50, tv_monto_total;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_sangria);

        boton_calcular = findViewById(R.id.btn_calcular);
        boton_guardar = findViewById(R.id.btn_guardar);

        edt_1000 = findViewById(R.id.et_cant_1000);
        edt_500 = findViewById(R.id.et_cant_500);
        edt_200 = findViewById(R.id.et_cant_200);
        edt_100 = findViewById(R.id.et_cant_100);
        edt_50 = findViewById(R.id.et_cant_50);

        tv_subtotal_1000 = findViewById(R.id.tv_subtotal_1000);
        tv_subtotal_500 = findViewById(R.id.tv_subtotal_500);
        tv_subtotal_200 = findViewById(R.id.tv_subtotal_200);
        tv_subtotal_100 = findViewById(R.id.tv_subtotal_100);
        tv_subtotal_50 = findViewById(R.id.tv_subtotal_50);
        tv_monto_total = findViewById(R.id.tv_total_monto);


        boton_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total=0;
                billetes_1000 = Integer.parseInt(edt_1000.getText().toString());
                total=total+multiplica(billetes_1000,1000);
                billetes_500 = Integer.parseInt(edt_500.getText().toString());
                total = total+multiplica(billetes_500,500);
                billetes_200 = Integer.parseInt(edt_200.getText().toString());
                total = total+multiplica(billetes_200,200);
                billetes_100 = Integer.parseInt(edt_100.getText().toString());
                total = total + multiplica(billetes_100,100);
                billetes_50 = Integer.parseInt(edt_50.getText().toString());
                total = total + multiplica(billetes_50,50);
                tv_monto_total.setText(total);

            }

            private int multiplica(int numA, int numB) {
                int a=numA, b= numB, res;
                res=a*b;
                return res;
            }

            public void resultado(){



            }
        });//fin boton calcular



    }




}