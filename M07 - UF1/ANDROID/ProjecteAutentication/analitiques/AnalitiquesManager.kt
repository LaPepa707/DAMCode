package com.montilivi.projecteautentificacio.analitiques

import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.firebase.analytics.FirebaseAnalytics

class analituquesManager(context: Context){
    private val fire_base_analytics: FirebaseAnalytics by lazy{
        FirebaseAnalytics.getInstance(context)
    }
    private fun registra_esdeveniment(
        nomEsdeveniment: String,
        params: Bundle){
        fire_base_analytics.logEvent(nomEsdeveniment, params)
    }
    fun registraClickBtn(btnName: String){
        val parameters = Bundle().apply {
            putString("btnName", btnName)
        }
        registra_esdeveniment("click_btn", parameters)
    }

    fun error_registre(error: String){
        val parameters = Bundle().apply {
            putString("error", error)
        }
        registra_esdeveniment("Error", parameters)
    }

    @Composable
    fun registraVisitaPantalla(nomPantalla: String){
        DisposableEffect(Unit) {
            onDispose {
                val parameters = Bundle().apply {
                    putString(FirebaseAnalytics.Param.SCREEN_NAME, nomPantalla)
                    putString(FirebaseAnalytics.Param.SCREEN_CLASS, nomPantalla)
                }
                registra_esdeveniment(FirebaseAnalytics.Event.SCREEN_VIEW, parameters)
            }
        }
    }
}