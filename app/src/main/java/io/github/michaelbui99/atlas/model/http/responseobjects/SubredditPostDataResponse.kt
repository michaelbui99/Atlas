/*
*   Classes generated with Json Schema to Kotlin parser
*   generated classes has been renamed to give more context to the data
*
*/

package io.github.michaelbui99.atlas.model.http.responseobjects

import com.fasterxml.jackson.annotation.JsonProperty

data class SubredditPostDataResponse(val data: List<ResponseDataWrapper>)

data class ResponseDataWrapper(val kind: String, val data: ResponseData)
data class ResponseData(
    val after: Any?,
    val dist: Long?,
    val modhash: String,
    @JsonProperty("geo_filter")
    val geoFilter: String,
    val children: List<PostDataWrapper>,
    val before: Any?,
)

data class PostDataWrapper(val kind: String, val data: PostData)
data class PostData(
    @JsonProperty("approved_at_utc")
    val approvedAtUtc: Any?,
    val subreddit: String,
    val selfText: String?,
    @JsonProperty("user_reports")
    val userReports: List<Any?>,
    val saved: Boolean,
    @JsonProperty("mod_reason_title")
    val modReasonTitle: Any?,
    val gilded: Long,
    val clicked: Boolean?,
    val title: String?,
    @JsonProperty("link_flair_richtext")
    val linkFlairRichtext: List<LinkFlairRichtext>?,
    @JsonProperty("subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    val hidden: Boolean?,
    val pwls: Long?,
    @JsonProperty("link_flair_css_class")
    val linkFlairCssClass: String?,
    val downs: Long,
    @JsonProperty("thumbnail_height")
    val thumbnailHeight: Any?,
    @JsonProperty("top_awarded_type")
    val topAwardedType: Any?,
    @JsonProperty("parent_whitelist_status")
    val parentWhitelistStatus: String?,
    @JsonProperty("hide_score")
    val hideScore: Boolean?,
    val name: String,
    val quarantine: Boolean?,
    @JsonProperty("link_flair_text_color")
    val linkFlairTextColor: String?,
    @JsonProperty("upvote_ratio")
    val upvoteRatio: Double?,
    @JsonProperty("author_flair_background_color")
    val authorFlairBackgroundColor: String?,
    @JsonProperty("subreddit_type")
    val subredditType: String,
    val ups: Long,
    @JsonProperty("total_awards_received")
    val totalAwardsReceived: Long,
    @JsonProperty("media_embed")
    val mediaEmbed: Map<String, Any>?,
    @JsonProperty("thumbnail_width")
    val thumbnailWidth: Any?,
    @JsonProperty("author_flair_template_id")
    val authorFlairTemplateId: String?,
    @JsonProperty("is_original_content")
    val isOriginalContent: Boolean?,
    @JsonProperty("author_fullname")
    val authorFullname: String?,
    @JsonProperty("secure_media")
    val secureMedia: Any?,
    @JsonProperty("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean?,
    @JsonProperty("is_meta")
    val isMeta: Boolean?,
    val category: Any?,
    @JsonProperty("secure_media_embed")
    val secureMediaEmbed: Map<String, Any>?,
    @JsonProperty("link_flair_text")
    val linkFlairText: String?,
    @JsonProperty("can_mod_post")
    val canModPost: Boolean,
    val score: Long,
    @JsonProperty("approved_by")
    val approvedBy: Any?,
    @JsonProperty("is_created_from_ads_ui")
    val isCreatedFromAdsUi: Boolean?,
    @JsonProperty("author_premium")
    val authorPremium: Boolean?,
    val thumbnail: String?,
    val edited: Any?,
    @JsonProperty("author_flair_css_class")
    val authorFlairCssClass: Any?,
    @JsonProperty("author_flair_richtext")
    val authorFlairRichtext: List<AuthorFlairRichtext>?,
    val gildings: Gildings,
    @JsonProperty("content_categories")
    val contentCategories: List<String>?,
    @JsonProperty("is_self")
    val isSelf: Boolean?,
    @JsonProperty("mod_note")
    val modNote: Any?,
    val created: Long,
    @JsonProperty("link_flair_type")
    val linkFlairType: String?,
    val wls: Long?,
    @JsonProperty("removed_by_category")
    val removedByCategory: Any?,
    @JsonProperty("banned_by")
    val bannedBy: Any?,
    @JsonProperty("author_flair_type")
    val authorFlairType: String?,
    val domain: String?,
    @JsonProperty("allow_live_comments")
    val allowLiveComments: Boolean?,
    @JsonProperty("selftext_html")
    val selftextHtml: String?,
    val likes: Any?,
    @JsonProperty("suggested_sort")
    val suggestedSort: Any?,
    @JsonProperty("banned_at_utc")
    val bannedAtUtc: Any?,
    @JsonProperty("view_count")
    val viewCount: Any?,
    val archived: Boolean,
    @JsonProperty("no_follow")
    val noFollow: Boolean,
    @JsonProperty("is_crosspostable")
    val isCrosspostable: Boolean?,
    val pinned: Boolean?,
    @JsonProperty("over_18")
    val over18: Boolean?,
    @JsonProperty("all_awardings")
    val allAwardings: List<AllAwarding>,
    val awarders: List<Any?>,
    @JsonProperty("media_only")
    val mediaOnly: Boolean?,
    @JsonProperty("link_flair_template_id")
    val linkFlairTemplateId: String?,
    @JsonProperty("can_gild")
    val canGild: Boolean,
    val spoiler: Boolean?,
    val locked: Boolean,
    @JsonProperty("author_flair_text")
    val authorFlairText: String?,
    @JsonProperty("treatment_tags")
    val treatmentTags: List<Any?>,
    val visited: Boolean?,
    @JsonProperty("removed_by")
    val removedBy: Any?,
    @JsonProperty("num_reports")
    val numReports: Any?,
    val distinguished: String?,
    @JsonProperty("subreddit_id")
    val subredditId: String,
    @JsonProperty("author_is_blocked")
    val authorIsBlocked: Boolean,
    @JsonProperty("mod_reason_by")
    val modReasonBy: Any?,
    @JsonProperty("removal_reason")
    val removalReason: Any?,
    @JsonProperty("link_flair_background_color")
    val linkFlairBackgroundColor: String?,
    val id: String,
    @JsonProperty("is_robot_indexable")
    val isRobotIndexable: Boolean?,
    @JsonProperty("num_duplicates")
    val numDuplicates: Long?,
    @JsonProperty("report_reasons")
    val reportReasons: Any?,
    val author: String,
    @JsonProperty("discussion_type")
    val discussionType: Any?,
    @JsonProperty("num_comments")
    val numComments: Long?,
    @JsonProperty("send_replies")
    val sendReplies: Boolean,
    val media: Any?,
    @JsonProperty("contest_mode")
    val contestMode: Boolean?,
    @JsonProperty("author_patreon_flair")
    val authorPatreonFlair: Boolean?,
    @JsonProperty("author_flair_text_color")
    val authorFlairTextColor: String?,
    val permalink: String,
    @JsonProperty("whitelist_status")
    val whitelistStatus: String?,
    val stickied: Boolean,
    val url: String?,
    @JsonProperty("subreddit_subscribers")
    val subredditSubscribers: Long?,
    @JsonProperty("created_utc")
    val createdUtc: Long,
    @JsonProperty("num_crossposts")
    val numCrossposts: Long?,
    @JsonProperty("mod_reports")
    val modReports: List<Any?>,
    @JsonProperty("is_video")
    val isVideo: Boolean?,
    @JsonProperty("comment_type")
    val commentType: Any?,
    val replies: Any?,
    @JsonProperty("collapsed_reason_code")
    val collapsedReasonCode: String?,
    @JsonProperty("parent_id")
    val parentId: String?,
    val collapsed: Boolean?,
    val body: String?,
    @JsonProperty("is_submitter")
    val isSubmitter: Boolean?,
    @JsonProperty("body_html")
    val bodyHtml: String?,
    @JsonProperty("collapsed_reason")
    val collapsedReason: Any?,
    @JsonProperty("associated_award")
    val associatedAward: Any?,
    @JsonProperty("unrepliable_reason")
    val unrepliableReason: Any?,
    @JsonProperty("score_hidden")
    val scoreHidden: Boolean?,
    @JsonProperty("link_id")
    val linkId: String?,
    val controversiality: Long?,
    val depth: Long?,
    @JsonProperty("collapsed_because_crowd_control")
    val collapsedBecauseCrowdControl: Any?,
)


data class LinkFlairRichtext(
    val e: String,
    val t: String,
)

data class AuthorFlairRichtext(
    val e: String,
    val t: String,
)

data class Gildings(
    @JsonProperty("gid_1")
    val gid1: Long?,
)

data class AllAwarding(
    @JsonProperty("giver_coin_reward")
    val giverCoinReward: Any?,
    @JsonProperty("subreddit_id")
    val subredditId: Any?,
    @JsonProperty("is_new")
    val isNew: Boolean,
    @JsonProperty("days_of_drip_extension")
    val daysOfDripExtension: Any?,
    @JsonProperty("coin_price")
    val coinPrice: Long,
    val id: String,
    @JsonProperty("penny_donate")
    val pennyDonate: Any?,
    @JsonProperty("coin_reward")
    val coinReward: Long,
    @JsonProperty("icon_url")
    val iconUrl: String,
    @JsonProperty("days_of_premium")
    val daysOfPremium: Any?,
    @JsonProperty("icon_height")
    val iconHeight: Long,
    @JsonProperty("tiers_by_required_awardings")
    val tiersByRequiredAwardings: Any?,
    @JsonProperty("resized_icons")
    val resizedIcons: List<ResizedIcon>,
    @JsonProperty("icon_width")
    val iconWidth: Long,
    @JsonProperty("static_icon_width")
    val staticIconWidth: Long,
    @JsonProperty("start_date")
    val startDate: Any?,
    @JsonProperty("is_enabled")
    val isEnabled: Boolean,
    @JsonProperty("awardings_required_to_grant_benefits")
    val awardingsRequiredToGrantBenefits: Any?,
    val description: String,
    @JsonProperty("end_date")
    val endDate: Any?,
    @JsonProperty("subreddit_coin_reward")
    val subredditCoinReward: Long,
    val count: Long,
    @JsonProperty("static_icon_height")
    val staticIconHeight: Long,
    val name: String,
    @JsonProperty("resized_static_icons")
    val resizedStaticIcons: List<ResizedStaticIcon>,
    @JsonProperty("icon_format")
    val iconFormat: Any?,
    @JsonProperty("award_sub_type")
    val awardSubType: String,
    @JsonProperty("penny_price")
    val pennyPrice: Any?,
    @JsonProperty("award_type")
    val awardType: String,
    @JsonProperty("static_icon_url")
    val staticIconUrl: String,
)

data class ResizedIcon(
    val url: String,
    val width: Long,
    val height: Long,
)

data class ResizedStaticIcon(
    val url: String,
    val width: Long,
    val height: Long,
)