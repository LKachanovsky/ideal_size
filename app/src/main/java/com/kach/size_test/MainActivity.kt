package com.kach.size_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kach.ideal_size.IdealSizeDialog
import com.kach.size_test.ui.theme.SizetestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SizetestTheme {
                Content()
            }
        }
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier
) {
    var doShowDialog by rememberSaveable { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(onClick = { doShowDialog = true }) {
            Text("Find your perfect fit")
        }
    }

    if (doShowDialog) {
        IdealSizeDialog(
            onDismiss = { doShowDialog = false },
            onOkClicked = { doShowDialog = false },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SizePreview() {
    Content()
}