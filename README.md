# Simulador de Batallas Pokémon

Este proyecto es un simulador de batallas Pokémon, diseñado para emular combates entre equipos de Pokémon con diferentes habilidades, movimientos y estadísticas. El objetivo es ofrecer una experiencia completa de batalla, con gestión de equipos, habilidades, tipos de ataque y defensa, y manejo de estados alterados.

## Estructura del Proyecto

### Clases Principales

- **`PKMN`**
  - Representa un Pokémon en el juego. Contiene atributos como tipo, estadísticas (HP, ataque, defensa, ataque especial, defensa especial, velocidad), IVs, EVs, nivel, estado y movimientos. Permite la aplicación de IVs y EVs para personalizar las estadísticas del Pokémon.

- **`Dex`**
  - Una clase de tipo `TreeMap` que almacena una lista de Pokémon, donde la clave es el número en la Pokédex nacional y el valor es el nombre del Pokémon. Facilita la consulta rápida de Pokémon por su número en la Pokédex.

- **`Team`**
  - Representa un equipo de Pokémon. Es un array de tipo `PKMN` que permite agregar (`addToTeam`), eliminar (`removeFromTeam`) y ver (`showTeam`) los Pokémon del equipo. Este equipo se usa en combates 3v3.

- **`Battle`**
  - La clase principal para gestionar un combate Pokémon 3v3. Contiene funciones para calcular el daño, seleccionar el siguiente Pokémon si uno es debilitado y realizar los cálculos de batalla según las estadísticas y movimientos.

- **`Move`**
  - La clase base para los movimientos de Pokémon. Incluye los atributos y métodos comunes para los movimientos de ataque y defensa que cada Pokémon puede aprender.

- **`Type`**
  - La clase para los tipos de los pokémon. Incluye todas las efectividades con los demas tipos, las cuales se usan para distintos cálculos.

- **`ATK` (extends `Move`)**
  - Una clase que extiende `Move` para representar movimientos ofensivos. Incluye propiedades específicas como potencia, precisión y tipo de movimiento, las cuales afectan el cálculo del daño.

- **`DEF` (extends `Move`)**
  - Una clase que extiende `Move` para representar movimientos defensivos. Estos movimientos pueden mejorar las estadísticas o proteger al Pokémon de ataques.

- **`App`**
  - La clase principal que contiene el método `main`. Aquí se inicia el simulador de batallas y se configura la experiencia de usuario, configurando equipos y ejecutando batallas.

### Funcionalidades Adicionales

- **Clonación de Movimientos y Pokémon:** Permite crear copias de movimientos (`Move`) y Pokémon (`PKMN`), generando nuevas estadísticas para reflejar variabilidad.