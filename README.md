# NikeDemoApp

Requirements:
This exercise is meant for our dev team to get acquainted with your skills and strengths. We would like you to build a simple app using Apple’s iTunes api. 

Thing we are looking for:

 

1.    A UI with more than one feed types from Coming Soon, Hot Tracks, New Releases, Top Albums, and/or Top Songs and including at least 50 items each from the feed generator you can see using this web page: https://rss.itunes.apple.com/en-us.

 

2.    The various feed types shall be displayed in multiple pages, feel free to use TabLayout or BottomNavigation view or Navigation Drawer or some PagerAdapter of your choice (These are just examples). The UX is completely your choice but must include at minimum an image of "artworkUrl100" and "artistName".

 

3.    Tapping on a feed item should push the user onto a new view where we see a larger image at the top of the screen and the same information that was shown in the main view, plus genre, release date, and copyright info below the image. A button should also be included on this second view. When tapped, it will fast app switch to a web browser displaying the album page using the "url" found in the JSON file.

 

4.    The apis needed for you to build the app is available in https://rss.itunes.apple.com/en-us. An Example: The endpoint for the Feed type of "Top Albums" could be found under the Feed Type label in the above web page. (https://rss.itunes.apple.com/api/v1/us/apple-music/top-albums/all/5/explicit.json).

 

5.    You are free to use any library for networking; we prefer the network execution to NOT BE DONE with an AsyncTask.

 

6.    Use an application architecture of your choice. We want to see your understanding of an Android app architecture!

 

7.    Minimum Android API is 24.

 

Your application's code must be entirely written in Kotlin. Feel free to make use of Android's framework, as well as AndroidX libraries.

 

Details on how the application is to be installed and the overall app design should be included in the applications README.md file.  Additionally, you may add any other information you deem to be relevant for the dev team. 

 

When completed, send a Github link for your final submission.

 

Nice-to-haves:

•    Use of Kotlin Coroutines is highly encouraged.

•    Pleasing to the eye transitions/animations

•    Use of Material Components

•    We do not mandate you to write any tests, but valid tests are a plus. 