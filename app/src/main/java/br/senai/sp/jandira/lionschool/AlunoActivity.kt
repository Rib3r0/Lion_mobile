package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class AlunoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlunoScreen()
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlunoScreen() {
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51,71,176)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment =Alignment.CenterHorizontally,
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "José Matheus da Silva Miranda",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(id = R.drawable.aluno),
                    contentDescription = "logo"
                )


            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White, shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    text = "NOTAS",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(51,71,176)
                )
                LazyColumn(
                    content = {
                        items(2){
                            Column() {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .padding(12.dp),
                                        text = "PWFE",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Black,
                                        color = Color(51,71,176)
                                    )
                                    Text(
                                        modifier = Modifier
                                            .padding(12.dp),
                                        text = "90",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Black,
                                        color = Color(51,71,176)
                                    )
                                }
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(25.dp)
                                        .padding(20.dp, 0.dp)
                                    ,
                                    backgroundColor = Color(0,0,0,25)
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .width(5.dp)
                                            .height(25.dp)
                                            .padding(0.dp, 0.dp,20.dp,0.dp)
                                        ,
                                        backgroundColor = Color(51,71,176)
                                    ) {

                                    }
                                }
                            }
                        }
                    }
                )

            }
        }

    }
}
