package com.farouk.exersize.features.authentication.presentation.signup

import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.farouk.exersize.R
import com.farouk.exersize.features.authentication.domain.entity.signup.UserSignupModel
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
import com.farouk.exersize.features.splash.presentaiton.SplashViewModel
import com.farouk.exersize.theme.blue3
import com.farouk.exersize.theme.darkYellow

class SignupScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel : AuthViewModel = getViewModel()
        Signup(viewModel)
    }

}
@Composable
fun Signup(viewModel: AuthViewModel ) {

    val errorDialog = remember { mutableStateOf(true) }
    val navigator = LocalNavigator.currentOrThrow

    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }
    val state = viewModel.signUpState.value


    if (state.isLoading) {
        LoadingDialog(onDismissRequest = {})
        errorDialog.value = true
    }


    if (state.error.isNotEmpty()) {
        if (errorDialog.value)
            ErrorDialog(onDismissRequest = {
                errorDialog.value = false
            } , title = "Server Error" , desc = state.error)
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
                    text = stringResource(id = R.string.create_an_account),
                    sizeWithSp = 24,
                )
                AuthText(
                    text = stringResource(id = R.string.enter_your_details_to_continue),
                    sizeWithSp = 16,
                    color = blue3,
                    fontWeight = FontWeight.Thin
                )
                Spacer(modifier = Modifier.height(40.dp))
                AuthText(text = stringResource(R.string.first_name), sizeWithSp = 16)
                OutLineTextFieldString(
                    onNameChange = { firstName.value = it },
                    label = stringResource(R.string.enter_your_first_name)
                )
                Spacer(modifier = Modifier.height(10.dp))

                AuthText(text = stringResource(R.string.last_name), sizeWithSp = 16)
                OutLineTextFieldString(
                    onNameChange = { lastName.value = it },
                    label = stringResource(R.string.enter_your_last_name)
                )
                Spacer(modifier = Modifier.height(10.dp))

                AuthText(text = stringResource(id = R.string.mobile_number), sizeWithSp = 16)
                OutLineTextFieldNumber(
                    onNameChange = { phone.value = it },
                    label = stringResource(id = R.string.enter_your_phone_number),
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                RoundedBtn(
                    onClick = {
                        val data : UserSignupModel = UserSignupModel(phone = phone.value , fname = firstName.value , lname = lastName.value)
                        viewModel.createNewAccount(data)
                        Log.d("data  = " , data.toString())
                    },
                    text = "Create an account"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    AuthText(
                        text = stringResource(id = R.string.already_a_member),
                        sizeWithSp = 14,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.surface
                    )
                    AuthLoginText(
                        text = stringResource(R.string.login),
                        sizeWithSp = 14,
                        color = darkYellow,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.clickable {
                            navigator.replace(LoginScreen())
                        })
                }
            }


        }
    }
}