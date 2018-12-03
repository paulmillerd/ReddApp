package com.paulmillerd.redditapp.redditApi.responseModels.account

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Subreddit(

	@field:SerializedName("public_description")
	val publicDescription: String? = null,

	@field:SerializedName("key_color")
	val keyColor: String? = null,

	@field:SerializedName("over_18")
	val over18: Boolean? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("user_is_banned")
	val userIsBanned: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("is_default_banner")
	val isDefaultBanner: Boolean? = null,

	@field:SerializedName("user_is_muted")
	val userIsMuted: Boolean? = null,

	@field:SerializedName("is_default_icon")
	val isDefaultIcon: Boolean? = null,

	@field:SerializedName("display_name_prefixed")
	val displayNamePrefixed: String? = null,

	@field:SerializedName("user_is_subscriber")
	val userIsSubscriber: Boolean? = null,

	@field:SerializedName("icon_size")
	val iconSize: List<Int?>? = null,

	@field:SerializedName("free_form_reports")
	val freeFormReports: Boolean? = null,

	@field:SerializedName("show_media")
	val showMedia: Boolean? = null,

	@field:SerializedName("default_set")
	val defaultSet: Boolean? = null,

	@field:SerializedName("user_is_moderator")
	val userIsModerator: Boolean? = null,

	@field:SerializedName("subreddit_type")
	val subredditType: String? = null,

	@field:SerializedName("banner_size")
	val bannerSize: Any? = null,

	@field:SerializedName("subscribers")
	val subscribers: Int? = null,

	@field:SerializedName("header_size")
	val headerSize: Any? = null,

	@field:SerializedName("community_icon")
	val communityIcon: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("primary_color")
	val primaryColor: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("link_flair_position")
	val linkFlairPosition: String? = null,

	@field:SerializedName("user_is_contributor")
	val userIsContributor: Boolean? = null,

	@field:SerializedName("link_flair_enabled")
	val linkFlairEnabled: Boolean? = null,

	@field:SerializedName("header_img")
	val headerImg: Any? = null,

	@field:SerializedName("icon_img")
	val iconImg: String? = null,

	@field:SerializedName("icon_color")
	val iconColor: String? = null,

	@field:SerializedName("banner_img")
	val bannerImg: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)