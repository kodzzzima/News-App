package com.example.latestnews.presentation.imageGallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.latestnews.R
import com.example.latestnews.databinding.FragmentImageGalleryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageGalleryFragment : Fragment(R.layout.fragment_image_gallery) {
    private lateinit var binding: FragmentImageGalleryBinding

    private val viewModel: ImageGalleryViewModel by viewModels()

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageGalleryBinding.bind(view)

        viewModel.imagesLiveData.observe(viewLifecycleOwner, ::observeLiveData)
    }

    private fun observeLiveData(images: Array<String>) {
        setUpViewPager(images)
    }

    private fun setUpViewPager(imageUrls: Array<String>) {
        viewPagerAdapter = ViewPagerAdapter(requireContext(), imageUrls)
        binding.viewPager.adapter = viewPagerAdapter
    }

}