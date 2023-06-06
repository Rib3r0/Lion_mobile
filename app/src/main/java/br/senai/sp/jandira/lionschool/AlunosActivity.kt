package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.ListAlunos
import br.senai.sp.jandira.lionschool.model.ListCursos
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AlunosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                AlunosScreen(intent.extras?.getString("sigla"),intent.extras?.getString("titulo"))
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlunosScreen(curso : String?,titulo : String?) {

    val context = LocalContext.current

    var alunos by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Alunos>())
    }
    //Hamad para a API
    val call = RetrofitFactory().getCursosService().getAlunos(curso)

    call.enqueue(object : Callback<ListAlunos> {
        override fun onResponse(
            call: Call<ListAlunos>,
            response: Response<ListAlunos>
        ) {
            alunos = response.body()!!.alunos
        }

        override fun onFailure(call: Call<ListAlunos>, t: Throwable) {
            Log.i("ds2m","onFailure: $t")
        }

    })
    Log.i("ds2m","$alunos")
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
                    text = titulo.toString(),
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
                    var isEnable by remember{ mutableStateOf( false)}
                    Card(
                        onClick = {
                            if(!isEnable){
                                color = Color(51,71,176)
                                colorText = Color.White
                                isEnable = true
                            }else{
                                color = Color.White
                                colorText = Color(51,71,176)
                                isEnable = false
                            }
                            Log.i("ds2m","onFailure: ${isEnable}")

                            val call = RetrofitFactory().getCursosService().getAlunosByStatus(curso,"Cursando")

                            call.enqueue(object : Callback<ListAlunos> {
                                override fun onResponse(
                                    call: Call<ListAlunos>,
                                    response: Response<ListAlunos>
                                ) {
                                    alunos = response.body()!!.alunos
                                }

                                override fun onFailure(call: Call<ListAlunos>, t: Throwable) {
                                    Log.i("ds2m","onFailure: $t")
                                }

                            })
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
                    var isEnableFinalizado by remember{ mutableStateOf( false)}
                    Card(
                        onClick = {
                            if(!isEnableFinalizado){
                                colorFinalizado = Color(51,71,176)
                                colorTextFinalizado = Color.White
                                isEnableFinalizado = true
                            }else{
                                colorFinalizado = Color.White
                                colorTextFinalizado = Color(51,71,176)
                                isEnableFinalizado = false
                            }

                            val call = RetrofitFactory().getCursosService().getAlunosByStatus(curso,"Finalizado")
                            call.enqueue(object : Callback<ListAlunos> {
                                override fun onResponse(
                                    call: Call<ListAlunos>,
                                    response: Response<ListAlunos>
                                ) {
                                    alunos = response.body()!!.alunos
                                }

                                override fun onFailure(call: Call<ListAlunos>, t: Throwable) {
                                    Log.i("ds2m","onFailure: $t")
                                }

                            })

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
                        items(alunos){

                            Card(
                                onClick = {
                                    val intent = Intent(context, AlunoActivity::class.java)
                                    intent.putExtra("matricula", it.matricula)
                                    intent.putExtra("foto", it.foto)
                                    intent.putExtra("nome", it.nome)
                                    context.startActivity(intent)
                                },
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .padding(20.dp, 0.dp)
                                ,
                                shape = RoundedCornerShape(20.dp),
                                backgroundColor = if (it.status == "Cursando") Color(51,71,176) else Color(229,182,87),

                                ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    AsyncImage(
                                        modifier = Modifier.size(80.dp),
                                        model = it.foto
                                        , contentDescription = "AVATAR"
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(
                                        modifier = Modifier.width(180.dp),
                                        text = it.nome.uppercase(),
                                        fontSize = 20.sp,
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