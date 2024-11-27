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
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
            Content(modifier = Modifier.padding(paddingValues))
        },
        bottomBar = { BottomBar()}
    )

}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 56.dp)
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(12.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
        ) {
            Text(
                text = "Hola buenas tardes"
            )
        }
    }
}

@Composable
fun ConstraintContent() {
    ConstraintLayout(Modifier.fillMaxSize()) {

    }
}
// shape = RoundedCornerShape(50.dp)
@Composable
fun BottomBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Añadimos tres elementos
        BottomBarItem(
            imageRes = R.drawable.home_button, // Icono de ejemplo
            text = "Inicio",
            onClick = { /* Acción del botón 1 */ }
        )

        BottomBarItem(
            imageRes = R.drawable.character_button, // Icono de ejemplo
            text = "Buscar",
            onClick = { /* Acción del botón 2 */ }
        )

        BottomBarItem(
            imageRes = R.drawable.homescreen_settings, // Icono de ejemplo
            text = "Perfil",
            onClick = { /* Acción del botón 3 */ }
        )
    }
}