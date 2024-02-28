package com.orge.findanything;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<String> historyList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Retrieve history data
        historyList = getHistoryData();

        // Display history in a ListView
        ListView listView = findViewById(R.id.listViewHistory);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        listView.setAdapter(adapter);

        // Register ListView for context menu
        registerForContextMenu(listView);
    }

    // Implement a method to retrieve history data
    private ArrayList<String> getHistoryData() {
        // Retrieve your history data (e.g., from a database or shared preferences)
        // Return a list of visited URLs
        ArrayList<String> historyList = new ArrayList<>();
        // Add sample data for demonstration
        historyList.add("https://www.google.com");
        historyList.add("https://www.yahoo.com");
        // Add more URLs as needed
        return historyList;
    }

    // Inflate context menu for long-press actions (e.g., delete)
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.history_menu, menu);
    }

    // Handle context menu item clicks (e.g., delete)
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (info == null) {
            return super.onContextItemSelected(item);
        }

        int position = info.position;

        if (item.getItemId() == R.id.delete) {
            deleteHistoryItem(position);
            return true;
        }

        return super.onContextItemSelected(item);
    }

    // Delete history item at specified position
    private void deleteHistoryItem(int position) {
        if (position >= 0 && position < historyList.size()) {
            historyList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "History item deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
