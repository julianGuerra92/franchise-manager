# Gestor de Franquicias - Accenture

Este proyecto fue construido siguiendo los principios de Clean Architecture y utilizando Spring WebFlux para lograr una aplicación escalable, mantenible y reactiva.

## Tabla de contenidos
- [Para Iniciar](#para-iniciar)
    - [Prerrequisitos](#prerrequisitos)
    - [Instalación](#instalación)
    - [Comandos Docker](#comandos-docker)
    - [Ejecución de Pruebas Unitarias](#ejecución-de-pruebas-unitarias)
- [Endpoints Existentes](#endpoints-existentes)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Consideraciones](#consideraciones)
- [Despliegue en AWS y Base de Datos](#despliegue-en-aws-y-base-de-datos)
## Para Iniciar
Sigue estas instrucciones para levantar el proyecto en tu máquina local.

### Prerrequisitos
- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (Java 17 o superior)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://docs.docker.com/get-docker/)

### Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/julianGuerra92/franchise-manager.git
   ```
2. Navega al directorio del proyecto:
   ```bash
   cd franchise-manager
   ```

### Comandos Docker
Para iniciar el proyecto en un entorno Docker:
```bash
docker-compose up --build
```
Esto levantará todos los servicios definidos en el archivo `docker-compose.yml`.

### URL Base de la API
Una vez que el proyecto se levanta con Docker, la API queda disponible en:

```
http://localhost:8080/api/
```

### Ejecución de Pruebas Unitarias
Para ejecutar las pruebas unitarias del proyecto:
```bash
mvn test
```

## Endpoints Existentes
A continuación se listan los endpoints REST disponibles:

- **Sucursal (Branch):**
  - `POST /branch` — Crea una sucursal.

- **Franquicia (Franchise):**
  - `POST /franchise` — Crea una franquicia.

- **Producto (Product):**
  - `POST /product` — Crea un producto.
  - `PUT /product/{id}` — Actualiza el stock de un producto.
  - `DELETE /product/{id}` — Elimina un producto.

## Ejemplos de JSON para cada endpoint
A continuación se muestran ejemplos de los cuerpos JSON que debes enviar en cada endpoint:

### Franquicia (Franchise)
**POST /franchise**
```json
{
  "name": "Franquicia Ejemplo"
}
```

### Sucursal (Branch)
**POST /branch**
```json
{
  "name": "Sucursal Principal",
  "franchiseId": 1
}
```

### Producto (Product)
**POST /product**
```json
{
  "name": "Producto A",
  "stock": 100.0,
  "branchId": 1
}
```

**PUT /product/{id}**
```json
{
  "stock": 150.0
}
```


## Estructura del Proyecto
El proyecto sigue la arquitectura limpia, separando claramente las capas de dominio, infraestructura y presentación:
- `domain/model`: Modelos de negocio.
- `domain/repository`: Interfaces de persistencia.
- `domain/usecase`: Casos de uso.
- `infrastructure`: Implementaciones técnicas (persistencia, configuración, etc).
- `presentation/rest`: Controladores y DTOs para la API REST.

## Tecnologías Utilizadas
- Java 17+
- Spring Boot
- Spring WebFlux
- Maven
- Docker

## Consideraciones
- El proyecto está diseñado para ser reactivo y escalable.
- La estructura facilita la mantenibilidad y la extensión de funcionalidades.
- Los endpoints aceptan y devuelven datos en formato JSON.

## Despliegue en AWS y Base de Datos

### Despliegue de Base de Datos con Terraform
La base de datos utilizada por la aplicación se despliega en AWS RDS utilizando Terraform. El archivo de configuración se encuentra en:

```
infra/rds-deploy.tf
```

Esto creará una instancia de RDS en AWS según la configuración definida en el archivo `rds-deploy.tf`.

### Despliegue de la Aplicación en AWS con GitHub Actions
El despliegue de la aplicación en AWS se realiza automáticamente mediante un flujo de trabajo de GitHub Actions. Cada vez que se realiza un push a la rama principal, el pipeline construye la aplicación y la despliega en AWS.

El archivo de configuración del workflow se encuentra en el repositorio de GitHub, y realiza las siguientes tareas:
- Compila y prueba la aplicación.
- Construye la imagen Docker.
- Publica la imagen en Amazon ECR.
- Despliega la aplicación en AWS (por ejemplo, ECS, EC2 o Elastic Beanstalk).

Para más detalles, revisa el archivo de workflow en el repositorio de GitHub.

URL del servicio desplegado: 
```
http://100.25.45.46:8080/api/
```