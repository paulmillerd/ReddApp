package com.paulmillerd.redditapp.ui.comments

import androidx.lifecycle.*
import com.paulmillerd.redditapp.SortOrder
import com.paulmillerd.redditapp.ThingType
import com.paulmillerd.redditapp.redditApi.responseModels.listing.Thing
import com.paulmillerd.redditapp.repository.CommentRepository

class CommentsViewModel: ViewModel() {

    private lateinit var commentRepository: CommentRepository
    private val _post = MutableLiveData<Thing>()
    private val _moreCommentsData = MutableLiveData<MoreCommentsData>()
    val post: LiveData<Thing> get() = _post
    private val commentsResponse = Transformations.switchMap(post) {
        commentRepository.getComments(it.data?.id ?: "", SortOrder.BEST)
    }
    private val moreCommentsResponse = Transformations.switchMap(_moreCommentsData) {
        commentRepository.getMoreComments(it.linkTypePrefix, it.linkId, it.children, it.parentId)
    }
    private val _comments = MediatorLiveData<List<Thing>>()
    val comments: LiveData<List<Thing>> get() = _comments

    init {
        _comments.addSource(commentsResponse) { response ->
            val commentsList = mutableListOf<Thing>()
            response.forEach { listing ->
                listing.data?.children?.let {
                    addCommentsToList(it, commentsList)
                }
            }
            _comments.postValue(commentsList)
        }

        _comments.addSource(moreCommentsResponse) { response ->
            val parentId = response.parentId
            val commentsList = _comments.value?.toMutableList()
            val indexToReplace = commentsList?.indexOfFirst {
                it.data?.parent_id == parentId && it.kind == ThingType.MORE.prefix
            }
            indexToReplace?.let {
                commentsList.removeAt(it)
                commentsList.addAll(it, response.moreComments)
            }
            _comments.postValue(commentsList)
        }
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

    fun getMoreCommentsFor(moreCommentsItem: Thing) {
        val linkTypePrefix = post.value?.kind
        val linkId = post.value?.data?.id
        val children = moreCommentsItem.data?.children
        val parentId = moreCommentsItem.data?.parent_id

        if (linkTypePrefix != null && linkId != null && children != null && parentId != null) {
            _moreCommentsData.postValue(MoreCommentsData(linkTypePrefix, linkId, children, parentId))
        }
    }

    data class MoreCommentsData(
            val linkTypePrefix: String,
            val linkId: String,
            val children: List<String?>,
            val parentId: String
    )

}