# Agenda
In the week 7 meeting, we plan to
- Get feedback on our beta release from the project manager<br>
- Discuss expectations and requirements for the next assignment<br>

# Team Report

## Plans and goals last week
- Get a beta version of the application finished. Get enough code done that our app has its basic functionality done.
- Record our video presentation for the assignment.

## Current progress and issues
We were able to get a beta version of our app up and running this week. The file system was fixed. The entry log and tagging pages were created. The Front end pages were integrated with the backend functions. And finally, the trend analysis page was implemented. We are very happy with our progress this week.<br>
.Several issues arose this week. The file system was found to be broken. Then it seemed to work for some of us but not others. This caused several issues with the entry log as well as the trend analysis, but after a bunch of hours pair programming and debugging we were able to iron that out. We also had smaller things come up, such as our toolbar not being present and our discard button not always working properly.<br>

## Plans and goals for next week
- Get user and developer documentation written<br>
- Continue to code and add features to the app.

## Member Reports:
#### Matt:
1. Plans and goals last week:<br>
Continue working on project towards beta release<br>
Continue refining living document as necessary<br>
2. Current progress and issues:<br>
Beta released - base functionality of app in place, but still a number of features left to implement<br>
Group survey feedback received - admittedly have felt like I domineered conversations and decisions too much, and need to work on it<br>
3. Plans and goals for next week:<br>
Help iron out bugs in preparation for full release<br>
Begin working on user and developer documentation<br>

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
pair-programmed with Sherry and created our first user interface successfully<br>
participated in 4 meetings to figure out how to link Travis CI with our project, and engaged in configuring the build on Travis CI. <br>
helped merge the branches<br>  
2. Progress & Issues:<br>
Finished building entry creation, entry tagging pages.<br>
Helped with resolving context issues of writing json entries to local files. <br>
Pair-programmed with Hunter, Sherry to work on debugging the overall behavior of the app for the beta release. <br>
Recorded the demo for beta release <br>
Issues:<br>
- action bar conflicts with title bar, there still exist unstable behavior of the action bar that might need to be looked into more <br>
- rating progress bar still has no ticks, needs to be added before final release <br>
- menu option needs to be changed into a more user friendly design choice <br>
3. Plans for next week:<br>
In week 7, I will <br>
Help with improving the usability of the app.<br>
Develop a navigation bar that is easier to find and use. <br>
Help with further implementing the entry log page and trend analysis page. <br>
Implement the adding tags feature on tag interface. <br>
Work on the overall style of the UI. <br>


#### Hunter:
1. Plans of last week:<br>
Continue to work on coding. I planned to further flesh out the “getters” and “setters” for our storage system as well as hopefully helping Sherry and Ester figure out the communication between the front end they wrote and the back end I have written (The EventListener() methods and such).<br>
2. Current Progress & Issues:<br>
This past week I fixed the backend file system to work in the emulator. Then I wrote the skeleton of the entry log view. After that, I spent the weekend pair programming with Ester and Sherry to solve bugs and figure out how to get the app functional for the beta release. We made the entry log page better and solved a bug with it crashing. We added onClicks to all of the buttons in the app, and we added communication with the backend to use the file system.<br>
Some issues I encountered this week were that the file system broke when used inside the emulator, even though it worked within JUnit. Then it was hard to integrate the entry log view because it is created programmatically and is not a static activity. Then it was working for some of us but not others. Lots of bugs of this scale. It was immensely helpful to work with Sherry and Ester to program on these things because I could not have solved these issues without them.
3. Plans and goals for next week:<br>
This coming week, I will continue to add functionality to the app. I will try and get the edit button functional and I will continue to collaborate with my teammates to pair program and get as much done as we can<br>
I also plan to attend the Thursday meeting and contribute to writing the documentation for this week's assignment.
