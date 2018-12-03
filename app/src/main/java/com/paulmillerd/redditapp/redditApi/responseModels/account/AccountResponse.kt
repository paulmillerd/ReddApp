package com.paulmillerd.redditapp.redditApi.responseModels.account

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class AccountResponse(

	@field:SerializedName("has_stripe_subscription")
	val hasStripeSubscription: Boolean? = null,

	@field:SerializedName("over_18")
	val over18: Boolean? = null,

	@field:SerializedName("pref_clickgadget")
	val prefClickgadget: Int? = null,

	@field:SerializedName("comment_karma")
	val commentKarma: Int? = null,

	@field:SerializedName("subreddit")
	val subreddit: Subreddit? = null,

	@field:SerializedName("features")
	val features: Features? = null,

	@field:SerializedName("pref_show_trending")
	val prefShowTrending: Boolean? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("has_visited_new_profile")
	val hasVisitedNewProfile: Boolean? = null,

	@field:SerializedName("created_utc")
	val createdUtc: Int? = null,

	@field:SerializedName("num_friends")
	val numFriends: Int? = null,

	@field:SerializedName("coins")
	val coins: Int? = null,

	@field:SerializedName("in_redesign_beta")
	val inRedesignBeta: Boolean? = null,

	@field:SerializedName("has_mod_mail")
	val hasModMail: Boolean? = null,

	@field:SerializedName("hide_from_robots")
	val hideFromRobots: Boolean? = null,

	@field:SerializedName("has_subscribed_to_premium")
	val hasSubscribedToPremium: Boolean? = null,

	@field:SerializedName("created")
	val created: Int? = null,

	@field:SerializedName("has_subscribed")
	val hasSubscribed: Boolean? = null,

	@field:SerializedName("seen_premium_adblock_modal")
	val seenPremiumAdblockModal: Boolean? = null,

	@field:SerializedName("pref_show_snoovatar")
	val prefShowSnoovatar: Boolean? = null,

	@field:SerializedName("force_password_reset")
	val forcePasswordReset: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("is_gold")
	val isGold: Boolean? = null,

	@field:SerializedName("is_mod")
	val isMod: Boolean? = null,

	@field:SerializedName("in_beta")
	val inBeta: Boolean? = null,

	@field:SerializedName("has_verified_email")
	val hasVerifiedEmail: Boolean? = null,

	@field:SerializedName("is_suspended")
	val isSuspended: Boolean? = null,

	@field:SerializedName("pref_geopopular")
	val prefGeopopular: String? = null,

	@field:SerializedName("new_modmail_exists")
	val newModmailExists: Any? = null,

	@field:SerializedName("is_sponsor")
	val isSponsor: Boolean? = null,

	@field:SerializedName("seen_redesign_modal")
	val seenRedesignModal: Boolean? = null,

	@field:SerializedName("suspension_expiration_utc")
	val suspensionExpirationUtc: Any? = null,

	@field:SerializedName("has_external_account")
	val hasExternalAccount: Boolean? = null,

	@field:SerializedName("has_gold_subscription")
	val hasGoldSubscription: Boolean? = null,

	@field:SerializedName("seen_layout_switch")
	val seenLayoutSwitch: Boolean? = null,

	@field:SerializedName("pref_nightmode")
	val prefNightmode: Boolean? = null,

	@field:SerializedName("link_karma")
	val linkKarma: Int? = null,

	@field:SerializedName("has_mail")
	val hasMail: Boolean? = null,

	@field:SerializedName("oauth_client_id")
	val oauthClientId: String? = null,

	@field:SerializedName("pref_autoplay")
	val prefAutoplay: Boolean? = null,

	@field:SerializedName("gold_creddits")
	val goldCreddits: Int? = null,

	@field:SerializedName("verified")
	val verified: Boolean? = null,

	@field:SerializedName("has_paypal_subscription")
	val hasPaypalSubscription: Boolean? = null,

	@field:SerializedName("pref_top_karma_subreddits")
	val prefTopKarmaSubreddits: Boolean? = null,

	@field:SerializedName("pref_show_twitter")
	val prefShowTwitter: Boolean? = null,

	@field:SerializedName("is_employee")
	val isEmployee: Boolean? = null,

	@field:SerializedName("pref_video_autoplay")
	val prefVideoAutoplay: Boolean? = null,

	@field:SerializedName("pref_no_profanity")
	val prefNoProfanity: Boolean? = null,

	@field:SerializedName("icon_img")
	val iconImg: String? = null,

	@field:SerializedName("inbox_count")
	val inboxCount: Int? = null,

	@field:SerializedName("gold_expiration")
	val goldExpiration: Any? = null,

	@field:SerializedName("seen_subreddit_chat_ftux")
	val seenSubredditChatFtux: Boolean? = null
)