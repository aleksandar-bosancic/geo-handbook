package com.rbhp.geohandbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rbhp.geohandbook.databinding.ActivityMainBinding;
import com.squareup.picasso.Cache;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private static final String CACHE_PREFERENCES_STRING = "cache_size";
    private static final String SELECTED_LANGUAGE = "selected_language";

    private NavController navController;
    private ActivityMainBinding binding;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_cities, R.id.navigation_news, R.id.navigation_attractions, R.id.navigation_info, R.id.navigation_settings)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        setUpPicasso();
        resetTitle();
    }

    private void setUpPicasso() {
        try {
            SharedPreferences preferences = this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
            int cacheSize = preferences.getInt(CACHE_PREFERENCES_STRING, 100);
            Log.println(Log.ASSERT, "aasdffasdf", String.valueOf(cacheSize));
//            OkHttpClient client = OkHttpClient.Builder.
            Picasso picassoSingleton = new Picasso.Builder(this)
                    .memoryCache(new LruCache(cacheSize * 1024 * 1024))
                    .downloader(new OkHttp3Downloader(getCacheDir(), (long) cacheSize * 1024 * 1024))
                    .build();
            Picasso.setSingletonInstance(picassoSingleton);
        } catch (IllegalStateException e) {
            Log.w("Picasso", "onCreate: Picasso instance already exists", e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences preferences = base.getSharedPreferences(base.getPackageName(), Context.MODE_PRIVATE);
        String language = preferences.getString(SELECTED_LANGUAGE, "en");
        super.attachBaseContext(updateBaseContextLocale(base, language));
    }

    public Context updateBaseContextLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        return updateResourcesLocaleLegacy(context, locale);
    }

    @SuppressWarnings("deprecation")
    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    private void resetTitle() {
        try {
            int label = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA).labelRes;
            if (label != 0) {
                setTitle(label);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Title reset", "Couldn't reset title");
        }
    }
}