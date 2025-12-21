package io.github.vihanmy.pgyappupload.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UIInputArea(
    hint: String,
    input: String,
    hintInner: String = "",
    onInputChanged: (String) -> Unit
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BasicTextField(
            textStyle = MaterialTheme.typography.body1,
            value = input,
            onValueChange = {
                onInputChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .border(1.dp, MaterialTheme.colors.onBackground.copy(0.1f), RoundedCornerShape(8.dp))
                .padding(10.dp)

        )

        if (input.isEmpty()) {
            Text(
                hintInner,
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.typography.body1.color.copy(0.4f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .border(1.dp, MaterialTheme.colors.onBackground.copy(0.1f), RoundedCornerShape(8.dp))
                    .padding(10.dp)

            )
        }

        Text(
            hint,
            style = MaterialTheme.typography.overline.copy(
                color = MaterialTheme.colors.onBackground.copy(0.6f),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            ),
            modifier = Modifier.padding(start = 10.dp).background(color = MaterialTheme.colors.background)
        )
    }
}