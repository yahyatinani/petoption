/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.whyrising.androiddevchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.whyrising.androiddevchallenge.R
import com.github.whyrising.androiddevchallenge.theme.MyTheme

@Composable
fun CustomTextField(
    value: String,
    modifier: Modifier,
    typography: Typography
) {
    val colors = MaterialTheme.colors

    BasicTextField(
        value = value,
        modifier = modifier
            .background(
                color = colors.primary.copy(0.1f),
                shape = CircleShape
            ),
        textStyle = TextStyle(color = colors.onSurface.copy(.7f))
            .merge(typography.caption),
        singleLine = true,
        onValueChange = { /*TODO*/ },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        modifier = Modifier.size(16.dp),
                        tint = colors.primary,
                        contentDescription = "Location"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    innerTextField()
                }
                Icon(
                    imageVector = Icons.Default.Close,
                    modifier = Modifier.size(16.dp),
                    tint = colors.onSurface.copy(0.5f),
                    contentDescription = "Clear text"
                )
            }
        }
    )
}

@Composable
fun SearchBar(typography: Typography) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTextField(
            value = "New York, USA",
            modifier = Modifier.weight(1f),
            typography = typography,
        )
        Spacer(modifier = Modifier.width(32.dp))
        Image(
            painter = painterResource(id = R.drawable.sample_avatar),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable ((Modifier) -> Unit)? = null,
    textStyle: TextStyle = LocalTextStyle.current,
) = ConstraintLayout(modifier = Modifier.height(IntrinsicSize.Min)) {
    val trailingIconSize = 40.dp
    val (trailRef, textFieldRef) = createRefs()

    BasicTextField(
        value = value,
        textStyle = textStyle,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .constrainAs(textFieldRef) {
                start.linkTo(parent.start)
            }
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.onSurface.copy(0.06f),
                RoundedCornerShape(12.dp)
            ),
        onValueChange = onValueChange,
    ) { innerTextField ->
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = trailingIconSize + 2.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            innerTextField()

            if (value.isEmpty() && placeholder != null)
                placeholder()
        }
    }

    if (trailingIcon != null) {
        trailingIcon(
            Modifier
                .size(trailingIconSize)
                .constrainAs(trailRef) {
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun MyApp() {
    Scaffold {
        Surface(
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        ) {
            var searchTextFiledState by remember { mutableStateOf("") }

            val colors = MaterialTheme.colors
            val typography = MaterialTheme.typography
            Column {
                SearchBar(typography)

                Spacer(modifier = Modifier.height(16.dp))

                CustomOutlinedTextField(
                    value = searchTextFiledState,
                    onValueChange = { searchTextFiledState = it },
                    textStyle = TextStyle(color = colors.onSurface.copy(.7f))
                        .merge(typography.subtitle2),
                    placeholder = {
                        Text(
                            text = "Search",
                            style = TextStyle(colors.onSurface.copy(.4f))
                                .merge(typography.subtitle2),
                        )
                    },
                    trailingIcon = { modifier ->
                        IconButton(
                            modifier = modifier
                                .background(
                                    colors.primary,
                                    shape = RoundedCornerShape(12.dp),
                                ),
                            onClick = { /*TODO*/ },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Search",
                                tint = colors.surface,
                                modifier = Modifier.size(28.dp),
                            )
                        }
                    }
                )
            }
        }
    }
}

/**
 *
 * Previews
 *
 **/

@Composable
@Preview
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Composable
@Preview
fun DarkPreview() {
    MyTheme(isDarkTheme = true) {
        MyApp()
    }
}
