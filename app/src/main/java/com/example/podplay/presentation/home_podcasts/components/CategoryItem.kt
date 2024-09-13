package com.example.podplay.presentation.home_podcasts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.podplay.domain.model.genres.GenreModel
import com.example.podplay.ui.theme.PodPlayTheme
import com.example.podplay.util.ThemePreviews


@Composable
fun CategoryItem(modifier: Modifier = Modifier, categoryName: String) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onBackground)
    ) {

        Text(
            text = categoryName,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
        )
    }

}


@Composable
fun Categories(modifier: Modifier = Modifier, categories: List<GenreModel>) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        items(categories, key = {
            it.id
        }) {
            CategoryItem(categoryName = it.name)
        }
    }
}

@ThemePreviews
@Composable
private fun CategoryItemPreview() {
    PodPlayTheme {
        CategoryItem(
            categoryName = "Tech"
        )
    }
}

@ThemePreviews
@Composable
private fun CategoriesPreview() {
    PodPlayTheme {
        Categories(
            categories = listOf(
                GenreModel(id = 1, name = "Lon Larsen"),
                GenreModel(id = 2, name = "Lon Larsen")
            )
        )
    }
}