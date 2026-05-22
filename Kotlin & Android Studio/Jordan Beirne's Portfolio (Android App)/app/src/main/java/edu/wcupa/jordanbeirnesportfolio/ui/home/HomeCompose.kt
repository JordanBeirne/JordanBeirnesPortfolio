package edu.wcupa.jordanbeirnesportfolio.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.wcupa.jordanbeirnesportfolio.R


@Composable
fun HomeCompose() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(R.drawable.home_background),
            contentDescription = "home_background",
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to \n Jordan Beirne's Portfolio!",
                fontSize = 32.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "This is a portfolio of projects created in CSC 461:",
                fontSize = 26.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Text(
                text = "\n1) Customizable Card",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 28.dp)
            )
            Text(
                text = "\n2) Restaurant Review Tracker",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 28.dp)
            )
            Text(
                text = "\n3) DnD Dice Roller",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 28.dp)
            )
            Text(
                text = "\n4) Unscramble Game w/ Hint",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 28.dp)
            )
            Text(
                text = "\n5) Coffee Release w/ Favorites",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = 28.dp)
            )
        }


    }
}