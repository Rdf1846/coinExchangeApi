# Coin Exchange Application

## Overview

The Coin/Money Exchange Application is a platform that facilitates the buying and selling of Indian Rupee coins. It includes seller registration, buyer requests, real-time location sharing using Google Maps, OTP verification, and Okta integration for authentication. The backend is developed using Spring Boot, while the frontend is built with React.js, ensuring mobile compatibility.

## Features

1. **Seller Registration**: Sellers can register and list their coin inventory.
2. **Buyer Requests**: Enables buyers to request specific coin denominations.
3. **Real-time Location Sharing**: Uses Google Maps for sellers to share their real-time location with buyers.
4. **OTP Verification**: Ensures security by verifying mobile numbers and emails using OTPs.
5. **Okta Integration**: Manages user authentication and authorization.

## Technologies Used

- **Backend**: Spring Boot
- **Frontend**: React.js
- **Database**: MySQL
- **Maps**: Google Maps API
- **Authentication**: Okta, Spring Security
- **OTP Service**: Twilio (for SMS), Gmail (for email)
- **Build Tool**: Maven

## Setup and Installation

### Prerequisites

- Java 11 or higher
- Node.js and npm
- MySQL
- Maven

### Backend Setup

1. **Clone the repository:**
    ```bash
    git clone [https://github.com/yourusername/coin-exchange.git](https://github.com/Rdf1846/coinExchangeApi.git)
    cd coin-exchange/backend
    ```

2. **Configure MySQL Database:**
    Update the `application.properties` file with your MySQL database details:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/yourdbname
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    ```

3. **Build the backend:**
    ```bash
    mvn clean install
    ```

4. **Run the backend:**
    ```bash
    mvn spring-boot:run
    ```

### Frontend Setup

1. **Navigate to the frontend directory:**
    ```bash
    cd ../frontend
    ```

2. **Install dependencies:**
    ```bash
    npm install
    ```

3. **Run the frontend:**
    ```bash
    npm start
    ```

### OTP Configuration

#### Twilio (for SMS)

1. **Sign up for a Twilio account** and get your Account SID and Auth Token.
2. **Configure Twilio in your application.properties:**
    ```properties
    twilio.account.sid=your_twilio_account_sid
    twilio.auth.token=your_twilio_auth_token
    twilio.phone.number=your_twilio_phone_number
    ```

#### Gmail (for email)

1. **Enable Less Secure Apps** on your Gmail account.
2. **Generate an App Password** if using two-factor authentication.
3. **Configure Gmail in your application.properties:**
    ```properties
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=your-email@gmail.com
    spring.mail.password=your-app-password
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    ```

### Google Maps Configuration

1. **Get a Google Maps API key** from the Google Cloud Console.
2. **Add the API key in your React application:**
    ```javascript
    const GOOGLE_MAPS_API_KEY = 'your_google_maps_api_key';
    ```

### Okta Configuration

1. **Sign up for an Okta account** and create a new application.
2. **Update the Okta details in your application.properties:**
    ```properties
    okta.client.orgUrl=https://your_okta_domain
    okta.client.token=your_okta_token
    ```

## Usage

1. **Register as a Seller**: Sign up and list the available coins for exchange.
2. **Request Coins as a Buyer**: Browse available coins and make requests.
3. **Share Real-Time Location**: Sellers can share their location with buyers for easier transactions.
4. **OTP Verification**: Verify your mobile number and email during registration.
5. **Secure Authentication**: Log in and manage your account using Okta.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support, please contact:
- **Name**: Rahul Kasana
- **Email**: rahul.kasana1846@gmail.com
- **Location**: Ghaziabad, Uttar Pradesh

---

Happy Money Trading!
