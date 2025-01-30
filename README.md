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
*   Servidor de base de datos
    ![Railway](https://railway.com/brand/logotype-light.svg)

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

create table usuarios
(
    id int auto_increment primary key,
    nombre   varchar(50)  null,
    apellido varchar(50)  null,
    email    varchar(100) null,
    password varchar(100) null
);

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
### Capturas
![ScreenShoot](https://southcentralus1-mediap.svc.ms/transform/thumbnail?provider=spo&farmid=194517&inputFormat=jpg&cs=MDAwMDAwMDAtMDAwMC0wMDAwLTAwMDAtMDAwMDQ4MTcxMGE0fFNQTw&docid=https%3A%2F%2Fmy.microsoftpersonalcontent.com%2F_api%2Fv2.0%2Fdrives%2Fb!FybQg087jEGyYG544oFhuTkKnrTKZDxOhJWeNrS31tFY2DIMuOaIRIX4EjEsR-za%2Fitems%2F017VU5QMD4JNKFOCQ3CRHIOWTBDPPIS2VN%3Ftempauth%3Dv1e.eyJzaXRlaWQiOiI4M2QwMjYxNy0zYjRmLTQxOGMtYjI2MC02ZTc4ZTI4MTYxYjkiLCJhcHBpZCI6IjAwMDAwMDAwLTAwMDAtMDAwMC0wMDAwLTAwMDA0ODE3MTBhNCIsImF1ZCI6IjAwMDAwMDAzLTAwMDAtMGZmMS1jZTAwLTAwMDAwMDAwMDAwMC9teS5taWNyb3NvZnRwZXJzb25hbGNvbnRlbnQuY29tQDkxODgwNDBkLTZjNjctNGM1Yi1iMTEyLTM2YTMwNGI2NmRhZCIsImV4cCI6IjE3MzgxMTk2MDAifQ.Y0y1dzzSVDr45byYfMHG0SLInxrB-5RGLynebnLxTHC93XY0VfT5HRPajuUzfZC9SDjwOgjx62aviJT85n4Xq5_YTEQaLXz_P5CQzhsO28baCUKZmPsJ40EqERW2hMmou9ToKEQqBSB0rsLtiCkEo76Ij1TvgchCdZPiSjxR_pPqohwTYX82OmPOtLZ7kv8Zb5gxHL-lkigxodTWi5-H7uNk7EIjynwhBMzATjAMs4y5BSaBnXvYLQCTy5wT57jtD2ym_zW6buw3Xj4UGeqvFMM8TLwSrtOtyVDn84IIcTMlmLOGNczTvROL5xK0usloAO_t1AvWSKdA08gSdpXNrBk3ULuDNftMse0-yuvryDl2duLDfshDEy-BJap8oTyi.5_Zn6Au3EHSfIoE3l1P-Y6MiZMKWg0Eg0ikX2FWRbHQ%26version%3DPublished&cb=63873697424&encodeFailures=1&width=1920&height=948&action=Access)
![ScreenShoot](https://southcentralus1-mediap.svc.ms/transform/thumbnail?provider=spo&farmid=194517&inputFormat=jpg&cs=MDAwMDAwMDAtMDAwMC0wMDAwLTAwMDAtMDAwMDQ4MTcxMGE0fFNQTw&docid=https%3A%2F%2Fmy.microsoftpersonalcontent.com%2F_api%2Fv2.0%2Fdrives%2Fb!FybQg087jEGyYG544oFhuTkKnrTKZDxOhJWeNrS31tFY2DIMuOaIRIX4EjEsR-za%2Fitems%2F017VU5QMHK5VKMGJTTRVCJCCHSHRNCPQUU%3Ftempauth%3Dv1e.eyJzaXRlaWQiOiI4M2QwMjYxNy0zYjRmLTQxOGMtYjI2MC02ZTc4ZTI4MTYxYjkiLCJhcHBpZCI6IjAwMDAwMDAwLTAwMDAtMDAwMC0wMDAwLTAwMDA0ODE3MTBhNCIsImF1ZCI6IjAwMDAwMDAzLTAwMDAtMGZmMS1jZTAwLTAwMDAwMDAwMDAwMC9teS5taWNyb3NvZnRwZXJzb25hbGNvbnRlbnQuY29tQDkxODgwNDBkLTZjNjctNGM1Yi1iMTEyLTM2YTMwNGI2NmRhZCIsImV4cCI6IjE3MzgxMTk2MDAifQ.83V9kTG7tPIvmz2nzcIKbeZP-pv97a-l_BrIF5rH2barTKih7J-Gi763qbVeJ7gim4i-fCUMwk3VP69znohGKHNP1lLwEzfGBGhWcrUQ5s-rPIYZCcWdpBJ9O8jRhSTwYieuKPE0bniGLcP_F4NTRbNStAxOWp4mpUgQqDVa3bJAy_ccy9i3069JTen9P6ioLa4rx-Ie0IgRVQwNiqHjfuBHnMOt8Zjc8shG3UT7BoW-5B3lPy1Wx0ymi51v6_8tCubv74AO_tr9YhZ0FieaU_Au39RWwNH3NRZV2orxmbupkuEduP3C0Bg3ZCdTwkZfjqv_R5ovUG6WKnUDeUDqeTwtOvYg6avljAPIw2CUTZWHUYhpaBEdDQN4oTiNNSrw.Pt3gsk4PcXLpQw1B4FSxo1ttoWpZdnbiIkwbIpXKo4A%26version%3DPublished&cb=63873697567&encodeFailures=1&width=1920&height=948)
![ScreenShoot](https://southcentralus1-mediap.svc.ms/transform/thumbnail?provider=spo&farmid=194517&inputFormat=jpg&cs=MDAwMDAwMDAtMDAwMC0wMDAwLTAwMDAtMDAwMDQ4MTcxMGE0fFNQTw&docid=https%3A%2F%2Fmy.microsoftpersonalcontent.com%2F_api%2Fv2.0%2Fdrives%2Fb!FybQg087jEGyYG544oFhuTkKnrTKZDxOhJWeNrS31tFY2DIMuOaIRIX4EjEsR-za%2Fitems%2F017VU5QMAMC2SMDCYBKNHY3GNWDT2WTDDZ%3Ftempauth%3Dv1e.eyJzaXRlaWQiOiI4M2QwMjYxNy0zYjRmLTQxOGMtYjI2MC02ZTc4ZTI4MTYxYjkiLCJhcHBpZCI6IjAwMDAwMDAwLTAwMDAtMDAwMC0wMDAwLTAwMDA0ODE3MTBhNCIsImF1ZCI6IjAwMDAwMDAzLTAwMDAtMGZmMS1jZTAwLTAwMDAwMDAwMDAwMC9teS5taWNyb3NvZnRwZXJzb25hbGNvbnRlbnQuY29tQDkxODgwNDBkLTZjNjctNGM1Yi1iMTEyLTM2YTMwNGI2NmRhZCIsImV4cCI6IjE3MzgxMTk2MDAifQ.1Lgbl1SSOtGPs42wrw8L7NoV2McKvYB0Uuz9ZbNOBsStGSIgBd1NOlFn_c8dndDhE3mLTgpZE6moscGYxjGOCUXwzebhH9xsRKVX0GinKrFN6v_WO830Nk0dpB5Ejwf3IOP47QdGVwZlkztNWwiaD0Vz2GQhmCLggYKjI98BDQxfQrPkvDA9G5hkJNtnhrtc1nA8U4RjOe-Zr0qTCOSaWsTU3Xhj82EvifdjRdZyarH1Xmc35BXiMZfHnaLYNgUPPNG8m4NMv111i4E1-NMzFmDbfAXLczUFU0mDcAg50zZJ78mCEg2dhMv_eiyH822Tc--e_X6YiE5QM905i1xxQoqM8mCj8Ym0_NJB2cnQe_lCgKZsAtcO1aqo4Ko6K876.PxRkrafGfJ9Zo2Dkp2mFepK9Op_zNPmp8Wv4OeomouI%26version%3DPublished&cb=63873697567&encodeFailures=1&width=1920&height=948)
![ScreenShoot](https://southcentralus1-mediap.svc.ms/transform/thumbnail?provider=spo&farmid=194517&inputFormat=jpg&cs=MDAwMDAwMDAtMDAwMC0wMDAwLTAwMDAtMDAwMDQ4MTcxMGE0fFNQTw&docid=https%3A%2F%2Fmy.microsoftpersonalcontent.com%2F_api%2Fv2.0%2Fdrives%2Fb!FybQg087jEGyYG544oFhuTkKnrTKZDxOhJWeNrS31tFY2DIMuOaIRIX4EjEsR-za%2Fitems%2F017VU5QMFQORKSGR6OGFAIE5TJKYUTZAGB%3Ftempauth%3Dv1e.eyJzaXRlaWQiOiI4M2QwMjYxNy0zYjRmLTQxOGMtYjI2MC02ZTc4ZTI4MTYxYjkiLCJhcHBpZCI6IjAwMDAwMDAwLTAwMDAtMDAwMC0wMDAwLTAwMDA0ODE3MTBhNCIsImF1ZCI6IjAwMDAwMDAzLTAwMDAtMGZmMS1jZTAwLTAwMDAwMDAwMDAwMC9teS5taWNyb3NvZnRwZXJzb25hbGNvbnRlbnQuY29tQDkxODgwNDBkLTZjNjctNGM1Yi1iMTEyLTM2YTMwNGI2NmRhZCIsImV4cCI6IjE3MzgxMTk2MDAifQ.DkEqGDlc175SFWfNHQF8KID1XhBLXyje-AUmiGPCwRTrPt0Ih6y1o51jCF-VwFOgmHJ_bzvjxi_KciGuLIJVEfGxdVCNmXQjyv-u8xE6J33lQX9-dGHiQ-u0nsvDbeL3HlsWANygT7lAOvR_oceV-nRiYiiBvNHmgtyJnxbyBppG8fIIbP4FkcGMHpwwSrlvbWTjybsXYxfWBiXUeh_CjvUgSnY100oz8xpb_Dq4rtdnK8-bYPCCMC3LZH3QOgqNbBc-Gv06rN7vvgZgxBNhYV0h98Ew2GET1yO8C5Zp_k5e2bhZfXy8778Pqv0YAbNejBnjpqz-GJ03bI-QUcByyvnAvluwc6T9z5k7ELUlLKmrwVTLnoHQnYpJ1z8O_SrN.rEoITvYZ8px1C71_FLFZO9ISikTYvlID69snPkXEb-k%26version%3DPublished&cb=63873697565&encodeFailures=1&width=1920&height=948)

### Compañeros
------------
* Wara Alejandra Cruz Cruz
* José Alejandro Fernández Sánchez 
* Evians Reyna Zabala Lazo

y BugaPunks ...

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