package liou.rayyuan.theamazingbehavior.widget

import android.content.Context
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.TextView

class DraggableTextView : TextView {

    private var slop = 0
    private var lastPositionX = 0f
    private var lastPositionY = 0f

    constructor(context: Context): super(context)

    constructor(context: Context, attr: AttributeSet): super(context, attr)

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr) {
        isClickable = true
        slop = ViewConfiguration.get(context).scaledTouchSlop
    }

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attr, defStyleAttr, defStyleRes)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return event?.let { motionEvent ->
            val action = motionEvent.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    lastPositionX = motionEvent.x
                    lastPositionY = motionEvent.y
                }
                MotionEvent.ACTION_MOVE -> {
                    val deltaX = (motionEvent.x - lastPositionX).toInt()
                    val deltaY = (motionEvent.y - lastPositionY).toInt()

                    if (Math.abs(deltaX) > slop || Math.abs(deltaY) > slop) {
                        ViewCompat.offsetLeftAndRight(this, deltaX)
                        ViewCompat.offsetTopAndBottom(this, deltaY)
                    }
                }
            }

            // return the last line
            true
        } ?: super.onTouchEvent(event)
    }
}