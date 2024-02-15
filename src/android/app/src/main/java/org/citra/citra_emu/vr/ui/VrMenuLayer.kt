package org.citra.citra_emu.vr.ui

import android.widget.Button
import android.widget.RelativeLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import org.citra.citra_emu.NativeLibrary
import org.citra.citra_emu.R
import org.citra.citra_emu.utils.EmulationLifecycleUtil
import org.citra.citra_emu.vr.VrActivity
import org.citra.citra_emu.vr.utils.VrMessageQueue

// vr in-game menu
class VrMenuLayer(activity: VrActivity) : VrUILayer(activity, R.layout.vr_menu_in_game) {
    private var menu: RelativeLayout? = null

    private var btnCloseMenu: Button? = null
    private var btnCloseGame: Button? = null
    private var btnRotateScreens: Button? = null
    private var btnMonoImg: Button? = null

    override fun onSurfaceCreated() {
        super.onSurfaceCreated()

        menu = window!!.findViewById(R.id.vr_menu)
        btnCloseMenu = window!!.findViewById(R.id.vr_menu_btn_close_menu)
        btnCloseGame = window!!.findViewById(R.id.vr_menu_btn_close_game)
        btnRotateScreens = window!!.findViewById(R.id.vr_menu_btn_rotation)
        btnMonoImg = window!!.findViewById(R.id.vr_menu_btn_mono)

        // needs to show cursor as well
        // borrowed code from keyboard
        menu!!.apply {
            requestFocus()
            WindowCompat.getInsetsController(window!!, window?.decorView!!)
                .show(WindowInsetsCompat.Type.ime())
        }

        btnCloseMenu?.setOnClickListener {
            VrMessageQueue.post(VrMessageQueue.MessageType.SHOW_MENU, 0)
        }
        btnCloseGame?.setOnClickListener {
            EmulationLifecycleUtil.closeGame()
        }
        btnRotateScreens?.setOnClickListener {
            NativeLibrary.switchOrientationVR()
        }
        btnMonoImg?.setOnClickListener {
            NativeLibrary.switchMonoStereoVR()
        }

    }

}