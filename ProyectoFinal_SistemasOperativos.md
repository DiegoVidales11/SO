# Sistemas de Archivos

## Ejercicio 1: Concepto y noción de archivo real y virtual

### **Definición**
- **Archivo Real**: Es un archivo que existe físicamente en un dispositivo de almacenamiento. Se almacena como bloques de datos en el sistema de archivos.
- **Archivo Virtual**: No existe físicamente en el almacenamiento. Es una representación lógica generada por el sistema operativo o software para mostrar información temporal o simulada.

### **Ejemplos**
- **Archivo Real**: Un archivo de texto guardado en el disco `C:\Users\Documents\archivo.txt` en Windows.
- **Archivo Virtual**: El sistema de archivos virtual en `/proc` en Linux, donde los archivos representan información del sistema, como `cpuinfo` o `meminfo`.

### **Caso Práctico**
Un **archivo virtual** es más útil que uno real cuando se necesita acceder a información dinámica del sistema sin consumir espacio en disco. Por ejemplo, `/proc/meminfo` en Linux permite obtener el uso de la memoria sin almacenar un archivo físico.

---

## Ejercicio 2: Componentes de un sistema de archivos

### **Componentes clave**
- **Metadatos**: Información sobre el archivo (nombre, tamaño, permisos).
- **Tabla de asignación**: Controla la ubicación de los bloques de datos.
- **Estructuras de directorio**: Organización jerárquica de archivos y carpetas.

### **Cuadro Comparativo: EXT4 vs NTFS**

| Componente               | EXT4 (Linux)                            | NTFS (Windows)                        |
|--------------------------|-----------------------------------------|---------------------------------------|
| **Metadatos**            | Inodos almacenan metadatos             | MFT (Master File Table)               |
| **Asignación de bloques**| Basada en extents                      | Basada en clústeres                   |
| **Registro de cambios**  | Journaling                             | Journaling con transacciones          |
| **Estructura de directorios**| Árbol jerárquico con inodos         | Árbol B+ optimizado                   |

### **Ventajas y Desventajas**
- **EXT4**: Mejor rendimiento en sistemas Linux, mayor integridad de datos.
- **NTFS**: Compatibilidad nativa en Windows, soporte de permisos avanzados.

---

## Ejercicio 3: Organización lógica y física de archivos

### **Organización Lógica**
```markdown
Root Directory (/)
├── home/
│   ├── user/
│   │   ├── Documents/
│   │   │   ├── file1.txt
│   │   ├── Downloads/
├── var/
│   ├── log/
│   │   ├── system.log
```

### **Traducción Lógica a Física**
La dirección lógica de un archivo es convertida en una **dirección física** mediante la tabla de asignación. En **EXT4**, esto se hace con "extents".

### **Ejemplo Práctico**
```python
# Simulación de almacenamiento físico
file = "file1.txt"
block_size = 4096  # Tamaño de bloque en bytes
file_size = 8192   # Tamaño del archivo

blocks_needed = file_size // block_size
print(f"El archivo '{file}' ocupa {blocks_needed} bloques físicos en el disco.")
```

---

## Ejercicio 4: Mecanismos de acceso a archivos

### **Mecanismos de acceso**
1. **Secuencial**: Acceso línea por línea.
2. **Directo**: Acceso mediante una posición específica.
3. **Indexado**: Utiliza índices para localizar registros.

### **Pseudocódigos**

**Secuencial**
```python
with open("archivo.txt", "r") as file:
    for line in file:
        print(line.strip())
```

**Directo**
```python
with open("archivo.txt", "r") as file:
    file.seek(20)  # Ir a la posición 20 del archivo
    print(file.read(10))  # Leer 10 caracteres desde esa posición
```

**Indexado**
```python
index = {1: 0, 2: 20, 3: 40}  # Índice de posiciones
with open("archivo.txt", "r") as file:
    pos = index[2]  # Acceso a la posición 2
    file.seek(pos)
    print(file.read(10))
```

---

## Ejercicio 5: Modelo jerárquico y mecanismos de recuperación

### **Modelo Jerárquico**
```markdown
Root Directory (/)
├── backup/
│   ├── level1/
│   │   ├── level2/
│   │   │   ├── data.txt
```

### **Simulación de falla**
```python
import shutil
import os

# Simular una copia de seguridad
source = "data.txt"
backup = "backup/data_backup.txt"

try:
    shutil.copy(source, backup)
    print("Copia de seguridad realizada con éxito.")
except FileNotFoundError:
    print("Archivo no encontrado.")
```

---

# Protección y Seguridad

## Ejercicio 1: Concepto y objetivos de protección y seguridad

### **Definición**
- **Protección**: Mecanismos para controlar el acceso a recursos del sistema.
- **Seguridad**: Mecanismos para proteger los datos contra amenazas externas.

### **Objetivos**
1. **Confidencialidad**: Protección de datos privados.
2. **Integridad**: Garantía de que los datos no sean alterados.
3. **Disponibilidad**: Acceso constante a los recursos.

---

## Ejercicio 7: Cifrado

### **Definiciones**
- **Cifrado Simétrico**: Usa la misma clave para cifrar y descifrar.
- **Cifrado Asimétrico**: Usa un par de claves (pública y privada).

### **Ejemplo Práctico**
**Simétrico**
```python
from cryptography.fernet import Fernet

key = Fernet.generate_key()
cipher = Fernet(key)

# Cifrado
data = "Hola, mundo".encode()
encrypted = cipher.encrypt(data)
print("Cifrado:", encrypted)

# Descifrado
decrypted = cipher.decrypt(encrypted)
print("Descifrado:", decrypted.decode())
```

**Asimétrico**
```python
from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.primitives import serialization

# Generar claves
private_key = rsa.generate_private_key(public_exponent=65537, key_size=2048)
public_key = private_key.public_key()

# Serializar claves
pem_public = public_key.public_bytes(
    encoding=serialization.Encoding.PEM,
    format=serialization.PublicFormat.SubjectPublicKeyInfo
)
print("Clave Pública:", pem_public)
```