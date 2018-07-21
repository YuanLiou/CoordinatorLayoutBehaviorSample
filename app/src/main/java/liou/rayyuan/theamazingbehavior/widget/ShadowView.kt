package liou.rayyuan.theamazingbehavior.widget

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import liou.rayyuan.theamazingbehavior.R
import liou.rayyuan.theamazingbehavior.behavior.ShadowBehavior

class ShadowView : AppCompatImageView, CoordinatorLayout.AttachedBehavior {

    constructor(context: Context): super(context) {
        setImageResource(R.drawable.shadow)
    }

    constructor(context: Context, attr: AttributeSet): super(context, attr) {
        setImageResource(R.drawable.shadow)
    }

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int): super(context, attr, defStyleAttr) {
        setImageResource(R.drawable.shadow)
    }

    override fun getBehavior(): CoordinatorLayout.Behavior<*> = ShadowBehavior()

}