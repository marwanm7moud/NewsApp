package com.awesome.newsapp.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.awesome.newsapp.presentation.screen.home.ArticleUiState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object NewsCustomNavType {
    val article = object : NavType<ArticleUiState>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): ArticleUiState? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): ArticleUiState {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: ArticleUiState): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: ArticleUiState) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}