package com.example.practicandobluetooth

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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
        id_button_refresh.setOnClickListener{pairedDeviceList()}
    }

    private fun pairedDeviceList()
    {
        m_pairedDevices = m_bluetoothAdapter!!.bondedDevices
        val list : ArrayList<BluetoothDevice> = ArrayList()
        if(!m_pairedDevices.isEmpty())
        {
            for (device: BluetoothDevice in m_pairedDevices)
            {
                list.add(device)
            }
        }
        else
        {
            Toast.makeText(this,"No se encontraron dispositivos enparejados",Toast.LENGTH_SHORT).show()
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,list)
        id_listView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ENABLE_BLUETOOTH)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                if(m_bluetoothAdapter!!.isEnabled)
                {
                    Toast.makeText(this,"El bluetooth ha sido habilitado",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this,"El bluetooth ha sido Deshabilitado",Toast.LENGTH_SHORT).show()
                }
            }
            else if(resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(this,"La coneccion Bluetooth ha sido cancelada",Toast.LENGTH_SHORT).show()
            }
        }
    }

}
