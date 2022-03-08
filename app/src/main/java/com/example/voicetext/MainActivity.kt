package com.example.voicetext

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.Toast
import com.example.voicetext.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private val RQ_SPEECH_REC = 102
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
//        setContentView(R.layout.activity_main)
//        var myVoiceButton = findViewById<Button>(R.id.btn_button)
        binding.btnButton.setOnClickListener {
            askSpeechInput()
        }
    }
// ? means there might be data null or not doesn't process if null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode,resultCode, data)

        if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK) {
            val result: ArrayList<String>? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            binding.tvText.text = result?.get(0).toString()
        }
    }


    private fun askSpeechInput() {
        // speech recognizer where should come from?  from this class is this
        if (!SpeechRecognizer.isRecognitionAvailable( this)){
            Toast.makeText( this, "Speech recognition is not available", Toast.LENGTH_SHORT).show()
        } else {
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something!")
            startActivityForResult(i,RQ_SPEECH_REC)

        }


    }
}