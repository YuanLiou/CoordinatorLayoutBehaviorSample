package liou.rayyuan.theamazingbehavior

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.*
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import liou.rayyuan.theamazingbehavior.behavior.ShadowBehavior
import liou.rayyuan.theamazingbehavior.widget.DraggableTextView

class MainActivity : AppCompatActivity() {

    private var viewRemoved = false
    private lateinit var toggleMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_activity_toolbar))

        // set button behavior
        val followerButton: Button = findViewById(R.id.main_activity_go_nest_scrolling_page)
        val layoutParams = followerButton.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = ShadowBehavior()
        followerButton.layoutParams = layoutParams

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

        main_activity_go_nest_scrolling_page.setOnClickListener {
            val intent = Intent(this, ScrollingSampleActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        menu?.run {
            menuInflater.inflate(R.menu.menu_main_activity, this)
            toggleMenuItem = findItem(R.id.menu_main_activity_reset)

            super.onCreateOptionsMenu(this)
        } ?: false

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            item?.let {
                when (it.itemId) {
                    R.id.menu_main_activity_reset -> {
                        draggableTextViewToggle()

                        if (!viewRemoved) {
                            // make views visible again
                            if (!main_activity_shadow_view.isShown) {
                                main_activity_shadow_view.visibility = View.VISIBLE
                            }

                            if (!main_activity_go_nest_scrolling_page.isShown) {
                                main_activity_go_nest_scrolling_page.visibility = View.VISIBLE
                            }
                        }

                        if (this::toggleMenuItem.isInitialized) {
                            val iconResId = if (viewRemoved) {
                                R.drawable.ic_remove_red_eye_black_24dp
                            } else {
                                R.drawable.ic_close_black_24dp
                            }

                            toggleMenuItem.setIcon(iconResId)
                        }

                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            } ?: false

    private fun draggableTextViewToggle() {
        if (viewRemoved) {
            // Add back view
            val draggableTextView = DraggableTextView(this).apply {
                id = R.id.draggable_textview
                text = "I'm a draggable text"
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)

                val padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f,
                        resources.displayMetrics).toInt()
                setPadding(padding, padding, padding, padding)

                setBackgroundColor(Color.parseColor("#FDBD2E"))
            }

            val topMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, resources.displayMetrics)
            draggableTextView.layoutParams = CoordinatorLayout.LayoutParams(
                    CoordinatorLayout.LayoutParams.WRAP_CONTENT,
                    CoordinatorLayout.LayoutParams.WRAP_CONTENT
            ).also {
                it.topMargin = topMargin.toInt()
            }

            val parentView: CoordinatorLayout = findViewById(R.id.main_activity_root_view)
            parentView.addView(draggableTextView)
            parentView.requestLayout()
        } else {
            // Remove the view
            val draggableTextView = findViewById<DraggableTextView>(R.id.draggable_textview)
            (draggableTextView.parent as ViewGroup).removeView(draggableTextView)
        }

        viewRemoved = !viewRemoved
    }
}
