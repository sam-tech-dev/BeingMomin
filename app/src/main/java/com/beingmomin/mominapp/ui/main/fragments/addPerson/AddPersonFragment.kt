package com.beingmomin.mominapp.ui.main.fragments.addPerson

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.customs.customEditText.CustomEditTextRegular
import com.beingmomin.mominapp.databinding.FragmentAddPersonBinding
import com.beingmomin.mominapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_person.*
import kotlinx.android.synthetic.main.layout_for_camera_gallery_selection.*
import javax.inject.Inject


class AddPersonFragment : BaseFragment<FragmentAddPersonBinding, AddPersonViewModel>(), AddPersonNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var mAddPersonViewModel: AddPersonViewModel

    val PERMISSION_CODE = 100
    val PICK_FROM_CAMERA = 200
    val PICK_FROM_GALLERY = 300

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_add_person

    override val viewModel: AddPersonViewModel
        get() {
            if (!::mAddPersonViewModel.isInitialized) {
                mAddPersonViewModel = ViewModelProviders.of(this, factory).get(AddPersonViewModel::class.java)
            }
            return mAddPersonViewModel
        }

    override fun goBack() {
        baseActivity?.onFragmentDetached(TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAddPersonViewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_father_name.gender = "Male"
        et_father_name.title = "Father's Name"
        et_mother_name.gender = "Female"
        et_mother_name.title = "mother's name"
        et_partner_name.gender = "Female"
        et_partner_name.title = "Partner's name"

        viewModel.setLocality()

        val ITEMS = arrayOf("Single", "Married", "Divorced", "Engaged")
        val adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, ITEMS)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_marital_status.setAdapter(adapter)

        sp_marital_status.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                sp_marital_status.isEnableErrorLabel = true
                sp_marital_status.setError("Please select marital status")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position >= 0) {
                    val marital_status = ITEMS.get(position)

                    when (marital_status) {
                        "Married" -> til_parner_name.visibility = View.VISIBLE

                        "Single" -> til_parner_name.visibility = View.GONE

                        "Engaged" -> til_parner_name.visibility = View.VISIBLE
                    }
                }
            }
        })


        val ITEMS_EDUCATION = arrayOf("Below 10th", "Secondary", "Higher secondary", "Diploma", "Graduate", "Post Graduate", "Doctorate")
        val education_adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, ITEMS_EDUCATION)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_education_level.setAdapter(education_adapter)

        val ITEMS_PROFESSION = arrayOf("Student", "Labour", "Farmer", "Teacher", "Doctor", "Engineer", "Other")
        val profession_adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, ITEMS_PROFESSION)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_profession.setAdapter(profession_adapter)

        sp_profession.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                sp_profession.isEnableErrorLabel = true
                sp_profession.setError("Please select profession")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position >= 0) {
                    val profession = ITEMS_PROFESSION.get(position)

                    when (profession) {
                        "Other" -> til_profession.visibility = View.VISIBLE
                        else -> til_profession.visibility = View.GONE
                    }
                }
            }
        })

        bt_browse_image.setOnClickListener {
            handlePermission()
        }

       bt_submit.setOnClickListener {
           if(validateEditText(et_first_name) && validateEditText(et_last_name) &&
                   validateEditText(et_first_name) ){


           }

       }
    }


    override fun setLocality(locality: String) {
        et_father_name.locality = locality
        et_mother_name.locality = locality
        et_partner_name.locality = locality
    }

    companion object {
        val TAG = AddPersonFragment::class.java.simpleName
        fun newInstance(): AddPersonFragment {
            val args = Bundle()
            val fragment = AddPersonFragment()
            fragment.setArguments(args)
            return fragment
        }
    }


    private fun handlePermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            showDialogForSelection()
        } else {
            if (checkCameraPermission() || checkStorageWritePermission() || checkStrorageReadPermission()) {
                //ask for permission
                activity?.let {
                    ActivityCompat.requestPermissions(it,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
                            PERMISSION_CODE)
                }
            } else {
                showDialogForSelection()
            }
        }


    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {

            PERMISSION_CODE -> {
                var permissionFlag = true;
                for (i in permissions.indices) {
                    val permission = permissions[i]
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        permissionFlag = false
                    }
                }

                if (permissionFlag) {
                    Toast.makeText(context, "Please grant all permissions to take photos", Toast.LENGTH_LONG).show()
                } else {
                    showDialogForSelection()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {

            when(requestCode){

                PICK_FROM_GALLERY->{
                    if(data!=null){
                        val selectedImage = data.getData()
                        val path = getPathFromURI(selectedImage)
                        iv_attached_profile.visibility=View.VISIBLE
                        iv_attached_profile.setImageBitmap(BitmapFactory.decodeFile(path))
                    }
                }

                PICK_FROM_CAMERA-> Log.d("az","")

            }

        }

    }

    fun checkStorageWritePermission(): Boolean {
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            return false
        }
    }

    fun checkStrorageReadPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            return false
        }
    }

    fun checkCameraPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            return false
        }
    }

    fun showDialogForSelection() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_for_camera_gallery_selection)
        dialog.ll_gallery.setOnClickListener {
            openGallery()
            dialog.dismiss()
        }
        dialog.ll_camera.setOnClickListener {
            //TODO : CAMERA IMAGE IMPLEMENTATION
        }

        dialog.show()
    }

    fun openGallery() {
        val i = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(i, PICK_FROM_GALLERY)
    }

    /* Get the real path from the URI */
    fun getPathFromURI(contentUri: Uri): String? {
        var res: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context!!.getContentResolver().query(contentUri, proj, null, null, null)
        if (cursor.moveToFirst()) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(column_index)
        }
        cursor.close()
        return res
    }

    fun  validateEditText(edittext:CustomEditTextRegular):Boolean{
        if(edittext.text.toString().isNotEmpty()){
            return true
        }else{
            edittext.setError("Please fill mentioned detail")
            return false
        }
    }


}
