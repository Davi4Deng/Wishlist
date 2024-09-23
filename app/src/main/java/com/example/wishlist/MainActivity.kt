package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView setup
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val wishlistAdapter = WishlistAdapter(mutableListOf())
        recyclerView.adapter = wishlistAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Input fields and button
        val itemNameInput = findViewById<EditText>(R.id.editTextText2)
        val priceInput = findViewById<EditText>(R.id.editTextText4)
        val urlInput = findViewById<EditText>(R.id.editTextText3)
        val submitButton = findViewById<Button>(R.id.button)

        // Submit button click listener
        submitButton.setOnClickListener {
            // Get user input
            val itemName = itemNameInput.text.toString()
            val price = priceInput.text.toString()
            var url = urlInput.text.toString()

            // 如果用户没有输入 "http://" 或 "https://"，为URL添加前缀
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://$url"
            }

            // Create new wishlist item
            val newItem = WishlistItem(itemName, price, url)

            // Add the new item to the RecyclerView
            wishlistAdapter.addItem(newItem)

            // Clear input fields
            itemNameInput.text.clear()
            priceInput.text.clear()
            urlInput.text.clear()
        }
    }
}
