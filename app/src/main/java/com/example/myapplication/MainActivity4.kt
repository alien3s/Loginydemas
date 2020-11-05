package app.com.androidloginjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity4 : AppCompatActivity() {
    var pasarId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
    }

    fun Login(view: View) {
        var user = findViewById<EditText>(R.id.x1)
        var pass = findViewById<EditText>(R.id.x2)
        var fileName = "ficheroInterno.json"
        var bufferedReader = BufferedReader(InputStreamReader(openFileInput(fileName)))
        var textoLeido = bufferedReader.readLine()
        val todo = StringBuilder()
        while (textoLeido != null) {
            todo.append(textoLeido + "\n")
            textoLeido = bufferedReader.readLine()
        }
        textoLeido = todo.toString()
        bufferedReader.close()

        val jsonObject = JSONObject(textoLeido)
        val jsonArray = jsonObject.optJSONArray("usuarios")
        for (i in 0 until jsonArray.length()) {

            val jsonObject = jsonArray.getJSONObject(i)
            if (jsonObject.optString("user").equals(user.text.toString()) and jsonObject.optString("contraseña").equals(pass.text.toString())) {
                val id = jsonObject.optString("id").toInt()
                pasarId = id
                var miIntent = Intent(this, MainActivity::class.java)
                miIntent.putExtra("id", pasarId.toString())
                startActivity(miIntent)
            }else{
                Toast.makeText(this, "The user or password are incorrect", Toast.LENGTH_LONG).show()

            }
        }
    }

    fun Cancelar(view: View) {
        onBackPressed()
    }


}