package com.github.whyrising.androiddevchallenge.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularIconButton(
    modifier: Modifier = Modifier,
    size: Dp = 26.dp,
    icon: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        elevation = 2.dp,
    ) {
        IconButton(
            modifier = Modifier
                .size(size)
                .padding(4.dp),
            onClick = { /*TODO*/ }
        ) {
            icon()
        }
    }
}

