package com.example.planetZe_gp5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EcoHubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_hub);

        // Set up click listeners for each clickable link
        setUpLink(R.id.tvUNSustainableGoals, "https://sdgs.un.org/");
        setUpLink(R.id.tvCleanTechnica, "https://cleantechnica.com/");
        setUpLink(R.id.tvEarth911, "https://earth911.com/");
        setUpLink(R.id.tvEnergyEfficientCanada, "https://natural-resources.canada.ca/energy-efficiency/products/appliances-for-residential-use/13630");
        setUpLink(R.id.tvEnergyEfficientGuide, "https://medium.com/@c.ron/energy-efficient-appliances-a-guide-to-eco-friendly-living-c0b5a0edea18");
        setUpLink(R.id.tvFrankAndOak, "https://ca.frankandoak.com/?utm_campaign=redirect");
        setUpLink(R.id.tvGoodForSunday, "https://goodforsunday.com/");
        setUpLink(R.id.tvSmartThermostats, "https://www.pcmag.com/picks/the-best-smart-thermostats");
        setUpLink(R.id.tvSmartLED, "https://www.nytimes.com/wirecutter/reviews/best-smart-led-light-bulbs/");
    }

    private void setUpLink(int textViewId, String url) {
        TextView textView = findViewById(textViewId);
        textView.setOnClickListener(view -> openUrl(url));
    }

    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}