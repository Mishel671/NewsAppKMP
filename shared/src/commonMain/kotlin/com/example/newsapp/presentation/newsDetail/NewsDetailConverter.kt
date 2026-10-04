package com.example.newsapp.presentation.newsDetail

import com.example.newsapp.common.platform.AppLogger
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

internal class NewsDetailConverter {
    private val publishedAtFormat = LocalDateTime.Format {
        day(); char('.'); monthNumber(); char('.');
        year(); char(' '); hour(); char(':'); minute()
    }

    fun toUiState(state: NewsDetailScreenState): NewsDetailUiState {
        if (state.detail == null) return NewsDetailUiState()
        return with(state.detail) {
            NewsDetailUiState(
                title = title,
                description = description,
                content = content,
                author = author,
                publishedAt = publishedAt?.let { formatPublishedAt(it) },
                sourceName = source?.name,
                url = url,
                urlToImage = urlToImage
            )
        }
    }


    private fun formatPublishedAt(date: String): String = try {
        Instant.parse(date)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .format(publishedAtFormat)
    } catch (e: Exception) {
        AppLogger.e("MainLog", "Not parse date: ${date}")
        date
    }
}