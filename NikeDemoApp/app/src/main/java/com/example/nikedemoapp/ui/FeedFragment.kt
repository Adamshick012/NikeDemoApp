package com.example.nikedemoapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nikedemoapp.R
import com.example.nikedemoapp.utils.GlobalConstants
import kotlinx.android.synthetic.main.fragement_feed.*

class FeedFragment  : Fragment(), MainAdapter.OnImageListener {

    private lateinit var feedViewModel: FeedViewModel
    private lateinit var viewManager: GridLayoutManager
    private lateinit var viewAdapter: MainAdapter
    var rssFeed = "top-albums"


    override fun onImageClick(position: Int) {
        val intent = Intent(activity , AlbumsDetailActivity::class.java)
        val albumDetails = feedViewModel.topAlbumsList.value?.get(position)
        val imageUrl = albumDetails?.artworkUrl100
        val listOfGenres = albumDetails?.genres?.joinToString(separator = ", ") { it.name }
        val artistName = albumDetails?.artistName
        val albumName = albumDetails?.name
        val releaseDate = albumDetails?.releaseDate
        val copyright = albumDetails?.copyright
        val linkToiTunes = albumDetails?.url

        intent.putExtra(GlobalConstants.IMAGE_URL_EXTRA, imageUrl)
        intent.putExtra(GlobalConstants.GENRES_EXTRA, listOfGenres)
        intent.putExtra(GlobalConstants.ARTIST_NAME_EXTRA, artistName)
        intent.putExtra(GlobalConstants.ALBUM_NAME_EXTRA, albumName)
        intent.putExtra(GlobalConstants.RELEASE_DATE_EXTRA, releaseDate)
        intent.putExtra(GlobalConstants.COPYRIGHT_EXTRA, copyright)
        intent.putExtra(GlobalConstants.ITUNES_LINK_EXTRA, linkToiTunes)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  = inflater.inflate(R.layout.fragement_feed, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshFeed()
    }

    fun refreshFeed()
    {
        feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedViewModel.getMusicList(rssFeed)
        feedViewModel.topAlbumsList.observe(viewLifecycleOwner, Observer { albumList ->
            viewAdapter.submitList(albumList)
        })
        viewManager = GridLayoutManager(this.context,2)
        viewAdapter = MainAdapter(this)
        recyclerViewTopAlbums.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}