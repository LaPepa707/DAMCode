package com.example.navegaciopager.ui.llista

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navegaciopager.dades.Concerts.obtenDadesDelsConcerts
import com.example.navegaciopager.model.Concert
import com.example.navegaciopager.ui.pantalla.PantallaDetalladaC
import kotlinx.coroutines.launch

@Composable
fun LlistaC(llista : List<Concert> = obtenDadesDelsConcerts()){
    val estatPaginador = rememberPagerState{llista.size}
    val ambitCorrutines = rememberCoroutineScope()
    Column()
    {

        ScrollableTabRow(selectedTabIndex = estatPaginador.currentPage, edgePadding = 8.dp){
            repeat(llista.size){
                    pagina->
                Tab(
                    selected = estatPaginador.currentPage == pagina,
                    onClick = {ambitCorrutines.launch{estatPaginador.animateScrollToPage(pagina)}},
                    text = {Text(text="Dada ${pagina + 1}")}
                )
            }
        }
        HorizontalPager(
            modifier = Modifier
                .weight(1f),
            state = estatPaginador,
            beyondViewportPageCount = 3
        ) {pagina->
            PantallaDetalladaC(int = llista[pagina].id)
        }
    }
}