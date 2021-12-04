package com.example.latestnews.presentation.newsDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.latestnews.R
import com.example.latestnews.databinding.NewsDetailFragmentBinding
import com.example.latestnews.util.DetailedNewsResult
import com.example.latestnews.util.loadFromUrl
import com.example.latestnews.util.toast
import com.example.latestnews.util.underline
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri
import androidx.navigation.fragment.findNavController
import com.example.latestnews.presentation.latestNews.LatestNewsFragment


@AndroidEntryPoint
class NewsDetailFragment : Fragment(R.layout.news_detail_fragment) {

    private lateinit var binding: NewsDetailFragmentBinding

    private val viewModel: NewsDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NewsDetailFragmentBinding.bind(view)

        viewModel.detailedNewsResultLiveData.observe(viewLifecycleOwner, ::handleDetailedNews)
    }

    @SuppressLint("SetTextI18n")
    private fun handleDetailedNews(detailedNewsResult: DetailedNewsResult) {
        when (detailedNewsResult) {
            is DetailedNewsResult.ErrorResult -> toast { detailedNewsResult.e.message.toString() }
            is DetailedNewsResult.SuccessResult -> {
                with(binding) {
                    detailTitle.text = detailedNewsResult.result.title
                    detailDescription.text = detailedNewsResult.result.description

                    detailAuthor.text = "Author: ${detailedNewsResult.result.author}"

                    detailTime.text = detailedNewsResult.result.published.substring(0, 16)

                    detailImage.loadFromUrl(detailedNewsResult.result.image)
                    detailImage.setOnClickListener {
                        openImages(arrayOf(detailedNewsResult.result.image))
                    }

                    detailMore.underline()
                    detailMore.setOnClickListener {
                        openUrl(detailedNewsResult.result.url)
                    }

                }
            }
            is DetailedNewsResult.Loading -> binding.detailTitle.text = detailedNewsResult.title
        }
    }

    private fun openImages(images: Array<String>) {
        val bundle = Bundle()
        bundle.apply {
            putStringArray(URL_IMAGES_ARRAY, images)
        }

        findNavController().navigate(R.id.action_newsDetailFragment_to_imageGalleryFragment, bundle)
    }

    private fun openUrl(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        requireActivity().startActivity(i)
    }

    companion object {
        const val URL_IMAGES_ARRAY = "URL_IMAGES_ARRAY"
    }
}