# Katherine's Personal Project

## Virtual Bunny Adoption Center

> This *Virtual Bunny Adoption Centre* application allows users to create a profile to browse and choose from a 
> selection of bunnies to adopt, as well as being able to view previously adopted bunnies!

**The application is useful for:**
   - people who are interested in adopting a bunny 
   - also a useful tool for adoption organizations that need a structured and organized way to put 
their bunnies up for adoption.  

Personally, I think bunnies are one of my favourite animals and after hearing about a local rabbit culling near the 
Vancouver International Airport, I was inspired to create this project to show appreciation for these cute animals and 
implement working code that enhances the process of animal adoption.  

## User Stories

- As a user, I want to be able to add a new adoption profile
- As a user, I want to be able to view bunnies up for adoption
- As a user, I want to be able to add adopted bunnies
- As a user, I want to be able to see my adopted bunnies
- As a user, I want to be able to save my created adoption profile with my name and adopted bunnies
- As a user, I want to be able to load my previously created adoption profile initially when the application is run


**Phase 4: Task 2:**
***
Thu Nov 25 11:35:54 PST 2021
Bunny added to owned Bunnies in profile.

Thu Nov 25 11:35:59 PST 2021
Bunny added to owned Bunnies in profile.

Thu Nov 25 11:36:04 PST 2021
Bunny added to owned Bunnies in profile.

Thu Nov 25 11:36:05 PST 2021
All bunnies cleared from owned Bunnies in profile.

Thu Nov 25 11:36:11 PST 2021
Bunny added to owned Bunnies in profile.
****


**Phase 4: Task 3:**
From inspection of my UML, my design seems reasonable but possible changes include:
- Introducing helper methods in my BunnyAdoptionGUI class, as I noticed some methods
  don't follow the single responsibility principle
- Reduce the number of JLabels and JButtons introduced in the BunnyAdoptionGUI class by creating a more general
  JLabel/Jbutton name and reusing it for various JLabels/JButtons with similar characteristics