package liou.rayyuan.theamazingbehavior.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton

class FlingScrollingBehavior : CoordinatorLayout.Behavior<View> {

    constructor()

    constructor(context: Context, attr: AttributeSet): super(context, attr)

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes == View.SCROLL_AXIS_VERTICAL && child is ImageButton
    }

    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float): Boolean {
        if (velocityY > 0) {
            child.animate().rotation(360f).setInterpolator(DecelerateInterpolator()).setDuration(1500).start()
        } else {
            child.animate().rotation(-360f).setInterpolator(DecelerateInterpolator()).setDuration(1500).start()
        }

        // true means parent has consumed the event, ignore fling event.
        return false
    }
}