package com.edimariyanto.meal.data.models.test

data class WebsocketResponse(
    val CLIENT_ID: Int,
    val DATA_FORMAT: String,
    val MESSAGE: String,
    val RATELIMIT_MAX_DAY: Int,
    val RATELIMIT_MAX_HOUR: Int,
    val RATELIMIT_MAX_MINUTE: Int,
    val RATELIMIT_MAX_MONTH: Int,
    val RATELIMIT_MAX_SECOND: Int,
    val RATELIMIT_REMAINING_DAY: Int,
    val RATELIMIT_REMAINING_HOUR: Int,
    val RATELIMIT_REMAINING_MINUTE: Int,
    val RATELIMIT_REMAINING_MONTH: Int,
    val RATELIMIT_REMAINING_SECOND: Int,
    val SERVER_NAME: String,
    val SERVER_TIME_MS: Long,
    val SERVER_UPTIME_SECONDS: Int,
    val SOCKETS_ACTIVE: Int,
    val SOCKETS_REMAINING: Int,
    val SOCKET_ID: String,
    val TYPE: String
)