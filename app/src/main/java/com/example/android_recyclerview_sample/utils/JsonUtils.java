package com.example.android_recyclerview_sample.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.android_recyclerview_sample.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /** Tag for the log message */
    private static final String TAG = JsonUtils.class.getSimpleName();

    /** Strings for the key associated with the JSON */
    private static final String KEY_NAME = "name";
    private static final String KEY_MAIN_NAME = "mainName";
    private static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String KEY_DESCRIPTION ="description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_INGREDIENTS = "ingredients";

    /**
     * Return a {@link Sandwich} Object that can be used to populate the UI by parsing the JSON
     */
    public static Sandwich parseSandwichJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            JSONObject baseJson = new JSONObject(json);

            JSONObject name = baseJson.getJSONObject(KEY_NAME);

            String mainName = name.getString(KEY_MAIN_NAME);

            JSONArray alsoKnownAsArray = name.getJSONArray(KEY_ALSO_KNOWN_AS);

            List<String> alsoKnownAs = new ArrayList<>();
            if (alsoKnownAsArray.length() != 0) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsArray.get(i).toString());
                }
            } else {
                alsoKnownAs = null;
            }

            String placeOfOrigin = baseJson.getString(KEY_PLACE_OF_ORIGIN);
            if (placeOfOrigin.isEmpty()) {
                placeOfOrigin = null;
            }

            String description = baseJson.getString(KEY_DESCRIPTION);

            String imageUrl = baseJson.getString(KEY_IMAGE);

            JSONArray ingredientsArray = baseJson.getJSONArray(KEY_INGREDIENTS);

            List<String> ingredients = new ArrayList<>();
            if (ingredientsArray.length() != 0) {
                for (int j = 0; j < ingredientsArray.length(); j++) {
                    ingredients.add(ingredientsArray.get(j).toString());
                }
            } else {
                ingredients = null;
            }

            // Return the Sandwich object
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, imageUrl, ingredients);

        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the Sandwich JSON results", e);
            return null;
        }
    }
}
