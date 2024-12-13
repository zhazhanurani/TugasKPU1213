package com.example.tugaskpu1213

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaskpu1213.Database.Pemilih
import com.example.tugaskpu1213.databinding.ItemListViewBinding

class PemilihAdapter (
    private val context: Context,
    private val deleteAction: (Pemilih) -> Unit
) : ListAdapter<Pemilih, PemilihAdapter.PemilihViewHolder>(DiffCallback) {

    // DiffUtil Callback untuk menghitung perubahan data
    object DiffCallback : DiffUtil.ItemCallback<Pemilih>() {
        override fun areItemsTheSame(oldItem: Pemilih, newItem: Pemilih): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pemilih, newItem: Pemilih): Boolean {
            return oldItem == newItem
        }
    }


    // ViewHolder untuk mengikat data dengan layout item
    inner class PemilihViewHolder(private val binding: ItemListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pemilih, position: Int)  {
            with(binding) {
                nomorPemilih.text = "${position + 1}."
                namaPemilih.text = data.nama


                // Set click listener on the item view
                editPemilih.setOnClickListener {
                    val intent = Intent(context, EditDataPemilih::class.java)
                    intent.putExtra("id", data.id)
                    intent.putExtra("nama", data.nama)
                    intent.putExtra("nik", data.nik)
                    intent.putExtra("jenis_kelamin", data.jenisKelamin)
                    intent.putExtra("alamat", data.alamat)
                    context.startActivity(intent)
                }
                showPemilih.setOnClickListener {
                    val intent = Intent(context, DetailDataPemilih::class.java)
                    intent.putExtra("nama", data.nama)
                    intent.putExtra("nik", data.nik)
                    intent.putExtra("jenis_kelamin", data.jenisKelamin)
                    intent.putExtra("alamat", data.alamat)
                    context.startActivity(intent)
                }
                hapusPemilih.setOnClickListener {
                    // Menjalankan aksi delete melalui callback
                    deleteAction(data)

                }

            }
        }
    }

    // Inflate layout item untuk RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemilihViewHolder {
        val binding = ItemListViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PemilihViewHolder(binding)
    }

    // Mengikat data ke ViewHolder
    override fun onBindViewHolder(holder: PemilihViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

