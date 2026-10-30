package com.example.profilemediasosial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String name = "Devon Lane";
    String username = "@devonlane";
    String bio = "Photography | Travel | Lifestyle";
    String contact = "devonlane@gmail.com";
    String country = "Indonesia";

    boolean following = false;

    TextView txtName;
    TextView txtUsername;
    TextView txtBio;
    TextView txtContact;
    TextView txtCountry;
    TextView txtFollowers;

    Button btnFollow;
    Button btnEdit;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showProfile();
    }

    // HALAMAN PROFILE
    private void showProfile() {

        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtUsername = findViewById(R.id.txtUsername);
        txtBio = findViewById(R.id.txtBio);
        txtContact = findViewById(R.id.txtContact);
        txtCountry = findViewById(R.id.txtCountry);
        txtFollowers = findViewById(R.id.txtFollowers);

        btnFollow = findViewById(R.id.btnFollow);
        btnEdit = findViewById(R.id.btnEdit);
        btnShare = findViewById(R.id.btnShare);

        txtName.setText(name);
        txtUsername.setText(username);
        txtBio.setText(bio);
        txtContact.setText(contact);
        txtCountry.setText(country);

        updateFollow();

        // Tombol Follow
        btnFollow.setOnClickListener(v -> {

            following = !following;
            updateFollow();

        });

        // Tombol Edit Profile
        btnEdit.setOnClickListener(v -> {
            openEditProfile();
        });

        // Tombol Share
        btnShare.setOnClickListener(v -> {
            Toast.makeText(
                    v.getContext(),
                    "Share Profile",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Memanggil galeri
        createGallery();
    }

    // HALAMAN EDIT PROFILE
    private void openEditProfile() {

        setContentView(R.layout.activity_edit_profile);

        EditText edtName = findViewById(R.id.edtName);
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtBio = findViewById(R.id.edtBio);
        EditText edtContact = findViewById(R.id.edtContact);

        Spinner spCountry = findViewById(R.id.spCountry);
        Button btnSave = findViewById(R.id.btnSave);

        // Pilihan negara
        String[] countries = {
                "Indonesia",
                "Malaysia",
                "Singapore",
                "Thailand",
                "Japan",
                "United States"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_spinner_item,
                        countries
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spCountry.setAdapter(adapter);

        // Mengisi data sebelumnya
        edtName.setText(name);
        edtUsername.setText(username);
        edtBio.setText(bio);
        edtContact.setText(contact);

        int position = adapter.getPosition(country);
        if (position >= 0) {
            spCountry.setSelection(position);
        }

        // Simpan perubahan
        btnSave.setOnClickListener(v -> {

            name = edtName.getText().toString().trim();
            username = edtUsername.getText().toString().trim();
            bio = edtBio.getText().toString().trim();
            contact = edtContact.getText().toString().trim();
            country = spCountry.getSelectedItem().toString();

            showProfile();

            Toast.makeText(
                    v.getContext(),
                    "Profile berhasil diperbarui",
                    Toast.LENGTH_SHORT
            ).show();

        });
    }

    // MENGATUR FOLLOW
    private void updateFollow() {

        if (following) {

            btnFollow.setText("Following");
            txtFollowers.setText("205K+ Followers");

        } else {

            btnFollow.setText("Follow");
            txtFollowers.setText("205K Followers");

        }
    }

    // MEMBUAT GALERI FOTO
    private void createGallery() {

        GridLayout grid = findViewById(R.id.gridPosts);

        int[] images = {
                R.drawable.post1,
                R.drawable.post2,
                R.drawable.post3
        };

        for (int image : images) {

            ImageView photo = new ImageView(this);

            photo.setImageResource(image);
            photo.setScaleType(ImageView.ScaleType.CENTER_CROP);

            GridLayout.LayoutParams params =
                    new GridLayout.LayoutParams();

            params.width = 0;
            params.height = dp(120);

            params.columnSpec =
                    GridLayout.spec(GridLayout.UNDEFINED, 1f);

            params.setMargins(
                    dp(3), dp(3), dp(3), dp(3)
            );

            photo.setLayoutParams(params);

            grid.addView(photo);
        }
    }

    // KONVERSI DP
    private int dp(int value) {

        return (int) (
                value * getResources()
                        .getDisplayMetrics().density
        );
    }

    // KEMBALI KE HALAMAN PROFILE
    @Override
    public void onBackPressed() {super.onBackPressed();

        showProfile();

    }
}
