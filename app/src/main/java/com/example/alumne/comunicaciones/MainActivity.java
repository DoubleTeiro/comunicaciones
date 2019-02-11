package com.example.alumne.comunicaciones;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ReceptorXarxa receptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receptor = new ReceptorXarxa();
        this.registerReceiver(receptor, filter);
    }

    public void onStart() {
        super.onStart();
        //Obtenim un gestor de les connexions de xarxa
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //Obtenim l’estat de la xarxa
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //Obtenim l’estat de la xarxa mòbil
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean connectat3G = networkInfo.isConnected();

        //Obtenim l’estat de la xarxa Wifi
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean connectatWifi = networkInfo.isConnected();

        //Si està connectat
        if (networkInfo != null && connectat3G) {
            //Xarxa OK
            Toast.makeText(this, "Xarxa ok, conectat3G", Toast.LENGTH_LONG).show();
        } else if (networkInfo != null && connectatWifi){
            //Xarxa OK
            Toast.makeText(this, "Xarxa ok, conectat Wifi", Toast.LENGTH_LONG).show();
        } else {
            //Xarxa no disponible
            Toast.makeText(this, "Xarxa no disponible", Toast.LENGTH_LONG).show();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        //Donam de baixa el receptor de broadcast quan es destrueix l’aplicació
        if (receptor != null) {
            this.unregisterReceiver(receptor);
        }
    }

    public void onClickAnar(View v) {
        WebView webView = (WebView) findViewById(R.id.webView1);
        EditText editText = (EditText) findViewById(R.id.editText1);
        String dir = editText.getText().toString();
        if(!dir.startsWith("http://") && !dir.startsWith("https://")) {
            dir = "http://" + dir;
            editText.setText(dir);
        }
        webView.loadUrl(dir);
    }
}
