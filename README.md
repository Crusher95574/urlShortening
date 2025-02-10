This is a simple URL shortener service built with Spring Boot. Project details - https://roadmap.sh/projects/url-shortening-service

# URL Shortener Service
A simple URL shortening service built with Spring Boot. This application allows users to create shortened URLs, retrieve original URLs, update and delete existing short URLs, and get statistics (such as access counts) for each short URL. It also handles redirection to the original URL.

## Features

- **Create Short URL:** Generate a new short URL for a given original URL.
- **Retrieve URL:** Get details (original URL, access count) for a given short URL code.
- **Update URL:** Update the original URL associated with a short URL.
- **Delete URL:** Remove a short URL from the system.
- **Redirection:** Redirect to the original URL when the short URL is accessed.
- **Statistics:** Track and view access counts for each short URL.
- **Environment Profiles:** Use an in-memory H2 database for development and a PostgreSQL cloud service (Aiven) for production.
- **Spring Security:** Configured to permit access to public endpoints and static resources while securing other endpoints.

## Tech Stack

- **Backend:** Spring Boot (Java 17)
- **Security:** Spring Security (configured with CORS, CSRF disabled, etc.)
- **Persistence:** Spring Data JPA
  - **Development:** H2 (in-memory)
  - **Production:** PostgreSQL (hosted on Aiven)
- **Deployment:** Google Cloud App Engine Standard
- **Build Tool:** Maven

2. Activate the Development Profile:
The default profile for local development is dev, which uses H2.
You can run the application with:
  mvn spring-boot:run -Dspring-boot.run.profiles=dev

3. Access the Application:
Frontend: Open http://localhost:8080 to view your static files (e.g., index.html).
H2 Console: Open http://localhost:8080/h2-console to inspect the H2 database.
API Endpoints: Use Postman or your browser to test endpoints such as /api/urls.

4. Configuration
Profiles
The project uses Spring profiles to differentiate between development and production:

Development (dev):
Uses an H2 in-memory database.
Production (prod):
Uses a PostgreSQL database hosted on Aiven. 

5. Security Configuration
The application uses Spring Security to:

Allow unauthenticated access to /api/urls/** and /h2-console/**.
Permit access to static resources (HTML, CSS, JS, etc.).
Enable CORS for specific origins.
The security configuration is defined in SecurityConfig.java.

6. Deployment
Deploying to Google Cloud App Engine
Create an App Engine Application:

Log into the GCP Console.
Create a new project or select an existing one.
Enable App Engine for your project and choose a region (for India, you might use asia-south1).
Configure the Production Environment:
Ensure your application-prod.properties is properly set up for PostgreSQL (Aiven).

Create an app.yaml File

7. Build the Application:
   mvn clean package

8. Deploy to App Engine:
gcloud app deploy
Ensure your active project is set with:
gcloud config set project YOUR_PROJECT_ID
   

7. API Endpoints
Some key endpoints include:

POST /api/urls – Create a new short URL.
GET /api/urls/{code} – Retrieve details of a short URL.
PUT /api/urls/{code} – Update an existing short URL.
DELETE /api/urls/{code} – Delete a short URL.
GET /api/urls/r/{code} – Redirect to the original URL.
GET /api/urls/{code}/stats – Retrieve statistics for a short URL.

Contributing
Contributions are welcome! Please fork the repository and submit a pull request with your improvements. For major changes, please open an issue first to discuss what you would like to change.


