package com.example.nfcreader.nfc

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcEvent
import com.example.nfcreader.MainActivity.Companion.MIME_TEXT_PLAIN


/**
 * NFCReader / com.example.nfcreader.nfc
 * Created by Fauzi Maulana (papero.mint@gmail.com)
 * On 11:26, Sep 08, 2022
 */
class OutcomingNfcManager(private val nfcActivity: NfcActivity) : NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback{

    override fun createNdefMessage(p0: NfcEvent?): NdefMessage {

        val outString = nfcActivity.getOutComingMessage()

        with(outString){
            val outBytes = this.toByteArray()
            val outRecord = NdefRecord.createMime(MIME_TEXT_PLAIN, outBytes)
            return NdefMessage(outRecord)
        }


    }

    override fun onNdefPushComplete(p0: NfcEvent?) {
        nfcActivity.signalResults()
    }

    interface NfcActivity {

        fun getOutComingMessage(): String

        fun signalResults()

    }

}


