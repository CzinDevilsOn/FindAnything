package com.orge.findanything;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Retrieve your history data (e.g., from a database or shared preferences)
        ArrayList<String> historyList = getHistoryData();

        // Display history in a ListView
        ListView listView = findViewById(R.id.listViewHistory);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        listView.setAdapter(adapter);
    }

    // Implement a method to retrieve your history data
    private ArrayList<String> getHistoryData() {
        // Retrieve your history data (e.g., from a database or shared preferences)
        // Return a list of visited URLs
        ArrayList<String> historyList = new ArrayList<>();
        // Add sample data for demonstration
        historyList.add("https://www.google.com");
        historyList.add("https://www.example.com");
        // Add more URLs as needed
        return historyList;
    }
}