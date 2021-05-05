# Agenda
In week 5 meeting, we plan to
- get feedback from project manager on the living document and revise the document afterwards<br>
- get feedback from project manager on what features would constitute a beta version of our app<br>

# Team Report

## Plans and goals last week
- Add test-automation and CI to the test plan in the living document
- Continue revising other parts of the living document
- Start coding 

## Current progress and issues
We have our CI system set up with a couple unit tests we have written. We also were able to get some coding done, specifically the UI for the “Entry Creation” page, and the back end for our Entry storage file.<br>
We ran into issues with GitHub merge conflicts stemming from accidentally adding some unnecessary files to git. We also had some problems setting up our CI service. We were able to resolve these by working through them in a couple additional meetings this week.<br>

## Plans and goals for next week
- Get a beta version of the application finished. Get enough code done that our app has its basic functionality done.
- Record our video presentation for the assignment.

## Member Reports:
#### Matt:
1. Plans and goals last week:<br>
Determine appropriate automated testing and integration scheme<br>
Further clarification and feedback on design and what we need<br>
Look into tutorials for Android Studio to prepare for coding<br>
2. Current progress and issues:<br>
Testing automation and CI scheme determined; using JUnit and Travis with Gradle as our build system<br>
Design more or less settled upon<br>
Coding in Android Studio Java-based; relatively straightforward<br>
Missed meetings; need to be more vigilant about attending and contributing<br>
3. Plans and goals for next week:<br>
Continue working on project towards beta release<br>
Continue refining living document as necessary<br>

#### Sherry:
1. Plans and goals last week:<br>
Continue to familiarize myself with Android Studio<br>
Start experimenting with the interface; focus on the entry view<br>
2. Current progress and issues:<br>
Entry creation UI mostly done, pair programming works really well<br>
Fix the broken build due to local files also included in the version control (take us tons of hours to finally fix it and we should definitely be more careful what to include in the version control) <br>
Work on Travis CI and finally get it work by overwriting the default gradle build in Travis<br>
I’m still not sure about the data flow within the application, so when issues come up I’ll shamelessly ask Hunter :)
3. Plans and goals for next week:<br>
Move to the interaction between frontend and backend and continue finishing up the rest of UI<br>
Find out if we can test our front end automatically instead of dummy runs manually<br>


#### Ester:
1. Plans of last week:<br>
I will continue to familiarize myself with using Android Studio to develop a mobile app, hopefully acknowledge the key concepts and techniques that will be useful and helpful to our project. Meanwhile, I will also try to come up with ideas of potential example tests for our app. 
2. Progress & Issues:<br>
In week 5, I pair-programmed with Sherry and created our first user interface successfully. I also participated in 4 meetings to figure out how to link Travis CI with our project, and engaged in configuring the build on Travis CI. I also helped merge the branches.  Although we encountered quite some issues in merging the branches because for both branches, we accidentally pushed some local files .gradle .idea onto our repo, which made no one able to pull successfully from main. In the end, we had to delete those files from our main branch, re-cloned the repo so that everything functions normally again. We also had issues figuring out what to write in the yml file for Travis CI, mainly struggling to realize whether we want Travis to run an emulator or not. In the end, I found a command that can override the default and only let Travis run the emulator-independent tests so it can build successfully. Lastly, I helped add information in the living document. <br> 

3. Plans for next week:<br>
Finish all basic skeletons of front end interfaces design, and manage to set up the <br> communication between frontend and backend so an operational use case can be tested. <br>
If I have time after finishing the frontend design, I will also help write more test cases as well. <br>


#### Hunter:
1. Plans of last week:<br>
 This coming week, I will help my team decide which testing service to use. Once we have that nailed down I will brainstorm some specific ways to test our application.<br>
I also plan to gain more familiarity with the way frontend and backend interface within an android application. At that point we should be ready to get coding, at least a shell of our application.<br>
2. Current Progress & Issues:<br>
This past week I attended 4 different meetings with my fellow group members. We met extra times to clear up git merging issues and figure out how to configure travis. Outside of these meetings, I personally coded up the start to the backend of our application, including an Entry class and read/write functions for our local json file storage system. I also included a couple of unit tests for this code.<br>
My issues this week were similar to what the team experienced as a whole. I accidentally added some local files to the version control system and caused a whole headache with Git. However, after hours of frustration, we finally got that resolved. We then ran into a dilemma of how to get Travis CI set up, but once again, after a meeting we were able to clear up the problem.
3. Plans and goals for next week:<br>
This coming week, I will continue to work on coding. I plan to further flesh out the “getters” and “setters” for our storage system as well as hopefully helping Sherry and Ester figure out the communication between the front end they wrote and the back end I have written (The EventListener() methods and such).
