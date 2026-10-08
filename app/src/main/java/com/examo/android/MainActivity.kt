package com.examo.android
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.examo.android.ui.ExamoApp
import com.examo.android.ui.theme.ExamoTheme
import com.examo.android.viewmodel.ExamoViewModel

class MainActivity:ComponentActivity(){
 override fun onCreate(savedInstanceState:Bundle?){
  super.onCreate(savedInstanceState)
  setContent{ExamoTheme{ExamoApp(viewModel())}}
 }
}
