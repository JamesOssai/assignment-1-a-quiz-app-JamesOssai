package work.nbcc.rickandmorty


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import work.nbcc.rickandmorty.databinding.FragmentCheatBinding

/**
 * A simple [Fragment] subclass.
 */
class Cheat : Fragment() {
    private lateinit var binding: FragmentCheatBinding

    private lateinit var args: CheatArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args = CheatArgs.fromBundle(arguments!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cheat,
            container, false
        )


        binding.apply {
            //set fields
            result.visibility = View.INVISIBLE;
            result.text = args.answer.toString()

            question.setText(args.question)

            cheat.setOnClickListener{ result.visibility = View.VISIBLE}

            cancel.setOnClickListener{
                activity!!.onBackPressed()

            }
        }



        return binding.root
    }




}
