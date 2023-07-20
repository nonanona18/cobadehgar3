package com.example.cobadehgar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.cobadehgar.EmailSignupSender;

import javax.mail.MessagingException;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    private void registerUser() {
        // Melakukan proses pendaftaran pengguna

        // konfirmasi email jika pendaftaran berhasil
        String recipientEmail = "recipient@example.com";
        String subject = "Konfirmasi Pendaftaran";
        String body = "Terima kasih telah mendaftar. Silakan klik tautan berikut untuk mengkonfirmasi pendaftaran.";

        try {
            EmailSignupSender.sendEmail(recipientEmail, subject, body);
            Toast.makeText(this, "Email konfirmasi telah dikirim", Toast.LENGTH_SHORT).show();
        } catch (MessagingException e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal mengirim email konfirmasi", Toast.LENGTH_SHORT).show();
        }
    }
}
