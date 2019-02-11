package com.example.alumne.comunicaciones;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ReceptorXarxa extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Obtenim un gestor de les connexions de xarxa
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

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
            Toast.makeText(context, "Xarxa ok, conectat3G", Toast.LENGTH_LONG).show();
        } else if (networkInfo != null && connectatWifi){
            //Xarxa OK
            Toast.makeText(context, "Xarxa ok, conectat Wifi", Toast.LENGTH_LONG).show();
        } else {
            //Xarxa no disponible
            Toast.makeText(context, "Xarxa no disponible", Toast.LENGTH_LONG).show();
        }
    }
}
