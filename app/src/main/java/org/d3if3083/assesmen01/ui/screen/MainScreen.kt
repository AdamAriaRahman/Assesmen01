package org.d3if3083.assesmen01.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3083.assesmen01.R
import org.d3if3083.assesmen01.navigation.Screen
import org.d3if3083.assesmen01.ui.theme.Assesmen01Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(imageVector = Icons.Outlined.Info ,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )

                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun  ScreenContent(modifier: Modifier) {
    var mbtiMu by rememberSaveable { mutableStateOf("") }
    var mbtiLain by rememberSaveable { mutableStateOf("") }
    var hasilMBTI by rememberSaveable { mutableStateOf("")}

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.mbti_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = mbtiMu,
            onValueChange = {mbtiMu = it},
            label = {Text(text = stringResource(R.string.mbti_mu))},

            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = mbtiLain,
            onValueChange = {mbtiLain = it},
            label = {Text(text = stringResource(R.string.mbti_lain))},

            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { hasilMBTI = cocokkanMBTI(mbtiMu,mbtiLain)},
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally),
            contentPadding = PaddingValues(16.dp))
        {
           Text(text = stringResource(R.string.cocokan))
        }
            Text(
                text = hasilMBTI,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                color = if (hasilMBTI == "Cocok") Color.Blue else Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp

            )
        Button(
            onClick = {
                      shareData(
                          context = context,
                          message = context.getString(R.string.bagikan_template,
                              mbtiMu, mbtiLain,hasilMBTI)
                      )
            },
            modifier = Modifier.padding(top = 8.dp). align(Alignment.CenterHorizontally),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)

            ) {
                Text(text = stringResource(R.string.bagikan))

        }
    }
}

private  fun cocokkanMBTI(mbtiMu: String, mbtiLain: String): String {
    return if
            (
        (mbtiMu.equals("ENFP", ignoreCase = true) && mbtiLain.equals("INTJ", ignoreCase = true)) ||
        (mbtiMu.equals("INTJ", ignoreCase = true) && mbtiLain.equals("ENFP", ignoreCase = true)) ||
        (mbtiMu.equals("ISTJ", ignoreCase = true) && mbtiLain.equals("ESTP", ignoreCase = true)) ||
        (mbtiMu.equals("ESTP", ignoreCase = true) && mbtiLain.equals("ISTJ", ignoreCase = true)) ||
        (mbtiMu.equals("ISFJ", ignoreCase = true) && mbtiLain.equals("ESFP", ignoreCase = true)) ||
        (mbtiMu.equals("ESFP", ignoreCase = true) && mbtiLain.equals("ISFJ", ignoreCase = true)) ||
        (mbtiMu.equals("INFJ", ignoreCase = true) && mbtiLain.equals("ENTJ", ignoreCase = true)) ||
        (mbtiMu.equals("ENTJ", ignoreCase = true) && mbtiLain.equals("INFJ", ignoreCase = true)) ||
        (mbtiMu.equals("ISTP", ignoreCase = true) && mbtiLain.equals("ESTP", ignoreCase = true)) ||
        (mbtiMu.equals("ESTP", ignoreCase = true) && mbtiLain.equals("ISTP", ignoreCase = true)) ||
        (mbtiMu.equals("ISFJ", ignoreCase = true) && mbtiLain.equals("ESTP", ignoreCase = true)) ||
        (mbtiMu.equals("INTP", ignoreCase = true) && mbtiLain.equals("ENTP", ignoreCase = true)) ||
        (mbtiMu.equals("ENTP", ignoreCase = true) && mbtiLain.equals("INTP", ignoreCase = true)) ||
        (mbtiMu.equals("ESTJ", ignoreCase = true) && mbtiLain.equals("ISTJ", ignoreCase = true)) ||
        (mbtiMu.equals("ISTJ", ignoreCase = true) && mbtiLain.equals("ESTJ", ignoreCase = true)) ||
        (mbtiMu.equals("INFP", ignoreCase = true) && mbtiLain.equals("ENFP", ignoreCase = true)) ||
        (mbtiMu.equals("ENFP", ignoreCase = true) && mbtiLain.equals("INFP", ignoreCase = true)) ||
        (mbtiMu.equals("ENFJ", ignoreCase = true) && mbtiLain.equals("INFJ", ignoreCase = true)) ||
        (mbtiMu.equals("INFJ", ignoreCase = true) && mbtiLain.equals("ENFJ", ignoreCase = true)) ||
        (mbtiMu.equals("ESFJ", ignoreCase = true) && mbtiLain.equals("ISFJ", ignoreCase = true)) ||
        (mbtiMu.equals("ISFJ", ignoreCase = true) && mbtiLain.equals("ESFJ", ignoreCase = true)) ||
        (mbtiMu.equals("ISFP", ignoreCase = true) && mbtiLain.equals("ESFP", ignoreCase = true)) ||
        (mbtiMu.equals("ESFP", ignoreCase = true) && mbtiLain.equals("ISFP", ignoreCase = true))

        ) {
        "Cocok"
    } else {
        "Kurang cocok"
    }
}

private  fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}




@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    Assesmen01Theme {
        MainScreen(rememberNavController())
    }
}
