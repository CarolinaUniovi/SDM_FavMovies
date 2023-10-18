package com.example.favmovies.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Conexion {
    private Context mContexto;

    public Conexion(Context mContext) {
        mContexto = mContext;
    }

    public boolean compruebaConexion(){
        boolean conectado= false;
        ConnectivityManager connectivityManager = (ConnectivityManager)
                mContexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        conectado = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return conectado;

    }
}
