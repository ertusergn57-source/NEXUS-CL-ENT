package com.nexus.client

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private val accent = Color.rgb(117, 92, 255)
    private val bg = Color.rgb(13, 15, 24)
    private val panel = Color.rgb(27, 30, 44)
    private val fg = Color.rgb(241, 242, 255)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg

        val scroll = ScrollView(this).apply { setBackgroundColor(bg) }
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(24), dp(18), dp(24))
        }
        scroll.addView(root)
        setContentView(scroll)

        root.addView(TextView(this).apply {
            text = "NEXUS"
            textSize = 34f
            setTextColor(accent)
            typeface = Typeface.create("sans-serif-black", Typeface.BOLD)
        })
        root.addView(label("BEDROCK CLIENT • ANDROID", 12f, Color.LTGRAY))
        root.addView(space(16))
        root.addView(TextView(this).apply {
            text = "STARTER BUILD  •  v0.1.0\nBu başlangıç uygulaması Minecraft'ı açmayı dener ve modül arayüzü sunar. Oyun içine modül enjekte etmez."
            textSize = 14f
            setTextColor(fg)
            setPadding(dp(14), dp(14), dp(14), dp(14))
            setBackgroundColor(panel)
        })
        root.addView(space(14))
        root.addView(actionButton("MINECRAFT'I AÇ") { launchMinecraft() })
        root.addView(space(18))
        root.addView(label("MODÜL PANELİ • PROTOTİP", 18f, fg))

        val groups = listOf(
            "HUD" to listOf("FPS göstergesi", "CPS sayacı", "WASD göstergesi", "LMB / RMB göstergesi", "Koordinat HUD'u"),
            "PVP" to listOf("Crystal PvP yardımcı paneli", "Vuruş göstergesi"),
            "GÖRSEL" to listOf("Arayüz teması", "Düşük ateş paketi desteği"),
            "DÜNYA" to listOf("Waypoint arayüzü", "Mini harita arayüzü")
        )
        groups.forEach { (group, modules) ->
            root.addView(space(14))
            root.addView(label(group, 16f, accent))
            modules.forEach { module ->
                val row = LinearLayout(this).apply {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL
                    setPadding(dp(10), dp(7), dp(6), dp(7))
                    setBackgroundColor(panel)
                }
                row.addView(TextView(this).apply {
                    text = module
                    textSize = 14f
                    setTextColor(fg)
                    layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                })
                row.addView(Button(this).apply {
                    text = "DEMO"
                    isAllCaps = false
                    setOnClickListener {
                        Toast.makeText(this@MainActivity,
                            "$module: Bu sürümde yalnızca arayüz örneği; Minecraft içinde çalışmıyor.",
                            Toast.LENGTH_LONG).show()
                    }
                })
                root.addView(row)
                root.addView(space(5))
            }
        }
        root.addView(space(16))
        root.addView(label("Not: Gerçek oyun içi HUD, mini harita ve PvP işlevleri için ayrıca uyumlu Minecraft entegrasyonu gerekir.", 12f, Color.LTGRAY))
    }

    private fun launchMinecraft() {
        val launch = packageManager.getLaunchIntentForPackage("com.mojang.minecraftpe")
        if (launch == null) {
            Toast.makeText(this, "Minecraft uygulaması bulunamadı. Resmî Minecraft uygulamasının kurulu olduğundan emin ol.", Toast.LENGTH_LONG).show()
        } else {
            try { startActivity(launch) }
            catch (_: Exception) { Toast.makeText(this, "Minecraft başlatılamadı.", Toast.LENGTH_LONG).show() }
        }
    }

    private fun actionButton(title: String, action: () -> Unit) = Button(this).apply {
        text = title
        isAllCaps = false
        setTextColor(Color.WHITE)
        setBackgroundColor(accent)
        setOnClickListener { action() }
        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    private fun label(value: String, size: Float, color: Int) = TextView(this).apply {
        text = value; textSize = size; setTextColor(color)
    }
    private fun space(value: Int) = View(this).apply {
        layoutParams = LinearLayout.LayoutParams(1, dp(value))
    }
    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}
