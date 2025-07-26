package com.example.news_eat_fronted

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.news_eat_fronted.databinding.ActivityMainBinding
import com.example.news_eat_fronted.presentation.ui.BookmarkFragment
import com.example.news_eat_fronted.presentation.ui.CategoryFragment
import com.example.news_eat_fronted.presentation.ui.HomeFragment
import com.example.news_eat_fronted.presentation.ui.MyPageFragment
import com.example.news_eat_fronted.util.base.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) replaceFragment(HomeFragment())

        clickBottomNavigation()
    }

    private fun clickBottomNavigation() {
        binding.bnvNewsEat.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menu_category -> {
                    replaceFragment(CategoryFragment())
                    true
                }
                R.id.menu_bookmark -> {
                    replaceFragment(BookmarkFragment())
                    true
                }
                R.id.menu_mypage -> {
                    replaceFragment(MyPageFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_news_eat, fragment)
            .commitNowAllowingStateLoss()
    }
}