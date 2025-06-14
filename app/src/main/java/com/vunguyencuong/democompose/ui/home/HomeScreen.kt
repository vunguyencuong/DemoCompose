package com.vunguyencuong.democompose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vunguyencuong.democompose.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity

@Composable
@Preview
fun HomeScreen(onHomeClick:(String) -> Unit = {}) {

     val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                return Velocity.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                return Velocity.Zero
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .nestedScroll(nestedScrollConnection)
        ) {
            // Top Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_banner_home),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(0.dp,0.dp),
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier
                        .padding(top = 24.dp, start = 16.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
            }

            CoinVaultSection()
            ButtonSection()
            ProBannerSection()
            ArticlesSection()
        }
    }

}


@Composable
private fun CoinVaultSection(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 0.dp)
            .offset(y = (-180).dp),
        contentAlignment = Alignment.CenterStart
    ) {
        //Background
        Image(
            painter = painterResource(R.drawable.img_bg_home_test),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        // Content
        Column(
            modifier = Modifier
                .padding(start = 28.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Center


        ){
            Text(
                text = "Your Coin Vault",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                )
            )
            Text(
                text = "100",
                style = TextStyle(
                    color = Color(0xFFFFDA6C),
                    fontSize = 32.sp
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Text(
                text = "Estimated total value",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                text = "$100",
                style = TextStyle(
                    color = Color(0xFFFFDA6C),
                    fontSize = 24.sp
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }

        //Coin Image
        Image(
            painter = painterResource(R.drawable.img_coin),
            contentDescription = null,
            modifier = Modifier
                .size(78.dp)
                .align(Alignment.TopEnd)
                .padding(top = 24.dp, end = 24.dp),
        )

        // Add Coin Button
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 32.dp)
                .size(height = 48.dp, width = 140.dp),
        ){
            Image(
                painter = painterResource(R.drawable.img_add_coin),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ){
                Image(
                    painter = painterResource(R.drawable.ic_add_square),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                )
                Text(
                    text = "Add Coin",
                    style = TextStyle(
                        color = Color(0xFF443C25),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun ButtonSection(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SoldCoinsButton()
        CollectionCoinsButton()
    }
}

@Composable
private fun SoldCoinsButton(){
    Box(
        modifier = Modifier
            .background(
                color = Color.White,
            )
            .border(
                width = 1.dp,
                color = Color(0xFFC4C1C0),
                shape = RoundedCornerShape(16.dp)
            )

    ) {

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(32.dp)
                .background(
                    color = Color(0xFFCE9674),
                    shape = RoundedCornerShape(bottomStart = 10.dp, topEnd = 16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_up),
                contentDescription = null,
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_sold_coin),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)

            )

            Text(
                text = "Sold Coins",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "View your past sales",
                style = TextStyle(
                    color = Color(0xFF7F7A76),
                    fontSize = 12.sp
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun CollectionCoinsButton(){
    Box(
        modifier = Modifier
            .background(
                color = Color.White
            )
            .border(
                width = 1.dp,
                color = Color(0xFFC4C1C0),
                shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp, topEnd = 0.dp, bottomStart = 16.dp)
            )

    ) {

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(32.dp)
                .background(
                    color = Color(0xFFCF7C5B),
                    shape = RoundedCornerShape(bottomStart = 10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_up),
                contentDescription = null,
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_collection_coin),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)

            )

            Text(
                text = "Collection",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "Manage your coins",
                style = TextStyle(
                    color = Color(0xFF7F7A76),
                    fontSize = 12.sp
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun ProBannerSection(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 0.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.img_banner),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(109.dp),
            contentScale = ContentScale.FillBounds
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(109.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp,top=8.dp, bottom = 8.dp)
            ) {
                Text(
                    text = "Unlock Pro – Maximize Your Collection",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    )
                )
                Text(
                    text = "Track unlimited coins, view real-time value, and access expert insights",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(start = 0.dp, end = 18.dp)
                    .background(Color.White, RoundedCornerShape(10.dp)),
            ) {
                Text(
                    text = "Try now",
                    style =TextStyle(
                        color = Color(0xFF403833),
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(horizontal = 21.dp, vertical = 9.dp)
                )
            }

        }

    }
}

@Composable
private fun ArticlesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 22.dp)
    ) {
        Text(
            text = "Article",
            style = TextStyle(
                color = Color(0xFF2A2725),
                fontSize = 18.sp
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 16.dp)
        ) {
            items(10) { index ->
                ArticleItem(title = "Article $index")
            }
        }
    }
}

@Composable
private fun ArticleItem(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(10.dp))
            .padding(vertical = 4.dp),
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}