package com.paulmillerd.redditapp.ui.vote

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.paulmillerd.redditapp.R
import com.paulmillerd.redditapp.RedditApp
import com.paulmillerd.redditapp.redditApi.responseModels.listing.Thing
import com.paulmillerd.redditapp.serviceManager.VotingManager
import com.paulmillerd.redditapp.toMagnitudeString
import kotlinx.android.synthetic.main.vote_view_layout.view.*
import javax.inject.Inject

class VoteView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    @Inject
    lateinit var votingManager: VotingManager

    private var thing: Thing? = null

    init {
        RedditApp.getAppComponent(context).inject(this)
        LayoutInflater.from(context).inflate(R.layout.vote_view_layout, this)
        upvote.setOnClickListener { onUpvoteClicked() }
        downvote.setOnClickListener { onDownvoteClicked() }
    }

    fun setThing(thing: Thing?) {
        this.thing = thing
        refreshView()
    }

    private fun refreshView() {
        score.text = thing?.tempScore?.toMagnitudeString(context)
        score.setTextColor(ContextCompat.getColor(context,
                when (thing?.tempLikes) {
                    null -> R.color.textColorDefault
                    true -> R.color.colorAccent
                    false -> R.color.colorPrimary
                }
        ))

        upvote.setColorFilter(ContextCompat.getColor(context,
                when (thing?.tempLikes) {
                    null, false -> R.color.textColorDefault
                    true -> R.color.colorAccent
                }))

        downvote.setColorFilter(ContextCompat.getColor(context,
                when (thing?.tempLikes) {
                    null, true -> R.color.textColorDefault
                    false -> R.color.colorPrimary
                }))
    }

    private fun onDownvoteClicked() {
        when (thing?.tempLikes) {
            true, null -> {
                thing?.data?.name?.let { votingManager?.downvote(it) }
                thing?.tempLikes = false
                if (thing?.tempScore != null) thing?.tempScore = thing?.tempScore!! - 1
            }
            false -> {
                thing?.data?.name?.let { votingManager?.resetVote(it) }
                thing?.tempLikes = null
                if (thing?.tempScore != null) thing?.tempScore = thing?.tempScore!! + 1
            }
        }
        refreshView()
    }

    private fun onUpvoteClicked() {
        when (thing?.tempLikes) {
            false, null -> {
                thing?.data?.name?.let { votingManager?.upvote(it) }
                thing?.tempLikes = true
                if (thing?.tempScore != null) thing?.tempScore = thing?.tempScore!! + 1
            }
            true -> {
                thing?.data?.name?.let { votingManager?.resetVote(it) }
                thing?.tempLikes = null
                if (thing?.tempScore != null) thing?.tempScore = thing?.tempScore!! - 1
            }
        }
        refreshView()
    }

}