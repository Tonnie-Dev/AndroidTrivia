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
        //Q.1
        Question(
            text = "What is Android Jetpack?",
            answers = listOf(
                "All of these",
                "Tools",
                "Documentation",
                "Libraries"
            )
        ),
        //Q.2
        Question(
            text = "What is the base class for layouts?",
            answers = listOf(
                "ViewGroup",
                "ViewSet",
                "ViewCollection",
                "ViewRoot"
            )
        ),

        //Q.3
        Question(
            text = "What layout do you use for complex screens?",
            answers = listOf(
                "ConstraintLayout",
                "GridLayout",
                "LinearLayout",
                "FrameLayout"
            )
        ),
        //Q.4
        Question(
            text = "What do you use to push structured data into a layout?",
            answers = listOf(
                "Data binding",
                "Data pushing",
                "Set text",
                "An OnClick method"
            )
        ),
        //Q.5
        Question(
            text = "What method do you use to inflate layouts in fragments?",
            answers = listOf(
                "onCreateView()",
                "onActivityCreated()",
                "onCreateLayout()",
                "onInflateLayout()"
            )
        ),

        //Q.6
        Question(
            text = "What's the build system for Android?",
            answers = listOf(
                "Gradle",
                "Graddle",
                "Grodle",
                "Groyle"
            )
        ),

        //Q.7
        Question(
            text = "Which class do you use to create a vector drawable?",
            answers = listOf(
                "VectorDrawable",
                "AndroidVectorDrawable",
                "DrawableVector",
                "AndroidVector"
            )
        ),

        //Q.8
        Question(
            text = "Which one of these is an Android navigation component?",
            answers = listOf(
                "NavController",
                "NavCentral",
                "NavMaster",
                "NavSwitcher"
            )
        ),

        //Q.9
        Question(
            text = "Which XML element lets you register an activity with the launcher activity?",
            answers = listOf(
                "intent-filter",
                "app-registry",
                "launcher-registry",
                "app-launcher"
            )
        ),

        //Q.10
        Question(
            text = "What do you use to mark a layout for data binding?",
            answers = listOf(
                "<layout>",
                "<binding>",
                "<data-binding>",
                "<dbinding>"
            )
        ),

        //Q.11
        Question(
            text = "In which of the following states does LiveData update UI controller such as a fragment ?",
            answers = listOf(
                "Resumed",
                "In the background",
                "Paused",
                "stopped"
            )


        ),

        //Q.12
        Question(
            "In the LiveData Observer pattern what's the observable item to be observed",
            answers = listOf(
                "Data in a LiveData Object",
                "UI Controller",
                "The Observer Method",
                "ViewModel Object"
            )
        ),

        //Q.13
        Question(
            "The lambda function passed into the Transformations.map() method is executed " +
                    "in which thread?",
            answers = listOf(
                "Main thread",
                "Background thread",
                "UI thread",
                "In a coroutine"
            )
        ),

        //Q.14
        Question(
            text = "What are the parameters for the Transformations.map()method",
            answers = listOf(
                "A source LiveData and a function",
                "Only a source LiveData",
                "No Parameters",
                "ViewModel and a function"
            )
        ),
        //Q.15
        Question(
            text = "Transformations.map() method provides an easy way to perform data manipulations on the LiveData and returns",
            answers = listOf(

                "A formatted String",
                "A LiveData Object",
                "A ViewModel object",
                "A RoomDatabase Object"
            )
        ),

        //Q.16
        Question(
            text = "In which class should you add the data-formatting logic that uses the " +
                    "Transformations.map() method to convert LiveData to a different value or format?",
            answers = listOf(
                "ViewModel",
                "Fragment",
                "Activity",
                "MainActivity"
            )
        ),

        //Q.17
        Question(
            text = "What are the two key things Retrofit needs to build a web services API?",
            answers = listOf(
                "The base URI for the web service, and a converter factory",
                "The base URI for the web service, and a GET query",
                "A network connection to the web service and an authorizatin toke",
                "A converter factory and a parser for the response"
            )
        ),

        ////Q.18
        Question(
            text = "What is the purpose of the Moshi Library",

            answers = listOf(
                "To parse a JSON response from a web service into Kotlin data objects",
                "To get data back from a web service",
                "To interact with Retrofit to make a web service request",
                "To rename Kotlin objects to match the key's in the JSON Response"
            )
        ),
        //Q.19
        Question(
            text = "What are Retrofit Call Adapters used for?",
            answers =
            listOf(
                "They add the ability to return something other than the default Call class in Retrofit",
                "They enable Retrofit to use coroutines",
                "They adapt the web service response into Kotlin data objects",
                "They change a Retrofit call into a web service call"
            )
        ),
        //Q.20
        Question(
            text = ("Which Glide method do you use to indicate the ImageView that will contain the loaded image?"),
            answers = listOf(
                "into()",
                "with()",
                "imageview()",
                "apply()"
            )
        ),
        //Q.21
        Question(
            text = "How do you specify a placeholder image to show when Glide is loading?",
            answers = listOf(
                "Call placeholder() method with a drawable",
                "Use into() method with a drawable",
                "Use RequestOptions()",
                "Call loadingImage() method with a drawble"
            )
        ),
        //Q.22
        Question(
            text = "How do you indicate that a method is a binding adapter?",
            answers = listOf(
                "Annotate the method with @BindingAdapter",
                "Call the setBindingAdapter() method on the LiveData",
                "Put the method into a Kotlin file called BindingAdapters.kt",
                "Use the android:adapter attribute in the XML layout"
            )
        ),

        //Q23
        Question(
            text = "Which component in the Android Architecture Components is responsible " +
                    "for keeping the offline cache up-to-date and getting data from the network",
            answers = listOf(
                "Repository",
                "LiveData",
                "Activities",
                "ViewModel"
            )
        ),

        //Q.24

        Question(
            text = "What is the best way to save structured data on the device file system for " +
                    "offline caching?",
            answers = listOf(
                "Room",
                "Files",
                "Shared Preferences",
                "Retrofit caching"
            )
        ),

        //Q.25

        Question(
            text = "The Transformations.map converts one LiveData into another .......",
            answers = listOf(
                "LiveData",
                "Repository",
                "ViewModel",
                "DAO Object"
            )
        ),

        //Q.26
        Question(
            text = "What are the concrete implementations of the WorkRequest Class?",
            answers = listOf(
                "OneTimeWorkPeriodicRequest and PeriodicWorkRequest",
                "OneTimeWorkPeriodicRequest",
                "OneTimeWorkRequest and " +
                        "RecurringWorkRequest",
                "OneTimeOffWorkRequest and " +
                        "RecurringWorkRequest"
            )
        ),

        //Q.27
        Question(
            text = "Which of the following classes does the WorkManager use to schedule the " +
                    "background task on API 23 and higher",
            answers = listOf(
                "Only JobScheduler",
                "BroadcastReceiver and AlarmManager",
                "AlarmManager and JobScheduler",
                "Scheduler and BroadcastReceiver"
            )
        ),

        //Q.28
        Question(
            text = "Which API do you use to add constraints to WorkRequest?",
            answers = listOf(
                "setConstraints()",
                "addConstraints()",
                "setConstraint()",
                "addConstraintsToWorkRequest"
            )
        ),

        //Q.29
        Question(
            text = "Which tag is used to define themes",
            answers = listOf("<style>", "<theme>", "<meta-tag>", "<styling")
        ),

        //Q.30
        Question(
            text = "Which of the following is NOT a good use of styles",
            answers = listOf(
                "Specify the constraints of a view",
                "Specify the background color of headings",
                "Unify the font size across views",
                "Specify the text color for a group of views"
            )
        ),

        //Q.31
        Question(
            text = "What is the difference between themes and styles?",
            answers = listOf(
                "Themes apply to the entire app while you can apply styles to specific views",
                "Themes cannot inherit from other themes but styles can inherit from other styles",
                "Styles cannot inherit from other styles but themes can inherit from other themes",
                "Themes are provided by the Android Systems while styles are defined by the developer"
            )
        ),

        //Q.32

        Question(
            text = "If a TextView in your app has a Theme that sets the " +
                    "fontSize to 12sp, a defined style sets to 14sp, and a fontSize" +
                    " attribute of 16sp, what is the size of the displayed font on the screen?",
            answers = listOf("16sp", "12sp", "14sp", "18sp")


        ),
        //Q.33
        Question(
            text = "Which of the following is true about the floating action button (FAB)?",
            answers = listOf(
                "The FAB is usually associated with a primary action the user can take on the screen",
                "The FAB must be positioned in the bottom-right corner, 16dp from the edge of the screen",
                "The FAB uses a special click handler so that you don't have to write your own view model code",
                "The FAB is a mandatory element for apps that implement Material Design principles"
            )
        ),
        //Q.34
        Question(
            text = "Which ViewGroup allows you to stack views on top of each other",
            answers = listOf(
                "CoordinatorLayout",
                "StackedViewsLayout",
                "ConstraintLayout",
                "You cannot stack views"
            )
        ),
        //Q.35
        Question(
            text = "Which of the following is mandatory for supporting RTL languages?",
            answers = listOf(
                "Replace Left and Right in properties with Start and End",
                "Make sure all icons use android:autoMirrored= \"true\"",
                "Switch to an RTL Language",
                "Provider content descriptions"

            )
        ),

        //Q.36
        Question(
            text = "Which of the following accessibility tools comes built in with the most Android devices?",
            answers = listOf(
                "TalkBack",
                "Accessibility Scanner",
                "In Android Studio, Refactor> Add RTL support where possible",
                "Lint"
            )
        ),

        //Q.37
        Question(
            text = "Which of the following is not true about chips?",
            answers = listOf(
                "You must always enable DarkTheme if your app uses chips",
                "YOu display chips as paret of a ChipGroup",
                "You can provide a color state list for a ChipGroup",
                "Chips are compact elements that represent an input, attribute or action"
            )
        ),
        //Q.38
        Question(
            text = "Which theme gives you styling for dark and light modes?",
            answers = listOf("DayNight", "DarkTheme", "DarkAndLightTheme", "Light")
        ),


        //Q.39
        Question(
            text = "What is a live region",
            answers = listOf(
                "A region of the screen that changes shape according to Material guidelines",
                "A node that contain information that is important for the user",
                "A view that allows streaming video",
                "An animated drawable"
            )
        ),
        //Q.40
        Question(
            text = "Android is licenced under which open source licencing license?",
            answers = listOf("Gnu's GPL", "Apache/MIT", "OSS", "Sourceforge")
        ),
        //Q.41
        Question(
            text = "What Layout should you use in your XML to hold your game view",
            answers = listOf("FrameLayout", "LinearLayout", "RelativeLayout", "TableLayout")
        ),
        //Q.42
        Question(
            text = "Which method is used to initiate a fragment",
            answers = listOf("onViewCreate()", "onCreateView()", "onCreate()", "onAttach()")
        )
        //Q.4876`        Question(text = "", answers = listOf())
    )


    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions =
        Math.min(
            (questions.size + 1) / 2,
            3
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater,
            R.layout.fragment_game,
            container,
            false
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

                        view.findNavController().navigate(
                            GameFragmentDirections.actionGameFragmentToGameWonFragment(
                                numQuestions,
                                questionIndex
                            )
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
            getString(
                R.string.title_android_trivia_question,
                questionIndex + 1,
                numQuestions
            )
    }


}
