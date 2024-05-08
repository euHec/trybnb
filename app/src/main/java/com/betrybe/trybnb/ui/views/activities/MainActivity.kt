package com.betrybe.trybnb.ui.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betrybe.trybnb.R
import com.betrybe.trybnb.ui.views.fragments.FragmentReservation
import com.betrybe.trybnb.ui.views.fragments.FragmentProfile
import com.betrybe.trybnb.ui.views.fragments.FragmentCreateReservation
import com.google.android.material.bottomnavigation.BottomNavigationItemView

@SuppressLint("RestrictedApi")
class MainActivity : AppCompatActivity() {
    private val menuButtonProfile: BottomNavigationItemView by lazy { findViewById(R.id.profile_menu_tem) }
    private val menuButtonReservation: BottomNavigationItemView by lazy { findViewById(R.id.reservation_menu_item) }
    private val menuButtonNewReservation: BottomNavigationItemView by lazy { findViewById(R.id.create_reservation_menu_item) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.main_fragment_container, FragmentReservation())
//            .commit()

        menuButtonProfile.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FragmentProfile())
                .commit()
        }

        menuButtonReservation.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FragmentReservation())
                .commit()
        }

        menuButtonNewReservation.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FragmentCreateReservation())
                .commit()
        }


    }
}
