# How to Obtain Source Code
Obtaining the source code simply involves cloning the GitHub repository, which can be done by running `git clone https://github.com/xuhanz/DailyDose.git` in a command line.

# Directory Layout
* app: Source Code and Resources<br>
	* src: Source Code<br>
		* main: Source Code<br>
			* assets: JSON data files<br>
			* java: Java files (Backend utilities, App Activity files, Custom views)<br>
			* res: App Resources (xml files, string/color constants, etc)<br>
		* test: Unit tests <br>
		* androidTest: Integration tests <br>
	* build.gradle - Gradle dependencies and configuration<br>
* gradle: Automatically generated gradle files<br>
* Reports: Weekly progress reports<br>
* DeveloperGuidelines.md: Guidelines for development of app<br>
* UserManual.md: Information on app and instructions for usage<br>

# How to Build
The process to build the app is more or less the same as the process to install and run it - Gradle, which is incorporated into the project in Android Studio, automates the build process with the press of a button; make alterations to the code as desired, then simply press the “Run” button and the code will be built and run on the Android emulator.

# How to Test
- JUnit is used for our testing infrastructure, as it is incorporated into Android Studio; the instrumentation tests can be directly ran from the test files located in `/app/src/androidTest/java/com.example.dailydose`; additionally, the unit tests (`/app/src/test/java/com.example.dailydose(test)`) are run whenever a new build is pushed to a branch in the Git repository.<br>
- Adding tests is fairly simple; the existing test files can be extended with new tests, but when adding new classes or functionality, it is preferable to create a new .java file for testing that feature.<br>
- There have been difficulties getting our CI system to include an emulator for instrumentation tests (`/app/src/androidTest/java/com.example.dailydose`). For now, these tests must be run manually from that folder with an emulator through Android Studio. These tests only pertain to that methods that have been implemented to interact directly with the database files, and only need to be run if/when those change or a new build is to be released.<br>
- Instrumentation tests should be added to `/app/src/androidTest/java/com.example.dailydose`, and unit tests should be added to `/app/src/test/java/com.example.dailydose`. For guidance on how tests should be formatted, it would be helpful to take a look at existing test files. <br>

# CI System
- Our entire JUnit test suite will be run each time the CI system does a build to make sure we have not broken prior functionality. <br>
- A CI build will be triggered by all changes in our code: pushes to our GitHub repository, a pull request, or when merging our branches. <br>
- Here’s the link to our Travis CI: https://travis-ci.com/github/xuhanz/DailyDose <br>

# Release Builds
To build a release, press the “Create a new release” button on GitHub with “Target” set to be branch “main”. Update the “Tag version” section to be v0.0.[VERSION NUMBER] if it is a beta and not the final version (ie v0.0.7 for the 7th beta version, v0.1.2 for the 12th etc). Or set it to be v[VERSION NUMBER].0.0 for a final production release (ie v1.0.0 for the first production release, 15.0.0 for the 15th etc). When building a release, make sure the CI build checks pass on the main branch prior to making the release, and also run the Instrumentation tests on your emulator, even if you have not changed anything with the file system, just for a sanity check.

# Reporting Bugs
Bugs can be reported through this page https://github.com/xuhanz/DailyDose/issues. Please make sure you are working on the latest release prior to reporting a bug (to verify that it hasn’t already been fixed) and you have all the prerequisites installed. Then, make sure this bug is reproducible and provide clear documentation of what is happening as well as the steps to reproduce it. Here is a great resource for an example of how to write good bug reports! https://developer.mozilla.org/en-US/docs/Mozilla/QA/Bug_writing_guidelines 

# Coding Guidelines:
- Java Guidelines: https://google.github.io/styleguide/javaguide.html <br>
- Android Development guidelines: https://source.android.com/setup/contribute/code-style <br>



