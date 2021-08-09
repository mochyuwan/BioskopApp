package ac.id.pei.rpl.bioskopapp


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Response
import javax.security.auth.callback.Callback

class FilmAdapter(private val list: ArrayList<FilmData>): RecyclerView.Adapter<FilmAdapter.FilmViewHolder>(){
    inner class FilmViewHolder(viewku: View): RecyclerView.ViewHolder(viewku) {
        var tvId: TextView = viewku.findViewById(R.id.tv_id_film)
       var tvJudul: TextView = viewku.findViewById(R.id.tv_judul_film)
       var tvDeksripsi: TextView = viewku.findViewById(R.id.tv_deksripsi_film)
        var tvRating: TextView = viewku.findViewById(R.id.tv_rating_film)
       var btnDelete: ImageButton = viewku.findViewById(R.id.del_film)
      var apiIterface: ServiceInterface? = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.data_film, parent, false)
        return FilmViewHolder(viewView)
    }

    override fun onBindViewHolder(holder:FilmViewHolder, position: Int) {
        val dataku = list[position]
       holder.tvId.text = dataku.id.toString()
        holder.tvJudul.text = dataku.Judul
        holder.tvDeksripsi.text = dataku.deskripsi
        holder.tvRating.text = dataku.rating


        holder.btnDelete.setOnClickListener {
            holder.apiIterface!!.deleteFilm(dataku.id!!).enqueue(object :
                retrofit2.Callback<FilmData> {
                override fun onResponse(call: retrofit2.Call<FilmData>, response: retrofit2.Response<FilmData>) {
                    if (response.isSuccessful) {
                        list.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(
                            holder.itemView.context,
                            "Delete Data Success",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<FilmData>, t: Throwable) {
                    Toast.makeText(
                        holder.itemView.context,
                        "Delete Data Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


//
    }




