package org.pondar.preferencesdemokotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val RESULT_CODE_PREFERENCES = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //read the values at app startup so we can show this in the UI
        val male = PreferenceHandler.isMale(this)
        val name = PreferenceHandler.getName(this)
        val notifications = PreferenceHandler.useNotifications(this)
        updateUI(name, male,notifications)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //This method updates our text views.
    private fun updateUI(name: String, male: Boolean, notifications:Boolean) {
        myName.text = name
        if (male)
            myGender.text = resources.getString(R.string.male)
        else
            myGender.text = resources.getString(R.string.female)
        if (notifications)
            useNotifications.text = getString(R.string.on)
        else
            useNotifications.text = getString(R.string.off)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULT_CODE_PREFERENCES)
        //the code means we came back from settings
        {
            //I can can these methods like this, because they are static
            val male = PreferenceHandler.isMale(this)
            val name = PreferenceHandler.getName(this)
            val notifications = PreferenceHandler.useNotifications(this)
            val message = "Welcome, $name, You are male? $male"
            val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
            toast.show()
            updateUI(name, male,notifications)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            //Start our settingsactivity and listen to result - i.e.
            //when it is finished.
            val intent = Intent(this, SettingsActivity::class.java)
            startActivityForResult(intent, RESULT_CODE_PREFERENCES)

        }
        return super.onOptionsItemSelected(item)
    }
}