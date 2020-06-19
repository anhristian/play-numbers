# Playing Numbers

## Introduction

This is an educational safe Android app that could be used by the 4-6 years old children 
for joyful learning of numbers from 1 to 10. 
Children will be able to practice interactively their knowledge and having fun at the same time. 
This app assumes that the child knows already some numbers, and it is designed to strengthen 
their knowledge by offering a large pool of activities with different levels of difficulty. 

The user will have the option to choose from two sections:

   * One section will offer a decent amount of activities where the task will be to match the number with the picture. 
   * The second section will offer activities for finding the missing number.

In the chosen section, the child could select the activity he or she wants to do. 
Each activity will have an example already solved to show the child how to work.
Additional to what appears on the screen there will be a voice feature incorporated that tells the instructions, 
say back to the child the number he selected, and gives short encouraging expressions.   

## Intended users 

   * Parents and children who prefer gadgets instead of books and take this as a more interesting way of learning.
 
   > As a parent of a 5-year-old child, I was paying attention to how a lot of parents use tablets and phones with their kids, I thought that creating such an app it will add usefulness to the time children spend on gadgets.

   * Parents who travel a lot and long distances with their children and intend to keep them interested in learning on the way by using a fun environment.

   > As a parent with experience in long flights with a child, I can say that these kinds of travels are not easy to survive. So, even for parents that are not so permissive with gadgets, as I find myself, this educational app is a good way to keep them busy when the parent cannot bring along too many entertainment staff. 

   * [Intended users](intended-users.md)
   
## Design documentation

   * [Wireframe diagram](wireframe.md)
   * [Entity-relationship diagram](erd.md)
   
## Device and external services

   * **_Google Sign In_**
   
      The user will have the choice to provide his or her email, which will help to record the child's progress on the activities.
      Otherwise, the user could skip it, but this will result in no saving the data from the progress.
      The app doesn't require the Google Sign In for its functionality. 
      
        * [Google Sign In](https://developers.google.com/identity/sign-in/android/sign-in)
        
   * **_Device's speaker_** 
   
       This will be used to add short voiced clips in the app for reproducing the voice that will guide the child on his or her activities. 
       By the desire, the user will have the choice to mute the sound. 
       The app doesn't require the Media player for its functionality. 
       
        * [Media player](https://developer.android.com/guide/topics/media/mediaplayer)
        
  
   