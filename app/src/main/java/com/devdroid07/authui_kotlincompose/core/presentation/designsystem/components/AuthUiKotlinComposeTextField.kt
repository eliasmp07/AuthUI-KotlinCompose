package com.devdroid07.authui_kotlincompose.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devdroid07.authui_kotlincompose.R
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.AuthUIKotlinComposeTheme
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.EmailIcon
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.EyeClosedIcon
import com.devdroid07.authui_kotlincompose.core.presentation.designsystem.theme.EyeOpenedIcon

/**
 * Campo de texto personalizable con soporte para contraseñas y manejo de errores.
 *
 * @param modifier Modificador para ajustar el diseño del campo de texto.
 * @param value El texto actual en el campo.
 * @param title Título que se muestra sobre el campo de texto (opcional).
 * @param onValueChange Función que se ejecuta cuando el texto cambia.
 * @param placeholder Texto que se muestra cuando el campo está vacío.
 * @param contentDescription Descripción para accesibilidad.
 * @param errorMessage Mensaje de error que se muestra debajo del campo (opcional).
 * @param leadingIcon Icono que se muestra al principio del campo de texto (opcional).
 * @param isPassword Indica si el campo es para contraseñas.
 * @param isError Indica si hay un error en el campo.
 * @param isEnabled Indica si el campo está habilitado.
 * @param keyboardOptions Opciones del teclado.
 * @param keyboardActions Acciones del teclado.
 */
@Composable
fun AuthUiKotlinComposeTextField(
    modifier: Modifier = Modifier,
    value: String,
    title: String? = null,
    onValueChange: (String) -> Unit,
    placeholder: String,
    contentDescription: String,
    errorMessage: String? = null,
    leadingIcon: ImageVector? = null,
    isPassword: Boolean = false,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    var hidePassword by remember {
        mutableStateOf(true)
    }
    Column {
        if (title != null){
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            enabled = isEnabled,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.8f
                ),
                unfocusedContainerColor = Color(0xFFF7F5F5),
                focusedContainerColor = Color(0xFFF7F5F5)
            ),
            isError = isError,
            visualTransformation = if (isPassword && hidePassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            leadingIcon = {
                if (leadingIcon != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = contentDescription,
                            tint = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        VerticalDivider(
                            color = if (isError) MaterialTheme.colorScheme.error else Color.Gray,
                            modifier = Modifier.height(30.dp)
                        )
                    }
                }
            },
            trailingIcon = {
                if (isPassword) {
                    IconButton(
                        onClick = { hidePassword = !hidePassword }
                    ) {
                        Icon(
                            imageVector = if (hidePassword) EyeOpenedIcon else EyeClosedIcon,
                            contentDescription = if(hidePassword) {
                                stringResource(id = R.string.show_password)
                            } else {
                                stringResource(id = R.string.hide_password)
                            }
                        )
                    }
                }
            }
        )
        if (errorMessage != null) {
            Text(
                modifier = Modifier.align(Alignment.End),
                text = errorMessage,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }
    }
}

@Preview
@Composable
private fun AuthUiKotlinComposeTextFieldPreview() {

    AuthUIKotlinComposeTheme {
        AuthUiKotlinComposeTextField(
            value = "",
            onValueChange = {

            },
            leadingIcon = EmailIcon,
            placeholder = "Ingresar el correo electronico",
            contentDescription = ""
        )
    }
}