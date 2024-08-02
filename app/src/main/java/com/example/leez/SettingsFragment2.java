package com.example.leez;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment2 extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        // Example: Respond to changes in a switch preference
        Preference analyticsSwitch = findPreference("key_analytics");
        if (analyticsSwitch != null) {
            analyticsSwitch.setOnPreferenceChangeListener((preference, newValue) -> {
                // Handle the change in the "Share Data for Analytics" preference
                boolean isEnabled = (boolean) newValue;
                if (isEnabled) {
                    // Enable analytics
                } else {
                    // Disable analytics
                }
                return true; // True to update the state of the preference with the new value
            });
        }

        // Example: Respond to a button click
        Preference clearCacheButton = findPreference("key_clear_cache");
        if (clearCacheButton != null) {
            clearCacheButton.setOnPreferenceClickListener(preference -> {
                // Handle the click on the "Clear Cache" button
                // You might want to show a confirmation dialog or perform the clearing action
                return true; // True to consume the click event
            });
        }

        // Add similar logic for other preferences as needed
    }
}
