package com.paulmillerd.redditapp.ui.comments

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.api.responseModels.listing.Listing
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.repository.CommentRepository

class CommentsViewModel: ViewModel() {

    private lateinit var commentRepository: CommentRepository
    private val _post = MutableLiveData<Thing>()
    val post: LiveData<Thing> get() = _post
    val getComments: LiveData<List<Listing>> = Transformations.switchMap(post) {
        commentRepository.getComments(it.data?.id ?: "", SortOrder.BEST)
    }

    fun init(commentRepository: CommentRepository) {
        this.commentRepository = commentRepository
    }

    fun setPostData(post: Thing) {
        _post.postValue(post)
    }

}