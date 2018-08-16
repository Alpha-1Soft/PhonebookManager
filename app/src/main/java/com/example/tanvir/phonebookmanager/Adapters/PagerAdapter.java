package com.example.tanvir.phonebookmanager.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tanvir.phonebookmanager.Fragments.ContactsFragment;
import com.example.tanvir.phonebookmanager.Fragments.FavoriteFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int totalTab;
    public PagerAdapter(FragmentManager fm, int totalTab) {
        super(fm);
        this.totalTab = totalTab;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                ContactsFragment contactsFragment = new ContactsFragment();
                return contactsFragment;
            case 1:
                FavoriteFragment favoriteFragment = new FavoriteFragment();
                return favoriteFragment;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return totalTab;
    }
}
