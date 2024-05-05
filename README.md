# spring-gradle-01
Prueba tecnica - Microservicio usando Spring y Gradle

El proyecto cuenta con un endpoint

* Funcionalidad: registrar usuario 
* Metodo HTTP: POST
* URL: http://localhost:8080/user
* JSON de prueba:
```
{
 	"name":"Maria",
 	"email": "maun42@gmail.com",
 	"password":"maun123",
 	"phones": [
 			{
 				"number":"123456",
 		    "citycode":"1",
 				"countrycode":"57"
 			}		
 	]
 }
```


# mejoras pendientes
   - Separar entidad y modelo
   - Encapsular los servicios
   - Agregar Pruebas unitarias
