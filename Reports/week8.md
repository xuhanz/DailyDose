# Agenda
In the week 8 meeting, we plan to
- Discuss plans for the week (who will work on what)<br>
- Ask for feedback after using our app<br>

# Team Report

## Plans and goals last week
- Get user and developer documentation written<br>
- Continue to code and add features to the app.

## Current progress and issues
This week, we got our documentation for users and developers written. We also made significant progress in adding features and making progress on our app. <br>
The issues that arose this week were nothing too major. We just had some miscommunication stemming from our use of the Slack channel. Since we started using GitHub issues and detailed commit messages instead of spamming the slack channel with progress updates,  there was some confusion on what was and wasn’t done. We should have been more clear about where to post and look for progress updates from other team members, but we got this issue ironed out for the future. <br>

## Plans and goals for next week
- Get user and developer documentation written<br>
- Continue to code and add features to the app.

## Member Reports:
#### Matt:
1. Plans and goals last week:<br>
Help iron out bugs in preparation for full release<br>
Begin working on user and developer documentation<br>
2. Current progress and issues:<br>
User and developer documentation completed for now<br>
Helped re-implement tests for JSON utility methods by making them integrated tests rather than unit tests<br>
3. Plans and goals for next week:<br>
Help peer review other group’s projects<br>
Continue working towards full release<br>

#### Sherry:
1. Plans and goals last week:<br>
Implement the custom tags feature on tagging interface.<br>
Add date feature (whether read from system or from user input) and refactor our json file and utility class to better prepare for the tag analysis page.<br>
2. Current progress and issues:<br>
Implement the majority of TagAnalysis View (java and xml file), which generates trends on the selected tag. <br>
Handling calendar and utilizing packages from others is really a headache but luckily Tag Analysis has most of its functionality now.<br>
Issues:<br>
I’m not used to working with Github commit messages so I end up spending several hours working on the same thing as Hunter. I will read the whole commit message and check all the branches before working on my stuff.

3. Plans and goals for next week:<br>
Refactor the average graph analysis page to display top rating tags and lowest rating tags in two separate graphs. <br>
Display the dates that the user has selected in the tag analysis view (instead of always displaying the date of “today”). <br>
Work on TagAnalysis Page to check redundancy in code and behavior in edge cases.<br>
Merge the tag analysis branch after get it fully tested. <br>
Fix any issue that comes up. <br>

#### Ester:
1. Plans of last week:<br>
Help with improving the usability of the app <br>
Develop a navigation bar that is easier to find and use. <br>
Help with further implementing the entry log page and trend analysis page. <br>
Implement the adding tags feature on the tag interface. <br>
Work on the overall style of the UI. <br>
2. Progress & Issues:<br>
Progress:<br>
Added numbers to the progress bar to display real-time progress <br>
Added date field to the Entry object and incorporated this in our app <br>
Implemented the edit button on Entry Log page<br>
Fixed the position of the title bar in Entry Log Page<br>
Implemented the custom tag feature<br>
Helped resolve some issues for the tag analysis page<br>
Issues:<br>
I think in the weeks left, I will ensure that I always post updates about my work progress on the slack channel besides writing commit messages to GitHub. Doing this will avoid causing confusion between team members about what part is implemented, what issues are resolved, etc. This week there were members that accidentally worked on the same thing and did not acknowledge it until the last moment, which I felt was a pity so I really want to avoid it in the future. <br>
3. Plans for next week:<br>
In week 8, I will <br>
Continue adding more features to the app<br>
Add more comments to our code and look for possible refactoring of code to make our code follow good code style and guidelines<br>
Continue improving the design, usability, and style of our app<br>


#### Hunter:
1. Plans of last week:<br>
Continue to add functionality to the app. I will try and get the edit button functional and I will continue to collaborate with my teammates to pair program and get as much done as we can<br>
I also plan to attend the Thursday meeting and contribute to writing the documentation for this week's assignment.<br>
2. Current Progress & Issues:<br>
This past week, aside from attending the meeting and doing my share of the documentation,  I pair-programmed with Ester for several hours both Saturday and Sunday. We were able to complete the edit button feature on the entry log page. We also fixed the “first Entry” bug reported by our project manager, added an updating number to the rating progress bar, implemented custom tag creation, fixed the menu issue on the entry log page, and added dates to entries and their representations on the Entry Log. After that, I added a helper function to calculate average ratings of entries on a per day basis for a given date range to help Sherry with her TagAnalysisView page graphs.<br>
I didn’t encounter any major issues this week, custom tagging was a bit of  headache to figure out and since we changed our workflow to be more reliant on using GitHub issues and reading commit messages on git, and stopped clogging the Slack channel with constant progress updates, that led to some miscommunication and Sherry was not aware that the date feature was already done and spent some unnecessary time figuring out how to implement it. Matthew also implemented a test change that was already 95% complete on another branch. In the future we will be sure to be more clear about our lines of communication so these things don’t happen.<br>
3. Plans and goals for next week:<br>
This coming week, I will continue to work to add features and iron out bugs in our app. In addition to that, I will complete the peer review I am assigned.
