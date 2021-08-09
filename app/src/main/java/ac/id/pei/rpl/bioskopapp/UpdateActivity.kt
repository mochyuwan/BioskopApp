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

class UpdateActivity : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etId: EditText
    lateinit var etNama: EditText
    lateinit var etTeater: EditText
    lateinit var valNama: String
    lateinit var valTeater: String
   private  lateinit var valId: String
    lateinit var apiService: ServiceInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        getMyData()
        declaration()
        myfunction()
    }
    fun declaration(){
        btnSubmit = findViewById(R.id.btn_up_submit)
        btnCancel = findViewById(R.id.btn_up_cancel)
        etId = findViewById(R.id.et_up_id)
        etNama = findViewById(R.id.et_up_nama)
        etTeater = findViewById(R.id.et_up_teater)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    fun getMyData(){
        val myValue = intent.extras
        if (myValue!=null){
            valId = myValue.getString("id").toString()
            valNama= myValue.getString("nama").toString()
            valTeater = myValue.getString("teater_id").toString()

        }
    }

    fun myfunction(){
        etId.setText(valId)
        etNama.setText(valNama)
        etTeater.setText(valTeater)
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            apiService.updateKontak(valId.toInt(), etNama.text.toString(), etTeater.text.toString())
                .enqueue(object : Callback<KursiData>{ override fun onResponse(call: Call<KursiData>, response: Response<KursiData>) {
                    startActivity(pindah)
                    Toast.makeText(baseContext, "Update Data Success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<KursiData>, t: Throwable) {
                    Toast.makeText(baseContext, "Update Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}
