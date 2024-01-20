# Notes-App-using-SpringBoot-Angular
Notes  App Using Spring, SpringBoot, MySQL, Angular.

I have developed an application to save Notes for Users 

# Use Case:
1- A system stores the notes of different users.

2- After a user successfully logins into the application, the API 
return recent 10 notes.

3- The system delete all the notes other than last 10 recent notes 
on the hourly basis.  

#Validations that are considered: 
 
1- The notes will only allow [@, ; & * + -] special characters.
2- The Notes cannot be more than 500 letters.
3- Allow User to delete the notes  

#Technologies used: Java 8 , Spring Boot , JPA , Angular, and CRON job is used using SpringBoot.

The frontend UI is designed for basic CRUD Functionality.
