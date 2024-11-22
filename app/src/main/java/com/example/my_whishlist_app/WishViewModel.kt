package com.example.my_whishlist_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_whishlist_app.data.Wish
import com.example.my_whishlist_app.data.Wishrespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//view Modal takes care communication between data and UI
//communication as folows viewmodel-> wishrepository-> wishdao-> wish database
class wishViewModel(private val wishrespository: Wishrespository = Graph.wishRepository):ViewModel(){

    var WishTitleState by mutableStateOf("")
    var WishDescriptionState by mutableStateOf("")

    fun onTitleChanged(newstring:String){
        WishTitleState = newstring
    }
    fun onDescriptionChanged(newstring:String){
        WishDescriptionState = newstring
    }

    lateinit var getAllwishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllwishes= wishrespository.getWhishes()
        }
    }
    fun addWish(wish:Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishrespository.addAWish(wish=wish)
        }
    }
    fun getAWishbyId(id:Long):Flow<Wish>{

        return  wishrespository.getAwishById(id)

    }
    fun updateWish(wish:Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishrespository.updateAWish(wish=wish)
        }
    }
    fun deleteWish(wish:Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishrespository.deleteAWish(wish=wish)
        }
    }
}