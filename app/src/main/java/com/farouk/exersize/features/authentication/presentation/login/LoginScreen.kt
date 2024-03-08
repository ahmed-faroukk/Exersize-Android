package com.farouk.exersize.features.authentication.presentation.login


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.farouk.exersize.R
import com.farouk.exersize.features.authentication.domain.entity.login.UserLoginModel
import com.farouk.exersize.features.authentication.presentation.AuthViewModel
import com.farouk.exersize.features.authentication.presentation.CodeVerfication.CodeVerificationScreen
import com.farouk.exersize.features.authentication.presentation.components.AuthLoginText
import com.farouk.exersize.features.authentication.presentation.components.AuthText
import com.farouk.exersize.features.authentication.presentation.components.ErrorDialog
import com.farouk.exersize.features.authentication.presentation.components.LoadingDialog
import com.farouk.exersize.features.authentication.presentation.components.OutLineTextFieldNumber
import com.farouk.exersize.features.authentication.presentation.components.OutLineTextFieldString
import com.farouk.exersize.features.authentication.presentation.components.RoundedBtn
import com.farouk.exersize.features.authentication.presentation.login.LoginScreen
import com.farouk.exersize.features.authentication.presentation.signup.SignupScreen
import com.farouk.exersize.theme.blue3
import com.farouk.exersize.theme.darkYellow

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: AuthViewModel = getViewModel()
        Login(viewModel)
    }

    @Composable
    fun Login(viewModel: AuthViewModel) {
        val navigator = LocalNavigator.currentOrThrow
        val errorDialog = remember { mutableStateOf(true) }

        val phone = remember {
            mutableStateOf("")
        }
        val state = viewModel.loginState.value


        if (state.isLoading) {
            LoadingDialog(onDismissRequest = {})
            errorDialog.value = true
        }


        if (state.error.isNotEmpty()) {
            if (errorDialog.value)
                ErrorDialog(onDismissRequest = {
                    errorDialog.value = false
                }, title = "Server Error", desc = state.error)
        }


        if (state.data?.data == true) {
            navigator.push(CodeVerificationScreen(phone.value))
        }



        Column(
            Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(Modifier.fillMaxWidth()) {
                item {
                    AuthText(
                        text = stringResource(R.string.welcome_back),
                        sizeWithSp = 24,
                    )
                    AuthText(
                        text = stringResource(R.string.enter_your_mobile_number),
                        sizeWithSp = 16,
                        color = blue3,
                        fontWeight = FontWeight.Thin
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Spacer(modifier = Modifier.height(10.dp))

                    AuthText(text = stringResource(R.string.mobile_number), sizeWithSp = 16)
                    OutLineTextFieldNumber(
                        onNameChange = { phone.value = it },
                        label = stringResource(R.string.enter_your_phone_number),
                    )
                    Spacer(modifier = Modifier.height(50.dp))

                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    RoundedBtn(
                        onClick = {
                                  viewModel.login(UserLoginModel(phone = phone.value))
                        },
                        text = stringResource(id = R.string.login)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        AuthText(
                            text = stringResource(R.string.don_t_have_account),
                            sizeWithSp = 14,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.surface
                        )
                        AuthLoginText(
                            text = stringResource(R.string.signup),
                            sizeWithSp = 14,
                            color = darkYellow,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.clickable {
                                navigator.replace(SignupScreen())
                            })
                    }
                }


            }
        }
    }
}
