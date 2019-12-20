package com.example.practicandobluetooth

import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.control_layout.*
import java.util.*

class ControlActivity: AppCompatActivity() {

    companion object{
        var m_myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var m_bluetooth_Socket : BluetoothSocket? = null
        lateinit var m_progress: ProgressDialog
        lateinit var m_bluetoothAdapter: BluetoothAdapter
        var m_isConnected: Boolean = false
        lateinit var m_adrres: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.control_layout)

        m_adrres = intent.getStringExtra(MainActivity.EXTRA_ADDRESS)
        ConnectToDevice(this).execute()

        //id_button_on.setOnClickListener { sendcomand() }
    }

    private fun sendcomand(input: String){

    }
    private fun dissconect(){

    }
    private class ConnectToDevice(c: Context) : AsyncTask<Void, Void, String> (){

        private var connectSuccess: Boolean = true
        private val context: Context

        init{
            this.context = c
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Void?): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

    }
}