package com.example.practicandobluetooth

import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.practicandobluetooth.ControlActivity.Companion.m_adrres
import com.example.practicandobluetooth.ControlActivity.Companion.m_bluetoothAdapter
import com.example.practicandobluetooth.ControlActivity.Companion.m_bluetooth_Socket
import com.example.practicandobluetooth.ControlActivity.Companion.m_isConnected
import com.example.practicandobluetooth.ControlActivity.Companion.m_myUUID
import com.example.practicandobluetooth.ControlActivity.Companion.m_progress
import kotlinx.android.synthetic.main.control_layout.*
import java.io.IOException
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

        id_button_on.setOnClickListener { sendcomand("a") }
        id_button_off.setOnClickListener { sendcomand("b") }
        id_button_disconect.setOnClickListener { dissconect() }}
    }

    private fun sendcomand(input: String){
        if (m_bluetooth_Socket != null) {
            try{
                m_bluetooth_Socket!!.outputStream.write(input.toByteArray())
            } catch(e: IOException) {
                e.printStackTrace()
            }
        }
    }
    private fun dissconect(){
        if (m_bluetooth_Socket != null) {
            try {
                m_bluetooth_Socket!!.close()
                m_bluetooth_Socket = null
                m_isConnected = false
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        //finish()
    }
    private class ConnectToDevice(c: Context) : AsyncTask<Void, Void, String> (){

        private var connectSuccess: Boolean = true
        private val context: Context

        init{
            this.context = c
        }

        override fun onPreExecute() {
            super.onPreExecute()
            m_progress = ProgressDialog.show(context, "Conectando...","Porfavor espera")
        }

        override fun doInBackground(vararg params: Void?): String? {
            try {
                if (m_bluetooth_Socket == null || !m_isConnected) {
                    m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                    val device: BluetoothDevice = m_bluetoothAdapter.getRemoteDevice(m_adrres)
                    m_bluetooth_Socket = device.createInsecureRfcommSocketToServiceRecord(m_myUUID)
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                    m_bluetooth_Socket!!.connect()
                }
            } catch (e: IOException) {
                connectSuccess = false
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (!connectSuccess) {
                Log.i("data", "couldn't connect")
            } else {
                m_isConnected = true
            }
            m_progress.dismiss()
        }


}