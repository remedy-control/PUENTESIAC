># PUENTESIAC

## Descripción del proyecto

Este proyecto tiene como finalidad escalar incidentes hacia la plataforma con el mismo nombre.

- Almacena el folio enviado por el sistema externo de SIAC junto a otros datos.
- Los pasa a una WO y a una base de datos.

## Estado del proyecto

Este proyecto actualmente se encuentra en uso y está desplegado en los 3 ambientes (desarrollo, QA y producción MX 165 y 166).

## Requerimientos

Se recomienda encarecidamente respetar estos puntos para poder usar la aplicación de forma correcta:

-   Tener instalado Java 1.8
-   Hacer deploy en un servidor Tomcat 8 o superior

## Ejecutar

Divido que el programa se ejecuta por factores externos no se puede ejecutar de manera local.

## Despliegue en el servidor

Para desplegar la aplicación, se requiere colocar el archivo `WS_SIACPUENTE.war` en el servidor de aplicaciones, siguiendo estos pasos:

1.  Accede al servidor de aplicaciones.
2.  Navega hasta la ubicación `/webapps8` del servidor.
3.  En la anterior ruta mencionada, coloca el arhcivo `WS_SIACPUENTE.war`, ubicado en la ruta `/PUENTESIAC/dist/WS_SIACPUENTE.war` del proyecto ya ejecutado.
4.  Una vez que hayas colocado el archivo en el servidor, el servidor de aplicaciones automáticamente desplegará el proyecto. Esto resultará en la creación de un directorio dedicado al proyecto en la siguiente ubicación: `/webapps8/WS_SIACPUENTE`.

