package com.orge.findanything;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TabManager tabManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        setupWebView();

        // Load a default page
        webView.loadUrl("https://www.google.com");

        // Initialize TabManager
        tabManager = new TabManager(this, webView, swipeRefreshLayout);

        // Call example method from TabManager
        tabManager.exampleMethodUsage();

        // Set up onBackPressedCallback
        setOnBackPressedCallback();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Handle links in WebView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Hide the SwipeRefreshLayout after the page is loaded
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Set up swipe-to-refresh functionality
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Trigger the refresh action
            webView.reload();
        });
    }

    public void goBack(View view) {
        tabManager.goBack();
    }

    public void goForward(View view) {
        tabManager.goForward();
    }

    public void openSettings(View view) {
        // Implement settings functionality using PopupMenu
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_bookmarks) {
                // Replace toast with logic to open bookmarks activity
                showToast("Bookmarks clicked");
                return true;
            } else if (id == R.id.menu_history) {
                // Replace toast with logic to open history activity
                showToast("History clicked");
                return true;
            } else if (id == R.id.menu_exit) {
                // Implement exit functionality
                finish();
                return true;
            }
            return false;
        });
        popupMenu.show();
    }

    public void openNewTab(View view) {
        tabManager.openNewTab("https://www.google.com");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setOnBackPressedCallback() {
        // Create an onBackPressedCallback
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!tabManager.goBack()) {
                    // If no tab to go back, execute default onBackPressed behavior
                    MainActivity.super.onBackPressed();
                }
            }
        };

        // Add the onBackPressedCallback to the activity's OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_bookmarks) {
            // Replace toast with logic to open bookmarks activity
            showToast("Bookmarks clicked");
            return true;
        } else if (id == R.id.menu_history) {
            // Replace toast with logic to open history activity
            showToast("History clicked");
            return true;
        } else if (id == R.id.menu_exit) {
            // Implement exit functionality
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
