package com.androidprojects.esprit.expandablelistviewsample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataTitles;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView) findViewById(R.id.expandableLvw);

        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataTitles, listDataChild);

        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Toast.makeText(getApplicationContext(), "Group Clicked " + listDataTitles.get(groupPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), listDataTitles.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), listDataTitles.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT)
                        .show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), listDataTitles.get(groupPosition) + " : "
                        + listDataChild.get(listDataTitles.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }


    private void prepareListData() {
        listDataTitles = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataTitles.add("Account settings");
        listDataTitles.add("Profile Settings");
        listDataTitles.add("Apps");

        List<String> accountSettings = new ArrayList<String>();
        accountSettings.add("Enable notifications");
        accountSettings.add("Change Language");
        accountSettings.add("Ads");
        accountSettings.add("Delete account");

        List<String> profileSettings = new ArrayList<String>();
        profileSettings.add("Show my contact info");
        profileSettings.add("Send me private notes");
        profileSettings.add("Share my personal info");


        List<String> apps = new ArrayList<String>();
        apps.add("Facebook");
        apps.add("Pinterest");
        apps.add("Twitter");
        apps.add("LinkedIn");

        listDataChild.put(listDataTitles.get(0), accountSettings);
        listDataChild.put(listDataTitles.get(1), profileSettings);
        listDataChild.put(listDataTitles.get(2), apps);
    }
}
