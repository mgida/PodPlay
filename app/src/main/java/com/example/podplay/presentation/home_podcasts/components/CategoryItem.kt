package com.example.podplay.presentation.home_podcasts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun CategoryItem(
    modifier: Modifier = Modifier,
    genreModel: GenreModel,
    onCategoryClicked: (id: Int) -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onBackground)
            .clickable {
                onCategoryClicked.invoke(genreModel.id)
            }
    ) {

        Text(
            text = genreModel.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
        )
    }
}


@Composable
fun Categories(
    modifier: Modifier = Modifier,
    categories: List<GenreModel>,
    onCategoryClicked: (id: Int) -> Unit
) {

    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        items(categories, key = {
            it.id
        }) {
            CategoryItem(genreModel = it, onCategoryClicked = onCategoryClicked)
        }
    }
}

@ThemePreviews
@Composable
private fun CategoryItemPreview() {
    PodPlayTheme {
        CategoryItem(genreModel = GenreModel(
            id = 7638,
            name = "Tech"
        ), onCategoryClicked = {}
        )
    }
}

@ThemePreviews
@Composable
private fun CategoriesPreview() {
    PodPlayTheme {
        Categories(
            categories = listOf(
                GenreModel(id = 1, name = "Tech"),
                GenreModel(id = 2, name = "Science"),
            )
        ) {}
    }
}