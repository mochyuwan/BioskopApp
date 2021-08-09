package ac.id.pei.rpl.bioskopapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response

class KursiActivity : AppCompatActivity() {
    lateinit var rvdata: RecyclerView
    lateinit var apiService: ServiceInterface
    private var ambilData: ArrayList<KursiData> = arrayListOf()
    lateinit var btnadd: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kursi)
        rvdata = findViewById(R.id.rv_data)
        btnadd = findViewById(R.id.btn_main_add)
        btnadd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
        apiService.getData().enqueue(object : Callback<List<KursiData>>{
            override fun onResponse(
                call: retrofit2.Call<List<KursiData>>,
                response: Response<List<KursiData>>
            ) {
                if (response.isSuccessful){
                    val res = response.body()
                    ambilData.addAll(res!!)
                    rvdata.layoutManager = LinearLayoutManager(this@KursiActivity)
                    rvdata.adapter = KursiAdapter(ambilData)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<KursiData>>, t: Throwable) {
            }
        })
    }
}


