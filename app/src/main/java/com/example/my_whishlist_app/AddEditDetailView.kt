package com.example.my_whishlist_app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.my_whishlist_app.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id:Long,
    viewModel:wishViewModel,
    navController: NavController
) {
    val snackmessage = remember{
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if(id!=0L){
        val wish =viewModel.getAWishbyId(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.WishTitleState =wish.value.title
        viewModel.WishDescriptionState =wish.value.description
    }
    else{
        viewModel.WishTitleState =""
        viewModel.WishDescriptionState =""
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
        AppBarView(title =
        if (id != 0L) stringResource(id = R.string.Update_Wish )
        else stringResource(id = R.string.Add_Wish )
        ) { navController.navigateUp() }
    }) {
        Column(modifier= Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            Spacer(modifier = Modifier.heightIn(10.dp))
            WishTextField(label = "Wish",
                value = viewModel.WishTitleState,
                onValueChanged ={viewModel.onTitleChanged(it)}
            )
            Spacer(modifier = Modifier.heightIn(10.dp))
            WishTextField(label = "Description",
                value = viewModel.WishDescriptionState,
                onValueChanged ={viewModel.onDescriptionChanged(it)}
            )
            Spacer(modifier = Modifier.heightIn(10.dp))
            Button(onClick = {
                if(viewModel.WishTitleState.isNotEmpty() && viewModel.WishDescriptionState.isNotEmpty()){
                    if(id!=0L){
                        viewModel.updateWish(
                            Wish(
                                id=id,
                                title= viewModel.WishTitleState.trim(),
                                description = viewModel.WishDescriptionState.trim()
                            )
                        )
                        snackmessage.value="Your Wish has been Updated"
                    }
                    else{
                        viewModel.addWish(
                            Wish(
                                title = viewModel.WishTitleState.trim(),
                                description = viewModel.WishDescriptionState.trim()
                            )
                        )
                        snackmessage.value="Your Wish has been created"
                    }

                }
                else{
                    snackmessage.value="Enter Field to create a Wish"
                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackmessage.value)
                    navController.navigateUp()
                }
            }) {
                Text(
                    text = if (id!=0L) stringResource(id = R.string.Update_Wish)
                        else stringResource(id =R.string.Add_Wish
                        ),
                    style= TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}
@Composable
fun WishTextField(
    label:String,
    value:String,
    onValueChanged:(String)->Unit
){
    OutlinedTextField(value = value,
        onValueChange = onValueChanged,
        label={Text(text=label,color= Color.Black)},
        modifier=Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            //Sytem defined
            textColor = Color.Black,
            //User Defined
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )
        )
}

@Preview
@Composable
fun WishTextfieldprev(){
    WishTextField(label = "text", value ="text" , onValueChanged ={} )
    //AddEditDetailView(id = 0L, viewModel = viewModel())
}