# javaProjecte (Ricard y Jose Manuel)
Treball de java(getio d'apartaments)

 * [modificacions](#Modificacions)

## Modificacions
1. [Gestion de reservas (fechas)](#Gestion_de_reservas)
2. [Nueva ventana para reservar productos](#Nueva_ventana_para_reservar_productos)
3. [Labels para indicar si la accion se a echo correctamente](#Labels_para_indicar_si_la_accion_se_a_echo_correctamente)
4. [Vista en tablaView y no en GridPlane](#Vista_en_tablaView_y_no_en_GridPlane)

### Gestion_de_reservas

Controlamos que una reserva no solape a otra, con la siguente query:
```sql
SELECT *
FROM tabla
WHERE (fehaIn BETWEEN fechaEntrada AND fechaSalida) OR (fechafi BETWEEN fechaEntrada AND fechaSalida) OR (fehaIn <= fechaEntrada  AND fechafi >= fechaSalida);
```
Esta query devolverá los registros de todas las reservas existentes entre esas fechas.
Esta query es enviada a una función el cual comprueva si hay registros en la fechas indicada, esta función devolverá un boleano y dependiendo el resultado mostrará un mensaje en la vista de si se realiza o se deniega la acción.

### Nueva_ventana_para_reservar_productos

Trasladamos de la vista "reservas" a la nueva vista "productReservas", que hace la gestión sobre la contratación de los servicios extras, el flujo sería el siguiente:
1. Ponemos el numero de reserva.
2. Seleccionamos el servicio.
3. Pulsamos botón de contratar.

Si la reserva no existe aparecerá un mensaje de error indicando que se tiene que crear previamente la reserva, este error saldrá en la vista en forma de label.
Cada servicio solo se podrá contratar un sola vez y si este ya esta contratado mostrará un mensaje de error indicando que ya está contartado, si tiene éxito la contratación mostrará un mensaje en la vista de operación exitosa.

### Labels para indicar si la accion se a echo correctamente

Aparte de la gestión anterior de los label hemos creado unos nuevos en las otras vistas para indicar si las acciones has sido exitosas.

### Vista en tablaView y no en GridPlane

Hemos cambiado todos los gridPlanes por tablesWies, modiifcando las vistas, insertando el nuevo codigo
```java

```
y en el controlador pondremos el siguente:
```java

```