# 🧩 Microservices Customer Registration System

A simple microservices-based project demonstrating customer registration with fraud detection and notification via RabbitMQ. Built using **Spring Boot**, **Eureka Server**, and **RabbitMQ**, with communication via **REST** (to be replaced by OpenFeign in the future).

---

## 📌 Overview

### Microservices Involved:

- **Customer Service**: Handles customer registration.
- **Fraud Service**: Checks if a customer is fraudulent.
- **Notification Service**: Sends notifications to successfully registered users.
- **RabbitMQ**: Acts as a message broker for notifications.
- **Eureka Server**: Service discovery for internal communication.

---

## 🔧 Tech Stack

| Component          | Technology             |
|-------------------|------------------------|
| Language           | Java                   |
| Framework          | Spring Boot            |
| Messaging          | RabbitMQ               |
| Service Registry   | Eureka Server          |
| Database           | PostgreSQL             |
| Communication      | REST (via `RestTemplate`) |
| Future Extensions  | Spring Cloud Gateway, OpenFeign |

---

## 📈 Architecture Flow
![Sequence Diagram](https://github.com/user-attachments/assets/85483570-22c3-410e-b479-8de3b2b9f1ba)

# **Workflow:**

1. A client sends a `CustomerRequest` to the **Customer Service**.
2. The **Customer Service**:
   - Saves the customer data.
   - Calls **Fraud Service** via Eureka + REST to check for fraud.
3. **Fraud Service** logs the fraud check and returns a response.
4. If the customer is not a fraudster:
   - The **Customer Service** publishes a message to **RabbitMQ**.
   - The **Notification Service** (consumer) receives the message and processes the notification.
5. If the customer is a fraudster:
   - Registration fails, and the client is notified.

---

## 🗃️ Project Structure

```
├── customer
├── fraud
├── notification
├── eureka-server
├── rabbitMQ (config and producer logic)
└── pom.xml (parent)
```


Each service is a Spring Boot app with its own `pom.xml`.

---

## 💬 Sample Communication

### 🧪 Fraud Check Request (from Customer Service)

```java
FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
    "http://FRAUD/api/v1/fraud-check/{customerId}",
    FraudCheckResponse.class,
    customer.getId()
);
```
# 📨 RabbitMQ Notification Producer
```java
rabbitTemplate.convertAndSend(exchange, routingKey, message);
```

# 📥 RabbitMQ Notification Consumer
```java
@RabbitListener(queues = "${rabbitmq.queue.name}")
public void receiveMessage(String message) {
    System.out.println("Received message: " + message);
}
```

# 🚀 Running the Project
`Currently, Docker is not set up. Each service must be run individually via IDE or terminal.`

## Prerequisites:
 - Java 17+
 - PostgreSQL running for customer and fraud services
 - RabbitMQ running (default port: 5672)
 - Eureka Server running before other services

## Run Order:
 -eureka-server
 -fraud
 -customer
 -rabbitMQ configuration logic
 -notification

Each service registers with Eureka upon startup.

# 📦 Future Improvements
 - Migrate REST calls to OpenFeign
 - Add Spring Cloud Gateway for API routing
 - Add Docker Compose for easy orchestration
 - Integrate centralized logging & monitoring
 - Implement token-based authentication (e.g., JWT)

## ✍️ Author

**Abror Allaberganov**  
Java Backend Developer with a passion for clean architecture and distributed systems.  
📧 abror.developer@gmail.com
