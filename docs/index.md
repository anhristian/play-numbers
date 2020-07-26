# Playing Numbers

## Introduction

This is an educational safe Android app that could be used by the 4-6 years old children 
for joyful learning of numbers from 1 to 10. 
Children will be able to practice interactively their knowledge and having fun at the same time. 
This app assumes that the child knows already some numbers, and it is designed to strengthen 
their knowledge. 

The user will have the option to choose from two sections:

   * One section offers activities where the task will be to match the number with the picture. 
   * The other section offers activities for finding the missing number and placing in the right place.

In the chosen section, the child could select the activity he or she wants to do. 
Each activity will have an example already solved to show the child how to work.
Additional to what appears on the screen, there will be a voice feature incorporated that tells the instructions, 
says back to the child the number he selected, and gives short encouraging expressions.   

## Intended users 

   * Parents and Teachers - using gadgets as a learning option
 
   > As a parent of a 5-year-old child, I want to find a way to make the learning process more interesting 
        for children so that the time spent on gadgets gains more usefulness.

   * Traveling parents and children

   > As a parent with experience in long flights with a child, I want to offer the other parents an option for keeping their children busy, 
        so the long trips will be with more peace for parents and with more fun for little travelers.
  
   
## Design documentation

   * [Wireframe diagram](wireframe.md)
   * [Entity-relationship diagram](erd.md)
   
## Device and external services

   * **_Google Sign In_**
   
      The user will have the choice to provide his or her email, which will help to record the child's progress on the activities.
      The app requires the Google Sign In for its functionality. 
      
        * [Google Sign In](https://developers.google.com/identity/sign-in/android/sign-in)
        
   * **_Device's speaker_** 
   
       This will be used to add short voiced clips in the app for reproducing the voice that will guide the child on his or her activities. 
       By the desire, the user will have the choice to mute the sound. 
       The app doesn't require the Media player for its functionality. 
       
        * [Media player](https://developer.android.com/guide/topics/media/mediaplayer)
        
###### [Entity classes](https://github.com/anhristian/play-numbers/tree/master/app/src/main/java/edu/cnm/deepdive/playnumbers/model/entity)

###### [DAO interfaces](https://github.com/anhristian/play-numbers/tree/master/app/src/main/java/edu/cnm/deepdive/playnumbers/model/dao)

###### [POJO classes](https://github.com/anhristian/play-numbers/tree/master/app/src/main/java/edu/cnm/deepdive/playnumbers/model/pojo)

###### [Repository classes and Database class](https://github.com/anhristian/play-numbers/tree/master/app/src/main/java/edu/cnm/deepdive/playnumbers/service)

###### [Data Definition Language (DDL)](https://github.com/anhristian/play-numbers/blob/master/docs/sql/ddl.sql)        
  
###### [MainViewModel class](https://github.com/anhristian/play-numbers/tree/master/app/src/main/java/edu/cnm/deepdive/playnumbers/viewmodel)
   