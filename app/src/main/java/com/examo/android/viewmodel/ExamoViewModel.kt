package com.examo.android.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examo.android.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
data class AppState(val loading:Boolean=false,val error:String?=null,val loggedIn:Boolean=false,val user:AuthUser?=null,val exams:List<Exam>=emptyList(),val forms:List<ExamForm>=emptyList(),val preparations:List<Preparation>=emptyList(),val subjects:List<Subject>=emptyList(),val schedules:List<Schedule>=emptyList())
class ExamoViewModel:ViewModel(){
 private val _state=MutableStateFlow(AppState());val state:StateFlow<AppState>=_state
 fun login(id:String,p:String)=viewModelScope.launch{_state.value=_state.value.copy(loading=true,error=null);runCatching{ApiClient.api.login(LoginRequest(id.trim(),p))}.onSuccess{r->Session.token=r.token;Session.user=r.user;_state.value=_state.value.copy(loading=false,loggedIn=true,user=r.user);loadData()}.onFailure{e->_state.value=_state.value.copy(loading=false,error=e.message?:"Login failed")}}
 fun register(n:String,m:String,e:String,p:String)=viewModelScope.launch{_state.value=_state.value.copy(loading=true,error=null);runCatching{ApiClient.api.register(RegisterRequest(n.trim(),m.trim(),e.trim(),p))}.onSuccess{r->Session.token=r.token;Session.user=r.user;_state.value=_state.value.copy(loading=false,loggedIn=true,user=r.user);loadData()}.onFailure{x->_state.value=_state.value.copy(loading=false,error=x.message?:"Registration failed")}}
 fun loadData()=viewModelScope.launch{try{val e=ApiClient.api.exams();val f=ApiClient.api.forms();val p=ApiClient.api.preparations();val s=ApiClient.api.subjects();val sc=ApiClient.api.schedules();_state.value=_state.value.copy(loading=false,exams=e,forms=f,preparations=p,subjects=s,schedules=sc)}catch(e:Exception){_state.value=_state.value.copy(loading=false,error=e.message?:"Could not load data")}}
 fun logout(){Session.token=null;Session.user=null;_state.value=AppState()}
}
