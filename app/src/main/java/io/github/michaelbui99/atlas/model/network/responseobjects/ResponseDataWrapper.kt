package io.github.michaelbui99.atlas.model.network.responseobjects

/**
 * Most Reddit API responses are wrapped in a object containing a kind-attribute and data.
 * Kind indicates what the thing is:
 * t1 = Comment,
 * t2 = Account,
 * t3 = Link,
 * t4 = Message,
 * t5 = Subreddit,
 * t6 = Award
 * */
data class ResponseDataWrapper<T>(val kind: String, val data: T)