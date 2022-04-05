package io.github.michaelbui99.atlas.model.network.responseobjects

import com.google.gson.annotations.SerializedName

/**
 * Response Object wrapping the data received, when fetching posts from a subreddit
 * e.g. GET www.reddit.com/r/Art.json
 * */
data class SubredditResponse(val kind: String, val data: SubredditResponseData) {

}

data class SubredditResponseData(
    val after: String,
    val dist: Long,
    val modhash: String,
    @SerializedName("geo_filter")
    val geoFilter: Any?,
    val children: List<ResponseDataWrapper<SubredditResponsePost>>,
    val before: Any?,
)

data class SubredditResponsePost(
    @SerializedName("approved_at_utc")
    val approvedAtUtc: Any?,
    val subreddit: String,
    val selftext: String,
    @SerializedName("author_fullname")
    val authorFullname: String,
    val saved: Boolean,
    @SerializedName("mod_reason_title")
    val modReasonTitle: Any?,
    val gilded: Long,
    val clicked: Boolean,
    val title: String,
    @SerializedName("link_flair_richtext")
    val linkFlairRichtext: List<SubredditResponseLinkFlairRichtext>,
    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    val hidden: Boolean,
    val pwls: Long,
    @SerializedName("link_flair_css_class")
    val linkFlairCssClass: String,
    val downs: Long,
    @SerializedName("thumbnail_height")
    val thumbnailHeight: Long?,
    @SerializedName("top_awarded_type")
    val topAwardedType: Any?,
    @SerializedName("hide_score")
    val hideScore: Boolean,
    val name: String,
    val quarantine: Boolean,
    @SerializedName("link_flair_text_color")
    val linkFlairTextColor: String,
    @SerializedName("upvote_ratio")
    val upvoteRatio: Double,
    @SerializedName("author_flair_background_color")
    val authorFlairBackgroundColor: Any?,
    @SerializedName("subreddit_type")
    val subredditType: String,
    val ups: Long,
    @SerializedName("total_awards_received")
    val totalAwardsReceived: Long,
    @SerializedName("media_embed")
    val mediaEmbed: Map<String, Any>,
    @SerializedName("thumbnail_width")
    val thumbnailWidth: Long?,
    @SerializedName("author_flair_template_id")
    val authorFlairTemplateId: String?,
    @SerializedName("is_original_content")
    val isOriginalContent: Boolean,
    @SerializedName("user_reports")
    val userReports: List<Any?>,
    @SerializedName("secure_media")
    val secureMedia: Any?,
    @SerializedName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean,
    @SerializedName("is_meta")
    val isMeta: Boolean,
    val category: String?,
    @SerializedName("secure_media_embed")
    val secureMediaEmbed: Map<String, Any>,
    @SerializedName("link_flair_text")
    val linkFlairText: String,
    @SerializedName("can_mod_post")
    val canModPost: Boolean,
    val score: Long,
    @SerializedName("approved_by")
    val approvedBy: Any?,
    @SerializedName("is_created_from_ads_ui")
    val isCreatedFromAdsUi: Boolean,
    @SerializedName("author_premium")
    val authorPremium: Boolean,
    val thumbnail: String,
    val edited: Boolean,
    @SerializedName("author_flair_css_class")
    val authorFlairCssClass: Any?,
    @SerializedName("author_flair_richtext")
    val authorFlairRichtext: List<SubredditResponseAuthorFlairRichtext>,
    val gildings: SubredditResponseGildings,
    @SerializedName("content_categories")
    val contentCategories: List<String>,
    @SerializedName("is_self")
    val isSelf: Boolean,
    @SerializedName("mod_note")
    val modNote: Any?,
    val created: Double,
    @SerializedName("link_flair_type")
    val linkFlairType: String,
    val wls: Long,
    @SerializedName("removed_by_category")
    val removedByCategory: Any?,
    @SerializedName("banned_by")
    val bannedBy: Any?,
    @SerializedName("author_flair_type")
    val authorFlairType: String,
    val domain: String,
    @SerializedName("allow_live_comments")
    val allowLiveComments: Boolean,
    @SerializedName("selftext_html")
    val selftextHtml: String?,
    val likes: Any?,
    @SerializedName("suggested_sort")
    val suggestedSort: Any?,
    @SerializedName("banned_at_utc")
    val bannedAtUtc: Any?,
    @SerializedName("view_count")
    val viewCount: Any?,
    val archived: Boolean,
    @SerializedName("no_follow")
    val noFollow: Boolean,
    @SerializedName("is_crosspostable")
    val isCrosspostable: Boolean,
    val pinned: Boolean,
    @SerializedName("over_18")
    val over18: Boolean,
    @SerializedName("all_awardings")
    val allAwardings: List<SubredditResponseAllAwarding>,
    val awarders: List<Any?>,
    @SerializedName("media_only")
    val mediaOnly: Boolean,
    @SerializedName("link_flair_template_id")
    val linkFlairTemplateId: String,
    @SerializedName("can_gild")
    val canGild: Boolean,
    val spoiler: Boolean,
    val locked: Boolean,
    @SerializedName("author_flair_text")
    val authorFlairText: String?,
    @SerializedName("treatment_tags")
    val treatmentTags: List<Any?>,
    val visited: Boolean,
    @SerializedName("removed_by")
    val removedBy: Any?,
    @SerializedName("num_reports")
    val numReports: Any?,
    val distinguished: Any?,
    @SerializedName("subreddit_id")
    val subredditId: String,
    @SerializedName("author_is_blocked")
    val authorIsBlocked: Boolean,
    @SerializedName("mod_reason_by")
    val modReasonBy: Any?,
    @SerializedName("removal_reason")
    val removalReason: Any?,
    @SerializedName("link_flair_background_color")
    val linkFlairBackgroundColor: String,
    val id: String,
    @SerializedName("is_robot_indexable")
    val isRobotIndexable: Boolean,
    @SerializedName("report_reasons")
    val reportReasons: Any?,
    val author: String,
    @SerializedName("discussion_type")
    val discussionType: Any?,
    @SerializedName("num_comments")
    val numComments: Long,
    @SerializedName("send_replies")
    val sendReplies: Boolean,
    @SerializedName("whitelist_status")
    val whitelistStatus: String,
    @SerializedName("contest_mode")
    val contestMode: Boolean,
    @SerializedName("mod_reports")
    val modReports: List<Any?>,
    @SerializedName("author_patreon_flair")
    val authorPatreonFlair: Boolean,
    @SerializedName("author_flair_text_color")
    val authorFlairTextColor: String?,
    val permalink: String,
    @SerializedName("parent_whitelist_status")
    val parentWhitelistStatus: String,
    val stickied: Boolean,
    val url: String,
    @SerializedName("subreddit_subscribers")
    val subredditSubscribers: Long,
    @SerializedName("created_utc")
    val createdUtc: Long,
    @SerializedName("num_crossposts")
    val numCrossposts: Long,
    val media: Any?,
    @SerializedName("is_video")
    val isVideo: Boolean,
    @SerializedName("post_hint")
    val postHint: String?,
    @SerializedName("url_overridden_by_dest")
    val urlOverriddenByDest: String?,
    val preview: SubredditResponsePreview?,
    @SerializedName("call_to_action")
    val callToAction: String?,
)

