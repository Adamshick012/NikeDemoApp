package com.example.nikedemoapp.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nikedemoapp.databinding.FragmentAlbumDetailsBinding

class AlbumsDetailFragment  : Fragment() {
    private lateinit var binding: FragmentAlbumDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        val application = requireNotNull(activity).application
        binding = FragmentAlbumDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val result = AlbumsDetailFragmentArgs.fromBundle(arguments!!).albumInfo
        val viewModelFactory = AlbumDetailViewModelFactory(result, application)
        binding.detailViewModel = ViewModelProvider(
            this, viewModelFactory).get(AlbumDetailViewModel::class.java)
        val actionBar = (activity as AppCompatActivity).supportActionBar

        actionBar?.title = result.artistName

        binding.itunesLink.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(result.url)
            startActivity(i)
        }
        
        return binding.root
    }
}