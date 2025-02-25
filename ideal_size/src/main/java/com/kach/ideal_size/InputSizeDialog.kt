package com.kach.ideal_size

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun IdealSizeDialog(
    onDismiss: () -> Unit,
    onOkClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var result by rememberSaveable { mutableStateOf<Size?>(null) }

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Card(
            modifier = modifier
        ) {
            if (result == null) {
                Input { size ->
                    result = size
                }
            } else {
                result?.let { result ->
                    Output(
                        size = result,
                        onOkClicked = onOkClicked,
                    )
                }
            }
        }
    }
}

@Composable
fun Input(
    modifier: Modifier = Modifier,
    onGetSizeRecommendationClicked: (Size) -> Unit,
) {
    var height by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.Find_Your_Perfect_Fit),
            style = TextStyle.Default.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.Height_colon),
                style = TextStyle.Default,
            )

            TextField(
                value = height,
                onValueChange = { height = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .widthIn(1.dp, Dp.Infinity)
            )

            Text(
                text = stringResource(R.string.cm),
                style = TextStyle.Default,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.Weight_colon),
                style = TextStyle.Default,
            )

            TextField(
                value = weight,
                onValueChange = { weight = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .widthIn(1.dp, Dp.Infinity)
            )

            Text(
                text = stringResource(R.string.kg),
                style = TextStyle.Default,
            )
        }

        Button(
            onClick = {
                processSizeRecommendation(height, weight)?.let { size ->
                    onGetSizeRecommendationClicked.invoke(size)
                }
            },
            modifier = Modifier
                .padding(top = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.Get_Size_Recommendation)
            )
        }
    }
}

@Composable
fun Output(
    size: Size,
    onOkClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.Your_Recommended_Size_size, size.name),
            style = TextStyle.Default.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            text = stringResource(R.string.Based_on_your_info_message, size.name),
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 24.dp)
        )

        Button(
            onClick = onOkClicked,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.OK)
            )
        }
    }
}

internal fun String.isNotFloatNumber(): Boolean {
    return try {
        this.toFloat()
        false
    } catch (ex: NumberFormatException) {
        true
    }
}

@Preview(showBackground = true)
@Composable
private fun InputSizeDialogPreview() {
    IdealSizeDialog(
        onDismiss = { },
        onOkClicked = { }
    )
}