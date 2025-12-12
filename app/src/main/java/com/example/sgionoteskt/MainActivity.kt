package com.example.sgionoteskt

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.sgionoteskt.features.etiqueta.view.EtiquetaFragment
import com.example.sgionoteskt.features.nota.view.NotaFragment
import com.example.sgionoteskt.features.papelera.view.PapeleraFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.Objects

class MainActivity : AppCompatActivity() {

    private lateinit var notaFragment: NotaFragment
    private lateinit var etiquetaFragment: EtiquetaFragment
    private lateinit var papeleraFragment: PapeleraFragment

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigation: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        confToolbarNav()
    }

    private fun init() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

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

        loadFragment(notaFragment)
    }

    private fun confToolbarNav() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigation = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigation.setNavigationItemSelectedListener { item ->
            seleccionNavegacion(item.itemId)
            true
        }
    }

    private fun seleccionNavegacion(itemID: Int) {
        drawerLayout.closeDrawer(GravityCompat.START)

        Handler(Looper.getMainLooper()).postDelayed({
            when (itemID) {
                R.id.notes -> loadFragment(notaFragment)
                R.id.tags -> loadFragment(etiquetaFragment)
                R.id.trash -> loadFragment(papeleraFragment)
            }
        }, 250)
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor, fragment)
            .commit()
    }

}