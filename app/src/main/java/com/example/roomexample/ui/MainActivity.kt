package com.example.roomexample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomexample.R
import com.example.roomexample.data.local.Data
import com.example.roomexample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

/*
Try to keep your activities as dumb as possible, do as much as you can in the View Model.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var myAdapter: MyAdapter
    private var recyclerViewCreated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.listenToDbData()

        insertButton.setOnClickListener {
            val data = Data(0, editText.text.toString())
            viewModel.insert(data)
        }

        deleteButton.setOnClickListener { viewModel.deleteAllData() }

        viewModel.dbData.observe(this, Observer { dataStrings ->
            if (!recyclerViewCreated) initRecyclerView(dataStrings) else updateRecyclerView(dataStrings)
        })
    }

    private fun updateRecyclerView(dataStrings: List<Data>) {
        myAdapter.updateData(dataStrings)
        myAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView(dataStrings: List<Data>) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MyAdapter(dataStrings).also {
                myAdapter = it
            }
        }
        recyclerViewCreated = true
    }
}