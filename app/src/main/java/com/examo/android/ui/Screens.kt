package com.examo.android.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.examo.android.data.*
import com.examo.android.viewmodel.AppState
@Composable fun DashboardScreen(s:AppState){LazyColumn(Modifier.fillMaxSize().padding(16.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){
 item{Text("Hello, ${s.user?.fullName ?: "Student"}!",style=MaterialTheme.typography.headlineMedium);Text("Your exam preparation at a glance.")}
 item{Stat("Upcoming Exams",s.exams.count{it.status=="Coming Soon"}.toString())};item{Stat("Subjects",s.subjects.size.toString())};item{Stat("Study Sessions",s.schedules.size.toString())}
 item{Stat("Preparation",if(s.preparations.isEmpty())"0%" else "${s.preparations.count{it.status=="Completed"}*100/s.preparations.size}%")};item{Text("Upcoming Exams",style=MaterialTheme.typography.titleLarge)}
 items(s.exams.take(4)){e->CardItem(e.examName,e.examDate?:"Date not announced",e.status)}
}}
@Composable fun Stat(t:String,v:String){Card(Modifier.fillMaxWidth()){Column(Modifier.padding(18.dp)){Text(t);Text(v,style=MaterialTheme.typography.headlineMedium,color=MaterialTheme.colorScheme.primary)}}}
@Composable fun ExamsScreen(exams:List<Exam>,forms:List<ExamForm>){LazyColumn(Modifier.fillMaxSize().padding(16.dp),verticalArrangement=Arrangement.spacedBy(10.dp)){item{Text("Exams",style=MaterialTheme.typography.headlineMedium)};items(exams){e->CardItem(e.examName,e.examDate?:"Date not announced",e.status)};item{Text("Exam Forms",style=MaterialTheme.typography.headlineMedium)};items(forms){f->CardItem(f.examName,"Registration: ${f.registerStartDate} - ${f.registerEndDate}",f.status)}}}
@Composable fun SubjectsScreen(items:List<Subject>){LazyColumn(Modifier.fillMaxSize().padding(16.dp),verticalArrangement=Arrangement.spacedBy(10.dp)){item{Text("Subjects",style=MaterialTheme.typography.headlineMedium)};items(items){x->Card(Modifier.fillMaxWidth()){Column(Modifier.padding(16.dp)){Text(x.subjectName,style=MaterialTheme.typography.titleLarge);Text("Status: ${x.status}");Text("Lectures: ${x.lectures}");x.materials?.let{Text("Materials: $it")}}}}}}
@Composable fun ScheduleScreen(items:List<Schedule>){LazyColumn(Modifier.fillMaxSize().padding(16.dp),verticalArrangement=Arrangement.spacedBy(10.dp)){item{Text("Study Schedule",style=MaterialTheme.typography.headlineMedium)};items(items){x->CardItem(x.subject,"${x.scheduleDate}  ${x.startTime}${x.endTime?.let{" - $it"}?:""}",x.description?:"Study session")}}}
@Composable fun CardItem(t:String,sub:String,status:String){Card(Modifier.fillMaxWidth()){Column(Modifier.padding(16.dp)){Text(t,style=MaterialTheme.typography.titleMedium);Text(sub);Spacer(Modifier.height(6.dp));AssistChip(onClick={},label={Text(status)})}}}
