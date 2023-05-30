package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.ListCursos
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreen() {

    val context = LocalContext.current

    var cursos by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Cursos>())
    }
    //Hamad para a API
    val call = RetrofitFactory().getCursosService().getCursos()

    call.enqueue(object : Callback<ListCursos> {
        override fun onResponse(
            call: Call<ListCursos>,
            response: Response<ListCursos>
        ) {
            cursos = response.body()!!.cursos
        }

        override fun onFailure(call: Call<ListCursos>, t: Throwable) {
            Log.i("ds2m","onFailure: $t")
        }

    })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(51,71,176)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(13.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier.width(80.dp),
                    text = stringResource(id = R.string.Logo),
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
                Text(
                    modifier = Modifier.padding(25.dp),
                    text = stringResource(id = R.string.cursos),
                    color = Color(51,71,176),
                    fontWeight = FontWeight.Black,
                    fontSize = 30.sp
                )
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    columns = GridCells.Adaptive(100.dp),
                    horizontalArrangement = Arrangement.spacedBy(46.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                    items(2) {
                        Card(
                            modifier = Modifier.size(150.dp)
                                .clickable {
                                    val intent = Intent(context, AlunosActivity::class.java)
                                    intent.putExtra("nome", "DS")
                                    context.startActivity(intent)
                                },
                            shape = RoundedCornerShape(20.dp),
                            backgroundColor = Color(51,71,176),

                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    modifier = Modifier.size(40.dp),
                                    painter = painterResource(id = R.drawable.code),
                                    contentDescription = ""
                                )
                                Text(
                                    text = "DS",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                )

                            }
                        }

                    }
                } )
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)) {

                }
            }
        }

    }
}