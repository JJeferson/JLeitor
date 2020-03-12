package com.j_leitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;









public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView textView;
    TextView resultado_ID;
    EditText Copia;

    final Activity activity= this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Chave do APK JLeitor
        //https://github.com/wgcarvalho/scan-qrcode-android-zxing/tree/master/app
        //https://www.youtube.com/watch?v=125WPZHxU7E
        //-----------------------------------
        /*
        bota na memória um string
        https://stackoverflow.com/questions/3591945/copying-to-the-clipboard-in-java/3592022
        * */
        //-----------------------------------


        textView = (TextView) findViewById(R.id.textView);
        resultado_ID = (TextView) findViewById(R.id.resultado_ID);
        btn = (Button)findViewById(R.id.btn);
        Copia = (EditText) findViewById(R.id.Copia);

        Copia.setText("");

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                    intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    intentIntegrator.setPrompt("SCAN");
                    intentIntegrator.setCameraId(0);
                    intentIntegrator.initiateScan();
                }
            });




    }//fim do oncreate




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(intentResult != null){
            if (intentResult.getContents() !=  null){
                alert(intentResult.getContents().toString());

            }else{
                alert("Scan cancelado");
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        resultado_ID.setText("Resultado: "+msg);
        Copia.setText(msg);



    }



}//fim da classe java
