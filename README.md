# DailyDose
Daily Dose will be a journaling app for Android that emphasizes ease-of-use; entries are meant to be small to so users can quickly jot down their thoughts and feelings in the moment, and can be rated on a 1-10 scale; data can be used to display trends in the user’s mood, and how different events and activities influence them.

## Functional Use Cases
Creation and deletion of journal entries is fully functional, as is viewing a log of past journal entries. The user can tag the entries they create with a static list of included tags as of now, in the future we plan to let them add more to that list. The user can also discard a journal entry before continuing to the tagging page if they no longer wish to make one. Another use that is currently functional is checking trends on how each tag affects mood. As of now, it is one bar graph representing average rating of tags, something we plan to expand on in further releases.

## Repository Layout
Reports directory: For weekly progress reports<br>
gradle/wrapper directory: Automatically generated gradle files<br>
app directory: Source Code<br>
&nbsp;&nbsp;&nbsp;&nbsp;-> app/build.gradle - Gradle dependencies and configuration<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app/src directory: Source Code<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-> app/src/test directory: JUnit tests<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app/src/main directory: Source Code<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-> app/src/main/assets directory: JSON data files<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app/src/main/res directory: App Resources (xml files, string/color constants, etc)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;app/src/main/java directory: Java files (Backend utilities, App Activity files, Custom views)<br>
  

## Build/Test Instructions
Travis CI automatically runs and tests the build upon commit to this repo.To build manually, open Android Studio with this repo cloned and press the "Sync Project with Gradle Files" button.<br>
Gradle automatically runs all JUnit tests every time there is a build.

## Run Instructions
1. Download Android Studio<br>
2. Clone this repostitory<br>
3. Open the AVD manager within Android Studio and download the Pixel 3 emulator with API 29<br>
4. Navigate to the "app" folder<br>
5. Press the "Sync Project with Gradle Files" button (elephant icon on the top right)
6. Select your emulator from the drop down list of emulators
7. Click the green "Run" button


## Resources
Android Studio Setup Guide: https://courses.cs.washington.edu/courses/cse340/21sp/docs/android.html<br>
AnyChart Android Charts: https://www.anychart.com/technical-integrations/samples/android-charts/
