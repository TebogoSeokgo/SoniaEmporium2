package com.example.soniaemporium2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val cartItems: List<CartItem>, // Updated to a custom CartItem model
    private val onRemoveClick: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.cartItemName)
        val itemImage: ImageView = itemView.findViewById(R.id.cartItemImage)
        val removeButton: Button = itemView.findViewById(R.id.removeCartItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.itemName.text = item.name
        holder.itemImage.setImageResource(item.imageResId) // Set image resource for each item
        holder.removeButton.setOnClickListener {
            onRemoveClick(item)
        }
    }


    override fun getItemCount(): Int {
        return cartItems.size
    }
}
