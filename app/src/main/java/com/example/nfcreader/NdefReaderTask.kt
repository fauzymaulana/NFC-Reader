package com.example.nfcreader

import android.nfc.NdefRecord
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import com.example.nfcreader.MainActivity.Companion.TAG
import java.io.UnsupportedEncodingException
import kotlin.experimental.and


/**
 * NFCReader / com.example.nfcreader
 * Created by Fauzi Maulana (papero.mint@gmail.com)
 * On 16:32, Sep 07, 2022
 */
class NdefReaderTask : AsyncTask<Tag, Void, String> (){

    override fun doInBackground(vararg p0: Tag?): String? {
//        var tag: Tag? = p0.get(0)
//
//        var ndef: Ndef = Ndef.get(tag)
//        if (ndef == null){
//            return null
//        }
//
//        var ndefMessage = ndef.cachedNdefMessage
//
//        val records = ndefMessage.records
//        for (ndefRecord in records) {
//            if (ndefRecord.tnf == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(
//                    ndefRecord.type,
//                    NdefRecord.RTD_TEXT
//                )
//            ) {
//                try {
//                    return readText(ndefRecord)
//                } catch (e: UnsupportedEncodingException) {
//                    Log.e(TAG, "Unsupported Encoding", e)
//                }
//            }
//        }
//
        return null
    }

//    @Throws(UnsupportedEncodingException::class)
//    private fun readText(record: NdefRecord): String? {
//        /*
//         * See NFC forum specification for "Text Record Type Definition" at 3.2.1
//         *
//         * http://www.nfc-forum.org/specs/
//         *
//         * bit_7 defines encoding
//         * bit_6 reserved for future use, must be 0
//         * bit_5..0 length of IANA language code
//         */
//        val payload = record.payload
//
//        // Get the Text Encoding
//        val textEncoding = if (payload[0] and 128.toByte().toInt() === 0) "UTF-8" else "UTF-16"
//
//        // Get the Language Code
//        val languageCodeLength = (payload[0] and 51).toInt()
//
//        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
//        // e.g. "en"
//
//        // Get the Text
//        return String(
//            payload,
//            languageCodeLength + 1,
//            payload.size - languageCodeLength - 1,
//            textEncoding
//        )
//    }
//
//    override fun onPostExecute(result: String?) {
//        if (result != null){
//            Log.d("This", "Read content: " + result)
//        }
//    }
}