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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KursiAdapter(private val listku: ArrayList<KursiData>): RecyclerView.Adapter<KursiAdapter.KursiViewHolder>(){
    inner class KursiViewHolder(viewku: View): RecyclerView.ViewHolder(viewku) {
        var tvId: TextView = viewku.findViewById(R.id.tv_id)
        var tvNama: TextView = viewku.findViewById(R.id.tv_nama)
        var tvIdTeater: TextView = viewku.findViewById(R.id.tv_teater_id)
        var btnDelete: ImageButton = viewku.findViewById(R.id.btn_data_del)
        var btnUpdate: ImageButton = viewku.findViewById(R.id.btn_data_edit)
        var apiIterface: ServiceInterface? = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KursiViewHolder {
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.data_kursi, parent, false)
        return KursiViewHolder(viewView)
    }

    override fun onBindViewHolder(holder: KursiViewHolder, position: Int) {
        val dataku = listku[position]
        holder.tvId.text = dataku.id.toString()
        holder.tvNama.text = dataku.nama
        holder.tvIdTeater.text = dataku.teater_id
        holder.btnUpdate.setOnClickListener {
            val bundle= Bundle()
            val pindah = Intent(holder.itemView.context, UpdateActivity::class.java)
            bundle.putString("id", dataku.id.toString())
            bundle.putString("nama", dataku.nama.toString())
            bundle.putString("teater_id", dataku.teater_id.toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
      holder.btnDelete.setOnClickListener {
            holder.apiIterface!!.deleteKontak(dataku.id!!).enqueue(object : Callback<KursiData>{
                override fun onResponse(call: Call<KursiData>, response: Response<KursiData>) {
                    if (response.isSuccessful){
                        listku.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(holder.itemView.context, "Delete Data Success", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<KursiData>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Delete Data Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    override fun getItemCount(): Int {
        return listku.size
    }

}
