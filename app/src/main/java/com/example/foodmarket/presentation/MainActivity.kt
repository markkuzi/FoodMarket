package com.example.foodmarket.presentation

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.foodmarket.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageAccount: CircleImageView = findViewById(R.id.imageAccount)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        Picasso.get().load(ACCOUNT_URL).into(imageAccount)

        viewModel.setupToolbar.observe(this) {
            if (it.isSecondaryScreen) {
                supportActionBar?.title = it.title
                supportActionBar?.subtitle = null
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setLogo(null)
                supportActionBar?.setHomeAsUpIndicator(R.drawable.toolbar_back_image)
            }
            else {
                supportActionBar?.title = getString(R.string.city)
                supportActionBar?.subtitle = "12 Августа, 2023"
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setLogo(R.drawable.toolbar_image)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val ACCOUNT_URL = "https://s3-alpha-sig.figma.com/img/738e/6e77/a92971e6075b85d18be0de93205d90cb?Expires=1687132800&Signature=FCndYJlBm8TzTblrx4DM7V0imqSpU9dyyIVL2LpAf6P1W4xO0gsuJp53OVqWc1A-qzsUHRK8NKhJnfmZOybn7AV7~OQGYAeKe7dnvh2ywbE6k5ojSxoesLjHn1f6bUAAF66dpBswZxD4M-hegplqKA0FCK5IrU99uIQQ33w0~UfrGOvaIJexw4h1emgUoNYpE6wdlpHgVx~6C1mc-K-YqSqGBr8dIcQa90ZnWL~mDWtPq67oJBWVUFrelJGlHKgsekVmYdVzbf9sYZdEj5279pqdinp2ps66tsgNJk3p3VG0Uew9WviJ8Bp2VacU8Czs4Bg5nzCOI2yHLGP6LTkm1g__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
    }
}