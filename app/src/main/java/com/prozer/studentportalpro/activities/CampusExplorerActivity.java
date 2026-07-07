package com.prozer.studentportalpro.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.prozer.studentportalpro.R;
import com.prozer.studentportalpro.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CampusExplorerActivity extends AppCompatActivity {

    Button btnCamera, btnLocation, btnHistory;

    ImageView imageView;

    TextView txtBuilding;
    TextView txtLatitude;
    TextView txtLongitude;
    TextView txtDateTime;

    FusedLocationProviderClient fusedLocationClient;
    DatabaseHelper databaseHelper;

    String latitude = "";
    String longitude = "";

    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {

                        if(result.getResultCode()==RESULT_OK){

                            Bundle extras =
                                    result.getData().getExtras();

                            Bitmap bitmap =
                                    (Bitmap) extras.get("data");

                            imageView.setImageBitmap(bitmap);

                            txtBuilding.setText(
                                    "Building : Captured Successfully"
                            );

                            String date =
                                    new SimpleDateFormat(
                                            "dd/MM/yyyy HH:mm:ss",
                                            Locale.getDefault())
                                            .format(new Date());

                            txtDateTime.setText(
                                    "Captured : " + date
                            );

                            databaseHelper.saveCampusLocation(
                                    txtBuilding.getText().toString(),
                                    latitude,
                                    longitude,
                                    date
                            );

                            Toast.makeText(
                                    CampusExplorerActivity.this,
                                    "Location Saved Successfully",
                                    Toast.LENGTH_LONG
                            ).show();
                        }

                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_campus_explorer);

        btnCamera=findViewById(R.id.btnCamera);

        btnLocation=findViewById(R.id.btnLocation);

        btnHistory=findViewById(R.id.btnHistory);

        imageView=findViewById(R.id.imageView);

        txtBuilding=findViewById(R.id.txtBuilding);

        txtLatitude=findViewById(R.id.txtLatitude);

        txtLongitude=findViewById(R.id.txtLongitude);

        txtDateTime=findViewById(R.id.txtDateTime);

        fusedLocationClient=
                LocationServices.getFusedLocationProviderClient(this);

        databaseHelper = new DatabaseHelper(this);

        btnCamera.setOnClickListener(v -> openCamera());

        btnLocation.setOnClickListener(v -> getLocation());

        btnHistory.setOnClickListener(v ->

                startActivity(

                        new Intent(

                                CampusExplorerActivity.this,

                                CampusHistoryActivity.class

                        )

                )

        );

    }

    private void openCamera(){

        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    100);

            return;
        }

        Intent intent =
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        cameraLauncher.launch(intent);

    }

    private void getLocation(){

        if(ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    200);

            return;
        }

        fusedLocationClient
                .getLastLocation()
                .addOnSuccessListener(location -> {

                    if(location!=null){

                        showLocation(location);

                    }

                });

    }

    private void showLocation(Location location){

        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());

        txtBuilding.setText("Current Location");

        txtLatitude.setText(
                "Latitude : " + latitude);

        txtLongitude.setText(
                "Longitude : " + longitude);

        Toast.makeText(
                this,
                "Location Retrieved Successfully",
                Toast.LENGTH_SHORT
        ).show();

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults);

        if(requestCode==100){

            if(grantResults.length>0 &&
                    grantResults[0]==PackageManager.PERMISSION_GRANTED){

                openCamera();

            }

        }

        if(requestCode==200){

            if(grantResults.length>0 &&
                    grantResults[0]==PackageManager.PERMISSION_GRANTED){

                getLocation();

            }

        }

    }

}