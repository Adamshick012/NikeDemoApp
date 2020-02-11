package com.example.nikedemoapp.repo

import com.example.nikedemoapp.data.RSSFeedService
import com.example.nikedemoapp.data.RSSJsonApi

class FeedSource  {
    private var rssFeedApi : RSSJsonApi = RSSFeedService.createService(RSSJsonApi::class.java)

    suspend fun getMusicList(feedType: String) = rssFeedApi.getFeed(feedType)
}