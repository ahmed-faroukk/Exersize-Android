package com.farouk.exersize.features.inbody.presentation.steper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.farouk.exersize.features.inbody.presentation.composables.InBodyErrorDialog
import com.farouk.exersize.features.inbody.presentation.screens.Gender
import com.farouk.exersize.features.inbody.presentation.screens.GenderScreen
import com.farouk.exersize.theme.ExersizeTheme
import com.farouk.exersize.theme.darkYellow

data class PersonalData(val tall: String, val weight: String, val age: String)

@Composable
fun InBodyStepper(){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val numberStep = 4
        var currentStep by rememberSaveable { mutableStateOf(1) }
        val titleList= arrayListOf("Gender","Personal Data","InBody","Profile Pic")
        val  userGender = remember {
            mutableStateOf(Gender.Unspecified)
        }
        val userPersonalData  = remember {
             mutableStateOf(PersonalData("", "", ""))
        }
        val userInBodyPdf = remember {
            mutableStateOf("")
        }
        val userProfilePic = remember {
            mutableStateOf("")
        }
        val allowToMove = remember {
            mutableStateOf(false)
        }
        val showDialog = remember {
        mutableStateOf(false)
    }


        Stepper(
            modifier = Modifier.fillMaxWidth(),
            numberOfSteps = numberStep,
            currentStep = currentStep,
            stepDescriptionList = titleList,
            unSelectedColor= darkYellow,
            selectedColor = MaterialTheme.colorScheme.surface,
        )
        if (showDialog.value)
        InBodyErrorDialog(title = "Can't go Next" , desc = "Please Fill Your Data First") {
            showDialog.value = false
        }

        if (currentStep == 1){
            GenderScreen(userGender)
            if (userGender.value == Gender.Unspecified)
                allowToMove.value = false
            else
                allowToMove.value = true
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween ,
            modifier = Modifier.fillMaxWidth()) {

            Button(
                onClick = { if (currentStep >1) currentStep-- },
                enabled = currentStep >1 ) {
                Text(text = "previous")
            }

            Button(
                onClick = { if (currentStep < numberStep )
                    when{
                        allowToMove.value -> {
                            currentStep++
                        }
                        !allowToMove.value ->{
                            showDialog.value = true
                        }
                    }
                          } ,
                enabled = currentStep < numberStep) {
                Text(text = "next")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExersizeTheme {
        InBodyStepper()
    }
}