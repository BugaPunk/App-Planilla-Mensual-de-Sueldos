# Planilla Mensual de Sueldos
Descripción
Esta aplicación móvil se diseñó para gestionar la planilla mensual de sueldos de empleados. Permite agregar, buscar, eliminar y editar información de empleados, así como realizar operaciones básicas de gestión de personal.
### Base de Datos

```sql
CREATE DATABASE db_planilla;
```

### Tablas
```sql
create table empleado
(
    id            int auto_increment
        primary key,
    nombre        varchar(50)    not null,
    apellidos     varchar(50)    not null,
    fecha_ingreso varchar(50)    not null,
    cargo         varchar(50)    not null,
    haber_basico  decimal(10, 2) not null
);

create table beneficios_empleado
(
    id              int auto_increment
        primary key,
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

create index empleado_id
    on beneficios_empleado (empleado_id);
```
Funcionalidades
Agregar empleados con información básica (nombre, apellidos, fecha de ingreso, cargo y haber básico)
Buscar empleados por nombre o apellidos
Eliminar empleados
Editar información de empleados
Mostrar lista de empleados
Tecnologías utilizadas
Kotlin como lenguaje de programación
Android Studio como entorno de desarrollo
Arquitectura de componentes (Activities, Fragments, ViewModels)
Base de datos local (no especificada en el código proporcionado)
Estructura del proyecto
app/src/main/java/com/bugabuga/planillamensualdesueldos: Paquete principal de la aplicación
activities: Paquete que contiene las Activities de la aplicación (MainActivity, OperationEmpleadoActivity)
adapters: Paquete que contiene los adaptadores para la lista de empleados (EmpleadoAdapter)
models: Paquete que contiene los modelos de datos (Empleado)
operations: Paquete que contiene las operaciones de gestión de empleados (EmpleadoOperation)
Notas
La aplicación utiliza una base de datos local no especificada en el código proporcionado.
La aplicación no incluye autenticación ni autorización de usuarios.
La aplicación no incluye validación de datos en la entrada de usuarios.

### Programación Móvil INF-245
Licencia
[Espero que hayas considerado la licencia de tu proyecto. Si no, puedes agregar una licencia como MIT o Apache 2.0]
