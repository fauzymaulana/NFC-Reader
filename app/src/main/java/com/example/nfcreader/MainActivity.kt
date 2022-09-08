package com.example.nfcreader

import android.R.attr
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.content.IntentFilter.MalformedMimeTypeException
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    companion object{
        const val MIME_TEXT_PLAIN = "text/plain"
        const val TAG = "MainActivity"
    }

    private var txtExplain: TextView? = null
    private var nfcAdapter: NfcAdapter? = null
    private var mainRootView: ConstraintLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        handleIntent(intent)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null){
            Snackbar.make(findViewById(R.id.mainRootView), "Device not support NFC", Snackbar.LENGTH_LONG)
                .show()
        }



    }

    private fun initView(){
        txtExplain = findViewById(R.id.txtExplain)
        mainRootView = findViewById(R.id.mainRootView)
    }

    private fun handleIntent(intent: Intent){
//        Read data from the tag
        var action: String? = intent.action

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)){
            var type: String? = intent.type
            if (MIME_TEXT_PLAIN.equals(type)){

                val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
                NdefReaderTask().execute(tag)
            }else{
                Log.d(TAG, "wrong mime type: " + type)
            }
        }else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)){
            val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            val techList: Array<String> = tag?.getTechList() as Array<String>
            val searchedTech = Ndef::class.java.name

            for (tech in techList) {
                if (searchedTech.equals(tech)) {
                    NdefReaderTask().execute(tag)
                    break
                }
            }


        }

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun onPause() {
        super.onPause()
    }

    fun setupForegroundDispatch(activity: Activity, adapter: NfcAdapter){
        val intent = Intent(activity.applicationContext, activity.javaClass)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getActivity(activity.applicationContext, 0, intent, 0)

        val filters = arrayOfNulls<IntentFilter>(1)
        val techList = arrayOf<Array<String>>()

        filters[0] = IntentFilter()
        filters[0]!!.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED)
        filters[0]!!.addCategory(Intent.CATEGORY_DEFAULT)
        try {
            filters[0]!!.addDataType(MIME_TEXT_PLAIN)
        } catch (e: MalformedMimeTypeException) {
            throw RuntimeException("Check your mime type.")
        }

        adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList)
    }

    fun stopForegroundDispatch(activity: Activity, adapter: NfcAdapter){
        adapter.disableForegroundDispatch(activity)
    }
}