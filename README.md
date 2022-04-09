# Basic-Example
An example of a simple app with a non activity splash screen that follows the MVVM Architecture. 

TECHNOLOGIES
Dagger-hilt.
Retrofit.  
Kotlin coroutines. 
ViewBinding. 
LiveData.
Glide.

It is also network aware and shows a Snackbar when connection is lost that will only disappear when network is back. 
It uses ktlint for code quality maintenance (run './gradlew ktlintcheck' to format code quality, run './gradlew ktlintFormat' to format code)
and glide for image loading.
You might want to change the splash screen image from png to a drawable.
![Screenshot 2022-04-09 at 22 43 09](https://user-images.githubusercontent.com/74045955/162592503-42534ebe-34b3-4c3f-8fba-67c33546ad06.png)
Splash Screen
![WhatsApp Image 2022-04-09 at 10 45 53 PM](https://user-images.githubusercontent.com/74045955/162592549-c4ae059c-9614-436e-94f9-645c165ae128.jpeg)
Screen with Network
![WhatsApp Image 2022-04-09 at 10 45 54 PM](https://user-images.githubusercontent.com/74045955/162592551-6a091319-af00-472d-a4c5-9dd4545c72e6.jpeg)
Screen without Network
