package com.vengateshm.teamlineupview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.vengateshm.teamlineupview.databinding.ActivityMainBinding
import com.vengateshm.teamlineupview.teamLineUpView.Member

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mutableListOf<Member>()
            .apply {
                add(Member("Neymar", null, 2.0, 5.0))
                add(Member("Allison", null, 1.0, 3.0))
                add(Member("Virgil", null, 3.0, 2.0))
                add(Member("Messi", null, 4.0, 4.0))
            }.also {
                binding.teamView.bind(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.teamColor,
                        this.theme
                    ), it
                )
            }
    }
}