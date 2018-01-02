package com.tsimbalyukstudio.testwork

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Bitmap
import android.graphics.Color
import java.util.*
import android.support.v4.content.ContextCompat
import android.view.GestureDetector
import android.view.View
import android.widget.ImageView
import uk.co.senab.photoview.PhotoView
import uk.co.senab.photoview.PhotoViewAttacher


class MainActivity : AppCompatActivity() {

    var ar: IntArray = intArrayOf(4);
    //var pva : PhotoViewAttacher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ar = intArrayOf(R.color.UKRAINE, R.color.BELARUS, R.color.POLAND, R.color.RUSSIA, R.color.MOLDOVA, R.color.ROMANIA)
        //val iv = findViewById<ImageView>(R.id.mapImg)
        //pva= PhotoViewAttacher(iv)
        //pva!!.update()

        startGame()
    }


    fun startGame() {

        var x: Int
        var y: Int

        mapImg.setOnTouchListener { v, aEvent ->
            x = aEvent.x.toInt();
            y = aEvent.y.toInt();
            mapImg.isDrawingCacheEnabled = true
            val hotspots = Bitmap.createBitmap(mapImg.drawingCache)

            var tempPos: Int = 0

            do {
                val color = ContextCompat.getColor(this, ar[tempPos])
                val red = (color shr 16 and 0xFF)
                val green = (color shr 8 and 0xFF)
                val blue = (color and 0xFF)
                if (red == Color.red(hotspots.getPixel(x, y)) && green == Color.green(hotspots.getPixel(x, y)) && blue == Color.blue(hotspots.getPixel(x, y))) {
                    break
                }
                tempPos++
            } while (tempPos < ar.size)

            when (tempPos) {
                0 -> Toast.makeText(this, "UKRAINE", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this, "BELARUS", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this, "POLAND", Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(this, "RUSSIA", Toast.LENGTH_SHORT).show()
                4 -> Toast.makeText(this, "MOLDOVA", Toast.LENGTH_SHORT).show()
                5 -> Toast.makeText(this, "ROMANIA", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "There are no such country!", Toast.LENGTH_SHORT).show()
            }
            onTouchEvent(aEvent)
        }
    }
}

