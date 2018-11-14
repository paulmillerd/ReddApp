package com.paulmillerd.redditapp.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.ThingType
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.repository.CommentRepository

class CommentsViewModel: ViewModel() {

    private lateinit var commentRepository: CommentRepository
    private val _post = MutableLiveData<Thing>()
    val post: LiveData<Thing> get() = _post
    private val commentsResponse = Transformations.switchMap(post) {
        commentRepository.getComments(it.data?.id ?: "", SortOrder.BEST)
    }
    val comments: LiveData<List<Thing>> = Transformations.map(commentsResponse) { response ->
        val comments = mutableListOf<Thing>()
        response.forEach { listing ->
            listing.data?.children?.let {
                addCommentsToList(it, comments)
            }
        }
        comments
    }

    fun init(commentRepository: CommentRepository) {
        this.commentRepository = commentRepository
    }

    fun setPostData(post: Thing) {
        _post.postValue(post)
    }

    private fun addCommentsToList(things: List<Thing>, wipCommentList: MutableList<Thing>) {
        things.forEach { thing ->
            if (thing.kind == ThingType.COMMENT.prefix || thing.kind == ThingType.MORE.prefix) {
                wipCommentList.add(thing)
                thing.data?.repliesListing?.data?.children?.let {
                    addCommentsToList(it, wipCommentList)
                }
            }
        }
    }

}