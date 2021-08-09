package ac.id.pei.rpl.bioskopapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var btnkursi: View
    lateinit var btnfilm: View
    lateinit var btnjadwal: View
    lateinit var btnteater: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnkursi = findViewById(R.id.btn_kursi)
        btnkursi.setOnClickListener {
            startActivity(Intent(this, KursiActivity::class.java))
        }
        btnfilm = findViewById(R.id.btn_film)
        btnfilm.setOnClickListener {
            startActivity(Intent(this, Activity_film::class.java))
        }
        btnjadwal = findViewById(R.id.btn_jadwal)
        btnjadwal.setOnClickListener {
            Toast.makeText(baseContext, "Server sedang Maintenance", Toast.LENGTH_SHORT).show()
        }
        btnteater = findViewById(R.id.btn_teater)
        btnteater.setOnClickListener {
            Toast.makeText(baseContext, "Server sedang Maintenance", Toast.LENGTH_SHORT).show()
        }
    }
}