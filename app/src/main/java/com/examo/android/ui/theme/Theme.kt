package com.examo.android.ui.theme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val Colors=lightColorScheme(primary=Color(0xFF2563EB),background=Color(0xFFF8FAFC),surface=Color.White)
@Composable fun ExamoTheme(content:@Composable()->Unit){MaterialTheme(colorScheme=Colors,content=content)}
