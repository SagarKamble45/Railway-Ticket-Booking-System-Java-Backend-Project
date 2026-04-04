markdown# 🚆 IRCTC Train Booking System

A command-line based Train Ticket Booking System built with Java, simulating core features of the IRCTC platform. The system uses JSON files as a local database to store train and user data.

## 📋 Features

- ✅ User Sign Up
- ✅ User Login with BCrypt password hashing
- ✅ Search Trains by Source and Destination
- ✅ View Available Seats
- ✅ Book a Train Seat
- ✅ View Bookings
- ✅ Cancel a Booking

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| Java 8 | Core language |
| Gradle | Build tool |
| Jackson Databind | JSON parsing |
| BCrypt (jBCrypt) | Password hashing |
| JSON files | Local database |

## 📁 Project Structure
IRCTC/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── ticket/booking/
│   │   │           ├── App.java               # Main entry point
│   │   │           ├── entities/
│   │   │           │   ├── Train.java         # Train entity
│   │   │           │   ├── Ticket.java        # Ticket entity
│   │   │           │   └── User.java          # User entity
│   │   │           ├── services/
│   │   │           │   ├── TrainService.java  # Train operations
│   │   │           │   └── UserBookingService.java # User & booking operations
│   │   │           ├── util/
│   │   │           │   └── UserServiceUtil.java # Password hashing utility
│   │   │           └── localDB/
│   │   │               ├── trains.json        # Train data
│   │   │               └── users.json         # User data
│   └── build.gradle
├── gradle/
├── gradlew
├── gradlew.bat
└── settings.gradle

## ⚙️ Setup & Run

### Prerequisites
- Java 8 or higher
- Gradle (or use the included Gradle wrapper)

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/IRCTC-Train-Booking.git
cd IRCTC-Train-Booking
```

2. **Run the application**
```bash
.\gradlew run       # Windows
./gradlew run       # Mac/Linux
```

## 🚉 Available Train Routes

| Train No | Route |
|----------|-------|
| 12301 | Delhi → Agra → Jaipur → Mumbai |
| 12302 | Bangalore → Chennai → Hyderabad → Mumbai |
| 12303 | Mumbai → Surat → Vadodara → Ahmedabad |
| 12304 | Delhi → Lucknow → Varanasi → Kolkata |
| 12305 | Bangalore → Jaipur → Delhi |

## 📌 How to Use

Sign Up      → Create a new account
Login        → Login with your credentials
Fetch Bookings → View your booked tickets
Search Train → Search trains by source & destination
Book a Seat  → Select a train and book a seat
Cancel Booking → Cancel an existing booking
Exit         → Exit the application


> **Note:** Station names must be entered in **lowercase** (e.g. `delhi`, `mumbai`)

## 🐛 Known Issues / Future Improvements

- [ ] Add ticket ID generation when booking
- [ ] Save booked ticket to user's profile in JSON
- [ ] Add date-based train search
- [ ] Add passenger name to ticket
- [ ] Migrate from JSON files to a real database (MySQL/PostgreSQL)
- [ ] Build a REST API with Spring Boot
- [ ] Add a frontend (React/Angular)

## 👨‍💻 Author

**Sagar Kamble**  
[GitHub](https://github.com/YOUR_USERNAME)