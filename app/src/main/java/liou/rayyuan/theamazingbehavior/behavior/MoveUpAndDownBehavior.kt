package liou.rayyuan.theamazingbehavior.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import liou.rayyuan.theamazingbehavior.R

class MoveUpAndDownBehavior : CoordinatorLayout.Behavior<View> {

    constructor()

    constructor(context: Context, attr: AttributeSet): super(context, attr)

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        // This should return true to receive nest scroll event
        return child.id == R.id.activity_scrolling_bottom_button && axes == View.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val oldTranslation = child.translationY
        val newTranslation = oldTranslation + dy

        when {
            newTranslation > child.height -> child.translationY = child.height.toFloat()
            newTranslation < 0 -> child.translationY = 0f
            else -> child.translationY = newTranslation
        }
    }
}