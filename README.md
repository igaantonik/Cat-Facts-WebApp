# Cat-Facts-WebApp

## Table of Contents
- [Description](#description)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Usage](#usage)
- [Contact](#contact)

## Description
**Cat Facts Explorer** is a full-stack application that provides users with random cat facts authored by random users. The project consists of a **Kotlin and Spring** backend and a **React and TypeScript** frontend.

- **Backend:** Utilizes Kotlin's Flow API to create a reactive service that fetches data from public APIs and exposes an endpoint `/cat-facts` using Server-Sent Events (SSE). Every 10 seconds, it retrieves random cat facts and user information, associates them, and streams the combined data to the frontend.

- **Frontend:** A responsive React application that displays cat facts in a grid of cards. Each card shows the author's name and a cat fact. The frontend subscribes to the backend's SSE endpoint using RxJS Observables to receive real-time updates and refresh the view accordingly.

## Technologies Used
- **Frontend:**
  - React
  - TypeScript
  - RxJS
  - CSS Flexbox/Grid

- **Backend:**
  - Kotlin
  - Spring Boot
  - Kotlin Flow API
  - Server-Sent Events (SSE)
  - Gradle



## Installation

### Prerequisites
Ensure you have the following installed on your machine:

- **Node.js** (v14 or higher)
- **npm**
- **Java** (JDK 17 or higher)
- **Gradle**

### Backend Setup
1. **Clone the repository**
   ```bash
   git clone https://github.com/igaantonik/Cat-Facts-WebApp.git
   cd Cat-Facts-WebApp/backend
   ```


2. **Build and run the backend**
   ```bash
   ./gradlew bootRun
   ```
   The backend service should now be running on the specified port (e.g., `http://localhost:8080`).

### Frontend Setup
1. **Navigate to the frontend directory**
   ```bash
   cd ../frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Configure environment variables**
   - Create a `.env` file based on the `.env.example` template.
     ```bash
     cp .env.example .env
     ```
   - Set the necessary environment variables in the `.env` file, such as:
     - `REACT_APP_BACKEND_URL` (e.g., `http://localhost:8080`)

4. **Run the frontend**
   ```bash
   npm run dev
   ```
   The frontend application should now be running on `http://localhost:5173`.




## Running Backend Tests

Navigate to the backend directory

```bash
cd backend
```
Execute the tests

```bash
./gradlew test
```

View Test Reports After running the tests, a detailed report can be found at `backend/build/reports/tests/test/index.html`. Open this file in your browser to see the results.


## Usage
Once both the backend and frontend are running:

1. **Access the Frontend**
   - Open your browser and navigate to `http://localhost:5173`.

2. **View Cat Facts**
   - The homepage displays a grid of cards, each showing an author's name and a cat fact.
   - Every 10 seconds, new cat facts are fetched and displayed in real-time.

3. **Responsive Design**
   - Resize your browser window to see the grid adjust the number of columns for optimal viewing.




## Contact
- **Author:** Iga Antonik
- **Email:** iga.antonik@gmail.com
- **LinkedIn:** [iga-antonik](www.linkedin.com/in/iga-antonik)
- **GitHub:** [igaantonik](https://github.com/igaantonik)

---

