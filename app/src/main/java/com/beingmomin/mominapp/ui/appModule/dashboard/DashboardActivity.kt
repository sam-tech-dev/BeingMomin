package com.beingmomin.mominapp.ui.appModule.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.beingmomin.mominapp.BR
import com.beingmomin.mominapp.R
import com.beingmomin.mominapp.ViewModelProviderFactory
import com.beingmomin.mominapp.databinding.ActivityDashboardBinding
import com.beingmomin.mominapp.databinding.NavHeaderDashboardBinding
import com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.addPerson.AddPersonFragment
import com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.home.HomeFragment
import com.beingmomin.mominapp.ui.base.BaseActivity
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_dashboard.*
import javax.inject.Inject

class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>(), DashboardNavigator, HasSupportFragmentInjector, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var mActivityDashboardBinding: ActivityDashboardBinding
    lateinit var dashboardViewModel: DashboardViewModel

    override val layoutId: Int
        get() = R.layout.activity_dashboard

    override val viewModel: DashboardViewModel
        get() {
            if (!::dashboardViewModel.isInitialized) {
                dashboardViewModel = ViewModelProviders.of(this, factory).get(DashboardViewModel::class.java)
            }
            return dashboardViewModel
        }

    override val bindingVariable: Int
        get() = BR.viewModel


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, DashboardActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        mActivityDashboardBinding = getViewDataBinding()
    }


    fun setUp() {
        setSupportActionBar(tb_main)

        val mDrawerToggle = object : ActionBarDrawerToggle(
                this,
                drawer_main,
                tb_main,
                R.string.open_drawer,
                R.string.close_drawer) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                hideKeyboard()
            }
        }
        drawer_main.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

        setupNavMenu()

        navigation_view_dashboard.setCheckedItem(R.id.nav_item_home)
        onNavigationItemSelected(navigation_view_dashboard.getMenu().getItem(0))

    }

    private fun setupNavMenu() {
        val navHeaderMainBinding: NavHeaderDashboardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.nav_header_dashboard, navigation_view_dashboard, false)
        navigation_view_dashboard.addHeaderView(navHeaderMainBinding.getRoot())
        navHeaderMainBinding.setViewModel(dashboardViewModel)
        navigation_view_dashboard.setNavigationItemSelectedListener(this)
    }

    private fun showHomeFragment() {
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(HomeFragment.TAG)
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.fl_main, HomeFragment.newInstance(), HomeFragment.TAG)
                .commit()
    }

    private fun showAddPersonFragment() {
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(AddPersonFragment.TAG)
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.fl_main, AddPersonFragment.newInstance(), AddPersonFragment.TAG)
                .commit()
    }


    override fun onHandleError(throwable: Throwable) {
        showToast(throwable.message!!)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        drawer_main.closeDrawer(GravityCompat.START)
        when (p0.getItemId()) {
            R.id.nav_item_home -> {
                showHomeFragment()
                return true
            }
            else -> {
                return false
            }
        }
    }
}
