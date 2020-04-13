/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidshowtime.trivia


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.androidshowtime.trivia.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    data class Question(
        val text: String,
        val answers: List<String>
    )

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (Or better yet, don't define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "What is Android Jetpack?",
            answers = listOf("All of these", "Tools", "Documentation", "Libraries")
        ),
        Question(
            text = "What is the base class for layouts?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        Question(
            text = "What layout do you use for complex screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        Question(
            text = "What do you use to push structured data into a layout?",
            answers = listOf("Data binding", "Data pushing", "Set text", "An OnClick method")
        ),
        Question(
            text = "What method do you use to inflate layouts in fragments?",
            answers = listOf(
                "onCreateView()",
                "onActivityCreated()",
                "onCreateLayout()",
                "onInflateLayout()"
            )
        ),
        Question(
            text = "What's the build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
        ),
        Question(
            text = "Which class do you use to create a vector drawable?",
            answers = listOf(
                "VectorDrawable",
                "AndroidVectorDrawable",
                "DrawableVector",
                "AndroidVector"
            )
        ),
        Question(
            text = "Which one of these is an Android navigation component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
        ),
        Question(
            text = "Which XML element lets you register an activity with the launcher activity?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
        ),
        Question(
            text = "What do you use to mark a layout for data binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
        ),

        Question(
            text = "In which of the following states does LiveData update UI controller such as a fragment ?",
            answers = listOf("Resumed", "In the background", "Paused", "stopped")


        ),
        Question(
            "In the LiveData Observer pattern what's the observable item to be observed",
            answers = listOf(
                "Data in a LiveData Object",
                "UI Controller",
                "The Observer Method",
                "ViewModel Object"
            )
        ),

        Question(
            "The lambda function passed into the Transformations.map() method is executed " +
                    "in which thread?", answers = listOf(
                "Main thread", "Background thread", "UI thread",
                "In a coroutine"
            )
        ),

        Question(
            text = "What are the parameters for the Transformations.map()method",
            answers = listOf(
                "A source LiveData and a function",
                "Only a source LiveData",
                "No Parameters",
                "ViewModel and a function"
            )
        ),

        Question(
            text = "Transformations.map() method provides an easy way to perform data manipulations on the LiveData and returns",
            answers = listOf(

                "A formatted String",
                "A LiveData Object",
                "A ViewModel object",
                "A RoomDatabase Object"
            )
        ),

        Question(
            text = "In which class should you add the data-formatting logic that uses the " +
                    "Transformations.map() method to convert LiveData to a different value or format?",
            answers = listOf("ViewModel", "Fragment", "Activity", "MainActivity")
        )

        , Question(
            text = "What are the two key things Retrofit needs to build a web services API?",
            answers = listOf(
                "The base URI for the web service, and a converter factory",
                "The base URI for the web service, and a GET query",
                "A network connection to the web service and an authorizatin toke",
                "A converter factory, and a parser for the response"
            )
        )

        , Question(
            text = "What is the purpose of the Moshi Library",

            answers = listOf(
                "To parse a JSON response from a web service into Kotlin data objects",
                "To get data back from a web service",
                "To interact with Retrofit to make a web service request",
                "To rename Kotlin objects to match the key's in the JSON Response"
            )
        ),

        Question(
            text = "What are Retrofit Call Adapters used for?", answers =
            listOf(
                "They add the ability to return something other than the default Call class in Retrofit",
                "They enable Retrofit to use coroutines",
                "They adapt the web service response into Kotlin data objects",
                "They change a Retrofit call into a web service call"
            )
        ),

        Question(
            text = ("Which Glide method do you use to indicate the ImageView that will contain the loaded image?"),
            answers = listOf(
                "into()", "with()", "imageview()", "apply()"
            )
        ),

        Question(
            text = "How do you specify a placeholder image to show when Glide is loading?",
            answers = listOf(
                "Call placeholder() method with a drawable",
                "Use into() method with a drawable",
                "Use RequestOptions()",
                "Call loadingImage() method with a drawble"
            )
        ),
        Question(
            text = "How do you indicate that a method is a binding adapter?",
            answers = listOf(
                "Annotate the method with @BindingAdapter",
                "Call the setBindingAdapter() method on the LiveData",
                "Put the method into a Kotlin file called BindingAdapters.kt",
                "Use the android:adapter attribute in the XML layout"
            )
        )
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false
        )


        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.

                        view.findNavController()
                            .navigate(
                                GameFragmentDirections.actionGameFragmentToGameWonFragment
                                    (numQuestions, questionIndex)
                            )
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController()
                        .navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
                }
            }
        }


//val args = GameWonFragmentArgs.fromBundle(arguments!!)
        //Toast.makeText(context, "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}", Toast.LENGTH_LONG).show()


        return binding.root
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {


        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }


}
