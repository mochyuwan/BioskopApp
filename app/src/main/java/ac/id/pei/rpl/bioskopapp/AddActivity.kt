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

class AddActivity : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etId: EditText
    lateinit var etNama: EditText
    lateinit var etTeater: EditText
    lateinit var apiService: ServiceInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        declaration()
        myfunction()
    }

    fun declaration(){
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etId = findViewById(R.id.et_add_id)
        etNama = findViewById(R.id.et_add_nama)
        etTeater = findViewById(R.id.et_add_teater)

        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    fun myfunction(){
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            val array = KursiData()
            array.id=etId.text.toString().toInt()
            array.nama= etNama.text.toString()
            array.teater_id = etTeater.text.toString()

            apiService.postKontak(array).enqueue(object : Callback<KursiData>{
                override fun onResponse(call: Call<KursiData>, response: Response<KursiData>) {
                    startActivity(Intent(this@AddActivity, MainActivity::class.java))
                    Toast.makeText(baseContext, "Add Data Success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<KursiData>, t: Throwable) {
                    Toast.makeText(baseContext, "Add Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}
