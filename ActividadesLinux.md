# Registro: Tareas en Máquina Virtual (Linux Mint)

## Actividad 1: Montaje y Desmontaje de Unidades

Conecto una memoria USB a la máquina virtual y accedo a la terminal. Escribo:

```bash
lsblk
```

Este comando despliega una lista con los dispositivos y particiones. Identifico la memoria USB como `/dev/sdb1`.

Creo un directorio para el montaje de la memoria:

```bash
sudo mkdir /mnt/usb
sudo mount /dev/sdb1 /mnt/usb
```

De esta forma, la memoria queda montada y accesible en `/mnt/usb`.

Verifico el montaje correctamente:

```bash
df -h
```

Esto muestra la memoria con su capacidad y uso.

Copio un archivo dentro de la memoria:

```bash
cp ~/archivo.txt /mnt/usb/
```

El archivo `archivo.txt` desde mi directorio personal se transfiere a la memoria.

Para desmontarla de manera segura:

```bash
sudo umount /mnt/usb
```

Después de esto, la memoria puede desconectarse sin riesgo.

---

## Actividad 2: Manejo de Entrada y Salida

Guardo en un archivo la lista de los archivos en mi directorio:

```bash
ls -l > listado.txt
```

Esto genera el archivo `listado.txt` con el listado y permisos del directorio.

Verifico su contenido:

```bash
cat listado.txt
```

Puedo observar en pantalla el listado guardado.

Añado la fecha actual al archivo:

```bash
date >> listado.txt
```

Esto agrega la fecha y hora al final del archivo.

Reviso nuevamente el contenido:

```bash
cat listado.txt
```

---

![Visualización ejercicio 2](/home/diego/Imágenes/ejercicio2_modificado.png)

## Actividad 3: Operaciones de Archivos

Creo un archivo nuevo llamado `archivo1.txt`:

```bash
echo "Este es un archivo de prueba" > archivo1.txt
```

Esto genera un archivo con el texto "Este es un archivo de prueba".

Copio este archivo al directorio `/tmp`:

```bash
cp archivo1.txt /tmp/
```

Así, hay una copia de `archivo1.txt` en `/tmp`.

Renombro el archivo en `/tmp`:

```bash
mv /tmp/archivo1.txt /tmp/archivo2.txt
```

Ahora, el archivo en `/tmp` se llama `archivo2.txt`.

Muevo `archivo2.txt` de regreso a mi directorio actual:

```bash
mv /tmp/archivo2.txt .
```

El archivo `archivo2.txt` vuelve a estar en mi directorio.

---

![Visualización ejercicio 3](/home/diego/Imágenes/ejercicio3_modificado.png)

## Actividad 4: Compresión de Archivos

Creo un directorio llamado `backup` y copio varios archivos dentro:

```bash
mkdir backup
cp archivo1.txt archivo2.txt backup/
```

Esto prepara un directorio con copias de los archivos `archivo1.txt` y `archivo2.txt`.

Comprimí el directorio en un archivo `.tar.gz`:

```bash
tar -czvf backup.tar.gz backup/
```

Esto crea el archivo comprimido `backup.tar.gz`.

Borro el directorio original:

```bash
rm -r backup/
```

Solo queda el archivo comprimido.

Extraigo su contenido:

```bash
tar -xzvf backup.tar.gz
```

Esto restaura el directorio `backup` y sus archivos.

---

![Visualización ejercicio 4](/home/diego/Imágenes/ejercicio4_modificado.png)

## Actividad 5: Administración de Permisos

Creo un archivo llamado `privado.txt`:

```bash
touch privado.txt
```

Esto genera un archivo vacío llamado `privado.txt`.

Configuro los permisos para que solo el propietario tenga acceso:

```bash
chmod 600 privado.txt
```

Esto limita el acceso al propietario.

Cambio el propietario del archivo (requiere privilegios de administrador):

```bash
sudo chown otro_usuario privado.txt
```

El archivo ahora pertenece al usuario `otro_usuario`.

---

![Visualización ejercicio 5](/home/diego/Imágenes/ejercicio5_modificado.png)

## Actividad 6: Información del Sistema

Listo los discos y particiones:

```bash
lsblk
```

Esto muestra todos los dispositivos conectados y sus particiones.

Verifico el tamaño de un directorio específico:

```bash
du -sh ~/Documentos
```

Esto indica el tamaño total de la carpeta `Documentos`.

Reviso el uso general del disco:

```bash
df -h
```

Esto muestra el espacio disponible en las particiones.

---

![Visualización ejercicio 6](/home/diego/Imágenes/ejercicio6_modificado.png)

## Actividad 7: Creación y Formateo de Particiones

Encuentro un disco sin particionar:

```bash
sudo fdisk -l
```

Identifico un disco sin particiones, por ejemplo, `/dev/sdc`.

Creo una nueva partición:

```bash
sudo fdisk /dev/sdc
```

Dentro de `fdisk`, utilizo:

- `n` para nueva partición.
- `p` para partición primaria.

Defino el tamaño y guardo con `w`.

Formateo la nueva partición como `ext4`:

```bash
sudo mkfs.ext4 /dev/sdc1
```

Esto prepara la partición para su uso.

Creo un punto de montaje y lo asocio a la partición:

```bash
sudo mkdir /mnt/nueva_particion
sudo mount /dev/sdc1 /mnt/nueva_particion
```

Escribo un archivo de prueba:

```bash
echo "Prueba de escritura" > /mnt/nueva_particion/test.txt
```

Confirmo que la partición funciona correctamente.

---

![Visualización ejercicio 7](/home/diego/Imágenes/ejercicio7_modificado.png)

## Pruebas

![Pruebas visuales](/home/diego/Imágenes/pruebas_modificado.png)
