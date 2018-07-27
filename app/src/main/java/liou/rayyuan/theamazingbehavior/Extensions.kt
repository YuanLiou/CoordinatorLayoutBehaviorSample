package liou.rayyuan.theamazingbehavior

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View

fun <T: View> Activity.bindView(@IdRes resId: Int): Lazy<T> = lazy {
    findViewById<T>(resId)
}

