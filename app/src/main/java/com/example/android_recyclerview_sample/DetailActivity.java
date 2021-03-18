package com.example.android_recyclerview_sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_recyclerview_sample.model.Sandwich;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private TextView alsoKnownTv;
    private TextView originTv;
    private TextView descriptionTv;
    private TextView ingredientsTv;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Sandwich sandwich = (Sandwich) intent.getSerializableExtra("EXTRA");
        alsoKnownTv = findViewById(R.id.also_known_as_tv);
        originTv = findViewById(R.id.origin_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        descriptionTv = findViewById(R.id.description_tv);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle(sandwich.getMainName());
        setUpToolbar();
        populateUI(sandwich);

        setUpToolbar();
    }

    private void populateUI(Sandwich sandwich) {

        // Get alsoKnownAs list of Strings
        List<String> alsoKnownList = sandwich.getAlsoKnownAs();
        if (alsoKnownList != null) {
            // If alsoKnownList is not null, use TextUtils.join method that returns a string containing
            // the tokens joined by delimiters.
            String alsoKnown = TextUtils.join(getString(R.string.new_line), alsoKnownList);
            // Set also known as String to the TextView
            alsoKnownTv.setText(alsoKnown);
        } else {
            // If the alsoKnownList is null, show message so that the user can be aware of
            // the information availability
            alsoKnownTv.setText(getString(R.string.message_not_available));
        }

        // Get place of origin string
        String originString = sandwich.getPlaceOfOrigin();
        // If origin string is not null, set the text to the origin TextView, otherwise hide the TextView
        if (originString != null) {
            originTv.setText(originString);
        } else {
            // If the alsoKnownList is null, show message so that the user can be aware of
            // the information availability
            originTv.setText(getString(R.string.message_not_available));
        }

        // Set the Description String to the description TextView
        descriptionTv.setText(sandwich.getDescription());

        // Get ingredients list of Strings
        List<String> ingredientsList = sandwich.getIngredients();
        if (ingredientsList != null) {
            // If ingredientsList is not null, use TextUtils.join method that returns a string containing
            // the tokens joined by delimiters.
            String ingredients = TextUtils.join(getString(R.string.new_line), ingredientsList);
            // Set ingredients String to the TextView
            ingredientsTv.setText(ingredients);
        }

        imageView = findViewById(R.id.app_bar_image);
        Glide.with(this).load(sandwich.getImage()).into(imageView);
    }

    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * When the arrow icon in the app bar is clicked, finishes DetailActivity.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
