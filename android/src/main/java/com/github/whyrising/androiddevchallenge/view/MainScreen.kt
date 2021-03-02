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
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.whyrising.androiddevchallenge.R
import com.github.whyrising.androiddevchallenge.theme.Blue700
import com.github.whyrising.androiddevchallenge.theme.Green100
import com.github.whyrising.androiddevchallenge.theme.Green900
import com.github.whyrising.androiddevchallenge.theme.MyTheme
import com.github.whyrising.androiddevchallenge.theme.Red300
import com.github.whyrising.androiddevchallenge.viewmodels.Category
import com.github.whyrising.androiddevchallenge.viewmodels.Gender
import com.github.whyrising.androiddevchallenge.viewmodels.MainViewModel
import com.github.whyrising.androiddevchallenge.viewmodels.PetViewModel

@Composable
fun LocationTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit = {},
    modifier: Modifier,
    typography: Typography
) {
    val colors = MaterialTheme.colors

    val textStyle = typography.caption.copy(color = colors.onSurface.copy(.7f))

    BasicTextField(
        value = text,
        modifier = modifier
            .background(
                color = colors.primary.copy(0.1f),
                shape = CircleShape
            ),
        textStyle = textStyle,
        singleLine = true,
        onValueChange = onValueChange,
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
                    Box {
                        innerTextField()

                        if (text.isEmpty())
                            Text(
                                text = "Location",
                                style = textStyle.copy(
                                    color = colors.onSurface.copy(.4f),
                                ),
                            )
                    }
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
fun LocationSearchBar(vm: MainViewModel, typography: Typography) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LocationTextField(
            text = vm.locationSearch,
            onValueChange = { vm.locationSearch = it },
            onClear = { vm.locationSearch = "" },
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
    title: String,
    painter: Painter,
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
                painter = painter,
                contentDescription = "",
                modifier = Modifier.size(32.dp),
                colorFilter = colorFilter,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
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
fun PetCell(
    modifier: Modifier,
    colors: Colors,
    petViewModel: PetViewModel
) {
    Card(
        modifier = modifier.padding(bottom = 20.dp),
        elevation = 1.dp,
        backgroundColor = MaterialTheme.colors.surface,
        border = BorderStroke(
            width = 2.dp,
            color = colors.onSurface.copy(.06f)
        ),
    ) {
        Column {
            ConstraintLayout {
                val likeBtnRef = createRef()

                Image(
                    painter = painterResource(id = petViewModel.id),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .height(128.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(17.dp))
                        .padding(horizontal = 1.dp),
                )

                CircularIconButton(
                    modifier = Modifier
                        .padding(top = 12.dp, end = 12.dp)
                        .constrainAs(likeBtnRef) {
                            end.linkTo(parent.end)
                        },
                ) {
                    val imageVector = when {
                        petViewModel.isLiked -> Icons.Filled.Favorite
                        else -> Icons.Filled.FavoriteBorder
                    }
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }

            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 16.dp,
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = petViewModel.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = 16.sp
                    )

                    val pair = when (petViewModel.gender) {
                        Gender.Male -> Pair(R.drawable.ic_mars, Blue700)
                        else -> Pair(R.drawable.ic_venus, Red300)
                    }
                    Icon(
                        painter = painterResource(id = pair.first),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .align(alignment = Alignment.CenterVertically),
                        tint = pair.second
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = petViewModel.breed,
                        style = MaterialTheme.typography.overline
                    )
                    DotDivider(
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    Text(
                        text = petViewModel.age,
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
fun MyApp(vm: MainViewModel) {
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
                    LocationSearchBar(vm, typography)

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

                LazyRow(modifier = Modifier.padding(start = outerPadding)) {
                    items(vm.petCategories) { petCategory ->
                        when (petCategory) {
                            Category.Dog -> PetCategory(
                                stringResource(R.string.dogs_category),
                                painter = painterResource(id = R.drawable.ic_dog_outline),
                                modifier = Modifier
                                    .background(
                                        color = Green100,
                                        shape = RoundedCornerShape(15.dp),
                                    ),
                                colorFilter = ColorFilter.tint(Green900),
                            )
                            else ->
                                PetCategory(
                                    title = stringResource(R.string.cats_category),
                                    painter = painterResource(id = R.drawable.ic_cat_outline),
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
                    itemsIndexed(vm.pets) { index, petViewModel ->
                        val padding = 10.dp
                        val modifier = when {
                            index % 2 == 0 -> Modifier.padding(end = padding)
                            else -> Modifier.padding(start = padding)
                        }
                        PetCell(modifier, colors, petViewModel)
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
        MyApp(MainViewModel())
    }
}

@ExperimentalFoundationApi
@Composable
@Preview
fun DarkPreview() {
    MyTheme(isDarkTheme = true) {
        MyApp(MainViewModel())
    }
}
