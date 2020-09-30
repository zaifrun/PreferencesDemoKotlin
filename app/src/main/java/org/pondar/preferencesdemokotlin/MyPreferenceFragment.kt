package org.pondar.preferencesdemokotlin

import android.content.Context
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager


//extending the standard preference fragment
class MyPreferenceFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs, rootKey)
    }

    companion object {

        //These values are specifed in the prefs.xml file
        //and needs to correspond exactly to those in the prefs.xml file
        //You can check the key values in that file and check that it
        //corresponds to the keys defined here.
        private const val SETTINGS_GENDERKEY = "male"
        private const val SETTINGS_NAMEKEY = "name"
        private const val SETTINGS_NOTIFICATONS = "notifications"


        fun useNotifications(context:Context) : Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(SETTINGS_NOTIFICATONS,true)
        }
        //note these are static methods - meaning they always exists
        //so we do not have to create an instance of this class to
        //get the values.
        fun isMale(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(SETTINGS_GENDERKEY, true)
        }

        fun getName(context: Context): String {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(SETTINGS_NAMEKEY, "")!!
        }
    }

}