package liou.rayyuan.theamazingbehavior.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import liou.rayyuan.theamazingbehavior.widget.DraggableTextView

class ShadowBehavior : CoordinatorLayout.Behavior<View> {

    constructor()

    constructor(context: Context, attr: AttributeSet)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is DraggableTextView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val horizontalMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, parent.resources.displayMetrics)
        val verticalMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, parent.resources.displayMetrics)

        // Change the child position
        child.x = dependency.x - horizontalMargin    // move to right
        child.y = dependency.y

        // if view moved or size changed, return true
        return true
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        super.onDependentViewRemoved(parent, child, dependency)
        // When dependency view has been removed.
        // Do something with it.
        child.visibility = View.GONE
    }
}