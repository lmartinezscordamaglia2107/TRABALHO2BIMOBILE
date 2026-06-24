package com.seuapp.gestaofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.seuapp.gestaofit.R

// --- DEFINIÇÕES DE MODELO ---
enum class TipoTransacao {
    ENTRADA, SAIDA
}

data class Transacao(
    val descricao: String,
    val valor: Double,
    val tipo: TipoTransacao,
    val data: LocalDate = LocalDate.now()
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val listaTransacoes = listOf(
                    Transacao(descricao = "Mensalidade - Lucas", valor = 120.00, tipo = TipoTransacao.ENTRADA),
                    Transacao(descricao = "Mensalidade - Anna", valor = 120.00, tipo = TipoTransacao.ENTRADA),
                    Transacao(descricao = "Manutenção de Esteiras", valor = 350.00, tipo = TipoTransacao.SAIDA),
                    Transacao(descricao = "Mensalidade - Ágata", valor = 150.00, tipo = TipoTransacao.ENTRADA),
                    Transacao(descricao = "Conta de Luz (Academia)", valor = 450.00, tipo = TipoTransacao.SAIDA)
                )

                DashboardScreen(transacoes = listaTransacoes)
            }
        }
    }
}

@Composable
fun DashboardScreen(transacoes: List<Transacao>) {
    val totalEntradas = transacoes.filter { it.tipo == TipoTransacao.ENTRADA }.sumOf { it.valor }
    val totalSaidas = transacoes.filter { it.tipo == TipoTransacao.SAIDA }.sumOf { it.valor }
    val saldoTotal = totalEntradas - totalSaidas

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color(0xFF00E676),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(id = R.string.txt_fluxo_caixa),
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
                Text(
                    text = String.format(Locale.getDefault(), "R$ %.2f", saldoTotal),
                    color = if (saldoTotal >= 0) Color(0xFF00E676) else Color(0xFFE53935),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = stringResource(id = R.string.txt_entradas), color = Color.Gray, fontSize = 12.sp)
                    Text(text = String.format(Locale.getDefault(), "R$ %.2f", totalEntradas), color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold)
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = stringResource(id = R.string.txt_saidas), color = Color.Gray, fontSize = 12.sp)
                    Text(text = String.format(Locale.getDefault(), "R$ %.2f", totalSaidas), color = Color(0xFFE53935), fontWeight = FontWeight.Bold)
                }
            }
        }

        Text(
            text = "Últimas Movimentações",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(transacoes) { transacao ->
                TransacaoItem(transacao = transacao)
            }
        }
    }
}

@Composable
fun TransacaoItem(transacao: Transacao) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF262626)),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = transacao.descricao, color = Color.White, fontWeight = FontWeight.Medium)
                Text(text = transacao.data.format(formatter), color = Color.Gray, fontSize = 12.sp)
            }
            Text(
                text = if (transacao.tipo == TipoTransacao.ENTRADA) {
                    String.format(Locale.getDefault(), "+ R$ %.2f", transacao.valor)
                } else {
                    String.format(Locale.getDefault(), "- R$ %.2f", transacao.valor)
                },
                color = if (transacao.tipo == TipoTransacao.ENTRADA) Color(0xFF4CAF50) else Color(0xFFE53935),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
