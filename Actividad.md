## **Administración de Memoria**

### **3.1 Política y filosofía**

#### **1. Diferencia entre fragmentación interna y externa**

- **Fragmentación interna:** es la pérdida de espacio en disco debido al hecho de que el tamaño de un determinado archivo sea inferior al tamaño del cluster, ya que teóricamente el archivo estaría obligado a ser referenciado como un cluster completo. Esto impacta no utilizando los recursos disponibles en su totalidad.
  
- **Fragmentación externa:** ocurre cuando el asignador deja secciones de bloques de memoria no utilizados entre partes de la memoria asignada. Esto impacta en la reducción de eficiencia y la ejecución de procesos.

#### **2. Políticas de reemplazo de páginas**

- **FIFO (First-In, First-Out):** Reemplaza la página que llegó primero en la memoria.
- **LRU (Least Recently Used):** Reemplaza la página con el mayor tiempo de no ser utilizada.
- **OPT (Optimal):** Reemplaza la página que no será usada durante más tiempo en el futuro.

**Más eficiente**  
LRU es generalmente más eficiente prácticamente porque utiliza datos reales del contexto actual para reemplazar las páginas.

### **3.2 Memoria real**

#### **1.**

```python
class Proceso:
    def __init__(self, id, tamano):
        self.id = id
        self.tamano = tamano
        self.asignado = False

class Particion:
    def __init__(self, tamano):
        self.tamano = tamano
        self.ocupado = False

def mostrar_estado(particiones, procesos):
    print("\nEstado de memoria:")
    for i, particion in enumerate(particiones):
        estado = "Ocupada" if particion.ocupado else "Libre"
        print(f"Partición {i}: {estado}")

    print("\nProcesos:")
    for proceso in procesos:
        estado = "Asignado" if proceso.asignado else "No asignado"
        print(f"Proceso {proceso.id}: {estado}")
    print()

def primera_cabida(particiones, procesos):
    for proceso in procesos:
        for particion in particiones:
            if not particion.ocupado and particion.tamano >= proceso.tamano:
                proceso.asignado = True
                particion.ocupado = True
                break

particiones = [Particion(100), Particion(200), Particion(300)]
procesos = [Proceso(1, 150), Proceso(2, 50), Proceso(3, 200)]

primera_cabida(particiones, procesos)
mostrar_estado(particiones, procesos)
```

#### **2. "**

def primera_cabida(particiones, procesos):
    for proceso in procesos:
        for particion in particiones:
            if not particion.ocupado and particion.tamano >= proceso.tamano:
                proceso.asignado = True
                particion.ocupado = True
                break

### **3.3 Organización de memoria virtual**

#### **1.**

- **Paginación:** se refiere al manejo de bloques de tamaño fijo llamados *páginas* en la memoria virtual y la conversión de éstas páginas a memoria real.

- **Segmentación:** se refiere al manejo de bloques de tamaño variable en memoria virtual y la conversión de estos segmentos a memoria real.  

**Ventajas y desventajas:**

- **Paginación:** maneja de manera eficiente la fragmentación externa, es posible ejecutar un programa cragando unicamente una parte en memoria y no es necesario que las páginas estén contiguas en memeoria. Pero el costo del hardware y software se incrementa al usar paginación.

- **Segmentación:** es posible definir segmentos que aún no existan, es fácil compartir segmentos, existe la posibilidad de que los segmentos crezcan dinámicamente según lo requiera el programa. Pero produce fragmentación externa.

#### **2.**

