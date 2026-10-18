#  Plateforme E-Commerce Microservices

**Projet académique — Master SDIA**

---

##  Informations du projet

| **Élément** | **Détail** |
|-------------|------------|
| **Réalisatrice** | Fatimazahra Massane |
| **Niveau** | Master SDIA (Systèmes Distribués et Intelligence Artificielle) |
| **Période** | Octobre 2026 |
| **Technologies** | Java 17, Spring Boot 3.x, Spring Cloud, Angular 19 |
| **Architecture** | Microservices distribués |

---

##  Objectifs du projet

Développement d'une application e-commerce complète basée sur une **architecture microservices** mettant en œuvre :

- ✅ Séparation des responsabilités métier par service
- ✅ Communication interservices avec OpenFeign
- ✅ Découverte automatique des services (Eureka)
- ✅ Routage centralisé (Spring Cloud Gateway)
- ✅ Configuration externalisée (Config Server)
- ✅ Interface utilisateur moderne (Angular)

---

##  Architecture technique

### Vue d'ensemble

┌─────────────────────────────────────────────────────────────┐
│                    CLIENT ANGULAR (4200)                     │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────────┐
│              SPRING CLOUD GATEWAY (8888)                     │
│         Point d'entrée unique de l'application               │
└─────┬──────────────┬──────────────┬──────────────┬──────────┘
      │              │              │              │
      ▼              ▼              ▼              ▼
┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────────────┐
│ Customer │  │Inventory │  │ Billing  │  │  Config Server   │
│  (8081)  │  │  (8082)  │  │  (8083)  │  │     (9999)       │
└────┬─────┘  └────┬─────┘  └────▲─────┘  └──────────────────┘
     │             │              │
     └─────────────┴──────────────┘
            OpenFeign Calls
                          
┌─────────────────────────────────────────────────────────────┐
│           EUREKA DISCOVERY SERVER (8761)                     │
│         Registre central des microservices                   │
└─────────────────────────────────────────────────────────────┘

###  Les microservices

#### **1. Customer Service** (Port 8081)
Gestion des clients de la plateforme.
- **Endpoints** : `/customers`, `/customers/{id}`
- **Fonctionnalités** : CRUD clients, consultation des données

#### **2. Inventory Service** (Port 8082)
Gestion du catalogue produits.
- **Endpoints** : `/products`, `/products/{id}`
- **Fonctionnalités** : Gestion du stock, consultation produits

#### **3. Billing Service** (Port 8083)
Gestion des factures et transactions.
- **Endpoints** : `/bills`, `/bills/{id}`, `/bills/full`
- **Fonctionnalités** : 
  - Création de factures
  - Association client/produits
  - Communication avec Customer et Inventory via **OpenFeign**

#### **4. Discovery Service** (Port 8761)
**Eureka Server** — Enregistrement et découverte des services.
- Interface web : `http://localhost:8761`
- Surveillance de la santé des instances

#### **5. Gateway Service** (Port 8888)
**Spring Cloud Gateway** — Routeur intelligent.
- Routage dynamique vers les microservices
- Load balancing intégré
- Filtres et sécurité centralisés

#### **6. Config Service** (Port 9999)
**Spring Cloud Config Server** — Configuration centralisée.
- Externalisation des propriétés
- Support Git pour le versioning de la configuration
- Refresh dynamique des configurations

#### **7. Frontend Angular** (Port 4200)
Interface utilisateur moderne et réactive.
- **Framework** : Angular 19
- **UI Library** : Bootstrap 5
- **Communication** : HttpClient avec la Gateway

---

##  Démarrage rapide

### Prérequis

```bash
# Vérifier les installations
java -version          # JDK 17+
mvn -version           # Maven 3.8+
node -v                # Node.js 18+
npm -v                 # npm 9+


 ### Installation


git clone https://github.com/fatimazahramassane/architecture-microservices.git
cd architecture-microservices

# 2. Compiler le backend
mvn clean install -DskipTests

# 3. Installer les dépendances frontend
cd ecom-client-ang
npm install

### Lancement des services

**Ordre recommandé :**
# Terminal 1 : Config Server
cd config-service
mvn spring-boot:run

# Terminal 2 : Eureka Discovery
cd discovery-service
mvn spring-boot:run

# Terminal 3 : Customer Service
cd customer-service
mvn spring-boot:run

# Terminal 4 : Inventory Service
cd inventory-service
mvn spring-boot:run

# Terminal 5 : Billing Service
cd billing-service
mvn spring-boot:run

# Terminal 6 : API Gateway
cd gateway-service
mvn spring-boot:run

# Terminal 7 : Frontend Angular
cd ecom-client-ang
npm start
### 🔍 Vérification

- **Eureka Dashboard** : http://localhost:8761
- **Application Angular** : http://localhost:4200

---

##  Tests et validation

### Test des endpoints
# Customer Service
curl http://localhost:8081/customers

# Inventory Service
curl http://localhost:8082/products

# Billing Service (avec enrichissement)
curl http://localhost:8083/bills/1

# Via Gateway
curl http://localhost:8888/customers
curl http://localhost:8888/products

### Configuration externalisée

# Test Config Server
curl http://localhost:9999/customer-service/default

##  Fonctionnalités clés

### Communication interservices
- **OpenFeign** pour les appels REST déclaratifs
- **Resilience4j** pour la tolérance aux pannes
- Load balancing client-side avec Spring Cloud LoadBalancer

### Découverte de services
- Enregistrement automatique au démarrage
- Health checks périodiques
- Désenregistrement propre à l'arrêt

### Configuration centralisée
- Properties externalisées dans Git
- Support des profils (dev, prod, test)
- Refresh sans redémarrage (@RefreshScope)

---

##  Structure du projet

architecture-microservices/
├── config-service/              # Config Server (9999)
├── discovery-service/           # Eureka Server (8761)
├── customer-service/            # Service Clients (8081)
├── inventory-service/           # Service Produits (8082)
├── billing-service/             # Service Factures (8083)
├── gateway-service/             # API Gateway (8888)
├── ecom-client-ang/             # Frontend Angular (4200)
├── pom.xml                      # Maven Parent
└── README.md                    # Ce fichier

## ️ Stack technique

| **Catégorie** | **Technologie** | **Version** |
|---------------|-----------------|-------------|
| Backend Framework | Spring Boot | 3.2.x |
| Cloud | Spring Cloud | 2023.0.x |
| Service Discovery | Netflix Eureka | Latest |
| API Gateway | Spring Cloud Gateway | Latest |
| Config Server | Spring Cloud Config | Latest |
| Communication | OpenFeign | Latest |
| Frontend | Angular | 19.x |
| UI | Bootstrap | 5.x |
| Build Tool | Maven | 3.8+ |
| Language | Java | 17+ |

---

##  Améliorations futures

- [ ] Authentification JWT avec Spring Security
- [ ] Documentation API avec Swagger/OpenAPI
- [ ] Tests d'intégration complets
- [ ] Containerisation avec Docker
- [ ] Orchestration avec Kubernetes
- [ ] Monitoring avec Spring Boot Actuator + Prometheus
- [ ] Distributed Tracing avec Zipkin/Sleuth

---

##  Auteur

**Fatimazahra Massane**  
Master SDIA — Université  
*Projet réalisé en Octobre 2026*

---

##  Ressources et références

- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Microservices Patterns](https://microservices.io/)
- [Angular Documentation](https://angular.io/docs)
- [OpenFeign GitHub](https://github.com/OpenFeign/feign)

---

**© 2026 — Projet académique Master SDIA**

---
