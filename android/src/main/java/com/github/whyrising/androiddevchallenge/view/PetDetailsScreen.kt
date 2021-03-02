package com.github.whyrising.androiddevchallenge.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.Typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Highlight
import androidx.compose.material.icons.filled.HighlightAlt
import androidx.compose.material.icons.filled.HighlightOff
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.whyrising.androiddevchallenge.R
import com.github.whyrising.androiddevchallenge.theme.MyTheme
import com.github.whyrising.androiddevchallenge.viewmodels.MainViewModel

@Composable
private fun IconTextBox(
    text: String,
    painter: Painter,
    colors: Colors,
    typography: Typography
) {
    Card(
        backgroundColor = colors.primary.copy(alpha = .1f),
        elevation = 0.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Icon(
                painter = painter,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                tint = colors.primary,
                contentDescription = ""
            )

            Text(
                text = text,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = typography.subtitle1.copy(
                    colors.onSurface.copy(
                        .6f
                    )
                )
            )
        }
    }
}

private fun placeholderText(): String {
    return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
        "Sed feugiat tellus quis lobortis varius. Quisque in vestibulum urna," +
        " non dignissim nulla. Quisque ac nulla in justo finibus consequat " +
        "vel non velit. Sed vitae purus arcu. Sed tristique elit orci, nec " +
        "sodales metus cursus non."
}

@Composable
fun PetDetails(
    navController: NavController,
    pet: Map<String, Any>,
    mainViewModel: MainViewModel
) {
    val colors = MaterialTheme.colors
    val typography = MaterialTheme.typography

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Details")
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = {
                            navController.navigateUp()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = {
                            mainViewModel.toggleLightDarkTheme()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Highlight,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                val roundFactor = 32.dp
                ConstraintLayout {
                    val (
                        likeBtnRef,
                        soundBtnRef,
                        imageRef,
                        titleRef,
                    ) = createRefs()

                    Image(
                        modifier = Modifier
                            .constrainAs(imageRef) {
                                top.linkTo(parent.top)
                                centerHorizontallyTo(parent)
                            }
                            .fillMaxWidth()
                            .height(340.dp)
                            .clip(
                                shape = RoundedCornerShape(
                                    bottomStart = roundFactor,
                                    bottomEnd = roundFactor,
                                )
                            ),
                        painter = painterResource(id = pet["id"] as Int),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Dog image",
                    )

                    CircularIconButton(
                        modifier = Modifier
                            .padding(top = 16.dp, end = 16.dp)
                            .constrainAs(likeBtnRef) {
                                end.linkTo(parent.end)
                            },
                        size = 40.dp
                    ) {
                        val isLiked = pet["liked"] as Boolean

                        val imageVector = when {
                            isLiked -> Icons.Filled.Favorite
                            else -> Icons.Filled.FavoriteBorder
                        }

                        Icon(
                            imageVector = imageVector,
                            contentDescription = "",
                            tint = colors.primary
                        )
                    }

                    CircularIconButton(
                        modifier = Modifier
                            .padding(end = 32.dp)
                            .constrainAs(soundBtnRef) {
                                end.linkTo(parent.end)
                                centerAround(imageRef.bottom)
                            },
                        size = 40.dp
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sound),
                            contentDescription = "",
                            tint = colors.primary
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxHeight()
                            .constrainAs(titleRef) {
                                top.linkTo(imageRef.bottom)
                            },
                    ) {
                        Text(
                            text = pet["name"] as String,
                            style = typography.h4.merge(
                                TextStyle(color = colors.primary)
                            )
                        )
                        Text(
                            text = pet["breed"] as String,
                            style = typography.subtitle2.copy(
                                colors.onSurface.copy(.4f)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val genderText: String
                    val genderIcon: Int
                    val i = pet["gender"] as Int

                    if (i == 0) {
                        genderText = "Boy"
                        genderIcon = R.drawable.ic_mars
                    } else {
                        genderText = "Girl"
                        genderIcon = R.drawable.ic_venus
                    }

                    IconTextBox(
                        genderText,
                        painterResource(id = genderIcon),
                        colors,
                        typography
                    )
                    IconTextBox(
                        "1 year",
                        painterResource(id = R.drawable.ic_clock),
                        colors,
                        typography
                    )
                    IconTextBox(
                        "8 Kg",
                        painterResource(id = R.drawable.ic_weight),
                        colors,
                        typography
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = placeholderText(),
                        style = typography.body1.copy(colors.onSurface.copy(.8f)),
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = { /*TODO*/ }
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(R.drawable.ic_adopt),
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.CenterVertically),
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Adopt me",
                                modifier = Modifier
                                    .padding(vertical = 8.dp),
                                style = typography.h5
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

/**
 *
 * Previews
 *
 **/

private val dogDesignTime = mapOf(
    "id" to R.drawable.dog1,
    "name" to "Hector",
    "breed" to "Breed",
    "gender" to 0,
    "liked" to true,
)

@Composable
@Preview
fun PetDetailsLightPreview() {
    MyTheme {
        PetDetails(rememberNavController(), dogDesignTime, MainViewModel())
    }
}

@Composable
@Preview
fun PetDetailsDarkPreview() {
    MyTheme(isDarkTheme = true) {
        PetDetails(rememberNavController(), dogDesignTime, MainViewModel())
    }
}