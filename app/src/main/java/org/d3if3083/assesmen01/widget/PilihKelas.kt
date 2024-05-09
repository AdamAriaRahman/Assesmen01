package org.d3if3083.assesmen01.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3083.assesmen01.ui.theme.Assesmen01Theme


@Composable
fun PilihKelas(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GenderButtonPrev() {
    Assesmen01Theme {
        PilihKelas(label = "contoh", isSelected = true, modifier = Modifier)
    }
}