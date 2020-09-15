package nl.omererdem.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import nl.omererdem.madlevel2task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questions: ArrayList<Question> = Question.questions

    private val questionAdapter = QuestionAdapter(questions)

    private val SWIPE_LEFT_DIRECTION = 4
    private val SWIPE_RIGHT_DIRECTION = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    fun initViews() {
        binding.rvQuestions.layoutManager = LinearLayoutManager(this)
        binding.rvQuestions.adapter = questionAdapter
        questionAdapter.notifyDataSetChanged()
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (direction == SWIPE_LEFT_DIRECTION) {
                    checkAnswer(questions[position], false)
                } else if (direction == SWIPE_RIGHT_DIRECTION) {
                    checkAnswer(questions[position], true)
                }
                questionAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun checkAnswer(question: Question, answer: Boolean) {
        if (question.answer == answer) {
            questions.remove(question)
        } else {
            showSnackbar()
        }
    }

    private fun showSnackbar() {
        Snackbar.make(binding.rvQuestions, getString(R.string.wrong_answer), Snackbar.LENGTH_LONG).show()
    }
}