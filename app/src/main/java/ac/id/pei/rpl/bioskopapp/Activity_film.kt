package ac.id.pei.rpl.bioskopapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response

class Activity_film : AppCompatActivity() {
    lateinit var rvdata: RecyclerView
    lateinit var apiService: ServiceInterface
    private var ambilDataFilm: ArrayList<FilmData> = arrayListOf()
    lateinit var btnadd: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film)
        rvdata = findViewById(R.id.rv_data_film)
        btnadd = findViewById(R.id.btn_main_add_film)
        btnadd.setOnClickListener {
            startActivity(Intent(this, AddActivityFilm::class.java))
        }
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
        apiService.getDataFilm().enqueue(object : Callback<List<FilmData>> {
            override fun onResponse(
                call: retrofit2.Call<List<FilmData>>,
                response: Response<List<FilmData>>
            ) {
                if (response.isSuccessful){
                    val res = response.body()
                    ambilDataFilm.addAll(res!!)
                    rvdata.layoutManager = LinearLayoutManager(this@Activity_film)
                    rvdata.adapter = FilmAdapter(ambilDataFilm)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<FilmData>>, t: Throwable) {
            }
        })
    }
}