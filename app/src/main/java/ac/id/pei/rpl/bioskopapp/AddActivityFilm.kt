package ac.id.pei.rpl.bioskopapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivityFilm : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etId: EditText
    lateinit var etJudul: EditText
    lateinit var etDeskripsi: EditText
    lateinit var Rating: EditText
    lateinit var apiService: ServiceInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)
        declaration()
        myfunction()
    }
    fun declaration(){
        btnSubmit = findViewById(R.id.btn_submit_film)
        btnCancel = findViewById(R.id.btn_cancel_film )
        etId = findViewById(R.id.id_film)
        etJudul = findViewById(R.id.judul_film)
        etDeskripsi = findViewById(R.id.deksripsi_film)
        Rating = findViewById(R.id.rating_film)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    fun myfunction() {
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            val array = FilmData()
            array.id = etId.text.toString().toInt()
            array.Judul = etJudul.text.toString()
            array.deskripsi = etDeskripsi.text.toString()
            array.rating = Rating.text.toString()

            apiService.postFilm(array).enqueue(object : Callback<FilmData> {
                override fun onResponse(call: Call<FilmData>, response: Response<FilmData>) {
                    startActivity(Intent(this@AddActivityFilm, MainActivity::class.java))
                    Toast.makeText(baseContext, "Add Data Success", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<FilmData>, t: Throwable) {
                    Toast.makeText(baseContext, "Add Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }

}