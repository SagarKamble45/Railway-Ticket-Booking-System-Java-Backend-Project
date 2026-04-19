# 🚆 IRCTC Train Booking System
A command-line based Train Ticket Booking System built with Java, simulating core features of the IRCTC platform. The system uses JSON files as a local database to store train and user data.

## ⚠️ Important Note
This project is **still under development** and not fully completed yet.
There are some known bugs that are currently being worked on.
You are welcome to explore the available features, but please note that
some functionality may not work as expected.
I will be updating the repository as the project progresses.
Stay tuned for future updates! 🚀

## 📋 Features
- ✅ User Sign Up
- ✅ User Login with BCrypt password hashing
- ✅ Search Trains by Source and Destination
- ✅ View Available Seats
- ✅ Book a Train Seat
- ✅ View Bookings
- ✅ Cancel a Booking
- ✅ Seat marked as available after cancellation
- ✅ Ticket ID generated on booking

## 🛠️ Tech Stack
| Technology | Usage |
|------------|-------|
| Java 8 | Core language |
| Gradle | Build tool |
| Jackson Databind | JSON parsing |
| BCrypt (jBCrypt) | Password hashing |
| JSON files | Local database |

## 📁 Project Structure
```
IRCTC/
├── app/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── ticket/booking/
│   │               ├── App.java
│   │               ├── entities/
│   │               │   ├── Train.java
│   │               │   ├── Ticket.java
│   │               │   └── User.java
│   │               ├── services/
│   │               │   ├── TrainService.java
│   │               │   └── UserBookingService.java
│   │               ├── util/
│   │               │   └── UserServiceUtil.java
│   │               └── localDB/
│   │                   ├── trains.json
│   │                   └── users.json
│   └── build.gradle
├── gradle/
├── gradlew
├── gradlew.bat
└── settings.gradle
```

## 🎬 Video Demo
[![IRCTC Train Booking System | Java CLI Project Demo](https://img.youtube.com/vi/2Q_XZeMOCeY/0.jpg)](https://youtu.be/2Q_XZeMOCeY)

> Click the thumbnail above to watch the demo on YouTube

## ⚙️ Setup & Run

### Prerequisites
- Java 8 or higher
- Gradle (or use the included Gradle wrapper)

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/SagarKamble45/IRCTC-Train-Booking.git
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
| 12306 | Mumbai → Pune |
| 12307 | Kolkata → Bhubaneswar → Visakhapatnam → Chennai → Bangalore |
| 12308 | Delhi → Agra → Gwalior → Bhopal → Nagpur → Hyderabad |
| 12309 | Chennai → Bangalore |
| 12310 | Ahmedabad → Vadodara → Surat → Mumbai → Pune → Solapur |
| 12311 | Delhi → Chandigarh → Amritsar |
| 12312 | Mumbai → Nashik → Aurangabad → Nagpur → Raipur |
| 12313 | Hyderabad → Nagpur |
| 12314 | Kolkata → Patna → Varanasi → Lucknow → Kanpur → Delhi |
| 12315 | Bangalore → Hyderabad → Nagpur → Bhopal → Agra → Delhi |
| 12316 | Jaipur → Agra → Delhi |
| 12317 | Mumbai → Pune → Bangalore → Chennai → Madurai |
| 12318 | Amritsar → Ludhiana → Delhi → Mathura → Agra → Gwalior |
| 12319 | Ahmedabad → Jaipur |
| 12320 | Chennai → Visakhapatnam → Bhubaneswar → Kolkata |
| 12321 | Pune → Solapur → Hyderabad → Bangalore → Coimbatore |
| 12322 | Delhi → Mathura → Agra → Jhansi → Bhopal → Nagpur |
| 12323 | Kolkata → Varanasi → Allahabad → Delhi → Jaipur |
| 12324 | Bangalore → Chennai |
| 12325 | Amritsar → Chandigarh → Delhi → Agra → Gwalior → Bhopal |

## 📌 How to Use
```
Sign Up        → Create a new account
Login          → Login with your credentials
Fetch Bookings → View your booked tickets
Search Train   → Search trains by source & destination
Book a Seat    → Select a train and book a seat
Cancel Booking → Cancel an existing booking
Exit           → Exit the application
```
> **Note:** Station names must be entered in **lowercase** (e.g. `delhi`, `mumbai`)

## 🐛 Known Issues / Future Improvements
- [ ] Migrate from JSON files to a real database (MySQL/PostgreSQL)
- [ ] Build a REST API with Spring Boot
- [ ] Add a frontend (React/Angular)

## 👨‍💻 Author
**Sagar Kamble**  
[GitHub](https://github.com/SagarKamble45)
