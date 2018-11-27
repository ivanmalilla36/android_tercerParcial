package com.example.egghunt3r.tercerparcial


import kotlinx.android.synthetic.main.vehiculo_layout.view.*
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso

class VehiculoAdapter(private val vehiculoList: VehiculoResult, private val context: Context): RecyclerView.Adapter<VehiculoAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vehiculo_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = vehiculoList.vehiculo.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindRepo(vehiculoList.vehiculo[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindRepo(vehiculo: Item) {
            with(vehiculo) {
                itemView.Marca.text = vehiculo.Marca
                itemView.Modelo.text = vehiculo.Modelo
                itemView.Año.text = vehiculo.Año
                Picasso.get().load(vehiculo.url).into(itemView.icon);
            }
        }
    }
}
