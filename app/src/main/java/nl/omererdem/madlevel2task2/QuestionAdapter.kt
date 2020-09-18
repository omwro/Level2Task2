package nl.omererdem.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nl.omererdem.madlevel2task2.databinding.ItemQuestionBinding

class QuestionAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemQuestionBinding.bind(itemView)

        // Bind questions to the textview
        fun databind(question: Question) {
            binding.tvQuestion.text = question.question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }

    // Bind question to the recyclerview
    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        holder.databind(questions[position])
    }

    // Get item size
    override fun getItemCount(): Int {
        return questions.size
    }
}