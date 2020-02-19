package com.androidwind.androidquick.ui.dialog.dialogactivity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.androidwind.androidquick.R
import com.androidwind.androidquick.ui.dialog.ViewHolder
import java.io.Serializable

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
abstract class BaseDialog(private val mContext: Context, @StyleRes private var mStyle: Int) : Dialog(mContext, mStyle) {

    @LayoutRes
    protected var mLayoutResId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    private fun initParams() {
        val layout: View by lazy {
            getLayoutInflater().inflate(mLayoutResId, null);
        }
        setContentView(layout)
        convertView(ViewHolder.create(layout), this)
    }

    abstract fun convertView(holder: ViewHolder, dialog: BaseDialog)

    interface ViewConvertListener : Serializable {

        fun convertView(holder: ViewHolder, dialog: BaseDialog)

        companion object {
            val serialVersionUID by lazy { System.currentTimeMillis() }
        }
    }
}