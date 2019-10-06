package com.gandan.lunchofseolleung

import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.CameraUpdate.REASON_GESTURE
import com.naver.maps.map.overlay.Marker



class StoreDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private var lat : Double = 0.0
    private var lng : Double = 0.0
    private lateinit var naverMap : NaverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)

        var db = FirebaseFirestore.getInstance()

        var storeMapFragment : MapView = findViewById<View>(R.id.storeMapFragment) as MapView
        storeMapFragment.getMapAsync(this)

        db.collection("storelist").whereEqualTo("storeName", intent.getStringExtra("storeName"))
            .get().addOnSuccessListener {
                query ->
                for(document in query){
                    Log.e("document", "${document.get("storeName")}")
                    lat = document.get("lat") as Double
                    lng = document.get("lng") as Double
                    if(lat != 0.0 && lng != 0.0)
                    onMapReady(naverMap)
                }
            }

        storeMapFragment.setOnTouchListener { view, event ->

            view.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_SCROLL -> {
                    view.parent.requestDisallowInterceptTouchEvent(false)
                    true
                }
            }
            false
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.let {
            it.moveCamera(CameraUpdate.scrollTo(LatLng(lat, lng)))

        }
        val marker = Marker()
        marker.let {
            it.position = LatLng(lat, lng)
            it.map = naverMap
        }
        val uiSettings = naverMap.uiSettings
        uiSettings.isCompassEnabled = true
    }
}
