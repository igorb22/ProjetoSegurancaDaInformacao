package com.example.segurados.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.segurados.R;
import com.example.segurados.view.ui.jogar.JogarFragment;
import com.example.segurados.view.ui.perfil.PerfilFragment;
import com.example.segurados.view.ui.ranking.RankingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private static final int PERFIL_FRAGMENT = 1;
    private static final int JOGAR_FRAGMENT = 2;
    private static final int RANKING_FRAMENT = 3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_perfil:
                    Toast.makeText(getBaseContext(),"PERFIL",Toast.LENGTH_LONG).show();

                    instanciaFragment(PERFIL_FRAGMENT);


                    return true;
                case R.id.nav_jogar:

                    Toast.makeText(getBaseContext(),"JOGAR",Toast.LENGTH_LONG).show();

                    instanciaFragment(JOGAR_FRAGMENT);

                    return true;

                case R.id.nav_ranking:

                    Toast.makeText(getBaseContext(),"RANKING",Toast.LENGTH_LONG).show();

                    instanciaFragment(RANKING_FRAMENT);

                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanciaFragment(PERFIL_FRAGMENT);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    public void instanciaFragment(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (i){

            case 1:
                PerfilFragment perfilFragment = new PerfilFragment();
                ft.replace(R.id.containerFragment, perfilFragment);
                ft.commit();
                break;

            case 2:
                JogarFragment jogarFragment = new JogarFragment();
                ft.replace(R.id.containerFragment, jogarFragment);
                ft.commit();

                break;

            case 3:
                RankingFragment rankingFragment = new RankingFragment();
                ft.replace(R.id.containerFragment, rankingFragment);
                ft.commit();
                break;
        }
    }
}
