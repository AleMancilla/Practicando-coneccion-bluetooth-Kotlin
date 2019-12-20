package com.example.practicandobluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var m_bluetoothAdapter: BluetoothAdapter
    lateinit var m_pairedDevices: Set<BluetoothDevice>
    var REQUEST_ENABLE_BLUETOOTH = 1

    companion object {
        val EXTRA_ADDRESS: String = "Devisse Address"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(m_bluetoothAdapter == null) // verifica si el dispositivo es compatible con bluetooth
        {
            Toast.makeText(this,"Dispositivo no compatible con bluetooth",Toast.LENGTH_SHORT).show()
            return
        }
        if(!m_bluetoothAdapter.isEnabled)
        {
            val event = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(event,REQUEST_ENABLE_BLUETOOTH)
        }
    }

    private fun pairedDeviceList()
    {

    }

}
