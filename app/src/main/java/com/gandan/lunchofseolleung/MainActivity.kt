package com.gandan.lunchofseolleung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db = FirebaseFirestore.getInstance()

        var list = arrayListOf<DataModels.StoreInfo>()
        var storeRecyclerView = findViewById<RecyclerView>(R.id.storeListRecyclerView)

        db.collection("storelist").get().addOnSuccessListener {
            result ->
            for(document in result){
                list.add(
                    DataModels.StoreInfo(
                        document.get("storeName").toString(),
                        document.get("address").toString(),
                        Integer.parseInt(document.get("priceCash").toString()),
                        Integer.parseInt(document.get("priceCard").toString()),
                        document.get("openTime").toString(),
                        document.get("closeTime").toString(),
                        document.get("menu").toString()
                    )
                )
            }
            storeRecyclerView.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = StoreListRecyclerAdapter(context, list)
            }
        }



    }
}
