package com.example.segurados.util;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.segurados.model.UsuarioHasPergunta;
import com.example.segurados.model.UsuarioViewModel;
import com.example.segurados.service.UsuarioHasPerguntaService;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Util {
    
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

        public AddResposta(UsuarioHasPergunta hps){
            this.hasPergunta = hps;
        }
        @Override
        public void run() {

            UsuarioHasPerguntaService hP = UsuarioHasPerguntaService.retrofit.create(UsuarioHasPerguntaService.class);
            final Call<Response> call = hP.createUsuarioHasPergunta(hasPergunta);

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, Response<Response> response) {

                    int code = response.code();

                    if (code == 200) {
                        // todo sucess

                    } else if (code == 400 || code == 401 || code == 403) {
                        //todo fail
                    }

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    // todo fld
                }
            });
        }


    }
}
