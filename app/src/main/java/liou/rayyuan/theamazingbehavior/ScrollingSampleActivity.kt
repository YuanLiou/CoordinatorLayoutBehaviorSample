package liou.rayyuan.theamazingbehavior

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_scrolling_sample.*
import liou.rayyuan.theamazingbehavior.adapter.SimpleTextAdapter

class ScrollingSampleActivity : AppCompatActivity() {

    private val simpleTextAdapter = SimpleTextAdapter()
    private val recyclerView: RecyclerView by bindView(R.id.activity_scrolling_recyclerview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling_sample)

        initRecyclerView()

        activity_scrolling_bottom_button.setOnClickListener {
            recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun initRecyclerView() {
        with(recyclerView) {
            val dividerItemDecoration = DividerItemDecoration(this.context,
                    (layoutManager as LinearLayoutManager).orientation)
            addItemDecoration(dividerItemDecoration)

            setHasFixedSize(true)
            adapter = simpleTextAdapter
        }

        val sampleTexts = resources.getStringArray(R.array.sample_names)
        repeat(4) {
            simpleTextAdapter.addText(*sampleTexts)
        }

    }

}