```python
import random

def mostrar_tabla_paginas(tabla_paginas):
    print("Tabla de páginas:")
    for i, marco in enumerate(tabla_paginas):
        print(f"Página {i} -> Marco {marco}")

def acceso_aleatorio_memoria(tabla_paginas):
    while True:
        pagina = input("Acceso a memoria virtual (ingresa número de página o -1 para salir): ")
        if pagina == "-1":
            break
        try:
            pagina = int(pagina)
            if 0 <= pagina < len(tabla_paginas):
                print(f"Acceso a marco físico: {tabla_paginas[pagina]}")
            else:
                print("Página inválida.")
        except ValueError:
            print("Entrada no válida.")

def main():
    tamano_memoria = 32
    tamano_pagina = 4
    num_paginas = tamano_memoria // tamano_pagina
    tabla_paginas = [random.randint(0, 9) for _ in range(num_paginas)]

    mostrar_tabla_paginas(tabla_paginas)
    acceso_aleatorio_memoria(tabla_paginas)

main()
```

### **3.4 Administración de memoria virtual**

#### **1.**

```python
def buscar_pagina(marcos, pagina):
    return pagina in marcos

def actualizar_lru(lru, marco):
    for key in lru.keys():
        lru[key] += 1
    lru[marco] = 0

def reemplazar_pagina(marcos, lru):
    return max(lru, key=lru.get)

def main():
    tamano_marco = 3
    marcos = []
    lru = {}
    secuencia = [7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2]
    fallos = 0

    for pagina in secuencia:
        if buscar_pagina(marcos, pagina):
            actualizar_lru(lru, pagina)
        else:
            if len(marcos) < tamano_marco:
                marcos.append(pagina)
            else:
                victima = reemplazar_pagina(marcos, lru)
                marcos.remove(victima)
                marcos.append(pagina)
            actualizar_lru(lru, pagina)
            fallos += 1

        print(f"Marcos: {marcos}")

    print(f"Número de fallos de página: {fallos}")

main()
```

#### **2.**

```
    Proceso
       |
Memoria Virtual Segmentada
       |
Segmentos de Páginas
       |
Tabla de Paginación
       |
Memoria Física
```
### **3.4 Administración de memoria virtual**

#### **1.**

En Linux, la memoria virtual se administra utilizando paginación. Cada proceso tiene su propio espacio de direcciones virtuales que es mapeado a direcciones físicas mediante una tabla de páginas. Esto permite que diferentes procesos usen la misma región física sin interferencias.

#### **2.**

```python
class Proceso:
    def __init__(self, id, tamano):
        self.id = id
        self.tamano = tamano

def simular_swapping(memoria, procesos, tamano_swap):
    swap = []
    for proceso in procesos:
        if sum(p.tamano for p in memoria) + proceso.tamano <= tamano_swap:
            memoria.append(proceso)
            print(f"Proceso {proceso.id} agregado a la memoria.")
        else:
            swap.append(proceso)
            print(f"Proceso {proceso.id} enviado al swap.")
    print("\nEstado final:")
    print("Memoria:", [p.id for p in memoria])
    print("Swap:", [p.id for p in swap])

def main():
    tamano_memoria = 300
    memoria = []
    procesos = [Proceso(1, 100), Proceso(2, 200), Proceso(3, 150)]

    simular_swapping(memoria, procesos, tamano_memoria)

main()
```

## **Administración de Entrada/Salida**


#### **1.**

- **Dispositivos de bloque:** Trabajan con bloques de datos fijos. Ejemplo: discos duros.
- **Dispositivos de carácter:** Procesan flujos de datos secuenciales. Ejemplo: teclado.


#### **2.**

```python
def manejador_dispositivo(entrada):
    print("Procesando datos del dispositivo virtual...")
    print(f"Entrada recibida: {entrada}")

def main():
    entrada = input("Ingresa datos para el dispositivo virtual: ")
    manejador_dispositivo(entrada)

main()
```

### **4.2 Mecanismos y funciones de los manejadores de dispositivos**

#### **1.**

Una interrupción por E/S es una señal que un dispositivo externo envía al procesador para indicar que ha completado una operación o requiere atención. El sistema operativo responde suspendiendo temporalmente la tarea actual para procesar la solicitud.

**Pseudocódigo para simular interrupciones:**

