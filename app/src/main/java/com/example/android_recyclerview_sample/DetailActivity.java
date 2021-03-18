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

        List<String> alsoKnownList = sandwich.getAlsoKnownAs();
        if (alsoKnownList != null) {

            String alsoKnown = TextUtils.join(getString(R.string.new_line), alsoKnownList);
            alsoKnownTv.setText(alsoKnown);
        } else {
            alsoKnownTv.setText(getString(R.string.message_not_available));
        }

        String originString = sandwich.getPlaceOfOrigin();
        if (originString != null) {
            originTv.setText(originString);
        } else {
            originTv.setText(getString(R.string.message_not_available));
        }

        descriptionTv.setText(sandwich.getDescription());

        List<String> ingredientsList = sandwich.getIngredients();
        if (ingredientsList != null) {
            String ingredients = TextUtils.join(getString(R.string.new_line), ingredientsList);

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


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
