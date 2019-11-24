package com.github.takahirom.empty

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewGroup = findViewById(R.id.checks) as ViewGroup
        View::class.java.declaredFields
            .filter { it.name.startsWith("SYSTEM_UI_FLAG") }
            .map {field->
                viewGroup.addView(CheckBox(this).apply {
                    text = field.name
                    setOnCheckedChangeListener { buttonView, isChecked ->
                        if (isChecked) {
                            systemUiVisibility = systemUiVisibility or field.getInt(null)
                        }else{
                            systemUiVisibility = systemUiVisibility and field.getInt(null).inv()
                        }
                    }
                })
            }
    }
}
