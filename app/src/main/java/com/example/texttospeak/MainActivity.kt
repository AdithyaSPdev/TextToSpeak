package com.example.texttospeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import com.example.texttospeak.databinding.ActivityMainBinding
import java.util.Locale
import kotlin.math.log

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var tts : TextToSpeech?  =  null
    private var binding: ActivityMainBinding? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        tts = TextToSpeech(this,this)

        binding?.button?.setOnClickListener{
            if(binding?.enteredText?.text!!.isEmpty()){
                Toast.makeText(this, "Please full the some text here ", Toast.LENGTH_SHORT).show()
            }
            else{
               speaker(binding?.enteredText?.text.toString())
            }
        }

    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)


            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("tts", "This language is not supported")
            }
        }

        else{
            Log.e("not","we got an error")
        }

    }



    private fun speaker(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        super.onDestroy()
        if(tts != null){
            tts?.stop()
            tts?.shutdown()
        }
        binding = null
    }

}