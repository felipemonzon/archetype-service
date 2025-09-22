# archetype-service
archetype kotlin service

# Version
![version](https://img.shields.io/badge/version-1.0.0-blue.svg)

Para más detalle mira el archivo [CHANGELOG](CHANGELOG)

# Quality Gate


### Pre-requisitos 📋
Tener instalado
* IntelliJ
* Gradle
* Kotlin
* MySQL

Para desencriptar los datos del archivo properties es necesario
agregar este parámetro al iniciar el proyecto

```
./gradlew encryptProperties --password=felipemonzon
```

Para encriptar los datos sensibles es necesario compilar con la siguiente instrucción:
```
./gradlew decryptProperties --password=felipemonzon
```

### Instalación 🔧

Proyecto generado y compilado con gradle

```
./gradlew
```

## Ejecutando las pruebas ⚙

Para ejecutar las pruebas y comprobar la calidad del código en sonar

```
./gradlew clean test --info
```

### Y las pruebas unitarias de codificación ⌨️

Las pruebas se realizaron con mockito y junit

```
  
```

## Despliegue 📦

## Construido con 🛠️

* Spring boot 3.5.5
* Kotlin
* Gradle
* Intellij IDEA

### Formateador de sintaxis de código 📋
Para formatear el código se realiza de la siguiente manera

```
./gradlew spotlessApply
```
Para validar el formato del código se realiza del siguiente manera

```
./gradlew spotlessCheck
```

## Versionado 📌

Usamos [GitHub](https://github.com/felipemonzon/archetype-kotlin-service) para el versionado.

## Autores ✒️

* **[Felipe Monzón](https://felipemonzon.github.io/)** - *WEB AND JAVA DEVELOPER*

## Contribuyendo 🖇


## Licencia 📄

Este proyecto está bajo la Licencia GNU General Public License v3.0 - mira el archivo [LICENSE.md](LICENSE) para detalles
