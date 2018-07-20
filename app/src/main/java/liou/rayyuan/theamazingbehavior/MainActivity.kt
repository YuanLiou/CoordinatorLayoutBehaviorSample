package liou.rayyuan.theamazingbehavior

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_activity_root_view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                main_activity_root_view.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val verticalPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18f, resources.displayMetrics)
                val horizontalPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, resources.displayMetrics)
                val layoutParams = main_activity_shadow_view.layoutParams
                        as CoordinatorLayout.LayoutParams
                layoutParams.width = draggable_textview.width + horizontalPadding.toInt()
                layoutParams.height = draggable_textview.height + verticalPadding.toInt()
                main_activity_shadow_view.layoutParams = layoutParams
            }
        })
    }
}
