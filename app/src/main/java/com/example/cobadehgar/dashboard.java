package com.example.cobadehgar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends AppCompatActivity {

    private DatabaseReference onOffRef;
    private DatabaseReference autoRef;
    private Button btnOn;
    private Button btnOff;
    private Button btnAuto;
    private DatabaseReference debuRef;
    private DatabaseReference udaraRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);

        // Inisialisasi referensi ke Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        debuRef = database.getReference("sensor/debu");
        udaraRef = database.getReference("sensor/udara");
        onOffRef = database.getReference("mode/manual");
        autoRef = database.getReference("mode/otomatis");

        // Dekarasi xml
        ProgressBar progressBar = findViewById(R.id.progressBar2);
        TextView deklarasiDebuTextView = findViewById(R.id.textProgress);
        btnOn = findViewById(R.id.btnon);
        btnOff = findViewById(R.id.btnoff);
        btnAuto = findViewById(R.id.btnuto);

        // Ngebaca data debu dari Firebase
        debuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Ambil nilai debu
                String nilaiDebu = dataSnapshot.getValue(String.class);

                // Konversi nilaiDebu ke tipe data
                int progressValue = Integer.parseInt(nilaiDebu);

                // Atur nilai ProgressBar
                progressBar.setProgress(progressValue);

                // Deklarasi teks dari nilai debu
                String statusdeclaration = progressValue <= 100    ? "SEHAT" : "TIDAK SEHAT";
                deklarasiDebuTextView.setText(statusdeclaration);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Buat pas error
            }
        });

        // Baca data udara dari Firebase
        udaraRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Baca nilai udara
                String nilaiUdara = dataSnapshot.getValue(String.class);

                // Baca nilai udara pada TextView dengan ID pm10
                TextView pm10TextView = findViewById(R.id.pm10);
                pm10TextView.setText(getString(R.string.pminfo, nilaiUdara));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Buat pas kesalahan saat membaca data udara
            }
        });

        //Button ke firebase
        //On
        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save nilai boolean "true" ke database, tombol "ON" diklik
                onOffRef.setValue(true);
                showToast("Value set to ON");
            }
        });

        //Off
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save nilai boolean "false" ke database, tombol "OFF" diklik
                onOffRef.setValue(false);
                showToast("Value set to OFF");
            }
        });

        // Ambil nilai boolean dari database when aplikasi dimulai
        onOffRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Update tampilan tombol pake nilai boolean dari database
                Boolean value = snapshot.getValue(Boolean.class);
                if (value != null && value) {
                    // Nilai true (ON), tombol "ON" klik
                    btnOn.setSelected(true);
                    btnOff.setSelected(false);
                } else {
                    // Nilai false (OFF), tombol "OFF" klik
                    btnOn.setSelected(false);
                    btnOff.setSelected(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
