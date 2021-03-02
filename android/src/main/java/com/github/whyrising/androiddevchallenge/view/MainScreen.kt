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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Colors
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
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.whyrising.androiddevchallenge.R
import com.github.whyrising.androiddevchallenge.theme.Green100
import com.github.whyrising.androiddevchallenge.theme.Green900
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
fun PetCategory(
    item: String,
    modifier: Modifier,
    colorFilter: ColorFilter,
) {
    val typography = MaterialTheme.typography
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val colors = MaterialTheme.colors
        Box(
            modifier = modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dog_outline),
                contentDescription = "Dog outline",
                modifier = Modifier.size(32.dp),
                colorFilter = colorFilter,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item,
            style = TextStyle(color = colors.onSurface.copy(.7f))
                .merge(typography.subtitle2),
        )
    }
}

@Composable
fun DotDivider(modifier: Modifier = Modifier) {
    val widthSpace = Modifier.width(8.dp)
    Spacer(modifier = widthSpace)
    Box(
        modifier = modifier
            .size(3.dp)
            .background(
                color = MaterialTheme.colors.onSurface,
                shape = CircleShape,
            )
            .padding(10.dp)
    )
    Spacer(modifier = widthSpace)
}

@Composable
fun LikeButton(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(10.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = CircleShape,
            )
            .clip(shape = CircleShape)
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            modifier = Modifier
                .padding(4.dp)
                .size(16.dp),
            contentDescription = "",
            tint = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun PetCell(
    modifier: Modifier,
    colors: Colors
) {
    Card(
        modifier = modifier.padding(bottom = 20.dp),
        elevation = 1.dp,
        shape = RoundedCornerShape(17.dp),
        backgroundColor = MaterialTheme.colors.surface,
        border = BorderStroke(
            width = 2.dp,
            color = colors.onSurface.copy(.06f)
        ),
    ) {
        Column {
            ConstraintLayout {
                val favIconRef = createRef()

                Image(
                    painter = painterResource(id = R.drawable.dog1),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .height(128.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(17.dp))
                        .padding(horizontal = 1.dp),
                )

                LikeButton(
                    modifier = Modifier
                        .constrainAs(favIconRef) {
                            end.linkTo(parent.end)
                        },
                )
            }

            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 16.dp,
                )
            ) {
                val textColor = Color(0xffec7172)
                val bgColor = Color(0xffFFDEDE)

                val blueText = Color(0XFF3270F6)
                val blueBg = Color(0xffbdd7ff)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Nora",
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = 16.sp
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_mars),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .align(alignment = Alignment.CenterVertically),
                        tint = blueText
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Corgi",
                        style = MaterialTheme.typography.overline
                    )
                    DotDivider(
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    Text(
                        text = "8 mth.",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically),
                        style = MaterialTheme.typography.overline,
                    )
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MyApp() {
    Scaffold {
        Surface {
            var searchTextFiledState by remember { mutableStateOf("") }

            val colors = MaterialTheme.colors
            val typography = MaterialTheme.typography
            Column {
                val outerPadding = 16.dp
                val outerModifier = Modifier.padding(
                    top = outerPadding,
                    start = outerPadding,
                    end = outerPadding
                )
                Column(
                    modifier = outerModifier
                ) {
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

                Spacer(modifier = Modifier.height(16.dp))

                val list = listOf("Dogs", "Cats", "Birds", "Rabbits", "Fish")

                LazyRow(modifier = Modifier.padding(start = outerPadding)) {
                    items(list) { item ->
                        when (item) {
                            "Dogs" -> PetCategory(
                                item,
                                modifier = Modifier
                                    .background(
                                        color = Green100,
                                        shape = RoundedCornerShape(15.dp),
                                    ),
                                colorFilter = ColorFilter.tint(Green900),
                            )
                            else -> PetCategory(
                                item = item,
                                modifier = Modifier
                                    .border(
                                        width = 2.dp,
                                        color = colors.onSurface.copy(.06f),
                                        shape = RoundedCornerShape(15.dp),
                                    ),
                                colorFilter = ColorFilter.tint(
                                    color = colors.onSurface.copy(.5f),
                                ),
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = outerPadding),
                    cells = GridCells.Fixed(2)
                ) {
                    itemsIndexed(
                        listOf(
                            "Dog1",
                            "Dog2",
                            "Dog3",
                            "Dog4",
                            "Dog5",
                            "Dog6"
                        )
                    ) { index, item ->

                        val padding = 10.dp
                        val modifier = when {
                            index % 2 == 0 -> Modifier.padding(end = padding)
                            else -> Modifier.padding(start = padding)
                        }
                        PetCell(modifier, colors)
                    }

                }
            }
        }
    }
}

/**
 *
 * Previews
 *
 **/

@ExperimentalFoundationApi
@Composable
@Preview
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalFoundationApi
@Composable
@Preview
fun DarkPreview() {
    MyTheme(isDarkTheme = true) {
        MyApp()
    }
}
