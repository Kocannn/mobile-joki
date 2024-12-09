package com.semesta.icnema_uts;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView movieImage;
    private TextView movieTitle, movieOverview, movieReleaseDate;
    private Button buttonBookTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize views
        movieImage = findViewById(R.id.detail_movie_image);
        movieTitle = findViewById(R.id.detail_movie_title);
        movieOverview = findViewById(R.id.detail_movie_overview);
        movieReleaseDate = findViewById(R.id.detail_movie_release_date);

        // Get data from intent
        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String releaseDate = getIntent().getStringExtra("release_date");
        String imageUrl = getIntent().getStringExtra("poster_path");
        buttonBookTicket = findViewById(R.id.button_book_ticket);

        // Set data to views
        movieTitle.setText(title);
        movieOverview.setText(overview);

        movieReleaseDate.setText("Release Date: " + releaseDate);
        Glide.with(this).load(imageUrl).into(movieImage);

        buttonBookTicket.setOnClickListener(v -> {
            // Show Booking Dialog
            BookingDialogFragment bookingDialog = BookingDialogFragment.newInstance(
                    title, overview, releaseDate, imageUrl);
            bookingDialog.show(getSupportFragmentManager(), "BookingDialog");
        });
    }
}
