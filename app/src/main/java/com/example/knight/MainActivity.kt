package com.example.knight

import android.annotation.SuppressLint
import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.knight.core.navigation.NavigationWrapper
import com.example.knight.ui.theme.KnightTheme
import kotlin.concurrent.fixedRateTimer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationWrapper()
        }
    }
}

// crear el navhostcontroller de cero con un tutorial
@Composable
fun AppGame() {
}

 // si pongo preview no puedo verlo por la funcion lambda

@Preview
@Composable
fun GameScreen() {
    // la idea es hacer una barra bonita arriba con las monedas
    // en el centro el monigote
    // y abajo un botón para pulsar y las quests para ganar más monedas
    Scaffold (
        topBar = { },
        content = { paddingValues ->
            ConstraintContent(modifier = Modifier.padding(paddingValues))
        },
        bottomBar = { BottomBar()}
    )

}

var hp:Int = 10; // una variable que varía en función del bicho (va escalando etc)
// fun Monster(string: String) : Int { // depende de la imagen del bicho

@Composable
fun Content() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ){
        Row (
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.33f),
                text = "Coins",
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                text = "?????",
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "?????",
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
    // lo suyo es hacer variables para la vida del bicho y cada
    // vez que se hace click le haces un daño random entre 1-3
    // cuando muere sumas monedas
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // hp del bicho // stats
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                modifier = Modifier,
                text = hp.toString(),
                fontSize = 28.sp
            )
        }
       // bicho
        Row (
            modifier = Modifier
                .size(350.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
           Image(
               painter = painterResource(R.drawable.pacmanred),
               contentDescription = "monster1",
               modifier = Modifier
                   .fillMaxSize()
           )
        }
        Column ( // arma // no deja ampliar el tamaño
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ){
            Column (
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End
            ){
                Image(
                    painter = painterResource(R.drawable.sword),
                    contentDescription = "monster1",
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }
        }
    }
}

val interactionSource = MutableInteractionSource()
@Composable
fun ConstraintContent(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (screen) = createRefs()
        Box (
          modifier = Modifier
              .constrainAs(screen) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
                  bottom.linkTo(parent.bottom)
              }
              .background(Color.Red)
              .fillMaxSize()
              .clickable (interactionSource = interactionSource, indication = null){  } // elimina el efecto
        ) {
            Content()
        }
    }
}
// shape = RoundedCornerShape(50.dp)
@Composable
fun BottomBar(modifier: Modifier = Modifier){
    BottomAppBar(
        modifier = Modifier
            .background(Color.White)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Añadimos tres elementos
            BottomBarItem(
                imageRes = R.drawable.sword, // Icono de ejemplo
                text = "Dungeon",
                onClick = { /* Acción del botón 1 */ }
            )
            VerticalDivider(color = Color.LightGray, thickness = 2.dp) // está por verse
            BottomBarItem(
                imageRes = R.drawable.magicbook, // Icono de ejemplo
                text = "Quests",
                onClick = { /* Acción del botón 2 */ }
            )
            VerticalDivider(color = Color.LightGray, thickness = 2.dp)
            BottomBarItem(
                imageRes = R.drawable.anvil, // Icono de ejemplo
                text = "Upgrades",
                onClick = { /* Acción del botón 3 */ }
            )
        }
    }
}