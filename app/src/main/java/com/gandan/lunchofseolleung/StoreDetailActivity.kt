package com.gandan.lunchofseolleung

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class StoreDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)

        var db = FirebaseFirestore.getInstance()

        db.collection("storelist").whereEqualTo("storeName", intent.getStringExtra("storeName"))
            .get().addOnSuccessListener {
                query ->
                for(document in query){
                    Log.e("document", "${document.get("storeName")}")
                }
            }
    }
}
