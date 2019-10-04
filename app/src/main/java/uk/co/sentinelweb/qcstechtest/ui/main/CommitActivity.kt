package uk.co.sentinelweb.qcstechtest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uk.co.sentinelweb.qcstechtest.R

class CommitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CommitFragment.newInstance())
                    .commitNow()
        }
    }

}
