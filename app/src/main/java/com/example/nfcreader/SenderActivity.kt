package com.example.nfcreader

import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.nfcreader.nfc.OutcomingNfcManager
import com.google.android.material.snackbar.Snackbar

class SenderActivity : AppCompatActivity(), OutcomingNfcManager.NfcActivity, View.OnClickListener {

    private lateinit var tvOutComingMessage: TextView
    private lateinit var etOutComingMessage: EditText
    private lateinit var btnSetOutComingMessage: Button
    private lateinit var senderRootView: ConstraintLayout

    private var nfcAdapter: NfcAdapter? = null

    private val isNfcSupported: Boolean = this.nfcAdapter != null

    private lateinit var outComingCallback: OutcomingNfcManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)

        initView()


        this. nfcAdapter = NfcAdapter.getDefaultAdapter(this)?.let { it }

        if (!isNfcSupported){
            Toast.makeText(this, "NFC is not supported on this device", Toast.LENGTH_LONG).show()
//            finish()
        }

        if (!nfcAdapter?.isEnabled!!){
            Toast.makeText(this, "NFC disabled on this device, Turn on to processed", Toast.LENGTH_LONG).show()
        }

        this.outComingCallback = OutcomingNfcManager(this)
        this.nfcAdapter?.setOnNdefPushCompleteCallback(outComingCallback, this)
        this.nfcAdapter?.setNdefPushMessageCallback(outComingCallback, this)

    }

    fun initView(){
        senderRootView = findViewById(R.id.senderRootView)
        tvOutComingMessage = findViewById(R.id.tv_out_message)
        etOutComingMessage = findViewById(R.id.et_message)
        btnSetOutComingMessage = findViewById(R.id.btn_set_out_message)
        senderRootView = findViewById(R.id.senderRootView)

        btnSetOutComingMessage.setOnClickListener(this)
    }

    fun setOutComingMessage() {
        val outMessage = this.etOutComingMessage.text.toString()
        this.tvOutComingMessage.text = outMessage
    }

    override fun getOutComingMessage(): String {
       return this.tvOutComingMessage.text.toString()
    }

    override fun signalResults() {
        runOnUiThread {
            Toast.makeText(this, "Beeming Compelete", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            btnSetOutComingMessage.id -> {

            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        this.intent = intent
    }

    fun signalResult(){
        runOnUiThread {
            Snackbar.make(senderRootView, "Beeming Complete", Snackbar.LENGTH_LONG).show()
        }
    }



}