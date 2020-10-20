package com.example.neostore.ProductDetails

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neostore.R
import com.example.neostore.table.Tabledata

class FavouriteprodAdapter (var favoriteLists: MutableList<Tabledata>, var context: Context) :
RecyclerView.Adapter<FavouriteprodAdapter.ViewHolder>() {
    private var firstClick = false

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.table_recycle_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tableItem = favoriteLists.get(position)

        holder.productname.text = tableItem.name
        holder.producername.text = tableItem.producer

        holder.productprice.text = tableItem.cost.toString()

        Glide.with(holder.itemView!!.context).load(tableItem.product_images)
            .into(holder.image)

        holder.rate.setRating(tableItem.rating.toFloat());
        if (Productdetails.favoriteDatabase?.favoriteDao()
                ?.isFavorite(favoriteLists.get(position).id) === 1
        )
            //holder.fav_btn.setImageResource(R.drawable.favourite_red) else
        // holder.fav_btn.setImageResource(R.drawable.favourite_null)


        /*holder.fav_btn.setOnClickListener(View.OnClickListener {

            if (Product_details.favoriteDatabase?.favoriteDao()
                    ?.isFavorite(favoriteLists.get(position).id) !== 1
            ) {
                holder.fav_btn.setImageResource(R.drawable.favourite_red)
                Product_details.favoriteDatabase?.favoriteDao()?.addData(favoriteLists.get(position))
            } else {
                holder.fav_btn.setImageResource(R.drawable.favourite_null)

                Product_details.favoriteDatabase!!.favoriteDao().deleteFavoritesById(favoriteLists.get(position))

            }
            notifyItemChanged(position)
            notifyItemRemoved(position)

            favoriteLists?.removeAt(position)

            notifyItemRangeChanged(position, favoriteLists?.size!!)

        })


         */
        holder.itemView!!.setOnClickListener {
            val context: Context = holder.itemView.context
            val i = Intent(
                context,
                Productdetails::class.java
            )
            i.putExtra("id", tableItem.id)
            i.putExtra("product_category_id", tableItem.product_category_id)
            i.putExtra("name", favoriteLists.get(position).name)
            i.putExtra("producer", favoriteLists.get(position).producer)
            i.putExtra("description", favoriteLists.get(position).description)

            i.putExtra("cost", favoriteLists.get(position).cost)
            i.putExtra("rating", favoriteLists.get(position).rating)
            i.putExtra("view_count", favoriteLists.get(position).view_count)
            i.putExtra("created", favoriteLists.get(position).created)
            i.putExtra("modified", favoriteLists.get(position).modified)


            i.putExtra("image", tableItem.product_images)
            i.putExtra("current_img", position)


            context.startActivity(i)
        }


    }

    override fun getItemCount(): Int {
        return favoriteLists.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productname: TextView = itemView!!.findViewById(R.id.title)
        val producername: TextView = itemView!!.findViewById(R.id.title1)
        val productprice: TextView = itemView!!.findViewById(R.id.title2)
        val rate: RatingBar = itemView!!.findViewById(R.id.ratingbar)
        val image: ImageView = itemView!!.findViewById(R.id.image)
       // val fav_btn: ImageView = itemView!!.findViewById(R.id.fav_btn);
    }
}
