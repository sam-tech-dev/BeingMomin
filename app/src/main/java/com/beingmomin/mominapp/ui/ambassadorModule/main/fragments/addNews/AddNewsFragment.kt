package com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.addNews

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
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
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
import com.beingmomin.mominapp.data.network.models.AddNewsApiBody
import com.beingmomin.mominapp.databinding.FragmentAddPersonBinding
import com.beingmomin.mominapp.ui.base.BaseFragment
import com.beingmomin.mominapp.utils.CommonUtils
import kotlinx.android.synthetic.main.fragment_add_news.*
import kotlinx.android.synthetic.main.layout_for_camera_gallery_selection.*
import kotlinx.android.synthetic.main.layout_person_tag.view.*
import java.io.File
import javax.inject.Inject


class AddNewsFragment : BaseFragment<FragmentAddPersonBinding, AddNewsViewModel>(), AddNewsNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var addNewsViewModel: AddNewsViewModel

    val PERMISSION_CODE = 100
    val PICK_FROM_CAMERA = 200
    val PICK_FROM_GALLERY = 300

    val ITEMS_NEWS_CATEGORIES = arrayOf("New born", "Mayyat", "Divorce", "Engagement", "Marrige", "Sammelan", "Other")


     var attachmentFile: File?=null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_add_news

    override val viewModel: AddNewsViewModel
        get() {
            if (!::addNewsViewModel.isInitialized) {
                addNewsViewModel = ViewModelProviders.of(this, factory).get(AddNewsViewModel::class.java)
            }
            return addNewsViewModel
        }

    override fun goBack() {
        baseActivity?.onFragmentDetached(TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNewsViewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pse_tag_person.title = "Search persons"
        addNewsViewModel.setLocalityInSearchDialog(pse_tag_person)

        val adapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_item, ITEMS_NEWS_CATEGORIES)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_news_category.setAdapter(adapter)

        sp_news_category.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                sp_news_category.isEnableErrorLabel = true
                sp_news_category.setError("Please select news category")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position >= 0) {
                    val news_cateogry = ITEMS_NEWS_CATEGORIES.get(position)

                    if (news_cateogry.equals("Other")) {
                        til_news_category.visibility = View.VISIBLE
                    }
                }
            }
        })


        pse_tag_person.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (pse_tag_person.text!!.length > 0) {
                    val personId = pse_tag_person.tag as Int
                    val personName = s.toString()
                    pse_tag_person.setText("")

                    addTaggedPerson(personId, personName)
                }

            }
        })


        bt_browse_image.setOnClickListener {
            handlePermission()
        }

        bt_submit.setOnClickListener {
            if (CommonUtils.validateEditText(et_news_title, "Enter news title") && CommonUtils.validateEditText(et_news_description, "Enter news description") &&
                    CommonUtils.validateEditText(dse_news_date, "Please enter date of news") && validateNewsCategory()) {

                var newsCategory = ""
                newsCategory = ITEMS_NEWS_CATEGORIES.get(sp_news_category.selectedItemPosition)
                if (newsCategory.equals("Other")) {
                    newsCategory = et_news_category.text.toString().trim()
                }

                val newsTitle = et_news_title.text.toString().trim()
                val newsDescription = et_news_description.text.toString().trim()
                val newsDate = dse_news_date.tag as String

                val taggedPersons = StringBuilder("")
                for (i in 0 until fl_tagged_persons.childCount) {
                    val viewChild = fl_tagged_persons.getChildAt(i)
                    if (i == 0) {
                        taggedPersons.append(viewChild.tv_person_name.tag as Int)
                    } else {
                        taggedPersons.append(",${viewChild.tv_person_name.tag as Int}")
                    }
                }

                Log.d("az","tagged Id "+taggedPersons)
                val request = AddNewsApiBody(newsTitle, newsDescription, newsDate, newsCategory, taggedPersons.toString(),
                        "", "")

                viewModel.requestToAddNews(request, attachmentFile)
            }
        }
    }

    private fun addTaggedPerson(personId: Int, personName: String) {

        val tagView = LayoutInflater.from(context).inflate(R.layout.layout_person_tag, null)
        tagView.tv_person_name.text = personName
        tagView.tv_person_name.tag = personId
        tagView.ib_tag_remove.setOnClickListener {
            fl_tagged_persons.removeView(tagView)
        }
        fl_tagged_persons.addView(tagView)
    }

    private fun validateNewsCategory(): Boolean {

        if (sp_news_category.selectedItemPosition != 0) {
            var profession = ITEMS_NEWS_CATEGORIES.get(sp_news_category.selectedItemPosition)
            if (profession.equals("Other")) {
                profession = et_news_category.text.toString().trim()
                if (profession.isEmpty()) {
                    return false
                } else {
                    return true
                }
            } else {
                return true
            }
        } else {
            sp_news_category.setError("Please select news category")
            return false
        }
    }

    companion object {
        val TAG = AddNewsFragment::class.java.simpleName
        fun newInstance(): AddNewsFragment {
            val args = Bundle()
            val fragment = AddNewsFragment()
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

        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {
                PICK_FROM_GALLERY -> {
                    if (data != null) {
                        val selectedImage = data.getData()
                        val path = getPathFromURI(selectedImage)
                        iv_news_attachment.visibility = View.VISIBLE
                        iv_news_attachment.setImageBitmap(BitmapFactory.decodeFile(path))
                        attachmentFile = File(path)

                    }
                }

                PICK_FROM_CAMERA -> Log.d("az", "")

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


    override fun handleError(throwable: Throwable) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun getFileExt(fileName: String): String {
        return fileName.substring(fileName.lastIndexOf(".") + 1)
    }

}
