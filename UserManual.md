# Description
Daily Dose is a journaling app with an emphasis on ease of use and trend analysis. It pings the user a few times a day to check in with them and collect quick user-inputted data, most notably how they are feeling on a scale from 1-10 and what they are currently doing; this data is then compiled and visualized to show the user the trends of what affects their mood the most.

# Installation & Running
1. Download Android Studio fromo https://developer.android.com/studio/<br>
2. Clone this repository<br>
3. Download Android 29 (File > Settings > Android SDK > check the box before Android 10.0(Q) API level 29)
4. Open the AVD manager (Tools > AVD Manager), click on Create Virtual Device, and download the Pixel 3 emulator with API 29 or later<br>
5. Navigate to the "app" folder in the project file browser sidebar<br>
6. Press the "Sync Project with Gradle Files" button (elephant icon on the top right)
7. Select your emulator from the drop down list of emulators next to the "Run" button
8. Once Gradle is finished syncing, click the green "Run" button to run the app

# How to use the software
Key Functionalities:<br>
Create an entry: The first page upon opening the app allows you to write a new entry in the text box.<br>
Rate an entry: Entries can be rated from 0-10 using the bar above the text box.<br>
Discard an entry: If you are not satisfied with your entry, you can discard it to rewrite it from scratch.<br>
Tag an entry: Once you are happy with your entry, press the "Continue" button to go to the tagging screen.<br>
Use existing tags: A pre-made selection of tags is available; simply tap them to tag your entry.<br>
Customize new tags: You may create new tags by pressing the + button on the tag selection screen.<br>
Submit an entry: Once you have tagged your entry, you may submit it and write another.<br>
Browse through past entry log: The entry log can be accessed from the 3-bars menu on the top right.<br>
Edit past entries: You can edit entries; this also allows you to re-rate and re-tag.<br>
Delete past entries: You can also delete entries you are no longer happy with.<br>
Visualize entry analysis: There are two different ways to view the tag analysis, both accessed from the 3-bars menu.<br>
Average rating of tags: Each bar represents the average rating of a particular tag across all entries.<br>
Rating trend over a period of time for selected tag (Work In Progress): Set the start and end date, select the tag to check the rating of, then tap "Go" to see average rating over time,<br>

# Known Bugs
- In the Average Tag Graph page, there is an extra toolbar that partially covers the menu button. <br>

# Reporting Bugs
Bugs can be reported through this page https://github.com/xuhanz/DailyDose/issues. Please make sure you are working on the latest release prior to reporting a bug (to verify that it hasn’t already been fixed) and you have all the prerequisites installed. Then, make sure this bug is reproducible and provide clear documentation of what is happening as well as the steps to reproduce it. Here is a great resource for an example of how to write good bug reports! https://developer.mozilla.org/en-US/docs/Mozilla/QA/Bug_writing_guidelines 
