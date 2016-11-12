package com.example.jayesh.contactdirectory;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jayesh.contactdirectory.network.ApiClient;
import com.example.jayesh.contactdirectory.network.ApiInterface;
import com.example.jayesh.contactdirectory.network.model.Contacts;
import com.example.jayesh.contactdirectory.network.model.UserDataResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static String TAG = MainActivity.class.getSimpleName();
    ListView contactsListView;
    TextView listviewPlaceholderTv;
    View placeHolderView;

    ContactsAdapter contactsAdapter;

    ArrayList<String> firstNameStringIndexedArrList = new ArrayList<>();
    HashMap<String, Integer> firstNameStringIndexedList = new HashMap<>();
    ArrayList<String> lastNameStringIndexedArrList = new ArrayList<>();
    HashMap<String, Integer> lastNameStringIndexedList = new HashMap<>();

    ArrayList<UserDataResponse> userDataResponseArrayList = new ArrayList<>();
    ArrayList<UserDataResponse> searchedDataSet = new ArrayList<>();

    String loading = "Loading...\nPlease Wait !! ";
    String noResult = "Nothing found. :(";
    String searching = "Searching...";
    String enterToSearch = "Enter to search.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        placeHolderView = getLayoutInflater().inflate(R.layout.placeholder, null);
//        listviewPlaceholderTv = (TextView) placeHolderView.findViewById(R.id.listviewPlaceholderTv);

        listviewPlaceholderTv = (TextView) findViewById(R.id.listviewPlaceholderTv);
        contactsListView = (ListView) findViewById(R.id.contactsListView);


        if (contactsListView.getAdapter() == null) {
            LayoutInflater layoutInflater = getLayoutInflater();
            contactsAdapter = new ContactsAdapter(getApplicationContext(), layoutInflater);
            contactsListView.setEmptyView(listviewPlaceholderTv);
            contactsListView.setAdapter(contactsAdapter);
        }

        getContacts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(onQueryTextListener);
        searchView.setOnCloseListener(onCloseListener);
//        searchItem.setOnActionExpandListener(onActionExpandListener);

        return true;
    }

    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String query) {
            if (!query.isEmpty()) {
                uponSearch(query);
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (!newText.isEmpty()) {
                uponSearch(newText);
            }
            return false;
        }
    };

    SearchView.OnCloseListener onCloseListener = new SearchView.OnCloseListener() {
        @Override
        public boolean onClose() {
            resetList();
            return false;
        }
    };

    MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            prepareListForSearch();
            return false;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            resetList();
            return false;
        }
    };

    private void getContacts() {

        listviewPlaceholderTv.setText(loading);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Contacts> call = apiInterface.getContactList(2, 1, 45);
        call.enqueue(new Callback<Contacts>() {
            @Override
            public void onResponse(Call<Contacts> call, Response<Contacts> response) {
                Log.d(TAG, "success");
                Log.d(TAG, "respose : " + response);

                userDataResponseArrayList.clear();
                userDataResponseArrayList = response.body().getUser_data();
                populateListView(userDataResponseArrayList);
                indexResponseList(userDataResponseArrayList);
            }

            @Override
            public void onFailure(Call<Contacts> call, Throwable t) {
                Log.d(TAG, "failure" + t.toString());
            }
        });
    }

    private void prepareListForSearch() {
        listviewPlaceholderTv.setText(enterToSearch);
        populateListView(new ArrayList<UserDataResponse>());
    }

    private void resetList() {
        listviewPlaceholderTv.setText(loading);
        populateListView(userDataResponseArrayList);
    }

    private void populateListView(ArrayList<UserDataResponse> userDataResponseArrayList) {
        contactsAdapter.setUserDataResponseArrayList(userDataResponseArrayList);
        contactsAdapter.notifyDataSetChanged();
    }

    private void indexResponseList(ArrayList<UserDataResponse> userDataResponseArrayList) {

        firstNameStringIndexedArrList.clear();
        firstNameStringIndexedList.clear();
        lastNameStringIndexedArrList.clear();
        lastNameStringIndexedList.clear();

        for (int i = 0; i < userDataResponseArrayList.size(); i++) {
            UserDataResponse userDataResponse = userDataResponseArrayList.get(i);
            firstNameStringIndexedArrList.add(userDataResponse.getFirstName());
            firstNameStringIndexedList.put(userDataResponse.getFirstName(), i);
            lastNameStringIndexedArrList.add(userDataResponse.getLastName());
            lastNameStringIndexedList.put(userDataResponse.getLastName(), i);
        }
    }

    private void uponSearch(String queryText) {

        searchedDataSet.clear();
        listviewPlaceholderTv.setText(searching);

        contactsAdapter.setUserDataResponseArrayList(new ArrayList<UserDataResponse>());
        contactsAdapter.notifyDataSetChanged();

        String queryTextCharacters = queryText;
        if (queryText.length() > 1) {
            queryTextCharacters = "";
            for (int j = 0; j < queryText.length(); j++) {
                queryTextCharacters = queryTextCharacters + queryText.charAt(j);
                if (j < (queryText.length() - 1)) {
                    queryTextCharacters += "*";
                }
            }
        }

        String queryTextRegEx = "^[^*]*(" + queryTextCharacters + ")[^*]*$"; //TODO: not perfected

        for (String firstName : firstNameStringIndexedArrList) {
            if (Pattern.matches(queryTextRegEx, firstName)) {
                int position = firstNameStringIndexedList.get(firstName);
                searchedDataSet.add(userDataResponseArrayList.get(position));
            }
        }

        for (String lastName : lastNameStringIndexedArrList) {
            if (lastName.equalsIgnoreCase(queryText)) {
                int position = lastNameStringIndexedList.get(lastName);
                searchedDataSet.add(userDataResponseArrayList.get(position));
            }
        }

        populateListView(searchedDataSet);
    }

}
