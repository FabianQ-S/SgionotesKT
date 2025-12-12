package com.example.sgionoteskt

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.sgionoteskt.features.etiqueta.view.EtiquetaFragment
import com.example.sgionoteskt.features.nota.view.NotaFragment
import com.example.sgionoteskt.features.nota_detalle.view.NotaDetalleActivity
import com.example.sgionoteskt.features.papelera.view.PapeleraFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var notaFragment: NotaFragment
    private lateinit var etiquetaFragment: EtiquetaFragment
    private lateinit var papeleraFragment: PapeleraFragment

    private lateinit var navigation: BottomNavigationView
    private lateinit var btnNuevaNota: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        navigation = findViewById(R.id.bottom_navigation)

        notaFragment = NotaFragment()
        etiquetaFragment = EtiquetaFragment()
        papeleraFragment = PapeleraFragment()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_content)) { view, insets ->
            val statusBar = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.setPadding(
                view.paddingLeft,
                statusBar.top,
                view.paddingRight,
                view.paddingBottom
            )
            insets
        }

        btnNuevaNota = findViewById(R.id.fab_add_note)

        btnNuevaNota.setOnClickListener {
            val intent = Intent(this,
                NotaDetalleActivity::class.java)
            startActivity(intent)
        }

        loadFragment(notaFragment)

        navigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.notes -> {
                    loadFragment(notaFragment)
                    true
                }
                R.id.tags -> {
                    loadFragment(etiquetaFragment)
                    true
                }
                R.id.trash -> {
                    loadFragment(papeleraFragment)
                    true
                }
                else -> false
            }
        }
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor, fragment)
            .commit()
    }

}