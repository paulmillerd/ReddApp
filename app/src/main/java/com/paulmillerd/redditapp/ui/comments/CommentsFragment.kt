package com.paulmillerd.redditapp.ui.comments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.repository.CommentRepository
import kotlinx.android.synthetic.main.fragment_comments.*
import javax.inject.Inject

class CommentsFragment : Fragment() {

    companion object {
        const val POST_DATA = "POST_DATA"
    }

    @Inject
    lateinit var commentRepository: CommentRepository

    private lateinit var viewModel: CommentsViewModel
    private val adapter = CommentsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_comments, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        context?.let { ctx ->
            RedditApp.getAppComponent(ctx).inject(this)
            viewModel = ViewModelProviders.of(this).get(CommentsViewModel::class.java)
            viewModel.init(commentRepository)
            viewModel.setPostData(arguments?.getSerializable(POST_DATA) as Thing)
            viewModel.post.observe(this, Observer { post ->
                post_title.text = post?.data?.title
            })
            viewModel.getComments.observe(this, Observer {
                it?.get(1)?.data?.children?.let { comments ->
                    adapter.commentList = comments
                    adapter.notifyDataSetChanged()
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comments_list.layoutManager = LinearLayoutManager(context)
        comments_list.adapter = adapter
    }

}