```text
Inicio:
  Mientras (TRUE):
    Si (dispositivo_envía_interrupción):
      Manejar la interrupción
      Reanudar tarea principal
Fin.
```
#### **2.**

```python
import time
import threading

def dispositivo():
    print("Dispositivo: Operación iniciada.")
    time.sleep(2)
    print("Dispositivo: Interrupción enviada.")

def manejador_interrupcion():
    print("Sistema operativo: Interrupción recibida.")
    print("Sistema operativo: Procesando solicitud.")

def main():
    hilo_dispositivo = threading.Thread(target=dispositivo)
    hilo_dispositivo.start()

    while hilo_dispositivo.is_alive():
        time.sleep(0.5)
        print("Sistema operativo: Esperando interrupción...")
    
    manejador_interrupcion()

main()
```

### **4.3 Estructuras de datos para manejo de dispositivos**

#### **1.**

Una cola de E/S organiza las solicitudes de entrada/salida en una estructura FIFO o por prioridades para garantizar que se procesen en orden.

#### **2.**

```python
import heapq

def procesar_cola(cola):
    while cola:
        prioridad, solicitud = heapq.heappop(cola)
        print(f"Procesando solicitud: {solicitud} con prioridad {prioridad}")

def main():
    cola = []
    heapq.heappush(cola, (2, "Lectura de archivo"))
    heapq.heappush(cola, (1, "Escritura en disco"))
    heapq.heappush(cola, (3, "Impresión"))

    print("Procesando cola de E/S con prioridad...")
    procesar_cola(cola)

main()
```

### **4.4 Operaciones de Entrada/Salida**

#### **1.**

**Flujo del proceso:**
1. El sistema operativo solicita el bloque correspondiente al archivo.
2. El controlador del disco accede al bloque físico en el disco.
3. Los datos se transfieren al búfer en la memoria.
4. El sistema operativo entrega los datos al proceso solicitante.

#### **2.**

```python
import asyncio

async def leer_archivo(archivo):
    print(f"Iniciando lectura de {archivo}...")
    await asyncio.sleep(2)  # Simula tiempo de lectura
    print(f"Archivo {archivo} leído.")

async def main():
    await asyncio.gather(leer_archivo("datos1.txt"), leer_archivo("datos2.txt"))

asyncio.run(main())
```

#### **3. Algoritmo de planificación de discos "Elevator (SCAN)"**

```python
def elevator_scan(peticiones, cabezal_inicial):
    peticiones.sort()
    izquierda = [p for p in peticiones if p < cabezal_inicial]
    derecha = [p for p in peticiones if p >= cabezal_inicial]

    print("Orden de acceso (SCAN):")
    for p in derecha + izquierda[::-1]:
        print(f"Atendiendo petición en cilindro {p}")

def main():
    peticiones = [98, 183, 37, 122, 14, 124, 65, 67]
    cabezal_inicial = 53
    elevator_scan(peticiones, cabezal_inicial)

main()
```

#### **4.**

```python
import threading

def dispositivo(nombre, tiempo):
    print(f"{nombre}: Operación iniciada.")
    time.sleep(tiempo)
    print(f"{nombre}: Operación completada.")

def main():
    dispositivos = [
        threading.Thread(target=dispositivo, args=("Disco duro", 3)),
        threading.Thread(target=dispositivo, args=("Impresora", 5)),
        threading.Thread(target=dispositivo, args=("Teclado", 2)),
    ]

    for d in dispositivos:
        d.start()

    for d in dispositivos:
        d.join()

    print("Todas las operaciones completadas.")

main()
```

## **Avanzados**

### **1.**

Los sistemas operativos modernos utilizan memoria caché para almacenar temporalmente datos frecuentemente accedidos. Esto reduce la latencia, ya que los datos se obtienen de la caché (más rápida que el disco). Además, el uso de algoritmos como LRU optimizan qué datos se mantienen en la caché.
