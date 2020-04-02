package work.nbcc.rickandmorty


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import work.nbcc.rickandmorty.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        setHasOptionsMenu(true)
        return binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
    }


    private var questionIndex = 0;


    private val listQuestion = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionIndex = savedInstanceState?.getInt("questionId") ?: 0

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateView(questionIndex)


        binding.apply {
            falses.setOnClickListener { answer(false) }
            trues.setOnClickListener { answer(true) }


            next.setOnClickListener {
                questionIndex++;
                if (questionIndex % listQuestion.size == 0) {
                    questionIndex = 0;
                }
                updateView(questionIndex);
            }

            previous.setOnClickListener {
                questionIndex--;
                if (questionIndex == -1) {
                    questionIndex = listQuestion.size - 1;
                }
                updateView(questionIndex);
            }


            cheat.setOnClickListener{

                val questionText = listQuestion[questionIndex].question
                val answer = listQuestion[questionIndex].answer.toString();


                val cheat = MainFragmentDirections
                    .actionMainFragmentToCheatFragment(answer, questionText)

                view!!.findNavController().navigate(cheat)
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    //Preserve the state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("questionId", questionIndex)
    }




    private fun updateView(questionId : Int) {

        binding.apply {
            question.setText(listQuestion[questionId].question)
        }

    }




    private fun answer(ans: Boolean) {
        if (ans == listQuestion[questionIndex].answer) {
            Toast.makeText(
                requireContext(),
                "You are right", Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                requireContext(),
                "You are wrong", Toast.LENGTH_SHORT
            ).show()
        }
    }


}
