package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.ListCursos
import retrofit2.Call
import retrofit2.http.GET

interface CursoService {

    @GET("lion-school/cursos")
    fun getCursos(): Call<ListCursos>
}