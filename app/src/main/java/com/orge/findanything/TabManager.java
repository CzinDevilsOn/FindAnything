package com.orge.findanything;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class TabManager {

    private final Context context;
    private WebView currentTab;
    private final SwipeRefreshLayout swipeRefreshLayout;
    private final List<WebView> tabList;

    public TabManager(Context context, WebView initialTab, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.tabList = new ArrayList<>();
        addTab(initialTab);
        switchToTab(initialTab);
    }

    public void addTab(WebView tab) {
        tabList.add(tab);
    }

    public void removeTab(WebView tab) {
        tabList.remove(tab);
    }

    public WebView getCurrentTab() {
        return currentTab;
    }

    public void switchToTab(WebView tab) {
        if (tabList.contains(tab)) {
            for (WebView webView : tabList) {
                webView.setVisibility(View.GONE);
            }
            tab.setVisibility(View.VISIBLE);
            currentTab = tab;
        }
    }

    public boolean goBack() {
        if (currentTab != null && currentTab.canGoBack()) {
            currentTab.goBack();
            return true;
        }
        return false;
    }

    public boolean goForward() {
        if (currentTab != null && currentTab.canGoForward()) {
            currentTab.goForward();
            return true;
        }
        return false;
    }

    public void refresh() {
        if (currentTab != null) {
            swipeRefreshLayout.setRefreshing(true);
            currentTab.reload();
        }
    }

    public void loadUrl(String url) {
        if (currentTab != null) {
            swipeRefreshLayout.setRefreshing(true);
            currentTab.loadUrl(url);
        }
    }

    public void openNewTab(String url) {
        WebView newTab = createNewTab();
        loadUrlInTab(newTab, url);
        addTab(newTab);
        switchToTab(newTab);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private WebView createNewTab() {
        WebView newWebView = new WebView(context);
        WebSettings newWebSettings = newWebView.getSettings();
        newWebSettings.setJavaScriptEnabled(true);
        newWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return newWebView;
    }

    private void loadUrlInTab(WebView tab, String url) {
        swipeRefreshLayout.setRefreshing(true);
        tab.loadUrl(url);
    }

    // Example usage of methods from TabManager
    public void exampleMethodUsage() {
        // Example usage of getCurrentTab()
        WebView currentTab = getCurrentTab();

        // Example usage of goBack()
        boolean wentBack = goBack();
        if (wentBack) {
            Log.d("TabManager", "Went back successfully");
        }

        // Example usage of goForward()
        boolean wentForward = goForward();
        if (wentForward) {
            Log.d("TabManager", "Went forward successfully");
        }

        // Example usage of refresh()
        refresh();

        // Example usage of loadUrl(String)
        String exampleUrl = "https://www.google.com";
        loadUrl(exampleUrl);

        // Example usage of removeTab(WebView)
        if (currentTab != null) {
            removeTab(currentTab);
        }
    }
}
