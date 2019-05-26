package com.example.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayList<String> mDeviceList = new ArrayList<String>();
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        ///*



        /*
        ArrayList list = new ArrayList();
        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        //*/


    /*
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        for(BluetoothDevice device1: pairedDevices){
            String devicename = device1.getName();
            String macAddress = device1.getAddress();
            mDeviceList.add("Name: "+devicename+"\n MAC Address: "+macAddress);
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, mDeviceList);
        listView.setAdapter(adapter);
    */


    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDeviceList.add(device.getName() + "\n" + device.getAddress() +"\n" + device.getBluetoothClass()+"\n"+device.getBondState());
                Log.i("BT1", device.getName() + "\n" + device.getAddress());
                listView.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, mDeviceList));
            }
        }
    };

}