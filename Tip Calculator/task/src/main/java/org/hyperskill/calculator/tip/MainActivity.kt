package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import android.text.TextWatcher as TextWatcher

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var slider: Slider
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)
        slider = findViewById(R.id.slider)

        textView.visibility = TextView.INVISIBLE

        editText = findViewById<EditText>(R.id.edit_text)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                updateTextView(s.toString(), slider.value)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        
        slider.addOnChangeListener(Slider.OnChangeListener { slider, _, _ ->
            updateTextView(editText.text.toString(), slider.value)
        })

    }

    private fun updateTextView(s: String, value: Float) {
        if (s.isNotBlank()) {
            val tipPercentage = value.toInt()
            val tipAmount = "%.2f".format(s.toDouble() * tipPercentage / 100)
            val result = "Tip amount: $tipAmount"
            textView.text = result
            textView.visibility = TextView.VISIBLE
        } else {
            textView.visibility = TextView.INVISIBLE
        }
    }
}