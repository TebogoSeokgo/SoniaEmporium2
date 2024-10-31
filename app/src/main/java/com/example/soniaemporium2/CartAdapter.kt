package com.example.soniaemporium2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val cartItems: MutableList<CartItem>,
    private val onItemRemoved: (CartItem) -> Unit,
    private val onQuantityChanged: () -> Unit // new callback for quantity change
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = cartItems.size

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.cartItemName)
        private val priceTextView: TextView = itemView.findViewById(R.id.itemPriceTextView)
        private val quantityTextView: TextView = itemView.findViewById(R.id.itemQuantityTextView)
        private val increaseButton: Button = itemView.findViewById(R.id.increaseButton)
        private val decreaseButton: Button = itemView.findViewById(R.id.decreaseButton)
        private val removeButton: Button = itemView.findViewById(R.id.removeCartItem)
        private val cartItemImage: ImageView = itemView.findViewById(R.id.cartItemImage) // Updated line

        fun bind(item: CartItem) {
            nameTextView.text = item.name
            priceTextView.text = "R${item.price}"
            quantityTextView.text = item.quantity.toString()

            // Load image from drawable resource
            cartItemImage.setImageResource(item.imageResId)

            increaseButton.setOnClickListener {
                item.quantity++
                quantityTextView.text = item.quantity.toString()
                onQuantityChanged() // notify cart activity to update total
            }

            decreaseButton.setOnClickListener {
                if (item.quantity > 1) {
                    item.quantity--
                    quantityTextView.text = item.quantity.toString()
                    onQuantityChanged() // notify cart activity to update total
                }
            }

            removeButton.setOnClickListener {
                onItemRemoved(item)
            }
        }
    }
}
