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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nikedemoapp.MyApplication
import com.example.nikedemoapp.databinding.FragmentAlbumDetailsBinding
import javax.inject.Inject

class AlbumDetailFragment  : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AlbumDetailViewModel> { viewModelFactory }

    private lateinit var binding: FragmentAlbumDetailsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MyApplication).appComponent.addAlbumDetailComponent()
            .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        binding = FragmentAlbumDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.detailViewModel = viewModel
        val actionBar = (activity as AppCompatActivity).supportActionBar

        viewModel.artistName.observe(viewLifecycleOwner, Observer { name ->
            actionBar?.title = name
        })

        viewModel.artistName.observe(viewLifecycleOwner, Observer { url ->
            binding.itunesLink.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        })

        return binding.root
    }
}