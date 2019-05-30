# javaProjecte (Ricard y Jose Manuel)
Treball de java(getio d'apartaments)

 * [modificacions](#Modificacions)

## Modificacions
1. [Gestion de reservas (fechas)](#Gestion_de_reservas)
2. [Nueva ventana para reservar productos](#Nueva_ventana_para_reservar_productos)
3. [Labels para indicar si la accion se a echo correctamente](#Labels_para_indicar_si_la_accion_se_a_echo_correctamente)
4. [Vista en tablaView y no en GridPlane](#Vista_en_tablaView_y_no_en_GridPlane)
5. [Autocompletar](#Autocompletar)

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

### Labels_para_indicar_si_la_accion_se_a_echo_correctamente

Aparte de la gestión anterior de los label hemos creado unos nuevos en las otras vistas para indicar si las acciones has sido exitosas.

### Vista_en_tablaView_y_no_en_GridPlane

Hemos cambiado todos los gridPlanes por tablesWies, modiifcando las vistas, insertando el nuevo codigo de Ejemplo:
```java
<TableView fx:id="tabla" layoutX="32.0" layoutY="51.0" prefHeight="400.0" prefWidth="346.0">
	<columns>
		<TableColumn fx:id="cDoc" text="Documentación" />
		<TableColumn fx:id="cNombre" editable="false" text="Nombre" />
		<TableColumn fx:id="cEmail" text="Email" />
		<TableColumn fx:id="cTel" text="Tel" />
	</columns> 
</TableView>
```
y en el controlador pondremos el siguente:
```java
ResultSet result = (ResultSet) clientobj.search(nameSearch.getText());
try {
	ObservableList<Personas> personData = FXCollections.observableArrayList();
	while(result.next()) {
		Personas persona = new Personas(result);
		personData.add(persona);
	}
	tabla.setItems(personData);
} catch (SQLException e) {
    System.out.println("SQLException"+ e.getMessage());
    System.out.println("SQLState"+ e.getSQLState());
    System.out.println("VendorError"+ e.getErrorCode());
}
```

### Autocompletar

Implementamos la funcion de hacer doble click a la tabla y que se autocomplete los campos a rellenar.

