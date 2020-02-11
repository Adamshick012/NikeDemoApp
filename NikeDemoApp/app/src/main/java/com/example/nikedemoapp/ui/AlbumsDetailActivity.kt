package com.example.nikedemoapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.nikedemoapp.R
import com.example.nikedemoapp.utils.GlobalConstants
import kotlinx.android.synthetic.main.acitivty_album_details.*

class AlbumsDetailActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivty_album_details)

        val intent = intent
        val imageUrl =intent.getStringExtra(GlobalConstants.IMAGE_URL_EXTRA)
        val listOfGenres = intent.getStringExtra(GlobalConstants.GENRES_EXTRA)
        val artistName = intent.getStringExtra(GlobalConstants.ARTIST_NAME_EXTRA)
        val albumName = intent.getStringExtra(GlobalConstants.ALBUM_NAME_EXTRA)
        val releaseDate = intent.getStringExtra(GlobalConstants.RELEASE_DATE_EXTRA)
        val copyright = intent.getStringExtra(GlobalConstants.COPYRIGHT_EXTRA)
        val linkToiTunes = intent.getStringExtra(GlobalConstants.ITUNES_LINK_EXTRA)

        main_text.text = getString(
            R.string.ablum_info, artistName, albumName, listOfGenres, releaseDate, copyright
        )
        itunes_link.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(linkToiTunes)
            startActivity(i)
        }
        Glide.with(this)
            .load(imageUrl)
            .transform(CenterCrop())
            .placeholder(ColorDrawable(Color.BLACK))
            .into(album_view)

    }
}