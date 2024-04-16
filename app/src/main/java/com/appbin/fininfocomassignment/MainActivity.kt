package com.appbin.fininfocomassignment

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.appbin.fininfocomassignment.database.UserInfo
import com.appbin.fininfocomassignment.repository.Repository
import com.appbin.fininfocomassignment.ui.home.UserData
import com.appbin.fininfocomassignment.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    val arrayName = ArrayList<String>()
    val arrayAge = ArrayList<String>()
    val arrayCity = ArrayList<String>()

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setData()


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.mobile_navigation)

        navController.graph = navGraph

        sharedPreferences = getSharedPreferences(Constants.spFile,0)
        editor = sharedPreferences.edit()

        if (sharedPreferences.getBoolean(Constants.isLogin,false)){
            navGraph.setStartDestination(R.id.home2)
            navHostFragment.navController.graph = navGraph
        }


        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                for (i:Int in 0 until arrayName.size){
                    val userData= UserInfo(i,arrayName.get(i),arrayAge.get(i),arrayCity.get(i))
                    Repository().insertData(userData)
                }

            }

        }



    }

    private fun setData() {
        arrayName.add("Jacob")
        arrayName.add("Chanchal")
        arrayName.add("Brenda")
        arrayName.add("Daksh")
        arrayName.add("Ashish")
        arrayName.add("Girish")


        arrayAge.add("35")
        arrayAge.add("30")
        arrayAge.add("15")
        arrayAge.add("25")
        arrayAge.add("7")
        arrayAge.add("90")
        arrayAge.add("54")

        arrayCity.add("Delhi")
        arrayCity.add("Mumbai")
        arrayCity.add("Hyderabad")
        arrayCity.add("Bengaluru")
        arrayCity.add("Jaipur")
        arrayCity.add("Sikkim")
    }
}