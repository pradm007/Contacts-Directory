package com.example.jayesh.contactdirectory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jayesh.contactdirectory.network.model.UserDataResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/11/16.
 */

public class ContactsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<UserDataResponse> userDataResponseArrayList = new ArrayList<>();

    public ContactsAdapter(Context context, LayoutInflater inflater) {
        super();
        this.context = context;
        this.inflater = inflater;
    }

    @Nullable
    @Override
    public UserDataResponse getItem(int position) {
        return userDataResponseArrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return userDataResponseArrayList.size();
    }

    public void setUserDataResponseArrayList(ArrayList<UserDataResponse> userDataResponseArrayList) {
        this.userDataResponseArrayList = userDataResponseArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        UsersDataViewHolderItem usersDataViewHolderItem;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item, null);

            usersDataViewHolderItem = new UsersDataViewHolderItem();
            usersDataViewHolderItem.nameTxVw = (TextView) convertView.findViewById(R.id.nameTxVw);
            usersDataViewHolderItem.descriptionTxtVw = (TextView) convertView.findViewById(R.id.descriptionTxtVw);
            usersDataViewHolderItem.profileImageView = (ImageView) convertView.findViewById(R.id.profileImageView);

            convertView.setTag(usersDataViewHolderItem);
        } else {
            usersDataViewHolderItem = (UsersDataViewHolderItem) convertView.getTag();
        }

        UserDataResponse userDataResponse = getItem(position);
        populateData(usersDataViewHolderItem, userDataResponse);

        return convertView;
    }

    private void populateData(UsersDataViewHolderItem usersDataViewHolderItem, UserDataResponse userDataResponse) {

        String imageUrl = userDataResponse.getPic48();
        Picasso.with(context).load(imageUrl).placeholder(R.drawable.ic_launcher).into(usersDataViewHolderItem.profileImageView);

        usersDataViewHolderItem.nameTxVw.setText(userDataResponse.getFirstName() + " " + userDataResponse.getLastName());
        usersDataViewHolderItem.descriptionTxtVw.setText(userDataResponse.getTitle());
    }

    static class UsersDataViewHolderItem {
        ImageView profileImageView;
        TextView nameTxVw;
        TextView descriptionTxtVw;
    }
}
