package com.example.latestnews.presentation.imageGallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.latestnews.R
import com.example.latestnews.databinding.FragmentImageGalleryBinding
import com.example.latestnews.databinding.NewsDetailFragmentBinding
import com.example.latestnews.presentation.newsDetail.NewsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageGalleryFragment : Fragment(R.layout.fragment_image_gallery) {
    private lateinit var binding: FragmentImageGalleryBinding

    private val viewModel: ImageGalleryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageGalleryBinding.bind(view)

//        viewModel.detailedNewsResultLiveData.observe(viewLifecycleOwner, ::handleDetailedNews)
    }

}