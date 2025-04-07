# Backend 

## 📋 Pré-requisitos

- Docker 20.10+
- Java 21
- JDK instalado
- IDE para desenvolvimento Spring Boot (IntelliJ, Eclipse, etc.)
- Portas 5432, 5050 e 9090 disponíveis

## 🐳 Docker Compose para o app

### 🛠️ Arquivo `docker-compose.yml`

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:13-alpine
    container_name: producao-db
    environment:
      POSTGRES_USER: admin
      POSTGRES_PASSWORD: admin123
      POSTGRES_DB: producao_db
    ports:
      - "5432:5432"
    volumes:
      - pg_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U admin"]
      interval: 5s
      timeout: 5s
      retries: 5

  pgadmin:
    image: dpage/pgadmin4
    container_name: producao-admin
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@admin.com
      PGADMIN_DEFAULT_PASSWORD: admin123
    ports:
      - "5050:80"
    depends_on:
      postgres:
        condition: service_healthy

  keycloak:
    image: quay.io/keycloak/keycloak:21.0.0
    container_name: producao-auth
    environment:
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin123
      KC_DB: postgres
      KC_DB_URL: jdbc:postgresql://postgres:5432/producao_db
      KC_DB_USERNAME: admin
      KC_DB_PASSWORD: admin123
    ports:
      - "9090:8080"
    command: ["start-dev"]
    depends_on:
      postgres:
        condition: service_healthy

volumes:
  pg_data:

networks:
  default:
    name: producao-network

networks:
  default:
    name: producao-network
```
## ⚙️ Configuração application.properties
```
# Configuração do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/producao_db
spring.datasource.username=admin
spring.datasource.password=admin123
spring.jpa.hibernate.ddl-auto=update

# Configuração Keycloak
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:9090/realms/seu-realm
```
## ⚙️ Configuração interna do Keycloak Localhost:9090
