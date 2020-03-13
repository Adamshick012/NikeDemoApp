package com.example.nikedemoapp.feed

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nikedemoapp.MyApplication
import com.example.nikedemoapp.R
import com.example.nikedemoapp.databinding.FragmentFeedBinding
import javax.inject.Inject

class FeedFragment  : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val feedViewModel by viewModels<FeedViewModel> { viewModelFactory }
    private lateinit var viewManager: GridLayoutManager
    private lateinit var viewAdapter: MainAdapter
    private lateinit var binding: FragmentFeedBinding
    var rssFeed = "top-albums"
    override fun onAttach(context: Context) {
        (requireActivity().application as MyApplication).appComponent.addFeedComponent()
            .create().inject(this)
        super.onAttach(context)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.activity_main_drawer, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        when (item.itemId) {
                R.id.nav_top_albums -> {
                    actionBar?.title = getString(R.string.top_albums)
                    rssFeed = "top-albums"
                }
                R.id.nav_coming_soon -> {
                    actionBar?.title = getString(R.string.coming_soon)
                    rssFeed = "coming-soon"
                }
                R.id.nav_hot_tracks -> {
                    actionBar?.title = getString(R.string.hot_tracks)
                    rssFeed = "hot-tracks"
                }
                R.id.nav_new_releases -> {
                    actionBar?.title = getString(R.string.new_releases)
                    rssFeed = "new-releases"
                }
                R.id.nav_top_songs -> {
                    actionBar?.title = getString(R.string.top_songs)
                    rssFeed = "top-songs"
                }
            }
        feedViewModel.getMusicList(rssFeed)
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_feed, container, false)
//        feedViewModel =
//            ViewModelProvider(this).get(FeedViewModel::class.java)
        binding.feedViewModel = feedViewModel
        binding.lifecycleOwner = this
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.top_albums)

        feedViewModel.navigateToAlbumDetail.observe(viewLifecycleOwner, Observer {result ->
            result?.let {album ->
                this.findNavController().navigate(
                    FeedFragmentDirections.actionFeedFragmentToAlbumsDetailFragment(album)
                )
                feedViewModel.onAlbumDetailNavigated()
            }
        })
        feedViewModel.getMusicList(rssFeed)
        viewManager = GridLayoutManager(this.context,2)
        viewAdapter =
            MainAdapter(ImageListener { albumId ->
                feedViewModel.onAlbumClicked(albumId)
            })
        binding.albumsList.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        setHasOptionsMenu(true)
        return binding.root
    }

}