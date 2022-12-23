package com.example.a7minuteworkout.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a7minuteworkout.database.HistoryDao
import com.example.a7minuteworkout.database.HistoryEntity
import com.example.a7minuteworkout.database.WorkoutApp
import com.example.a7minuteworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class activity_finish : AppCompatActivity() {


    private var binding:ActivityFinishBinding?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)





        setSupportActionBar(binding?.toolbarFinishActivity)
        if (supportActionBar!=null)
        {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }


        binding?.btnFinish?.setOnClickListener {
            finish()
        }

        val dao=(application as WorkoutApp).db.historyDao()
        addDateToDatabase(dao)


    }

    private fun addDateToDatabase(historyDao: HistoryDao){

        val c=Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date: ",""+dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())
        val date=sdf.format(dateTime)
        Log.e("Formatted Date: ",""+date)

        GlobalScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.e("Date :",

                "Added")
       }
   }

}