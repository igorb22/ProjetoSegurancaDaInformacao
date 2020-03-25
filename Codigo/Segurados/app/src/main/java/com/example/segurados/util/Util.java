package com.example.segurados.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.example.segurados.model.UsuarioHasPergunta;
import com.example.segurados.model.UsuarioHasPergunta;
import com.example.segurados.model.UsuarioViewModel;
import com.example.segurados.service.UsuarioHasPerguntaService;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Util {
    public static boolean status;
    public static boolean checkInternet(Context ctx){
        ConnectivityManager connectivityManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static void removeUser() {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        RealmResults<UsuarioViewModel> user = realm.where(UsuarioViewModel.class).findAll();

        user.deleteAllFromRealm();

        realm.commitTransaction();


        realm.close();
    }
    public static class AddResposta extends Thread {
        private UsuarioHasPergunta hasPergunta;
        private String token;
        private Context ctx;
        public AddResposta(Context ctx, UsuarioHasPergunta hps, String token){
            this.hasPergunta = hps;
            this.token = token;
            this.ctx = ctx;
        }
        @Override
        public void run() {

            UsuarioHasPerguntaService hP = UsuarioHasPerguntaService.retrofit.create(UsuarioHasPerguntaService.class);
            Call<UsuarioHasPergunta> call = hP.createUsuarioHasPergunta(hasPergunta, "bearer " + token);
            call.enqueue(new Callback<UsuarioHasPergunta>() {
                @Override
                    public void onResponse(Call<UsuarioHasPergunta> call, Response<UsuarioHasPergunta> response) {

                        Toast.makeText(ctx, response.code() + " c", Toast.LENGTH_LONG).show();


                    int code = response.code();
                    if (code == 200) {
                        System.out.println(response.body());
                        Toast.makeText(ctx, response.body() + " ", Toast.LENGTH_LONG).show();

                    } else if (code == 400 || code == 401 || code == 403) {
                        //todo fail
                        Toast.makeText(ctx, response.message() + " ", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<UsuarioHasPergunta> call, Throwable t) {
                    // todo fld
                  //  t.printStackTrace();
                    Toast.makeText(ctx, t.getMessage() , Toast.LENGTH_LONG).show();

                }
            });
        }


    }
}
