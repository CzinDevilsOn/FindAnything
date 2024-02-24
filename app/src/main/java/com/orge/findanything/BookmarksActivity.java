package com.orge.findanything;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        // Retrieve your bookmarks data (e.g., from a database or shared preferences)
        ArrayList<String> bookmarksList = getBookmarksData();

        // Display bookmarks in a ListView
        ListView listView = findViewById(R.id.listViewBookmarks);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookmarksList);
        listView.setAdapter(adapter);
    }

    // Implement a method to retrieve your bookmarks data
    private ArrayList<String> getBookmarksData() {
        // Retrieve your bookmarks data (e.g., from a database or shared preferences)
        // Return a list of bookmarked URLs
        ArrayList<String> bookmarksList = new ArrayList<>();
        // Add sample data for demonstration
        bookmarksList.add("https://www.github.com");
        bookmarksList.add("https://www.wikipedia.org");
        // Add more URLs as needed
        return bookmarksList;
    }
}