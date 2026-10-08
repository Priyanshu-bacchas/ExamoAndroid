package com.examo.android.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.examo.android.viewmodel.ExamoViewModel
@Composable fun ExamoApp(vm:ExamoViewModel){
 val s by vm.state.collectAsState();var page by remember{mutableStateOf("Home")}
 if(!s.loggedIn){LoginScreen(s.loading,s.error,vm::login,vm::register);return}
 Scaffold(topBar={TopAppBar(title={Text("Examo")},actions={IconButton({vm.logout()}){Icon(Icons.Default.Logout,"Logout")}})},bottomBar={NavigationBar{
  listOf("Home" to Icons.Default.Home,"Exams" to Icons.Default.Event,"Subjects" to Icons.Default.MenuBook,"Schedule" to Icons.Default.CalendarMonth).forEach{(n,i)->NavigationBarItem(page==n,{page=n},{Icon(i,n)},label={Text(n)})}
 }}){p->Box(Modifier.padding(p).fillMaxSize()){when(page){"Home"->DashboardScreen(s);"Exams"->ExamsScreen(s.exams,s.forms);"Subjects"->SubjectsScreen(s.subjects);else->ScheduleScreen(s.schedules)}}}
}
