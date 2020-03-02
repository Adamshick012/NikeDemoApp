package com.example.nikedemoapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.nikedemoapp.R
import com.example.nikedemoapp.databinding.FragmentAlbumDetailsBinding
import com.example.nikedemoapp.utils.GlobalConstants

class AlbumsDetailFragment  : Fragment() {
    private lateinit var binding: FragmentAlbumDetailsBinding

            override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        val args = AlbumsDetailFragmentArgs.fromBundle(arguments!!)
        binding.mainText.text = getString(
            R.string.ablum_info, args.artistName, args.albumName, args.listOfGenres, args.releaseDate, args.copyright
        )
        binding.itunesLink.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(args.linkToiTunes)
            startActivity(i)
        }
        Glide.with(this)
            .load(args.imageUrl)
            .placeholder(ColorDrawable(Color.BLACK))
            .into(binding.albumView)

        return binding.root
    }

}