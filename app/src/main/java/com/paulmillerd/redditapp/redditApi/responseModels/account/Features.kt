package com.paulmillerd.redditapp.redditApi.responseModels.account

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class Features(

	@field:SerializedName("email_verification")
	val emailVerification: EmailVerification? = null,

	@field:SerializedName("show_amp_link")
	val showAmpLink: Boolean? = null,

	@field:SerializedName("five_follower_send_message")
	val fiveFollowerSendMessage: Boolean? = null,

	@field:SerializedName("heartbeat_events")
	val heartbeatEvents: Boolean? = null,

	@field:SerializedName("chat_subreddit_notification_ftux")
	val chatSubredditNotificationFtux: Boolean? = null,

	@field:SerializedName("activity_service_read")
	val activityServiceRead: Boolean? = null,

	@field:SerializedName("post_to_profile_beta")
	val postToProfileBeta: Boolean? = null,

	@field:SerializedName("outbound_clicktracking")
	val outboundClicktracking: Boolean? = null,

	@field:SerializedName("show_secret_santa")
	val showSecretSanta: Boolean? = null,

	@field:SerializedName("bypass_provider_preferences")
	val bypassProviderPreferences: Boolean? = null,

	@field:SerializedName("activity_service_write")
	val activityServiceWrite: Boolean? = null,

	@field:SerializedName("redesign_crosspost_creation")
	val redesignCrosspostCreation: Boolean? = null,

	@field:SerializedName("expando_events")
	val expandoEvents: Boolean? = null,

	@field:SerializedName("adblock_test")
	val adblockTest: Boolean? = null,

	@field:SerializedName("chat_subreddit")
	val chatSubreddit: Boolean? = null,

	@field:SerializedName("subreddit_rules")
	val subredditRules: Boolean? = null,

	@field:SerializedName("legacy_search_pref")
	val legacySearchPref: Boolean? = null,

	@field:SerializedName("mweb_xpromo_interstitial_comments_android")
	val mwebXpromoInterstitialCommentsAndroid: Boolean? = null,

	@field:SerializedName("show_user_sr_name")
	val showUserSrName: Boolean? = null,

	@field:SerializedName("live_happening_now")
	val liveHappeningNow: Boolean? = null,

	@field:SerializedName("profile_redesign_settings")
	val profileRedesignSettings: Boolean? = null,

	@field:SerializedName("mobile_web_targeting")
	val mobileWebTargeting: Boolean? = null,

	@field:SerializedName("profile_redesign_posts")
	val profileRedesignPosts: Boolean? = null,

	@field:SerializedName("adserver_reporting")
	val adserverReporting: Boolean? = null,

	@field:SerializedName("oc_checkboxes")
	val ocCheckboxes: Boolean? = null,

	@field:SerializedName("post_embed")
	val postEmbed: Boolean? = null,

	@field:SerializedName("scroll_events")
	val scrollEvents: Boolean? = null,

	@field:SerializedName("programmatic_ads")
	val programmaticAds: Boolean? = null,

	@field:SerializedName("whitelisted_pms")
	val whitelistedPms: Boolean? = null,

	@field:SerializedName("email_digest_header_prefix")
	val emailDigestHeaderPrefix: EmailDigestHeaderPrefix? = null,

	@field:SerializedName("new_loggedin_cache_policy")
	val newLoggedinCachePolicy: Boolean? = null,

	@field:SerializedName("block_user_by_report")
	val blockUserByReport: Boolean? = null,

	@field:SerializedName("new_profile_layout")
	val newProfileLayout: Boolean? = null,

	@field:SerializedName("geopopular_tw_holdout")
	val geopopularTwHoldout: GeopopularTwHoldout? = null,

	@field:SerializedName("eu_cookie_policy")
	val euCookiePolicy: Boolean? = null,

	@field:SerializedName("native_ad_server")
	val nativeAdServer: Boolean? = null,

	@field:SerializedName("ad_moderation")
	val adModeration: Boolean? = null,

	@field:SerializedName("oc_discovery")
	val ocDiscovery: Boolean? = null,

	@field:SerializedName("personalization_prefs")
	val personalizationPrefs: Boolean? = null,

	@field:SerializedName("external_accounts")
	val externalAccounts: Boolean? = null,

	@field:SerializedName("give_hsts_grants")
	val giveHstsGrants: Boolean? = null,

	@field:SerializedName("popular_re_sort_v4")
	val popularReSortV4: PopularReSortV4? = null,

	@field:SerializedName("top_content_email_digest")
	val topContentEmailDigest: TopContentEmailDigest? = null,

	@field:SerializedName("ads_auction")
	val adsAuction: Boolean? = null,

	@field:SerializedName("chat_menu_notification")
	val chatMenuNotification: Boolean? = null,

	@field:SerializedName("profile_redesign_comments")
	val profileRedesignComments: Boolean? = null,

	@field:SerializedName("new_overview")
	val newOverview: Boolean? = null,

	@field:SerializedName("users_listing")
	val usersListing: Boolean? = null,

	@field:SerializedName("geopopular_ie_holdout")
	val geopopularIeHoldout: GeopopularIeHoldout? = null,

	@field:SerializedName("reddit_request_sr_processing")
	val redditRequestSrProcessing: Boolean? = null,

	@field:SerializedName("crossposting_recent")
	val crosspostingRecent: Boolean? = null,

	@field:SerializedName("pause_ads")
	val pauseAds: Boolean? = null,

	@field:SerializedName("reddit_premium")
	val redditPremium: Boolean? = null,

	@field:SerializedName("user_otp")
	val userOtp: Boolean? = null,

	@field:SerializedName("mweb_xpromo_revamp_v2")
	val mwebXpromoRevampV2: MwebXpromoRevampV2? = null,

	@field:SerializedName("https_redirect")
	val httpsRedirect: Boolean? = null,

	@field:SerializedName("oc_creation")
	val ocCreation: Boolean? = null,

	@field:SerializedName("crossposting_ga")
	val crosspostingGa: Boolean? = null,

	@field:SerializedName("ads_auto_extend")
	val adsAutoExtend: Boolean? = null,

	@field:SerializedName("reddit_premium_gold_award_only")
	val redditPremiumGoldAwardOnly: Boolean? = null,

	@field:SerializedName("profile_redesign")
	val profileRedesign: Boolean? = null,

	@field:SerializedName("screenview_events")
	val screenviewEvents: Boolean? = null,

	@field:SerializedName("upgrade_cookies")
	val upgradeCookies: Boolean? = null,

	@field:SerializedName("mweb_xpromo_modal_listing_click_daily_dismissible_ios")
	val mwebXpromoModalListingClickDailyDismissibleIos: Boolean? = null,

	@field:SerializedName("original_content")
	val originalContent: Boolean? = null,

	@field:SerializedName("ios_promoted_posts")
	val iosPromotedPosts: Boolean? = null,

	@field:SerializedName("send_pm_creators_top_ten")
	val sendPmCreatorsTopTen: Boolean? = null,

	@field:SerializedName("profile_redesign_pinning")
	val profileRedesignPinning: Boolean? = null,

	@field:SerializedName("rte_video")
	val rteVideo: Boolean? = null,

	@field:SerializedName("moat_tracking")
	val moatTracking: Boolean? = null,

	@field:SerializedName("orangereds_as_emails")
	val orangeredsAsEmails: Boolean? = null,

	@field:SerializedName("chat_group_rollout")
	val chatGroupRollout: Boolean? = null,

	@field:SerializedName("mweb_xpromo_interstitial_comments_ios")
	val mwebXpromoInterstitialCommentsIos: Boolean? = null,

	@field:SerializedName("do_not_track")
	val doNotTrack: Boolean? = null,

	@field:SerializedName("spez_modal")
	val spezModal: Boolean? = null,

	@field:SerializedName("creator_notif_redis")
	val creatorNotifRedis: Boolean? = null,

	@field:SerializedName("geopopular_se_holdout")
	val geopopularSeHoldout: GeopopularSeHoldout? = null,

	@field:SerializedName("listing_service_rampup")
	val listingServiceRampup: Boolean? = null,

	@field:SerializedName("interest_targeting")
	val interestTargeting: Boolean? = null,

	@field:SerializedName("ads_auto_refund")
	val adsAutoRefund: Boolean? = null,

	@field:SerializedName("mweb_xpromo_modal_listing_click_daily_dismissible_link")
	val mwebXpromoModalListingClickDailyDismissibleLink: Boolean? = null,

	@field:SerializedName("geopopular")
	val geopopular: Boolean? = null,

	@field:SerializedName("chat_rollout")
	val chatRollout: Boolean? = null,

	@field:SerializedName("mweb_xpromo_modal_listing_click_daily_dismissible_android")
	val mwebXpromoModalListingClickDailyDismissibleAndroid: Boolean? = null,

	@field:SerializedName("mobile_native_banner")
	val mobileNativeBanner: Boolean? = null,

	@field:SerializedName("news")
	val news: Boolean? = null,

	@field:SerializedName("default_srs_holdout")
	val defaultSrsHoldout: DefaultSrsHoldout? = null,

	@field:SerializedName("drafts")
	val drafts: Boolean? = null,

	@field:SerializedName("force_https")
	val forceHttps: Boolean? = null,

	@field:SerializedName("inbox_push")
	val inboxPush: Boolean? = null,

	@field:SerializedName("mobile_ad_image")
	val mobileAdImage: Boolean? = null,

	@field:SerializedName("ios_profile_edit")
	val iosProfileEdit: Boolean? = null,

	@field:SerializedName("partner_connection")
	val partnerConnection: Boolean? = null,

	@field:SerializedName("oc_discovery_filtering")
	val ocDiscoveryFiltering: Boolean? = null,

	@field:SerializedName("search_public_traffic")
	val searchPublicTraffic: Boolean? = null,

	@field:SerializedName("android_promoted_posts")
	val androidPromotedPosts: Boolean? = null,

	@field:SerializedName("top_ten_notif")
	val topTenNotif: Boolean? = null,

	@field:SerializedName("drafts_sharing")
	val draftsSharing: Boolean? = null
)