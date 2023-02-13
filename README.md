# libreria
#Lider de equipo: Raúl

Trabajo final android 

#Estructura de datos
Usuario
Libro

Usuario-Libro?

#Estructura de código
#Actividades
Splash 

Login
    user + pass
    Btn Log in
    Btn registrar nuevo usuario

Pantalla registro 
    nombre + user + pass + repetir pass
    Btn registrar

Pantalla principal
    Btn para acceder al listado del json
    Btn para acceder al listado del usuario
    Btn para ver los libros con más likes
    Btn para ver los libros con más favs
    
Pantalla para mostrar los datos del json
    Listado de datos -> Recycler View 
    Btn guardar diferentes libros // Se puede varios a la vez

Pantalla para mostrar los libros del usuario
    Listado de datos -> Recycler View 

Pantalla para mostrar más likes / favs
    Listado de datos -> Recycler View
    Btn guardar diferentes libros // Se puede varios a la vez


En los recycler al pulsar un item entrará a los datos del item en otra actividad
En todos excepto en el recycler del usuario hay un checkbox para poder seleccionar los que se quiera guardar

Pantalla para mostrar los datos de un libro
    Muestra los datos de un libro // ScrollView
    Tiene botones con las distintas posibilidades: Fav, Read, Reading, Discard, Like, Dislike 
    Si el libro ya está descargado esos datos se podrán actualizar

#Logica de negocio
ADOs para la bbdd -> 3
Descarga JSON
Filtrar por likes / favs
Adapter/s del listado
Controller login
Controller registrar 
Controller datos libro
Notificaciones

#Interfaz
Interfaz de todas las actividades(layout)

#Descripción del proyecto

El proyecto contendra un splash. El splash aparecera al iniciarse la aplicación y tras finalizar se abre la ventana login

En la ventana login el usuario deberá introducir sus datos correctos o darle a registrar nuevo usuario
En la ventana registrar el usuario deberá crear un nuevo usuario que no exista previamente en la bbdd
Al registrar un usuario se notificará

Si el usuario se ha logueado correctamente, entrará a la ventana principal donde hay un menú con cuatro botones
Cada uno lleva a un listado diferente, listado de libros en internet, libros más gustados por todos los usuarios,
libros más favoritos entre todos los usuarios, libros del usuario actual.
En cada listado aparecerán los distintos libros, aparecerá siempre el titulo, en caso del json 
y de los más favs / likes aparecerá un checkBox, en likes/ favs aparecerá cuantos tienen
y en la de libros del usuario aparecerán los iconos de lo que ha seleccionado

En los que tienen checkbox el usuario podrá seleccionar varios y al darle a un bton al final de la actividad
se registrarán los datos del libro en la base de datos.

Al clickar en un item de cualquier listado llevará a una actividad que muestre todos los datos del libro  
y que muestre los distintos estados (Reading, Like, ...), en caso de que el libro no esté descargado el usuario podrá descargarlo, 
en caso de que ya lo haya descargado se mostrará un botón para actualizar los estados.   
    