data class SubredditResponseLinkFlairRichtext(
    val e: String,
    val t: String,
)

data class SubredditResponseAuthorFlairRichtext(
    val e: String,
    val t: String,
)

data class SubredditResponseGildings(
    @SerializedName("gid_1")
    val gid1: Long?,
)

data class SubredditResponseAllAwarding(
    @SerializedName("giver_coin_reward")
    val giverCoinReward: Any?,
    @SerializedName("subreddit_id")
    val subredditId: Any?,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("days_of_drip_extension")
    val daysOfDripExtension: Any?,
    @SerializedName("coin_price")
    val coinPrice: Long,
    val id: String,
    @SerializedName("penny_donate")
    val pennyDonate: Any?,
    @SerializedName("award_sub_type")
    val awardSubType: String,
    @SerializedName("coin_reward")
    val coinReward: Long,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("days_of_premium")
    val daysOfPremium: Any?,
    @SerializedName("tiers_by_required_awardings")
    val tiersByRequiredAwardings: Any?,
    @SerializedName("resized_icons")
    val resizedIcons: List<SubredditResponseResizedIcon>,
    @SerializedName("icon_width")
    val iconWidth: Long,
    @SerializedName("static_icon_width")
    val staticIconWidth: Long,
    @SerializedName("start_date")
    val startDate: Any?,
    @SerializedName("is_enabled")
    val isEnabled: Boolean,
    @SerializedName("awardings_required_to_grant_benefits")
    val awardingsRequiredToGrantBenefits: Any?,
    val description: String,
    @SerializedName("end_date")
    val endDate: Any?,
    @SerializedName("subreddit_coin_reward")
    val subredditCoinReward: Long,
    val count: Long,
    @SerializedName("static_icon_height")
    val staticIconHeight: Long,
    val name: String,
    @SerializedName("resized_static_icons")
    val resizedStaticIcons: List<SubredditResponseResizedStaticIcon>,
    @SerializedName("icon_format")
    val iconFormat: String?,
    @SerializedName("icon_height")
    val iconHeight: Long,
    @SerializedName("penny_price")
    val pennyPrice: Long?,
    @SerializedName("award_type")
    val awardType: String,
    @SerializedName("static_icon_url")
    val staticIconUrl: String,
)

data class SubredditResponseResizedIcon(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseResizedStaticIcon(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponsePreview(
    val images: List<SubredditResponseImage>,
    val enabled: Boolean,
)

data class SubredditResponseImage(
    val source: SubredditResponseSource,
    val resolutions: List<SubredditResponseResolution>,
    val variants: SubredditResponseVariants,
    val id: String,
)

data class SubredditResponseSource(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseResolution(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseVariants(
    val gif: SubredditResponseGif?,
    val mp4: SubredditResponseMp4?,
    val obfuscated: SubredditResponseObfuscated?,
    val nsfw: SubredditResponseNsfw?,
)

data class SubredditResponseGif(
    val source: SubredditResponseSource2,
    val resolutions: List<SubredditResponseResolution2>,
)

data class SubredditResponseSource2(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseResolution2(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseMp4(
    val source: SubredditResponseSource3,
    val resolutions: List<SubredditResponseResolution3>,
)

data class SubredditResponseSource3(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseResolution3(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseObfuscated(
    val source: SubredditResponseSource4,
    val resolutions: List<SubredditResponseResolution4>,
)

data class SubredditResponseSource4(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseResolution4(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseNsfw(
    val source: SubredditResponseSource5,
    val resolutions: List<SubredditResponseResolution5>,
)

data class SubredditResponseSource5(
    val url: String,
    val width: Long,
    val height: Long,
)

data class SubredditResponseResolution5(
    val url: String,
    val width: Long,
    val height: Long,
)
