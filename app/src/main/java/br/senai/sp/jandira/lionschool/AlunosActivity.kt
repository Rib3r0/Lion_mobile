package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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

class AlunosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                AlunosScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlunosScreen() {

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51,71,176)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DESENVOLVIMENTO DE SISTEMAS",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White, shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(27.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(9.dp))
                    var color by remember { mutableStateOf(Color.White) }
                    var colorText by remember { mutableStateOf(Color(51,71,176)) }
                    var isEnable = false
                    Card(
                        onClick = {
                            if(isEnable == false){
                                color = Color(51,71,176)
                                colorText = Color.White
                                isEnable = true
                            }else if(isEnable == true){
                                color = Color.White
                                colorText = Color(51,71,176)
                                isEnable = false
                            }

                                  },
                        border = BorderStroke(2.dp, Color(51,71,176)),
                        backgroundColor = color,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(7.dp),
                            color = colorText,
                            text = "cursando",
                            fontWeight = FontWeight.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(9.dp))
                    var colorFinalizado by remember { mutableStateOf(Color.White) }
                    var colorTextFinalizado by remember { mutableStateOf(Color(51,71,176)) }
                    var isEnableFinalizado = false
                    Card(
                        onClick = {
                            if(isEnableFinalizado == false){
                                colorFinalizado = Color(51,71,176)
                                colorTextFinalizado = Color.White
                                isEnableFinalizado = true
                            }else if(isEnableFinalizado == true){
                                colorFinalizado = Color.White
                                colorTextFinalizado = Color(51,71,176)
                                isEnableFinalizado = false
                            }

                        },
                        backgroundColor = colorFinalizado ,
                        border = BorderStroke(2.dp, Color(51,71,176)),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(7.dp),
                            color = colorTextFinalizado,
                            text = "finalizado",
                            fontWeight = FontWeight.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(9.dp))
                    TextField(
                        modifier = Modifier.height(30.dp),
                        value = "2023",
                        onValueChange = {}
                    )
                }
                LazyColumn(
                    content = {
                        items(2){
                            Card(
                                onClick = {
                                    val intent = Intent(context, AlunoActivity::class.java)
                                    intent.putExtra("nome", "DS")
                                    context.startActivity(intent)
                                },
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .padding(20.dp, 0.dp)
                                ,
                                shape = RoundedCornerShape(20.dp),
                                backgroundColor = Color(51,71,176),

                                ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        modifier = Modifier.size(80.dp),
                                        painter = painterResource(id = R.drawable.aluno),
                                        contentDescription = ""
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(
                                        modifier = Modifier.width(180.dp),
                                        text = "José Matheus da Silva Miranda",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Black,
                                        color = Color.White,
                                    )

                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                )

            }
        }

    }
}