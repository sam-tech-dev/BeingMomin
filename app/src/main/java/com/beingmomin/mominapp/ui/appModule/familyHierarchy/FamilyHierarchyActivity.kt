package com.beingmomin.mominapp.ui.appModule.familyHierarchy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.data.network.models.Children
import com.beingmomin.mominapp.databinding.ActivityFamilyHierarchyBinding
import com.beingmomin.mominapp.ui.base.BaseActivity
import com.beingmomin.mominapp.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_family_hierarchy.*
import kotlinx.android.synthetic.main.layout_hierarchy_child.view.*
import javax.inject.Inject


class FamilyHierarchyActivity : BaseActivity<ActivityFamilyHierarchyBinding, FamilyHierarchyViewModel>(), FamilyHierarchyNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var activityFamilyHierarchyBinding: ActivityFamilyHierarchyBinding
    lateinit var familyHierarchyViewModel: FamilyHierarchyViewModel


    override val layoutId: Int
        get() = R.layout.activity_family_hierarchy

    override val viewModel: FamilyHierarchyViewModel
        get() {
            if (!::familyHierarchyViewModel.isInitialized) {
                familyHierarchyViewModel = ViewModelProviders.of(this, factory).get(FamilyHierarchyViewModel::class.java)
            }
            return familyHierarchyViewModel
        }

    override val bindingVariable: Int
        get() = BR.viewModel

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FamilyHierarchyActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        activityFamilyHierarchyBinding = getViewDataBinding()

        viewModel.loadFamilyHierarchy(intent.extras)
    }


    override fun onHandleError(throwable: Throwable) {
        showToast(throwable.message!!)
    }


    override fun inflateHierarchy(root: Children?, child: Children, index: Int) {

        val personView = layoutInflater.inflate(R.layout.layout_hierarchy_child, null)
        personView.tv_person_name.text = child.name

        if (child.children == null || child.children.size == 0) {
            personView.v_vertical_bottom_line.visibility = View.INVISIBLE
        } else {
            val lineParams = personView.v_horizontal_top_line_left.layoutParams
            lineParams.width = ViewUtils.dpToPx((30f * child.spaceVal))
            personView.v_horizontal_top_line_left.layoutParams = lineParams

            val lineParamsRight = personView.v_horizontal_top_line_right.layoutParams
            lineParamsRight.width = ViewUtils.dpToPx((30f * child.spaceVal))
            personView.v_horizontal_top_line_right.layoutParams = lineParamsRight
        }

        if (root != null) {
            if (index == 0) {
                personView.v_horizontal_top_line_left.visibility = View.INVISIBLE
                if (root.children.size == 1) {
                    personView.v_horizontal_top_line_right.visibility = View.INVISIBLE
                }
            } else if (index == root.children.size - 1) {
                personView.v_horizontal_top_line_right.visibility = View.INVISIBLE
            }
        } else {
            personView.v_horizontal_top_line_left.visibility = View.INVISIBLE
            personView.v_horizontal_top_line_right.visibility = View.INVISIBLE
        }

        personView.id = child.personId
        cnstrt_hierarchy.addView(personView)

        val constraintSet = ConstraintSet()
        constraintSet.clone(cnstrt_hierarchy)

        if (child.fatherId == 0) {
            constraintSet.connect(personView.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            constraintSet.connect(personView.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
            constraintSet.connect(personView.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT)
        } else {
            if (index == 0) {
                constraintSet.connect(child.personId, ConstraintSet.TOP, child.fatherId, ConstraintSet.BOTTOM)
                constraintSet.connect(child.personId, ConstraintSet.LEFT, child.fatherId, ConstraintSet.LEFT)
            } else {
                constraintSet.connect(child.personId, ConstraintSet.TOP, child.fatherId, ConstraintSet.BOTTOM)
                constraintSet.connect(child.personId, ConstraintSet.LEFT, root!!.children.get(index - 1).personId, ConstraintSet.RIGHT)
            }
        }
        constraintSet.applyTo(cnstrt_hierarchy)

        if (child.children != null) {
            child.children.forEachIndexed { counter, son ->
                inflateHierarchy(child, son, counter)
            }
        }
    }
}
