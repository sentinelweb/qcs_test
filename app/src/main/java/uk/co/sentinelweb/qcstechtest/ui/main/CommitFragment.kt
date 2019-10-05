package uk.co.sentinelweb.qcstechtest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import uk.co.sentinelweb.qcstechtest.R

class CommitFragment : Fragment() {

    companion object {
        fun newInstance() = CommitFragment()
    }

    private val commitViewModel: CommitViewModel by viewModel()
    private lateinit var commitListRecycler: RecyclerView
    private lateinit var commitListAdapter: CommitListRecyclerViewAdapter
    private lateinit var commitListSwipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflate = inflater.inflate(R.layout.main_fragment, container, false)
        commitListRecycler = inflate.findViewById(R.id.commit_list_recycler)
        commitListSwipe = inflate.findViewById(R.id.commit_list_swipe)
        return inflate
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView(commitListRecycler)
    }

    override fun onDestroyView() {
        commitViewModel.release()
        super.onDestroyView()
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val commitsLive = commitViewModel.commits()
        commitListAdapter = CommitListRecyclerViewAdapter(
            commitsLive, this
        )
        // hide the refresh
        commitsLive.observe(this, Observer { commitListSwipe.isRefreshing = false })

        recyclerView.adapter = commitListAdapter

        commitListSwipe.setOnRefreshListener {
            commitViewModel.reloadData()
        }

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
