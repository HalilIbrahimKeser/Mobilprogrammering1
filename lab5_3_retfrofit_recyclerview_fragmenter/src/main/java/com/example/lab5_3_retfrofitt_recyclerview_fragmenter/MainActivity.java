package com.example.lab5_3_retfrofitt_recyclerview_fragmenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.fragments.AlbumsFragment;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.fragments.UsersFragment;
import com.example.lab5_3_retfrofitt_recyclerview_fragmenter.models.User;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment fragment = UsersFragment.newInstance(1);
            replaceFragmentWidth(fragment, false);
        }
    }

    public void replaceFragmentWidth(Fragment newFragment, boolean addTobackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (addTobackStack)
            fragmentManager
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .add(R.id.containerFragment, newFragment)
                    .commit();
        else
            fragmentManager
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.containerFragment, newFragment)
                    .commit();
    }
}