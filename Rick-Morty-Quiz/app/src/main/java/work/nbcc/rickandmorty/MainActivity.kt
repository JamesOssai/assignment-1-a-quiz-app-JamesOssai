package work.nbcc.rickandmorty

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private var questionValue = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateView()

        findViewById<ImageView>(R.id.next).setOnClickListener{next()}
        findViewById<ImageView>(R.id.previous).setOnClickListener{previous()}
        findViewById<Button>(R.id.trues).setOnClickListener{answer(true)}
        findViewById<Button>(R.id.falses).setOnClickListener{answer(false)}
    }
    private fun next(){
        questionValue = (questionValue + 1) % 20
        updateView()
    }
    private fun previous(){
        if(questionValue==0)
            questionValue=20-1
        else
            questionValue-=1
        updateView()
    }

    //Check the answer
    private fun answer(ans:Boolean){
        if(questionBank[questionValue].answer == ans) {
            showPopup("Correct")
            next()
        }else{
            showPopup("Incorrect")
        }
    }
    private fun updateView() {
        findViewById<TextView>(R.id.question).setText(questionBank[questionValue].question)
    }

    //Show popup dialog
    private fun showPopup(message: String){
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
        val view = inflater.inflate(R.layout.alert_popup,null)
        val textView: TextView = view.findViewById(R.id.textView)
        textView.setText(message)

        var alerDialog = AlertDialog.Builder(this)
            .setTitle("The result")
            .setNeutralButton("OK",null)
            .create()
        alerDialog.setView(view)
        alerDialog.show()
    }
    private val questionBank = listOf(
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



}
