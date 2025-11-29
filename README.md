# DIT3-1-DELEON-JASMINEROBELLE-ACT06


1.Which API did you choose and why?
I chose the NewsAPI for this project. My primary reason was that it returns a large list of articles, which allowed me to demonstrate proficiency in handling complex, nested JSON data. This choice specifically required me to implement and populate a RecyclerView, which is a core skill for professional Android development.

2.How did you implement data fetching and JSON parsing?
Data fetching was implemented using the Retrofit library because it provides a clean, type-safe way to define API endpoints. I combined Retrofit with the GsonConverterFactory, which handles all the JSON parsing. I created specific Kotlin data classes that mirrored the structure of the NewsAPI response. When the API call succeeded, Gson automatically converted the JSON into these data objects, which were then immediately used to update the RecyclerView Adapter.

3.What challenges did you face when handling errors or slow connections?
The main challenge was ensuring a smooth user experience regardless of the connection quality. To handle this, I first used a network check (ConnectivityManager) to immediately inform the user with a Snackbar if there was no internet. For API-specific errors (like an invalid API key or a bad request), I checked the Retrofit response code and displayed a descriptive error message in the UI. To address slow connections, I used a ProgressBar that is displayed during the network request and hidden when the data is finally received, providing necessary visual feedback.

4.How would you improve your app's UI or performance in future versions?
To improve the UI, I would replace the basic list layout with visually appealing CardViews within the RecyclerView to make each article look more professional and distinct. For performance, the most critical improvement would be using an image loading library like Glide or Coil to efficiently handle the article images. Additionally, I would implement pagination to fetch articles in small batches instead of all at once, which would reduce load times and save on data usage.
