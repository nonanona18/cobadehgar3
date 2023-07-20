package com.example.cobadehgar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class home_fr extends AppCompatActivity {
    private TextView deskripsiTextView;

    TextView username;
    Button dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        username = findViewById(R.id.useremail);
        dashboard = findViewById(R.id.detailbtn);
        deskripsiTextView = findViewById(R.id.textViewCard);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                username.setText(intent.getStringExtra("Dataemail"));
                Intent maju = new Intent(home_fr.this, dashboard.class);
                startActivity(maju);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference deskripsiRef = database.getReference("deskripsi_panjang");

        String statusInfo = getString(R.string.status_info_bar);
        deskripsiTextView.setText(statusInfo);

        // Ambil data dari Firebase Realtime Database realtime
        deskripsiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Ambil nilai dari dataSnapshot ke TextView
                int nilaiSensor = dataSnapshot.getValue(Integer.class);

                String deskripsi;

                if (nilaiSensor < 50) {
                    deskripsi = "Kualitas Udara di rumah Anda SEHAT. Udara relatif bersih dengan sedikit partikel debu.";
                } else if (nilaiSensor >= 50 && nilaiSensor < 100) {
                    deskripsi = "Kualitas Udara di rumah Anda SEHAT. Kebersihan udara dan partikel debu berada pada level \"sedang\". Terus jaga kesehatan rumah Anda.";
                } else {
                    deskripsi = "Kualitas Udara di rumah Anda TIDAK SEHAT. Segera aktifkan Freshism! Terus jaga kesehatan rumah Anda.";
                }

                deskripsiTextView.setText(deskripsi);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle jika ada error
            }
        });
    }
}
