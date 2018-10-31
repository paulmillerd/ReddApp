package com.paulmillerd.redditapp.api.responseModels.Listing

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Data(

		@field:SerializedName("modhash")
	val modhash: String? = null,

		@field:SerializedName("children")
	val children: List<ChildrenItem?>? = null,

		@field:SerializedName("before")
	val before: Any? = null,

		@field:SerializedName("dist")
	val dist: Int? = null,

		@field:SerializedName("after")
	val after: String? = null,

		@field:SerializedName("secure_media")
	val secureMedia: Any? = null,

		@field:SerializedName("link_flair_richtext")
	val linkFlairRichtext: List<Any?>? = null,

		@field:SerializedName("link_flair_background_color")
	val linkFlairBackgroundColor: String? = null,

		@field:SerializedName("author_flair_richtext")
	val authorFlairRichtext: List<Any?>? = null,

		@field:SerializedName("saved")
	val saved: Boolean? = null,

		@field:SerializedName("over_18")
	val over18: Boolean? = null,

		@field:SerializedName("hide_score")
	val hideScore: Boolean? = null,

		@field:SerializedName("subreddit")
	val subreddit: String? = null,

		@field:SerializedName("subreddit_id")
	val subredditId: String? = null,

		@field:SerializedName("score")
	val score: Int? = null,

		@field:SerializedName("suggested_sort")
	val suggestedSort: Any? = null,

		@field:SerializedName("num_comments")
	val numComments: Int? = null,

		@field:SerializedName("mod_reason_title")
	val modReasonTitle: Any? = null,

		@field:SerializedName("whitelist_status")
	val whitelistStatus: String? = null,

		@field:SerializedName("can_gild")
	val canGild: Boolean? = null,

		@field:SerializedName("is_robot_indexable")
	val isRobotIndexable: Boolean? = null,

		@field:SerializedName("spoiler")
	val spoiler: Boolean? = null,

		@field:SerializedName("id")
	val id: String? = null,

		@field:SerializedName("locked")
	val locked: Boolean? = null,

		@field:SerializedName("created_utc")
	val createdUtc: Int? = null,

		@field:SerializedName("link_flair_template_id")
	val linkFlairTemplateId: Any? = null,

		@field:SerializedName("likes")
	val likes: Any? = null,

		@field:SerializedName("banned_at_utc")
	val bannedAtUtc: Any? = null,

		@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

		@field:SerializedName("downs")
	val downs: Int? = null,

		var editedBool: Boolean? = null,

		var editedTime: Int? = null,

		@field:SerializedName("created")
	val created: Double? = null,

		@field:SerializedName("author")
	val author: String? = null,

		@field:SerializedName("author_flair_background_color")
	val authorFlairBackgroundColor: Any? = null,

		@field:SerializedName("link_flair_text_color")
	val linkFlairTextColor: String? = null,

		@field:SerializedName("gildings")
	val gildings: Gildings? = null,

		@field:SerializedName("report_reasons")
	val reportReasons: Any? = null,

		@field:SerializedName("approved_by")
	val approvedBy: Any? = null,

		@field:SerializedName("is_video")
	val isVideo: Boolean? = null,

		@field:SerializedName("is_original_content")
	val isOriginalContent: Boolean? = null,

		@field:SerializedName("subreddit_name_prefixed")
	val subredditNamePrefixed: String? = null,

		@field:SerializedName("media_embed")
	val mediaEmbed: MediaEmbed? = null,

		@field:SerializedName("mod_reason_by")
	val modReasonBy: Any? = null,

		@field:SerializedName("domain")
	val domain: String? = null,

		@field:SerializedName("approved_at_utc")
	val approvedAtUtc: Any? = null,

		@field:SerializedName("name")
	val name: String? = null,

		@field:SerializedName("no_follow")
	val noFollow: Boolean? = null,

		@field:SerializedName("ups")
	val ups: Int? = null,

		@field:SerializedName("author_flair_type")
	val authorFlairType: String? = null,

		@field:SerializedName("media_only")
	val mediaOnly: Boolean? = null,

		@field:SerializedName("permalink")
	val permalink: String? = null,

		@field:SerializedName("content_categories")
	val contentCategories: Any? = null,

		@field:SerializedName("wls")
	val wls: Int? = null,

		@field:SerializedName("author_flair_css_class")
	val authorFlairCssClass: Any? = null,

		@field:SerializedName("num_reports")
	val numReports: Any? = null,

		@field:SerializedName("pinned")
	val pinned: Boolean? = null,

		@field:SerializedName("mod_reports")
	val modReports: List<Any?>? = null,

		@field:SerializedName("gilded")
	val gilded: Int? = null,

		@field:SerializedName("hidden")
	val hidden: Boolean? = null,

		@field:SerializedName("mod_note")
	val modNote: Any? = null,

		@field:SerializedName("removal_reason")
	val removalReason: Any? = null,

		@field:SerializedName("media")
	val media: Any? = null,

		@field:SerializedName("title")
	val title: String? = null,

		@field:SerializedName("author_flair_text")
	val authorFlairText: Any? = null,

		@field:SerializedName("send_replies")
	val sendReplies: Boolean? = null,

		@field:SerializedName("archived")
	val archived: Boolean? = null,

		@field:SerializedName("author_flair_text_color")
	val authorFlairTextColor: Any? = null,

		@field:SerializedName("num_crossposts")
	val numCrossposts: Int? = null,

		@field:SerializedName("thumbnail_width")
	val thumbnailWidth: Int? = null,

		@field:SerializedName("can_mod_post")
	val canModPost: Boolean? = null,

		@field:SerializedName("secure_media_embed")
	val secureMediaEmbed: SecureMediaEmbed? = null,

		@field:SerializedName("is_self")
	val isSelf: Boolean? = null,

		@field:SerializedName("author_fullname")
	val authorFullname: String? = null,

		@field:SerializedName("link_flair_css_class")
	val linkFlairCssClass: Any? = null,

		@field:SerializedName("selftext")
	val selftext: String? = null,

		@field:SerializedName("link_flair_text")
	val linkFlairText: String? = null,

		@field:SerializedName("selftext_html")
	val selftextHtml: Any? = null,

		@field:SerializedName("subreddit_type")
	val subredditType: String? = null,

		@field:SerializedName("is_meta")
	val isMeta: Boolean? = null,

		@field:SerializedName("user_reports")
	val userReports: List<Any?>? = null,

		@field:SerializedName("is_crosspostable")
	val isCrosspostable: Boolean? = null,

		@field:SerializedName("subreddit_subscribers")
	val subredditSubscribers: Int? = null,

		@field:SerializedName("distinguished")
	val distinguished: Any? = null,

		@field:SerializedName("clicked")
	val clicked: Boolean? = null,

		@field:SerializedName("author_flair_template_id")
	val authorFlairTemplateId: Any? = null,

		@field:SerializedName("url")
	val url: String? = null,

		@field:SerializedName("thumbnail_height")
	val thumbnailHeight: Int? = null,

		@field:SerializedName("parent_whitelist_status")
	val parentWhitelistStatus: String? = null,

		@field:SerializedName("stickied")
	val stickied: Boolean? = null,

		@field:SerializedName("link_flair_type")
	val linkFlairType: String? = null,

		@field:SerializedName("visited")
	val visited: Boolean? = null,

		@field:SerializedName("pwls")
	val pwls: Int? = null,

		@field:SerializedName("quarantine")
	val quarantine: Boolean? = null,

		@field:SerializedName("category")
	val category: Any? = null,

		@field:SerializedName("banned_by")
	val bannedBy: Any? = null,

		@field:SerializedName("contest_mode")
	val contestMode: Boolean? = null,

		@field:SerializedName("view_count")
	val viewCount: Any? = null,

		@field:SerializedName("is_reddit_media_domain")
	val isRedditMediaDomain: Boolean? = null,

		@field:SerializedName("post_hint")
	val postHint: String? = null,

		@field:SerializedName("preview")
	val preview: Preview? = null
)