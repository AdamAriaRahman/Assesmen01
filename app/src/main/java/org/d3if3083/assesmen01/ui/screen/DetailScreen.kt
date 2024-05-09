package org.d3if3083.assesmen01.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3083.assesmen01.R
import org.d3if3083.assesmen01.database.MbtiDb
import org.d3if3083.assesmen01.ui.theme.Assesmen01Theme
import org.d3if3083.assesmen01.util.ViewModelFactory
import org.d3if3083.assesmen01.widget.PilihKelas


const val KEY_ID_MBTI = "idMbti"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val db = MbtiDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var nama by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var kelas by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getMahasiswa(id) ?: return@LaunchedEffect
        nama = data.namaNya
        nim = data.nimNya
        kelas = data.kelasNya
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.kembali
                            ),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_mbti))
                    else
                        Text(text = stringResource(R.string.edit_hasil))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        if (nama == "" || nim == "" || kelas == "") {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }

                        if (id == null) {
                            viewModel.insert(nama, nim, kelas)
                        } else {
                            viewModel.update(id, nama, nim, kelas)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check, contentDescription = stringResource(
                                R.string.simpan
                            ),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        if (id != null) {
                            DeleteAction { showDialog = true}
                            DisplayAlertDialog(
                                openDialog = showDialog,
                                onDismissRequest = {showDialog = false }) {
                                showDialog = false
                                viewModel.delete(id)
                                navController.popBackStack()
                            }
                        }
                    }
                }
            )
        }
    ) { padding ->
        FormMbti(
            namaMhs = nama,
            onTitleChange = { nama = it },
            nimMhs = nim,
            onDescChange = { nim = it },
            kelasMhs = kelas,
            onKelasChange = { kelas = it },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormMbti(
    namaMhs: String, onTitleChange: (String) -> Unit,
    nimMhs: String, onDescChange: (String) -> Unit,
    kelasMhs: String, onKelasChange: (String) -> Unit,
    modifier: Modifier
) {

    val kelasOption = listOf(
        "Kurang Cocok",
        "Cocok",

    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = namaMhs, onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(R.string.nama)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nimMhs, onValueChange = { onDescChange(it) },
            label = { Text(text = stringResource(R.string.mbti)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Apak hasil pencocokan MBTI mu?")
        
        
        Column(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .fillMaxWidth()
        ) {
            kelasOption.forEach { text ->
                PilihKelas(
                    label = text,
                    isSelected = kelasMhs == text,
                    modifier = Modifier
                        .selectable(
                            selected = kelasMhs == text,
                            onClick = { onKelasChange(text) },
                            role = Role.RadioButton
                        )
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun DeleteAction(delete: () -> Unit) {
    var expended by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { expended = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false })
        {
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.hapus)) },
                onClick = {
                    expended = false
                    delete()
                })
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    Assesmen01Theme {
        DetailScreen(rememberNavController())
    }
}