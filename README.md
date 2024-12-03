Component: ACF (Annual Carbon Footprint)
Instructions:
After the registration, the app will have the following user flow:
1. welcome activity (class Welcome)
    * Text View for message display
    * Button to start the process

2. CountrySelection class 
    * Spinner that allows you to choose your country. Functioned by using locale.
    * Button to continue on the process, once pressed, the region will be saved to the database. This action cannot be reversed. Then it will send an intent to the next class.

3. ACFQuestion class (ACF means annual carbon footprint)
    * Text view for displaying questions
    * Some dynamic buttons to display options. 
    * After each time the user press any button, their choice will be recorded by a HashMap with the question number as key (in String format to store on the database) and button order as the value. 
    * The text of questions and answers are stored in the LocalData class. 
    * After the final question is answered, the total ACF is calculated by the calculation class (explanation at the end of section) then the whole HashMap is uploaded to the database. Then it automatically processes to the result.

4. ACFResults class
    * Text view for displaying the result
        * “Loading data” should be seen when the app loads the data
    * The app will read the data under the user’s annualCarbonFootprint’s subkey “total” on the database and display it. The value is in tonnes.
    * Button that process the user to the next page.

5. ACFResult2 class
    * Pie Chart used to display the distribution of the 4 emission areas. (colors based on given colors)
    * Text view to display the actual percentage of each area, rounded off to 2 decimal places.
    * The button on the left will lead back to the last page when pressed
    * The button on the right will process to the next class when pressed

6. ACFResults3 class
    * Text view in the middle will display your total ACF, rounded off to 2 decimal places.
    * Text view at the above will display your region average and your percentage difference, both rounded off to 2 decimal places.
    * Text view at the bottom will display the global average (assumed to be 2 tonnes) and your percentage difference rounded off to 2 decimal places.
    * The button on the left will lead back to the last page when pressed
    * The button on the right will process to the next class when pressed, which is the main activity class.

7. Calculation
    * This is a back-end class.
    * When the constructor is called, it will provide a singleton instance.
    * The calculateCarbonFootprint method takes the above HashMap as an argument.
    * When the calculateCarbonFootprint method is called, it will call each subsequent methods. As the housing data is stored on the database, the second part of the code is located in the updateAfterRead method.
    * For the methods for calculating each parts, except for housing, it will first check if the corresponding key is present in the HashMap, then depends on the cases to write the data onto the database and return the value (in tonnes). For some parts with smaller sub-parts, similar methods are invoked.
    * For the calculate housing method, it will first get the id according to the inputs, which is computed based on the data on the database. Then it will search the emission data from the database then write it on the database (under the user) and return (in tonnes).
    * After all 4 subset are calculated, the total value is then summed up and stored to the database.

8. EcoHubActivity class:
   * provides a hub of eco-friendly resources with clickable links to different sustainability websites by navigating the EcoHubActivity and tapping links which connect to the corresponding website
   * developers can update the URLs by editing the setUpLink() in onCreate(),or adding a TextView in the activity_eco_hub.xml and registering the TextView in the setUpLink() by adding a new call with the new id and URL
   * setUpLink(int textViewId, String url): attaches a ClickListener to a TextView to open the provided URL in browser
   * openUrl(String url): launches an intent to open the specified URL in the device's web browser
   * AppCompatActivity, TextView, Intent.ACTION_VIEW: these dependencies provide support for creating and managing the UI and browser navigation

9. Splash_screen class:
   * the Splash_screen activity displays a splash screen for 3 seconds before transitioning to the main application screen
   * developers can change the splash screen duration by adjusting the 3000 milliseconds in the postDelayed(), and changing the next activity by replacing MainActivity in the Intent with another activity
   * Handler().postDelayed(Runnable, long delayMillis): schedules the transition from the splash screen to the main activity after 3 seconds
   * Intent, finish(): launches the MainActivity once the splash screen ends and removes the splash screen
   * AppCompatActivity, Handler: these dependencies provide support for handling UI and activity transition


Added dependencies:
One dependency is added for the card view for holding the pie chart. Another is added to compute the pie chart.
