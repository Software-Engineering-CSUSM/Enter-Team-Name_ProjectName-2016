System Requirement Specicifications
=====================================
Test Taker Application
---

### Purpose:
> This document describes the requirements for the design of a test taking application based in the Java (or HTML) language. The requirements will be described in the use case format identifying the basic system flow. The process requirements for the new Condition Reports (CRs) and Condition Report iterations is described in the Use Case specification.

### Background:
> This application will create and maintain a study reference for students and teachers to use for specified material. The project will communicate with a server to maintain classes, exams, questions and other study materials. Some of the operations of this application include:
>- Creating Tests
>- Using Flashcards
>- Playing Study Games
>- Sharing information with other students for ease of use
>- Both Admin and Student roles
>- Course/Test/User Statistics
>
>The application will be using the Java Portable Development Language in the Eclipse IDE. All version control will be handled by the github repository located here. Communication will be handled via Slack.

### Definitions, Acronyms, Abbreviations

### System Requirements

#### Actors
Students 
> Can take tests, create questions, create quizzes (no credit), view scores

Teachers
> Can create tests, create questions, take tests, provide tests to students

System Administrators 
> Can create courses, assign teachers and students to courses

### Behavior Flow 

### Basic Use Cases
>Creating a new question
1. Open the application
1. Select the semester
1. Select the class
1. Select option for question:
    1. Assign question to an existing/new test
    1. Add question without a test
1. Select question type
1. Enter question
1. Enter possible answers (if required)
1. Select correct answer (if required)
1. Click the “Save” button
1. User returns to the main screen - whatever we decide it is.
> Creating a new test
1. Open application
1. Sign in
1. Select class
Select new test
Name test
Select question  / create new question
Assign question point value
Repeat 6 / end test
1. <optionally: assign test point value>
2.3.3 Managing questions
Open Application
Select the Semester
Select the Class
Select the relevant Question
Select Question to edit
Select Option to edit
Question Type
Multiple Choice
Open Answer
Question Text
Possible Answers
Number of Answers (Multiple Choice)
Acceptable Answers (Open Answer)
Point Weight
Confirm Changes
Repeat from 4
2.3.4 Adding a test date to Calendar
Open application
Go to the in-app calendar
Click on a date
Option appears to add a Quiz or Test to that day (multiple can be added on one day)
Press enter or save and you will be given a month view of the calendar
On the day/s you have a quiz or test a little Q or T will appear on the date
2.3.11 Taking a test
Open application
Sign in
Select class
Select test
Computer presents test details
Take option to take test
Computer presents question
Answer question
<optionally: computer gives per-question evaluation/ allows retries if any>
 Repeat from 7, or end test
Computer compares responses given to correct responses/(other grading method)
Computer summs scores, presents to user, records in system.
2.3.12 Playing a study game
Open Application
User must have created questions 
Select Semester
Select class
Option for user to play or exit(return to main screen)
Game starts, user can win, fail, or exit to get out of the game
Number counter for number of correct answers from user
Game ends and displays score
Option for user to play again, switch semester or exit(return to main screen)	
2.3.13 Using flashcards
Open Application
Select the semester
Select the class
                a.  Option to include additional classes
Option to use a previously used set of flashcards or create new flashcard
 Choose number of questions
Generate questions in random order from question database.
Assign correct answers to questions.
Option to select a Hint 
Button to show answer to flashcard
Option to save flashcard set.
 Exit Flashcards
Prompt user to save before quitting
