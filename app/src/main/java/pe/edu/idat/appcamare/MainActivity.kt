package pe.edu.idat.appcamare

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.result.contract.ActivityResultContracts
import pe.edu.idat.appcamare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCompartir.setOnClickListener(this)
        binding.btnTomarFoto.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCompartir -> compartirFoto()
            R.id.btnTomarFoto -> tomarFoto()
        }
    }

    private fun tomarFoto() {
        abrirCamara.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private fun compartirFoto() {

    }

    private val abrirCamara = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data!!
            val imagenBitmap = data.extras!!.get("data") as Bitmap
            binding.ivFoto.setImageBitmap(imagenBitmap)
        }
    }
}