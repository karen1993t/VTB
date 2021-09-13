package com.vtb.vtb_project.ui.open_vtb_card_steps


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vtb.vtb_project.R
import com.vtb.vtb_project.databinding.FragmentPassportPhotoBinding


class PassportPhotoFragment : Fragment() {
    var showBinding: FragmentPassportPhotoBinding? = null
    private lateinit var currentPhotoPath: String
    private lateinit var uriPhotoPassport: Uri
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        uriPhotoPassport = Uri.parse("")
        showBinding = FragmentPassportPhotoBinding.inflate(inflater)
        return showBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBinding?.btnClose?.setOnClickListener {
            showBinding?.root?.let { view ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_passportPhotoFragment_to_home)
            }
        }
        showBinding?.btnGoToCapturePassportPhoto?.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_go_to_detectPassportPhoto)

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        showBinding = null
    }
}