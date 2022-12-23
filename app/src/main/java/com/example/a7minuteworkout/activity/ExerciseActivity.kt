package com.example.a7minuteworkout.activity


import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.model.Constants
import com.example.a7minuteworkout.model.ExerciseModel
import com.example.a7minuteworkout.adapters.ExerciseStatusAdapter
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.databinding.ActivityExerciseBinding
import com.example.a7minuteworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList


class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {


    private var binding : ActivityExerciseBinding?=null
    private var restTimer: CountDownTimer? = null
    private var restProgress= 0
    private var restTimerDuration:Long = 1
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress= 0
    private var exerciseTimerDuration:Long = 1
    private var exerciseList : ArrayList<ExerciseModel>?=null
    private var currentExercisePosition = -1

    private var tts:TextToSpeech?=null
    private var player:MediaPlayer?=null

    private var exerciseAdapter : ExerciseStatusAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {

        binding=ActivityExerciseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolBarExercise)
        if (supportActionBar!=null)
        {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.defaultExerciseList()
        tts= TextToSpeech(this,this)

        binding?.toolBarExercise?.setNavigationOnClickListener {
            customDialogForBackButton()
        }

        setupRestView()
       setupExerciseStatusRecyclerView()
    }

    override fun onBackPressed() {
        customDialogForBackButton()

    }

    private fun customDialogForBackButton(){
        val customDialog=Dialog(this)
        val dialogBinding=DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.btnNo.setOnClickListener {
           customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun setupExerciseStatusRecyclerView(){
        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter=exerciseAdapter
    }

    private fun setupRestView(){

        try {
            val soundURI = Uri.parse(
                "android.resource://com.example.a7minuteworkout/"+ R.raw.press_start
            )

            player=MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping=false
            player?.start()

        }catch (e:Exception){
            e.printStackTrace()
        }

        binding?.upComing?.visibility=View.VISIBLE
        binding?.upComingExerciseName?.visibility=View.VISIBLE
        binding?.flProgressBar?.visibility=View.VISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.tvExercise?.visibility=View.INVISIBLE
        binding?.flProgressBar2?.visibility=View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE


        binding?.upComingExerciseName?.text = exerciseList!![currentExercisePosition+1].getName()


        if(restTimer!=null)
        {
            restTimer?.cancel()
            restProgress=0
        }

        setRestProgressBar()
    }

    private fun setupExerciseView(){
        binding?.upComing?.visibility=View.INVISIBLE
        binding?.upComingExerciseName?.visibility=View.INVISIBLE
        binding?.flProgressBar?.visibility=View.INVISIBLE
        binding?.tvTitle?.visibility=View.INVISIBLE
        binding?.tvExercise?.visibility=View.VISIBLE
        binding?.flProgressBar2?.visibility=View.VISIBLE
        binding?.ivImage?.visibility=View.VISIBLE
        if(exerciseTimer!=null)
        {
            exerciseTimer?.cancel()
            exerciseProgress=0
        }

        speakText(exerciseList!![currentExercisePosition].getName())



        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExercise?.text=exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar()
    }


    private fun setRestProgressBar(){
        binding?.progressBar?.progress=restProgress

        restTimer = object:CountDownTimer(restTimerDuration *10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress=10-restProgress
                binding?.tvTimer?.text=(10-restProgress).toString()
            }


            override fun onFinish() {
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupExerciseView()
            }

        }.start()

    }

    private fun setExerciseProgressBar(){
        binding?.progressBar2?.progress=exerciseProgress

        exerciseTimer = object:CountDownTimer(exerciseTimerDuration*30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBar2?.progress=30-exerciseProgress
                binding?.tvTimer2?.text=(30-exerciseProgress).toString()
            }


            override fun onFinish() {



               if(currentExercisePosition < exerciseList?.size!!-1) {
                   exerciseList!![currentExercisePosition].setIsSelected(false)
                   exerciseList!![currentExercisePosition].setIsCompleted(true)
                   exerciseAdapter!!.notifyDataSetChanged()
                   setupRestView()
               }

                else{

                  finish()
                   val intent = Intent(this@ExerciseActivity, activity_finish::class.java)
                   startActivity(intent)
               }

            }

        }.start()

    }
    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null) {
            restTimer?.cancel()

            restProgress = 0
        }
        if(exerciseTimer!=null)
        {
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        if(tts !=null)
        {
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player!=null)
        {
            player!!.stop()
        }

        binding=null
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS)
        {
            val result = tts!!.setLanguage(Locale.UK)
            if(result==TextToSpeech.LANG_MISSING_DATA ||
                result==TextToSpeech.LANG_NOT_SUPPORTED){

                Log.e("TTS","the language specified is not supported!")
            }
            else{
                Log.e("TTS","Initialization failed")
            }
        }
    }
    private fun speakText(text:String)
    {
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
}