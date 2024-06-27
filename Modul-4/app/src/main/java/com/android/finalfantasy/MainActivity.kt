 package com.android.finalfantasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar


 class MainActivity : AppCompatActivity(){
     private val viewModel: MainViewModel by lazy {
         ViewModelProvider(this).get(MainViewModel::class.java)
     }
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         viewModel.tripleTriadLiveData.observe(this, { state ->
             processTripleTriadResponse(state)
         })

         val areaClick =findViewById<View>(R.id.recylerView) as RecyclerView
         areaClick.setOnClickListener {
             val intent = Intent(this, TripleTriadDetail::class.java)
             intent.putExtra("OBJECT_INTENT", 1)
             startActivity(intent)
         }
     }

     private fun processTripleTriadResponse(state: ScreenState<List<TripleTriadData>?>){

         val progressBar = findViewById<ProgressBar>(R.id.progressBar)

         when(state){
             is ScreenState.Loading -> {
                 progressBar.visibility = View.VISIBLE
             }
             is ScreenState.Success -> {
                 progressBar.visibility = View.GONE
                 if(state.data != null){
                     val adapter = MainAdapter(state.data)
                     val recyclerView = findViewById<RecyclerView>(R.id.recylerView)

                     recyclerView?.layoutManager =
                         StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                     recyclerView?.adapter = adapter
                 }
             }
             is ScreenState.Error -> {
                     progressBar.visibility = View.GONE
                     val view = progressBar.rootView
                     Snackbar.make(view, state.message!!, Snackbar.LENGTH_LONG).show()
             }
         }
     }
 }
