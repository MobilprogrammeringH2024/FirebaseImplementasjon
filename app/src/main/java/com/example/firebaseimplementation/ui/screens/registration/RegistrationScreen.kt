package com.example.firebaseimplementation.ui.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firebaseimplementation.R
import com.example.firebaseimplementation.ui.navigation.AppScreens


@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.register_new_user),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 200.dp)
        )

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(2f))

            EmailField(uiState.email, viewModel::onEmailChange)
            Spacer(modifier = Modifier.height(20.dp))
            ConfirmEmailField(uiState.confirmEmail, viewModel::onConfirmEmailChange)

            Spacer(modifier = Modifier.height(20.dp))

            PasswordField(uiState.password, viewModel::onPasswordChange)
            Spacer(modifier = Modifier.height(20.dp))
            ConfirmPasswordField(uiState.confirmPassword, viewModel::onConfirmPasswordChange)

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                CancelButton(navController)

                Spacer(modifier = Modifier.width(20.dp))

                RegisterButton(navController, viewModel)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Spacer(modifier = Modifier.weight(0.3f))
        }
    }
}

@Composable
fun EmailField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(0.9f)
) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = {
            onNewValue(it)
        } ,
        placeholder = { Text(stringResource(R.string.email)) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email,
                contentDescription = stringResource(R.string.email)
            )
        }
    )
}

@Composable
fun ConfirmEmailField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(0.9f)
) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(R.string.confirm_email)) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email,
                contentDescription = stringResource(R.string.confirm_email)
            )
        }
    )
}

@Composable
fun PasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(0.9f)
) {
    var isVisible by remember { mutableStateOf(true) }
    var isVisibleToggled by remember { mutableStateOf(false) }

    val icon = if (isVisible) painterResource(R.drawable.ic_visibility_on) else painterResource(R.drawable.ic_visibility_off)
    val visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange ={
            onNewValue(it)
            if (!isVisibleToggled) isVisible = it == ""
        }               ,
        placeholder = { Text(stringResource(R.string.password)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = stringResource(R.string.password)) },
        trailingIcon = {
            IconButton(onClick = {
                isVisible = !isVisible
                if (!isVisibleToggled) isVisibleToggled = true
            }) {
                Icon(painter = icon, contentDescription = if (isVisible) "Hide password" else "Show password")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation
    )
}

@Composable
fun ConfirmPasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(0.9f)
) {
    var isVisible by remember { mutableStateOf(true) }
    var isVisibleToggled by remember { mutableStateOf(false) }

    val icon = if (isVisible) painterResource(R.drawable.ic_visibility_on) else painterResource(R.drawable.ic_visibility_off)
    val visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = {
            onNewValue(it)
            if (!isVisibleToggled) isVisible = it == ""
        },
        placeholder = { Text(stringResource(R.string.confirm_password)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = stringResource(R.string.confirm_password)) },
        trailingIcon = {
            IconButton(onClick = {
                isVisible = !isVisible
                if (!isVisibleToggled) isVisibleToggled = true
            }) {
                Icon(painter = icon, contentDescription = if (isVisible) "Hide password" else "Show password")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation
    )
}

@Composable
fun CancelButton(navController: NavController) {
    Button(
        onClick = { navController.navigate(AppScreens.LOGIN_SELECTION.name) },
        modifier = Modifier.width(150.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(stringResource(R.string.cancel))
    }
}

@Composable
fun RegisterButton(navController: NavController, viewModel: RegistrationViewModel) {
    Button(
        onClick = { viewModel.onSignUpClick(navController) },
        modifier = Modifier.width(150.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(stringResource(R.string.register))
    }
}