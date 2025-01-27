# Planilla Mensual de Sueldos

## Descripción
---------------

Esta aplicación móvil se diseñó para gestionar la planilla mensual de sueldos de empleados. Permite agregar, buscar, eliminar y editar información de empleados, así como realizar operaciones básicas de gestión de personal.

## Funcionalidades
------------------

*   Agregar empleados con información básica (nombre, apellidos, fecha de ingreso, cargo y haber básico)
*   Buscar empleados por nombre o apellidos
*   Eliminar empleados
*   Editar información de empleados
*   Mostrar lista de empleados

## Tecnologías utilizadas
-------------------------

*   Kotlin como lenguaje de programación
*   Android Studio como entorno de desarrollo
*   Arquitectura de componentes (Activities, Fragments, ViewModels)
*   Base de datos local (no especificada en el código proporcionado)

## Estructura del proyecto
---------------------------

*   [app/src/main/java/com/bugabuga/planillamensualdesueldos](cci:7://file:///home/ronald/IdeaProjects/PlanillaMensualdeSueldos/app/src/main/java/com/bugabuga/planillamensualdesueldos:0:0-0:0): Paquete principal de la aplicación
*   `activities`: Paquete que contiene las Activities de la aplicación (MainActivity, OperationEmpleadoActivity)
*   `adapters`: Paquete que contiene los adaptadores para la lista de empleados (EmpleadoAdapter)
*   `models`: Paquete que contiene los modelos de datos (Empleado)
*   `operations`: Paquete que contiene las operaciones de gestión de empleados (EmpleadoOperation)

## Configuracion
En el archivo MySQLconnection.kt, reemplaza la cadena de conexión con la configuración de tu base de datos.
```kotlin
return DriverManager.getConnection(
            "jdbc:mysql://tu_ip:3306/db_planilla", // tu ip y tu base de datos, para esta app db_planilla
            "root", // Tu usuario de la base de datos (normalmente root)
            "" // Tu contraseña normalmente vacía
)
```


### Base de Datos

```sql
CREATE DATABASE db_planilla;
```

### Tablas
```sql
create table empleado
(
    id int auto_increment primary key,
    nombre        varchar(50)    not null,
    apellidos     varchar(50)    not null,
    fecha_ingreso varchar(50)    not null,
    cargo         varchar(50)    not null,
    haber_basico  decimal(10, 2) not null
);

create table beneficios_empleado
(
    id int auto_increment primary key,
    empleado_id     int            not null,
    anos_trabajo    int            not null,
    categoria       varchar(50)    not null,
    bono_movilidad  decimal(10, 2) not null,
    bono_extra      decimal(10, 2) not null,
    bono_antiguedad decimal(10, 2) not null,
    total_ganado    decimal(10, 2) not null,
    iva             decimal(10, 2) not null,
    afp             decimal(10, 2) not null,
    club            decimal(10, 2) not null,
    total_descuento decimal(10, 2) not null,
    liquido_pagable decimal(10, 2) not null,
    constraint beneficios_empleado_ibfk_1
        foreign key (empleado_id) references empleado (id)
);
```
## Notas
---------------------------

*   La aplicación utiliza una base de datos local no especificada en el código proporcionado.
*   La aplicación no incluye autenticación ni autorización de usuarios.
*   La aplicación no incluye validación de datos en la entrada de usuarios.

## Consideraciones
------------------
Agregar el comando 'skip-grant-tables' en el archivo 'my.cnf' sobre "[mysql]" y debajo de "port=3306" para evitar problemas con la base de datos.
### Linux

```bash
/opt/lampp/etc/my.cnf
```
### Windows
```bash
C:\xampp\mysql\bin\my.ini
```

## Licencia
------------

Copyright 2025 BugaPunks

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.