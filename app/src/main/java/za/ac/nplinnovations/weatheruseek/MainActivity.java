package za.ac.nplinnovations.weatheruseek;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.ac.nplinnovations.weatheruseek.databinding.ActivityMainBinding;
import za.ac.nplinnovations.weatheruseek.pojos.Constants;
import za.ac.nplinnovations.weatheruseek.pojos.LocationData;
import za.ac.nplinnovations.weatheruseek.pojos.api.connection.Connection;
import za.ac.nplinnovations.weatheruseek.pojos.api.connection.Methods;
import za.ac.nplinnovations.weatheruseek.pojos.api.responses.ResponseWeather;

import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private LocationData mLocationData;

    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Connection.PROTOCOL + Connection.URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    Methods methods = retrofit.create(Methods.class);
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(LayoutInflater.from(this).inflate(R.layout.loading_view, null));
        alert = builder.create();
        alert.setCancelable(false);
        alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alert.show();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        Intent intent = getIntent();
        if (intent != null) {
            mLocationData = (LocationData) intent.getSerializableExtra(Constants.PASSED_DATA);
            methods.getWeatherReport(mLocationData.getLatitude(), mLocationData.getLongitude(), Connection.API_KEY)
                    .enqueue(new Callback<List<ResponseWeather>>() {
                @Override
                public void onResponse(Call<List<ResponseWeather>> call, Response<List<ResponseWeather>> response) {
                    Log.d(TAG, "onResponse: isSuccessful " + response.isSuccessful());
                    Log.d(TAG, "onResponse:  " + response.body());
                    Log.d(TAG, "onResponse:  message " + response.message());

                    Bundle b = new Bundle();
                    b.putSerializable(Constants.PASSED_DATA, (Serializable) response.body().get(0));
                    navController.navigate(R.id.FirstFragment, b);
                    alert.dismiss();
                }

                @Override
                public void onFailure(Call<List<ResponseWeather>> call, Throwable t) {
                    alert.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error message");
                    builder.setMessage("Unfortunately we could not load your weather information.");
                    builder.setNeutralButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.super.onBackPressed();
                        }
                    });

                    alert = builder.create();
                    alert.setCancelable(false);
                    alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    alert.show();
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    Log.d(TAG, "onFailure: " + t.getStackTrace().toString());
                    Log.d(TAG, "onFailure: " + call.toString());

                }
            });
        }



        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}