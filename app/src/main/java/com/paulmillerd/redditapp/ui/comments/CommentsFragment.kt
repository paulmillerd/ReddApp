package com.paulmillerd.redditapp.ui.comments

import android.graphics.PorterDuff
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.api.responseModels.listing.Thing
import com.paulmillerd.redditapp.repository.CommentRepository
import com.paulmillerd.redditapp.toMagnitudeString
import kotlinx.android.synthetic.main.fragment_comments.*
import ru.noties.markwon.Markwon
import javax.inject.Inject

class CommentsFragment : Fragment(), MoreCommentsViewHolder.MoreCommentsVhCallback {

    companion object {
        const val POST_DATA = "POST_DATA"
    }

    @Inject
    lateinit var commentRepository: CommentRepository

    private lateinit var viewModel: CommentsViewModel
    private val adapter = CommentsAdapter(this)

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
                score.text = post?.data?.score?.toMagnitudeString(ctx)
                if (URLUtil.isValidUrl(post?.data?.thumbnail)) {
                    thumbnail.visibility = VISIBLE
                    context?.let { ctx ->
                        Glide.with(ctx)
                                .load(post?.data?.thumbnail)
                                .into(thumbnail)
                    }
                } else {
                    thumbnail.visibility = GONE
                }
                if (!TextUtils.isEmpty(post?.data?.selftext)) {
                    adapter.submitList(listOf(CommentsAdapter.CommentOrSelfText(
                            selfText = Markwon.markdown(ctx, post?.data?.selftext ?: "").toString()
                    )))
                }
            })
            viewModel.comments.observe(this, Observer { comments ->
                progress_bar.visibility = GONE
                comments?.let { commentList ->
                    val newList = mutableListOf<CommentsAdapter.CommentOrSelfText>()

                    val selfText = viewModel.post.value?.data?.selftext
                    if (!TextUtils.isEmpty(selfText)) {
                        newList.add(CommentsAdapter.CommentOrSelfText(
                                selfText = Markwon.markdown(ctx, selfText ?: "").toString()
                        ))
                    }

                    commentList.forEach { comment ->
                        newList.add(CommentsAdapter.CommentOrSelfText(
                                comment = comment
                        ))
                    }

                    adapter.submitList(newList)
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { ctx ->
            progress_bar.indeterminateDrawable.setColorFilter(
                    ContextCompat.getColor(ctx, R.color.colorPrimary),
                    PorterDuff.Mode.SRC_ATOP
            )
        }
        comments_list.layoutManager = LinearLayoutManager(context)
        comments_list.adapter = adapter
    }

    override fun onMoreCommentsTapped(moreCommentsItem: Thing) {
        viewModel.getMoreCommentsFor(moreCommentsItem)
    }

}