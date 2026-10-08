package com.examo.android.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
@Composable fun LoginScreen(loading:Boolean,error:String?,login:(String,String)->Unit,register:(String,String,String,String)->Unit){
 var signup by remember{mutableStateOf(false)};var name by remember{mutableStateOf("")};var mobile by remember{mutableStateOf("")};var email by remember{mutableStateOf("")};var pass by remember{mutableStateOf("")}
 Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),verticalArrangement=Arrangement.Center){
  Text("Examo",style=MaterialTheme.typography.displaySmall,color=MaterialTheme.colorScheme.primary);Text(if(signup)"Create your account" else "Welcome back",style=MaterialTheme.typography.headlineSmall);Spacer(Modifier.height(24.dp))
  if(signup){Field("Full Name",name){name=it};Spacer(Modifier.height(10.dp));Field("Mobile Number",mobile){mobile=it};Spacer(Modifier.height(10.dp))}
  Field("Email",email){email=it};Spacer(Modifier.height(10.dp));OutlinedTextField(pass,{pass=it},Modifier.fillMaxWidth(),label={Text("Password")},visualTransformation=PasswordVisualTransformation())
  error?.let{Spacer(Modifier.height(10.dp));Text(it,color=MaterialTheme.colorScheme.error)};Spacer(Modifier.height(18.dp))
  Button(enabled=!loading,onClick={if(signup)register(name,mobile,email,pass)else login(email,pass)},Modifier.fillMaxWidth()){Text(if(loading)"Please wait..." else if(signup)"Create Account" else "Login")}
  TextButton({signup=!signup}){Text(if(signup)"Already have an account? Login" else "Create new account")}
 }
}
@Composable private fun Field(label:String,value:String,onChange:(String)->Unit){OutlinedTextField(value,onChange,Modifier.fillMaxWidth(),label={Text(label)},singleLine=true)}
