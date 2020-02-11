package com.example.nikedemoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.nikedemoapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = getString(R.string.top_albums)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.main_content, FeedFragment(), "main_fragment")
        transaction.commit()
        nav_view.setNavigationItemSelectedListener {
            val mainFragment = manager.findFragmentByTag("main_fragment") as FeedFragment
            when (it.itemId) {
                R.id.nav_top_albums -> {
                    toolbar.title = getString(R.string.top_albums)
                    mainFragment.rssFeed = "top-albums"
                }
                R.id.nav_coming_soon -> {
                    toolbar.title = getString(R.string.coming_soon)
                    mainFragment.rssFeed = "coming-soon"
                }
                R.id.nav_hot_tracks -> {
                    toolbar.title = getString(R.string.hot_tracks)
                    mainFragment.rssFeed = "hot-tracks"
                }
                R.id.nav_new_releases -> {
                    toolbar.title = getString(R.string.new_releases)
                    mainFragment.rssFeed = "new-releases"
                }
                R.id.nav_top_songs -> {
                    toolbar.title = getString(R.string.top_songs)
                    mainFragment.rssFeed = "top-songs"
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            mainFragment.refreshFeed()
            true
        }
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
