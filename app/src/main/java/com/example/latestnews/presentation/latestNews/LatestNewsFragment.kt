package com.example.latestnews.presentation.latestNews

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.latestnews.R
import com.example.latestnews.databinding.LatestNewsFragmentBinding
import com.example.latestnews.domain.news.ItemNews
import com.example.latestnews.util.NewsResult
import com.example.latestnews.util.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LatestNewsFragment : Fragment(R.layout.latest_news_fragment) {

    private lateinit var binding: LatestNewsFragmentBinding

    private val viewModel: LatestNewsViewModel by viewModels()

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LatestNewsFragmentBinding.bind(view)

        setupNewsList()

        setupSwipeContainer()
        viewModel.newsResultLiveData.observe(viewLifecycleOwner, ::handleNewsList)
    }

    private fun setupSwipeContainer() {
        binding.swipeContainer.setColorSchemeResources(R.color.red)
        binding.swipeContainer.setOnRefreshListener {
            viewModel.getNewsList()
        }
    }

    private fun handleNewsList(newsResult: NewsResult) {
        when (newsResult) {
            is NewsResult.SuccessResult -> {
                renderNewsList(newsResult.result)
                binding.swipeContainer.isRefreshing = false
            }
            is NewsResult.ErrorResult -> {
                binding.swipeContainer.isRefreshing = false
                toast { newsResult.e.message.toString() }
            }
            is NewsResult.EmptyResult -> toast { "empty" }

            is NewsResult.Loading -> binding.swipeContainer.isRefreshing = true
        }
    }

    private fun renderNewsList(news: List<ItemNews>?) {
        newsAdapter.collection = news.orEmpty()
    }

    private fun setupNewsList() {
        val spanCount = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 2
            else -> 1
        }
        binding.newsList.apply {
            layoutManager = GridLayoutManager(activity, spanCount)
            adapter = newsAdapter
        }
        newsAdapter.clickListener = { news ->
            val bundle = Bundle()
            bundle.apply {
                putString(News_TITLE, news.title)
                putString(News_ID, news.newsId)
            }

            findNavController().navigate(
                R.id.action_latestNewsFragment_to_newsDetailFragment,
                bundle
            )
        }
    }

    companion object {
        const val News_ID = "news_id"
        const val News_TITLE = "news_title"
    }
}